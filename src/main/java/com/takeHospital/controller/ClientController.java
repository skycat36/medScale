package com.takeHospital.controller;

import com.takeHospital.domain.Client;
import com.takeHospital.domain.Opn;
import com.takeHospital.repository.ClientRepository;
import com.takeHospital.repository.OpnRepository;
import com.takeHospital.service.metodsScale.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

@Controller
public class ClientController {

    private Logger log = LoggerFactory.getLogger(ClientController.class.getName());

    private Integer mortalityRisk;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OpnRepository opnRepository;

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

    private List<Client> clientList = new ArrayList<>();

    @GetMapping("/client_list/add_client")
    public String addClient(
            Model model
    ){
        model.addAttribute("opnList", opnRepository.findAll());
        return "/page/for_client/createClient";
    }

    @PostMapping("/client_list/add_client")
    public String addClient(
            @RequestParam String fam,
            @RequestParam String name,
            @RequestParam String secName,
            @RequestParam String birthdate,
            @RequestParam String dateOfArrival,
            @RequestParam(defaultValue = "") String opn,
            @RequestParam String gestation,
            @RequestParam String weight,
            @RequestParam String sex,
            Model model
    ) {
        Map<String, String> clientError = new HashMap<>();
        Client client = new Client();

        if (fam.equals("")) {
            clientError.put("famError", "Поле фамилия не может быть пустым.");
            client.setFam(null);
        }else{
            client.setFam(fam);
        }

        if (name.equals("")) {
            clientError.put("nameError", "Поле имя не может быть пустым.");
            client.setName(null);
        }else{
            client.setName(name);
        }

        if (secName.equals("")) {
            clientError.put("secNameError", "Поле отчество не может быть пустым.");
            client.setSecName(null);
        }else{
            client.setSecName(secName);
        }

        if (gestation.equals("")) {
            clientError.put("gestationError", "Поле гестация не может быть пустым.");
            client.setGestation(null);
        }else{
            client.setGestation(Integer.parseInt(gestation));
        }

        if (weight.equals("")) {
            clientError.put("weightError", "Поле вес не может быть пустым.");
            client.setWeight(null);
        }else{
            model.addAttribute("weight", weight);
            client.setWeight(Integer.parseInt(weight));
        }

        if(opn.equals("")){
            clientError.put("opnError", "ОПН не может быть пустым.");
            client.setOpn(null);
        }else{
            client.setOpn(opnRepository.findById(Long.parseLong(opn)).get().getId());
        }

        if (birthdate.equals("")) {
            clientError.put("birthdateError", "Поле даты рождения не может быть пустым.");
            client.setBirthdate(null);
        }else{
            client.setBirthdate(LocalDate.parse(birthdate));
        }

        if (dateOfArrival.equals("")) {
            clientError.put("dateOfArrivalError", "Поле даты прибытия не может быть пустым.");
            client.setDateOfArrival(null);
        }else{
            client.setDateOfArrival(LocalDate.parse(dateOfArrival));
        }

        client.setSex(sex);

        if (clientError.size() > 0) {
            model.addAttribute("opnList", opnRepository.findAll());
            model.mergeAttributes(clientError);
            model.addAttribute("client", client);

            log.error("Error validation for client " + client.getId());

            return "/page/for_client/createClient";
        }

        Long id = clientRepository.save(client).getId();

        log.info("Client with ID = " + id + " has added.");

        return "redirect:/client_list/select/" + id;
    }

    @GetMapping("/client_list")
    public String searchUsersByFam(
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(defaultValue = "") String selectedField,
            Model model){

        if (filter != null && !filter.isEmpty()) {
            switch (selectedField){
                case "fam": {
                    this.clientList = clientRepository.findByFam(filter);
                    break;
                }
                case "opn": {
                    Long idOpn = opnRepository.findByOpn(filter).getId();
                    this.clientList = clientRepository.findByOpn(idOpn);
                    break;
                }
                case "birthdate": {
                    try {
                        this.clientList = clientRepository.findByBirthdate(LocalDate.parse(filter));
                    }catch (DateTimeParseException ex){
                        model.addAttribute("textError", "Дата введена некорректно");
                    }
                    break;
                }
                default: this.clientList = clientRepository.findAll(); break;
            }

            model.addAttribute("users", this.clientList);
        } else {
            this.clientList = clientRepository.findAll();
            model.addAttribute("users", clientRepository.findAll());
        }


        Map<Long, String> mapOpn = new HashMap<>();
        for (Opn opn: opnRepository.findAll()){
            mapOpn.put(opn.getId(), opn.getOpn());
        }

        List<String> opnListClient = new ArrayList<>();
        for (Client cl: this.clientList){
            opnListClient.add(mapOpn.get(cl.getOpn()));
        }

        model.addAttribute("usersOpn", opnListClient);

        model.addAttribute("filter", filter);
        model.addAttribute("colums", getListWithNameColums());
        return "/page/for_client/clientList";
    }

