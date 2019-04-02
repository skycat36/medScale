package com.takeHospital.service;

import com.takeHospital.domain.Client;
import com.takeHospital.domain.Opn;
import com.takeHospital.domain.parametrsForScheme.ParamScheme;
import com.takeHospital.repository.ClientRepository;
import com.takeHospital.repository.OpnRepository;
import com.takeHospital.repository.SchemeRepository;
import com.takeHospital.service.metodsScale.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class StatisticService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OpnRepository opnRepository;

    @Autowired
    private SchemeRepository schemeRepository;

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

    public StatisticService() {
    }

    public List getColClientInOPN(){
        List<Opn> allOpn = opnRepository.findAll();
        List<Integer> allValue = new ArrayList<>();
        for (Opn ob: allOpn){
            allValue.add(clientRepository.findByOpn(ob.getId()).size());
        }
        return Arrays.asList(getListNameOpn(), allValue);
    }

    public List getLetalInOPN(){
        List<Opn> allOpn = opnRepository.findAll();
        List<String> allValue = new ArrayList<>();
        int iterForDie = 0;
        for (Opn ob: allOpn){
            for(Client client: clientRepository.findByOpn(ob.getId())){
                if (client.getDateOfDeath() != null){
                    iterForDie++;
                }
            }
            allValue.add(String.valueOf(iterForDie));
            iterForDie = 0;
        }
        return Arrays.asList(getListNameOpn(), allValue);
    }

    public List getTimeClientInOPN(){
        List<Opn> allOpn = opnRepository.findAll();
        List<Double> allValue = new ArrayList<>();
        Long countDay;
        int countDayInBad = 0;
        int countPerson = 0;
        for (Opn ob: allOpn){
            for(Client client: clientRepository.findByOpn(ob.getId())){
                if (client.getDateOfDeparture() != null) {
                    countDay = ChronoUnit.DAYS.between(client.getDateOfArrival(), client.getDateOfDeparture());
                    countDayInBad += countDay;
                    countPerson++;
                }

            }
            if (countPerson > 0) {
                allValue.add(Double.parseDouble(String.valueOf(countDayInBad / countPerson)));
            }else {
                allValue.add(0.0);
            }
            countDayInBad = 0;
            countPerson = 0;
        }
        return Arrays.asList(getListNameOpn(), allValue);
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

    public Map<String, List<Integer>> getSeverityOnEachScale(){
        List<ParamScheme> paramSchemeList = schemeRepository.findAll();
        Map<String, List<Integer>> mapSeverityOnEachScale = new HashMap<>();
        List<Client> clientList = clientRepository.findAll();

        for (ParamScheme ps: paramSchemeList) {
            mapSeverityOnEachScale.put(ps.getNameSheme(), Arrays.asList(0,0,0,0,0,0,0,0,0,0));
        }

        for (Client client: clientList){
            if (client.getSofa() != null){
                mapSeverityOnEachScale.put("sofa",
                        setValueForListSeverityOnEachScale(mapSeverityOnEachScale.get("sofa"),
                                sofaService.getCountProcLethalOutcome(client.getSofa())));
            }

            if (client.getTrips() != null){
                mapSeverityOnEachScale.put("trips",
                        setValueForListSeverityOnEachScale(mapSeverityOnEachScale.get("trips"),
                                tripsService.getCountProcLethalOutcome(client.getTrips())));
            }

            if (client.getSnapPe() != null){
                mapSeverityOnEachScale.put("snappe",
                        setValueForListSeverityOnEachScale(mapSeverityOnEachScale.get("snappe"),
                                sofaService.getCountProcLethalOutcome(client.getSnapPe())));
            }

            if (client.getPcs() != null){
                pcsService.setThisDateAndBorn(client.getBirthdate(), LocalDate.now());
                mapSeverityOnEachScale.put("pcs",
                        setValueForListSeverityOnEachScale(mapSeverityOnEachScale.get("pcs"),
                                pcsService.getCountProcLethalOutcome(client.getPcs())));
            }

            if (client.getCrib2() != null){
                mapSeverityOnEachScale.put("crib2",
                        setValueForListSeverityOnEachScale(mapSeverityOnEachScale.get("crib2"),
                                crib2Service.getCountProcLethalOutcome(client.getCrib2())));
            }

            if (client.getNtiss() != null){
                mapSeverityOnEachScale.put("ntiss",
                        setValueForListSeverityOnEachScale(mapSeverityOnEachScale.get("ntiss"),
                                ntissService.getCountProcLethalOutcome(client.getNtiss())));
            }

        }

        return mapSeverityOnEachScale;
    }

    private void addYearInMap(Map<String, Integer> map, int year){
        LocalDate localDate = LocalDate.of(year, 1, 1);
        if (map.containsKey(localDate.toString())){
            map.put(localDate.toString(), map.get(localDate.toString())+1);
        }else {
            map.put(localDate.toString(), 1);
        }
    }

    private List<Integer> setValueForListSeverityOnEachScale(List<Integer> listValue, Integer value){
        for (int i = 10; i <= 100; i= i + 10){
            if ((value - i) >= 0 && (value - i) < 10){
                listValue.set(i / 10 - 1, listValue.get(i / 10 - 1) + 1);
            }
        }
        return listValue;
    }

    private List<String> getListNameOpn(){
        List<Opn> allOpn = opnRepository.findAll();
        List<String> nameOpnList = new ArrayList<>();
        for (Opn ob: allOpn){
            nameOpnList.add(ob.getOpn());
        }
        return nameOpnList;
    }
}
