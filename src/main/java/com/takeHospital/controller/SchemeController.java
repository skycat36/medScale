package com.takeHospital.controller;

import com.takeHospital.domain.Client;
import com.takeHospital.domain.exeptions.SchemeExeption;
import com.takeHospital.repository.ClientRepository;
import com.takeHospital.service.metodsScale.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/client_list/select/{idClient}/add_scheme")
public class SchemeController {

    Client client;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SNAPPEService snappeService;

    @Autowired
    private NTISSService ntissService;

    @Autowired
    private SOFAService sofaService;

    @Autowired
    private TRIPSService tripsService;

    @Autowired
    private CRIB2Service crib2Service;

    @Autowired
    private PCSService pcsService;

    @GetMapping("/CRIB2")
    public String showSchemeCRIB2(
            @PathVariable String idClient,
            Model model
    ){
        client = clientRepository.findById(Long.parseLong(idClient)).get();
        try{
            crib2Service.proveGestation(client.getGestation(), client.getWeight(), client.getSex());
        } catch (SchemeExeption exeption) {
            model.addAttribute("gestationError", exeption.getMessage());
        }
        return "/scheme/CRIB2";
    }

    @PostMapping("/CRIB2")
    public String addSchemeCRIB2(
            @PathVariable String idClient,
            @RequestParam("param[]") String[] param,
            @RequestParam(defaultValue = "") String maxBE,
            @RequestParam(defaultValue = "") String temp,
            Model model

    ){
        Map<String, String> schemeError = new HashMap<>();
        Map<String, String> attributes = new HashMap<>();

        client = clientRepository.findById(Long.parseLong(idClient)).get();
        attributes.put("maxBE", maxBE);
        attributes.put("temp", temp);

        if(temp.equals("")){ schemeError.put("tempError", "Поле пустое."); }

        if(maxBE.equals("")){ schemeError.put("maxBEError", "Поле пустое."); }

        try {
            crib2Service.proveGestation(client.getGestation(), client.getWeight(), client.getSex());
        }catch (SchemeExeption exeption) {
            schemeError.put("gestationError", "Вес не соответствует сроку гестации.");
        }

        if (schemeError.size() > 0){
            model.mergeAttributes(schemeError);
            model.mergeAttributes(attributes);
            return "/scheme/CRIB2";
        }

        client.setCrib2(new CRIB2Service(ControllerUtils.getBallsFromStringArr(param), Double.parseDouble(temp),
                client.getGestation(), client.getWeight(), Double.parseDouble(maxBE), client.getSex()).getCountBalls());

        clientRepository.save(client);

        return "redirect:/client_list/select/" + idClient;
    }

    @GetMapping("/SNAPPE")
    public String showSchemeSNAPPE(){
        return "/scheme/SNAPPE";
    }

    @PostMapping("/SNAPPE")
    public String addSchemeSNAPPE(
            @PathVariable String idClient,
            @RequestParam("param[]") String[] param,
            Model model

    ){
        client = clientRepository.findById(Long.parseLong(idClient)).get();

        client.setSnapPe(new SNAPPEService(ControllerUtils.getBallsFromStringArr(param)).getCountBalls());

        return "redirect:/client_list/select/" + idClient;
    }

    @GetMapping("/NTISS")
    public String showSchemeNTISS(){ return "/scheme/NTISS"; }

    @PostMapping("/NTISS")
    public String addSchemeNTISS(
            @PathVariable String idClient,
            @RequestParam String result,
            Model model

    ){
        client = clientRepository.findById(Long.parseLong(idClient)).get();

        client.setNtiss(new NTISSService(Integer.parseInt(result)).getCountBalls());
        return "redirect:/client_list/select/" + idClient;
    }

    @GetMapping("/PCS")
    public String showSchemePCS(){ return "/scheme/PCS"; }

    @PostMapping("/PCS")
    public String addSchemePCS(
            @PathVariable String idClient,
            @RequestParam("param[]") String[] param,
            Model model

    ){
        client = clientRepository.findById(Long.parseLong(idClient)).get();

        client.setPcs(new PCSService(ControllerUtils.getBallsFromStringArr(param), client.getBirthdate(), LocalDate.now()).getCountBalls());
        return "redirect:/client_list/select/" + idClient;
    }

    @GetMapping("/TRIPS")
    public String showSchemeTRIPS(){ return "/scheme/TRIPS";}

    @PostMapping("/TRIPS")
    public String addSchemeTRIPS(
            @PathVariable String idClient,
            @RequestParam("param[]") String[] param,
            Model model

    ){
        client = clientRepository.findById(Long.parseLong(idClient)).get();

        client.setTrips(tripsService.getCountProcLethalOutcome(ControllerUtils.getBallsFromStringArr(param)));
        return "redirect:/client_list/select/" + idClient;
    }

    @GetMapping("/SOFA")
    public String showSchemeSOFA(){ return "/scheme/SOFA";}

    @PostMapping("/SOFA")
    public String addSchemeSOFA(
            @PathVariable String idClient,
            @RequestParam("param[]") String[] param,
            Model model

    ){

        client = clientRepository.findById(Long.parseLong(idClient)).get();

        client.setSofa(new SOFAService(ControllerUtils.getBallsFromStringArr(param)).getCountBalls());
        return "redirect:/client_list/select/" + idClient;
    }
}
