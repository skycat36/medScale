package com.takeHospital.controller;

import com.takeHospital.domain.Worker;
import com.takeHospital.domain.parametrsForScheme.ParamScheme;
import com.takeHospital.repository.SchemeRepository;
import com.takeHospital.repository.WorkerRepository;
import com.takeHospital.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WorkerController {

    private Logger log = LoggerFactory.getLogger(WorkerController.class.getName());

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private SchemeRepository schemeRepository;

    @Autowired
    private WorkerService workerService;

    @GetMapping("/worker_list")
    public String searchUsersByFam(
            @RequestParam(required = false, defaultValue = "") String filter,
            @AuthenticationPrincipal Worker admin,
            Model model){

        if (filter != null && !filter.isEmpty()) {
            model.addAttribute("users", workerRepository.findByFam(filter));
        } else {
            model.addAttribute("users", workerRepository.findAll());
        }

        if (admin.getId() == 1){
            model.addAttribute("admin", admin);
        }

        model.addAttribute("filter", filter);
        model.addAttribute("colums", getListWithNameColums());
        return "/page/for_worker/workerList";
    }


    @GetMapping("/edit_profile_worker")
    public String showProfileWorker(
            @AuthenticationPrincipal Worker worker,
            Model model
    ){
        model.addAttribute("user", worker);
        return "/page/for_worker/editProfileWorker";
    }

    @GetMapping("/edit_profile_worker/create_profile_worker")
    public String showCreateProfileWorker(
            @AuthenticationPrincipal Worker worker
    ){
        if (worker.getId() == 1) {
            return "/page/for_worker/createProfileWorker";
        }else {
            return "redirect:/";
        }
    }

    @GetMapping("/edit_profile_worker/change_param_scheme")
    public String showRegulateParamScheme(
            @AuthenticationPrincipal Worker worker,
            Model model
    ){
        if (worker.getId() == 1) {
            model.addAttribute("ntiss", schemeRepository.findByNameSheme("ntiss"));
            model.addAttribute("pcs", schemeRepository.findByNameSheme("pcs"));
            model.addAttribute("snappe", schemeRepository.findByNameSheme("snappe"));
            model.addAttribute("sofa", schemeRepository.findByNameSheme("sofa"));
            return "/page/for_worker/changeParamScheme";
        }else {
            return "redirect:/";
        }
    }

    @PostMapping("/edit_profile_worker/change_param_scheme")
    public String createProfileWorker(
            @RequestParam String ballNtiss,
            @RequestParam String procNtiss,
            @RequestParam String procPcs,
            @RequestParam String ballSnappe,
            @RequestParam String procSnappe,
            @RequestParam String ballSofa,
            @RequestParam String procSofa,
            Model model
    ){
        Map<String, String> mapErrors = new HashMap<>();

        if (ballNtiss.equals("")){
            mapErrors.put("ballNtissError", "Поле пустое.");
        }

        if (procNtiss.equals("")){
            mapErrors.put("procNtissError", "Поле пустое.");
        }

        if (procPcs.equals("")){
            mapErrors.put("procPcsError", "Поле пустое.");
        }

        if (ballSnappe.equals("")){
            mapErrors.put("ballSnappeError", "Поле пустое.");
        }

        if (procSnappe.equals("")){
            mapErrors.put("procSnappeError", "Поле пустое.");
        }

        if (ballSofa.equals("")){
            mapErrors.put("ballSofaError", "Поле пустое.");
        }

        if (procSofa.equals("")){
            mapErrors.put("procSofaError", "Поле пустое.");
        }

        if (mapErrors.isEmpty()) {
            ParamScheme paramScheme = schemeRepository.findByNameSheme("ntiss");
            paramScheme.setColibrBall(Integer.parseInt(ballNtiss));
            paramScheme.setColibrProc(Integer.parseInt(procNtiss));
            schemeRepository.save(paramScheme);

            paramScheme = schemeRepository.findByNameSheme("pcs");
            paramScheme.setColibrProc(Integer.parseInt(procPcs));
            schemeRepository.save(paramScheme);

            paramScheme = schemeRepository.findByNameSheme("snappe");
            paramScheme.setColibrBall(Integer.parseInt(ballSnappe));
            paramScheme.setColibrProc(Integer.parseInt(procSnappe));
            schemeRepository.save(paramScheme);

            paramScheme = schemeRepository.findByNameSheme("sofa");
            paramScheme.setColibrBall(Integer.parseInt(ballSofa));
            paramScheme.setColibrProc(Integer.parseInt(procSofa));
            schemeRepository.save(paramScheme);

            return "redirect:/edit_profile_worker/change_param_scheme";

        }else {
            ParamScheme paramScheme = new ParamScheme();
            if (!ballNtiss.equals("")) {paramScheme.setColibrBall(Integer.parseInt(ballNtiss));}
            if (!procNtiss.equals("")) {paramScheme.setColibrProc(Integer.parseInt(procNtiss));}
            model.addAttribute("ntiss", paramScheme);

            paramScheme = new ParamScheme();
            if (!procPcs.equals("")) {
                paramScheme.setColibrBall(-1);
                paramScheme.setColibrProc(Integer.parseInt(procPcs));}
            model.addAttribute("pcs", paramScheme);

            paramScheme = new ParamScheme();
            if (!ballSnappe.equals("")) {paramScheme.setColibrBall(Integer.parseInt(ballSnappe));}
            if (!procSnappe.equals("")) {paramScheme.setColibrProc(Integer.parseInt(procSnappe));}
            model.addAttribute("snappe", paramScheme);

            paramScheme = new ParamScheme();
            if (!ballSofa.equals("")) {paramScheme.setColibrBall(Integer.parseInt(ballSofa));}
            if (!procSofa.equals("")) {paramScheme.setColibrProc(Integer.parseInt(procSofa));}
            model.addAttribute("sofa", paramScheme);
            model.mergeAttributes(mapErrors);
            return "/page/for_worker/changeParamScheme";
        }
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
            return "/page/for_worker/createProfileWorker";
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
            log.error("Profile worker not update.");
            return "/page/for_worker/editProfileWorker";
        }

        log.info("Profile worker has update.");

        model.addAttribute("user", workerService.updateProfile(worker1, worker));
        return "redirect:/logout";
    }

    @PostMapping("/edit_profile_worker/delete/{idWorker}")
    public String deleteProfileWorker(
            @PathVariable String idWorker
    ){
        if (!idWorker.equals("1")) {
            workerRepository.deleteById(Long.parseLong(idWorker));
            log.info("Profile worker with id = " + idWorker + " deleted.");
        }
        return "redirect:/logout";
    }

    @PostMapping("/edit_profile_worker/delete_some_profile/{idWorker}")
    public String deleteSomeProfileWorker(
            @PathVariable String idWorker
    ){
        if (!idWorker.equals("1")) {
            workerRepository.deleteById(Long.parseLong(idWorker));
            log.info("Profile worker with id = " + idWorker + " deleted.");
        }
        return "redirect:/worker_list";
    }

    @GetMapping("/edit_profile_worker/change_user/{idWorker}")
    public String changeProfileWorker(
            @AuthenticationPrincipal Worker worker,
            @PathVariable String idWorker,
            Model model
    ){
        if (worker.getId() == 1) {
            model.addAttribute("user", workerRepository.findById(Long.parseLong(idWorker)).get());
            log.info("Profile worker " + idWorker + " has changed.");
            return "/page/for_worker/changeProfileWorker";
        }else {
            log.error("Prove admin need for this action");
            return "redirect:/";
        }
    }

    @PostMapping("/edit_profile_worker/change_user/{idWorker}")
    public String changeProgileSomeWorker(
            @PathVariable String idWorker,
            @RequestParam String password2,
            @ModelAttribute @Valid Worker worker,       //@ModelAttribute и @Valid вытягивает пользователя из формы пустого без id
            BindingResult bindingResult,
            Model model
    ){
        Map<String, String> errors;
        Worker worker1 = workerRepository.findById(Long.parseLong(idWorker)).get();

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
            log.error("Profile worker " + idWorker + " not changed.");
            return "/page/for_worker/changeProfileWorker";
        }

        model.addAttribute("user", workerService.updateProfile(worker1, worker));
        log.info("Profile worker " + idWorker + " has changed.");
        return "redirect:/worker_list";
    }

    private List<String> getListWithNameColums(){
        return  Arrays.asList("Фамилия", "Имя", "Отчество", "Должность");
    }

}
