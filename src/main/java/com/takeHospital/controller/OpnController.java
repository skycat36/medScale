package com.takeHospital.controller;

import com.takeHospital.domain.Opn;
import com.takeHospital.domain.Worker;
import com.takeHospital.repository.ClientRepository;
import com.takeHospital.repository.OpnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OpnController {
    @Autowired
    private OpnRepository opnRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/department")
    public String showDepartment(
            @AuthenticationPrincipal Worker worker,
            Model model
    ){
        model.addAttribute("colums", getListWithNameColums());
        model.addAttribute("user", worker);
        model.addAttribute("opns", opnRepository.findAll());
        return "/page/for_client/opnClient";
    }

    @PostMapping("/department/delete/{idOpn}")
    public String deleteDepartment(
            @PathVariable String idOpn
    ){
        opnRepository.deleteById(Long.parseLong(idOpn));
        clientRepository.deleteClientsByOpn(Long.parseLong(idOpn));
        return "redirect:/department";
    }

    @GetMapping("/department/create_department")
    public String showCreateDepartment(
    ){
        return "/page/for_client/createOpn";
    }

    @PostMapping("/department/create_department")
    public String createDepartment(
            @RequestParam String nameOpn,
            Model model
    ){
        Map<String, String> opnError = new HashMap<>();

        if (nameOpn.equals("")){
            opnError.put("nameOpnError", "Поле названия отделения пустое");
        }
        if (opnRepository.findByOpn(nameOpn) != null){
            opnError.put("nameOpnError", "Такое отделение уже есть");
        }

        if (opnError.isEmpty()) {
            opnRepository.save(new Opn(nameOpn));
            return "redirect:/department";
        }else {
            model.addAttribute("nameOpn", nameOpn);
            model.mergeAttributes(opnError);
            return "/page/for_client/createOpn";
        }
    }

    @GetMapping("/department/update/{idOpn}")
    public String showUpdateDepartment(
            @PathVariable String idOpn,
            Model model
    ){
        model.addAttribute("nameOpn", opnRepository.findById(Long.parseLong(idOpn)).get().getOpn());
        return "/page/for_client/updateOpn";
    }

    @PostMapping("/department/update/{idOpn}")
    public String updateDepartment(
            @PathVariable String idOpn,
            @RequestParam String nameOpn,
            Model model
    ){
        Map<String, String> opnError = new HashMap<>();

        if (nameOpn.equals("")){
            opnError.put("nameOpnError", "Поле названия отделения пустое");
        }

        if (opnRepository.findByOpn(nameOpn) != null){
            opnError.put("nameOpnError", "Такое отделение уже есть");
        }

        if (opnError.isEmpty()) {
            Opn opn = opnRepository.findById(Long.parseLong(idOpn)).get();
            opn.setOpn(nameOpn);
            opnRepository.save(opn);
            return "redirect:/department";
        }else {
            model.addAttribute("nameOpn", nameOpn);
            model.mergeAttributes(opnError);
            return "/page/for_client/updateOpn";
        }
    }

    private List<String> getListWithNameColums(){
        return  Arrays.asList("Название отделения");
    }
}
