package com.takeHospital.service;

import com.takeHospital.domain.Client;
import com.takeHospital.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public List<Client> findClientByName(){
        return null;
    }

    public List<Client> findClientByFam(){
        return null;
    }

    public List<Client> findClientBySecName(){
        return null;
    }

    public List<Client> findClientByOpn(){
        return null;
    }

    public void updateProfileClient(Client client, Map<String, Object> schemeClient){
        client.setFam(schemeClient.get("fam").toString());
        client.setName(schemeClient.get("name").toString());
        client.setSecName(schemeClient.get("secName").toString());
        client.setBirthdate(LocalDate.parse(schemeClient.get("birthdate").toString()));
        client.setDateOfArrival(LocalDate.parse(schemeClient.get("dateOfArrival").toString()));
        client.setSurvayDate(LocalDate.parse(schemeClient.get("survayDate").toString()));
        client.setDateOfDeparture(LocalDate.parse(schemeClient.get("dateOfDeparture").toString()));
        client.setDateOfDeath(LocalDate.parse(schemeClient.get("dateOfDeath").toString()));
        clientRepository.save(client);
    }

    public void updateScheme(Client client, Map<String, Integer> schemeClient) {

        client.setCrib2(schemeClient.get("crib2"));
        client.setSnapPe(schemeClient.get("snapPe"));
        client.setNtiss(schemeClient.get("ntiss"));
        client.setPcs(schemeClient.get("pcs"));
        client.setTrips(schemeClient.get("trips"));
        client.setSofa(schemeClient.get("sofa"));

        clientRepository.save(client);
    }

}