    @GetMapping("/client_list/select/{idClient}")
    public String selectClientForWork(
            @PathVariable String idClient,
            Model model
    ){
        Client client = clientRepository.findById(Long.parseLong(idClient)).get();
        model.addAttribute("listSchemeClient", getListSchemeWhatHaveClient(client));
        model.addAttribute("client", client);
        model.addAttribute("listForSelectScheme", getListNameScheme());
        model.addAttribute("listForDeleteScheme", getListNameSchemeWhatHaveClient(client));
        model.addAttribute("mortalityRisk", this.mortalityRisk);
        model.addAttribute("opnList", opnRepository.findAll());
        model.addAttribute("weight",client.getWeight().toString());
        return "/page/for_client/selectedClient";
    }

    @PostMapping("/client_list/select/{idClient}")
    public String addSchemeClientById(
            @PathVariable String idClient,
            @RequestParam(required = false) String add_scheme,
            @RequestParam(required = false) String update_profile,
            @RequestParam(required = false) String delete_scheme,
            @RequestParam(required = false) String selectedScheme,
            @RequestParam(required = false) String deleteScheme,
            @RequestParam String fam,
            @RequestParam String name,
            @RequestParam String secName,
            @RequestParam String birthdate,
            @RequestParam String dateOfArrival,
            @RequestParam String survayDate,
            @RequestParam String dateOfDeparture,
            @RequestParam String dateOfDeath,
            @RequestParam(required = false, defaultValue = "") String opn,
            @RequestParam String gestation,
            @RequestParam String weight,
            @RequestParam String sex,
            Model model
    ){
        Client client = clientRepository.findById(Long.parseLong(idClient)).get();
        Map<String, String> clientError = new HashMap<>();

        if(fam.equals("")){
            clientError.put("famError", "Поле фамилия не может быть пустым.");
            client.setFam(null);
        }else {
            client.setFam(fam);
        }

        if(name.equals("")){
            clientError.put("nameError", "Поле имя не может быть пустым.");
            client.setName(null);
        }else {
            client.setName(name);
        }

        if(secName.equals("")){
            clientError.put("secNameError", "Поле отчество не может быть пустым.");
            client.setSecName(null);
        }else {
            client.setSecName(secName);
        }

        if(birthdate.equals("")){
            clientError.put("birthdateError", "Поле даты рождения не может быть пустым.");
            client.setBirthdate(null);
        }else {
            client.setBirthdate(LocalDate.parse(birthdate));
        }

        if(dateOfArrival.equals("")){
            clientError.put("dateOfArrivalError", "Поле даты прибытия не может быть пустым.");
            client.setDateOfArrival(null);
        }else {
            client.setDateOfArrival(LocalDate.parse(dateOfArrival));
        }

        if(opn.equals("")){
            clientError.put("opnError", "ОПН не может быть пустым.");
            client.setOpn(null);
        }else {
            client.setOpn(opnRepository.findById(Long.parseLong(opn)).get().getId());
        }

        if(gestation.equals("")){
            clientError.put("gestationError", "Поле гестация не может быть пустым.");
            client.setGestation(null);
        }else {
            client.setGestation(Integer.parseInt(gestation));
        }

        if(weight.equals("")){
            clientError.put("weightError", "Поле вес не может быть пустым.");
            client.setWeight(null);
        }else {
            client.setWeight(Integer.parseInt(weight));
            model.addAttribute("weight",weight);
        }

        client.setSex(sex);

        if (clientError.size() > 0){
            model.mergeAttributes(clientError);
            model.addAttribute("listSchemeClient", getListSchemeWhatHaveClient(client));
            model.addAttribute("listForSelectScheme", getListNameScheme());
            model.addAttribute("listForDeleteScheme", getListNameSchemeWhatHaveClient(client));
            model.addAttribute("mortalityRisk", this.mortalityRisk);
            model.addAttribute("opnList", opnRepository.findAll());
            model.addAttribute("client", client);
            log.error("Error validation for client " + client.getId());
            return "/page/for_client/selectedClient";
        }

        if (!survayDate.equals("")){
            client.setSurvayDate(LocalDate.parse(survayDate));
        }

        if (!dateOfDeparture.equals("")){
            client.setDateOfDeparture(LocalDate.parse(dateOfDeparture));
        }

        if (!survayDate.equals("")){
            client.setSurvayDate(LocalDate.parse(survayDate));
        }

        if (!dateOfDeath.equals("")){
            client.setDateOfDeath(LocalDate.parse(dateOfDeath));
        }

        log.info("Client update with id " + client.getId());

        if (add_scheme != null){
            clientRepository.save(client);
            return "redirect:/client_list/select/" + idClient + "/add_scheme/" + selectedScheme;
        }

        if (update_profile != null){
            clientRepository.save(client);
            return "redirect:/client_list";
        }

        if (delete_scheme != null){
            deleteSchemeClient(client, deleteScheme);
            clientRepository.save(client);
            return "redirect:/client_list/select/" + idClient;
        }
        return "redirect:/client_list/select/" + idClient;
    }

