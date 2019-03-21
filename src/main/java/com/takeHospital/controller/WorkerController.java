package com.takeHospital.controller;

import com.takeHospital.domain.Worker;
import com.takeHospital.repository.WorkerRepository;
import com.takeHospital.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class WorkerController {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private WorkerService workerService;

    @GetMapping("/worker_list")
    public String searchUsersByFam(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model){

        if (filter != null && !filter.isEmpty()) {
            model.addAttribute("users", workerRepository.findByFam(filter));
        } else {
            model.addAttribute("users", workerRepository.findAll());
        }

        model.addAttribute("filter", filter);
        model.addAttribute("colums", getListWithNameColums());
        return "workerList";
    }


    @GetMapping("/edit_profile_worker")
    public String showProfileWorker(
            @AuthenticationPrincipal Worker worker,
            Model model
    ){
        model.addAttribute("user", worker);
        return "editProfileWorker";
    }

    @GetMapping("/edit_profile_worker/create_profile_worker")
    public String showCreateProfileWorker(){
        return "createProfileWorker";
    }

    @PostMapping("/edit_profile_worker/create_profile_worker")
    public String createProfileWorker(
            @ModelAttribute @Valid Worker worker,       //@ModelAttribute и @Valid вытягивает пользователя из формы пустого без id
            BindingResult bindingResult,
            @RequestParam String password2,
            Model model
    ){
        Map<String, String> errors;

        if (workerRepository.findByLogin(worker.getLogin()) != null){
            bindingResult.addError(new FieldError("worker","login", "Такой логин уже есть."));
        }

        if (!worker.getPassword().equals(password2)){
            bindingResult.addError(new FieldError("worker","password2", "Поля паролей не совпадают"));
        }

        if (password2 == ""){
            bindingResult.addError(new FieldError("worker", "password2", "Поле проверки пароля не может быть пустым" ));
        }

        if (bindingResult.hasErrors()){
            errors = ControllerUtils.getErrors(bindingResult);
            model.addAttribute("user", worker);
            model.mergeAttributes(errors);
            return "createProfileWorker";
        }
        workerService.addWorker(worker);
        return "redirect:/worker_list";
    }

    @PostMapping("/edit_profile_worker")
    public String editProfileWorker(
            @AuthenticationPrincipal Worker worker1,    //@AuthenticationPrincipal выдергивает

            @RequestParam String password2,
            @ModelAttribute @Valid Worker worker,       //@ModelAttribute и @Valid вытягивает пользователя из формы пустого без id
            BindingResult bindingResult,
            Model model
    ){
        Map<String, String> errors;

        if (!worker1.getLogin().equals(worker.getLogin())) {
            if (workerRepository.findByLogin(worker.getLogin()) != null){
                bindingResult.addError(new FieldError("worker","login", "Такой логин уже есть."));
            }
        }

        if (!worker.getPassword().equals(password2)){
            bindingResult.addError(new FieldError("worker","password2", "Поля паролей не совпадают"));
        }

        if (password2 == ""){
            bindingResult.addError(new FieldError("worker", "password2", "Поле проверки пароля не может быть пустым" ));
        }

        if (bindingResult.hasErrors()){
            errors = ControllerUtils.getErrors(bindingResult);
            worker.setId(worker1.getId());
            model.addAttribute("user", worker);
            model.mergeAttributes(errors);
            return "editProfileWorker";
        }

        model.addAttribute("user", workerService.updateProfile(worker1, worker));
        //SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/logout";
    }

    @PostMapping("/edit_profile_worker/delete/{idWorker}")
    public String deleteProfileWorker(
            @PathVariable String idWorker
    ){
        if (!idWorker.equals("1")) {
            workerRepository.deleteById(Long.parseLong(idWorker));
        }
        return "redirect:/logout";
    }

    private List<String> getListWithNameColums(){
        return  Arrays.asList("Фамилия", "Имя", "Отчество", "Должность");
    }

}
