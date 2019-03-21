package com.takeHospital.service;

import com.takeHospital.domain.Client;
import com.takeHospital.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class StatisticService {

    @Autowired
    private ClientRepository clientRepository;

    public StatisticService() {
    }

    public List getColClientInOPN(){
        int colClient = clientRepository.findAll().size();
        List<String> allOpn = clientRepository.findAllOpn();
        List<Integer> allValue = new ArrayList<>();
        for (String ob: allOpn){
            allValue.add(clientRepository.findByOpn(ob).size());
        }
        return Arrays.asList(allOpn, allValue);
    }

    public List getLetalInOPN(){
        List<String> allOpn = clientRepository.findAllOpn();
        List<String> allValue = new ArrayList<>();
        int iterForDie = 0;
        for (String ob: allOpn){
            for(Client client: clientRepository.findByOpn(ob)){
                if (client.getDateOfDeath() != null){
                    iterForDie++;
                }
            }
            allValue.add(String.valueOf(iterForDie));
            iterForDie = 0;
        }
        return Arrays.asList(allOpn, allValue);
    }

    public List getTimeClientInOPN(){
        List<String> allOpn = clientRepository.findAllOpn();
        List<Double> allValue = new ArrayList<>();
        Long countDay;
        int countDayInBad = 0;
        int countPerson = 0;
        for (String ob: allOpn){
            for(Client client: clientRepository.findByOpn(ob)){
                if (client.getDateOfDeparture() != null) {
                    countDay = ChronoUnit.DAYS.between(client.getDateOfArrival(), client.getDateOfDeparture());
                    countDayInBad += countDay;
                    countPerson++;
                }

            }
            allValue.add(Double.parseDouble(String.valueOf(countDayInBad / countPerson)));
            countDayInBad = 0;
            countPerson = 0;
            //allValue.add(String.valueOf(iterForDie));
        }
        return Arrays.asList(allOpn, allValue);
    }

    public List getLetalInYear(){
        Map<String, Integer> dateIntegerMap = new HashMap<>();
        for (Client client: clientRepository.findAll()){
            if (client.getDateOfDeath() != null){
                addYearInMap(dateIntegerMap, client.getDateOfDeath().getYear());
            }
        }
        return Arrays.asList(dateIntegerMap.keySet(), dateIntegerMap.values());
    }

    private void addYearInMap(Map<String, Integer> map, int year){
        LocalDate localDate = LocalDate.of(year, 1, 1);
        if (map.containsKey(localDate.toString())){
            map.put(localDate.toString(), map.get(localDate.toString())+1);
        }else {
            map.put(localDate.toString(), 1);
        }
    }

}