    @PostMapping("/client_list/delete/{idClient}")
    public String deleteByIdUser(
            @PathVariable String idClient,
            Model model
    ){

        try {
            deleteClientInClientListById(Long.parseLong(idClient));
            clientRepository.deleteById(Long.parseLong(idClient));
            log.info("Client with id = " + idClient + " has deleted.");
            //model.addAttribute("textError", "Дата введена некорректно");
        }catch (ConcurrentModificationException ex){
            log.error("Client with id = " + idClient + " not deleted.");
            model.addAttribute("textError", "Пользователя в базе данных нет.");
        }


        model.addAttribute("users", clientList);
        model.addAttribute("colums", getListWithNameColums());
        return "/page/for_client/clientList";
    }


    private List<String> getListWithNameColums(){
        return  Arrays.asList("Фамилия", "Имя", "Отчество", "Исход",
                "Дата рождения", "Дата поступления", "Дата выписки",
                "Дата смерти");
    }

    private void deleteClientInClientListById(Long idClient){
        Client c = null;
        for (Client client: this.clientList) {
            if (client.getId() == idClient){
                c = client;
            }
        }
        if (c != null) {
            this.clientList.remove(c);
        }
    }

    private List<String> getListNameScheme(){
        return Arrays.asList("CRIB2", "SNAPPE", "NTISS", "PCS", "TRIPS", "SOFA");
    }

    private void deleteSchemeClient(Client client, String delScheme){
        if (delScheme.equals("CRIB2")) {
            client.setCrib2(null);
        }
        if (delScheme.equals("SNAPPE")) {
            client.setSnapPe(null);
        }
        if (delScheme.equals("NTISS")) {
            client.setNtiss(null);
        }
        if (delScheme.equals("PCS")) {
            client.setPcs(null);
        }
        if (delScheme.equals("TRIPS")) {
            client.setTrips(null);
        }
        if (delScheme.equals("SOFA")) {
            client.setSofa(null);
        }
    }

    private List<List<String>> getListSchemeWhatHaveClient(Client client){
        List<List<String>> listInfoAboutScheme = new ArrayList<>();
        this.mortalityRisk = 0;
        int tempRisk = 0, countScheme = 0;

        if (client.getCrib2()!=null) {
            tempRisk = crib2Service.getCountProcLethalOutcome(client.getCrib2());
            countScheme++;
            listInfoAboutScheme.add(Arrays.asList("CRIB2",
                    client.getCrib2().toString(),
                    String.valueOf(tempRisk) + "%"));
            this.mortalityRisk += tempRisk;
        }
        if (client.getSnapPe()!=null) {
            tempRisk = snappeService.getCountProcLethalOutcome(client.getSnapPe());
            countScheme++;
            listInfoAboutScheme.add(Arrays.asList("SNAPPE",
                    client.getSnapPe().toString(),
                    String.valueOf(tempRisk) + "%"));
            this.mortalityRisk += tempRisk;
        }
        if (client.getNtiss()!=null) {
            tempRisk = ntissService.getCountProcLethalOutcome(client.getNtiss());
            countScheme++;
            listInfoAboutScheme.add(Arrays.asList("NTISS",
                    client.getNtiss().toString(),
                    String.valueOf(tempRisk) + "%"));
            this.mortalityRisk += tempRisk;
        }
        if (client.getPcs()!=null) {
            pcsService.setThisDateAndBorn(client.getBirthdate(), LocalDate.now());
            tempRisk = pcsService.getCountProcLethalOutcome(client.getPcs());
            countScheme++;
            listInfoAboutScheme.add(Arrays.asList("PCS",
                    client.getPcs().toString(),
                    String.valueOf(tempRisk) + "%"));
            this.mortalityRisk += tempRisk;
        }
        if (client.getTrips()!=null) {
            tempRisk = tripsService.getCountProcLethalOutcome(client.getTrips());
            countScheme++;
            listInfoAboutScheme.add(Arrays.asList("TRIPS",
                    client.getTrips().toString(), String.valueOf(tempRisk) + "%"));
            this.mortalityRisk += tempRisk;
        }
        if (client.getSofa()!=null) {
            tempRisk = sofaService.getCountProcLethalOutcome(client.getSofa());
            countScheme++;
            listInfoAboutScheme.add(Arrays.asList("SOFA",
                    client.getSofa().toString(),
                    String.valueOf(tempRisk) + "%"));
            this.mortalityRisk += tempRisk;
        }

        if (countScheme > 0){
            this.mortalityRisk = this.mortalityRisk / countScheme;
        }else {
            return null;
        }
        return listInfoAboutScheme;
    }

    private List<String> getListNameSchemeWhatHaveClient(Client client){
        List<String> list = new ArrayList<>();

        if (client.getCrib2()!=null) {
            list.add("CRIB2");
        }
        if (client.getSnapPe()!=null) {
            list.add("SNAPPE");
        }
        if (client.getNtiss()!=null) {
            list.add("NTISS");
        }
        if (client.getPcs()!=null) {
            list.add("PCS");
        }
        if (client.getTrips()!=null) {
            list.add("TRIPS");
        }
        if (client.getSofa()!=null) {
            list.add("SOFA");
        }

        return list;
    }

}
