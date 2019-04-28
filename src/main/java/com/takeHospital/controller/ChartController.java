package com.takeHospital.controller;

import com.takeHospital.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class ChartController {


    @Autowired
    private StatisticService statisticService;

    @GetMapping("/chart_stat")
    public String showStatistic(
            Model model
    ){
        return "/page/for_statistic/statisticData";
    }

    @PostMapping("/chart_stat")
    public String paramForStatic(
            @RequestParam String selectedField,
            Model model
    ){
        model.addAttribute("selectedField", selectedField);
        switch (selectedField){
            case "colClientInOPN": {
                List temp = statisticService.getColClientInOPN();
                model.addAttribute("listValue", temp.get(1));
                model.addAttribute("listOpn",  temp.get(0));

            }break;

            case "letalInOPN": {
                List temp = statisticService.getLetalInOPN();
                model.addAttribute("listValue", temp.get(1));
                model.addAttribute("listOpn",  temp.get(0));
            }break;

            case "timeClientInOPN": {
                List temp = statisticService.getTimeClientInOPN();
                model.addAttribute("listValue", temp.get(1));
                model.addAttribute("listOpn",  temp.get(0));
            } break;

            case "letalInYear": {
                List temp = statisticService.getLetalInYear();
                model.addAttribute("listValue", temp.get(1));
                model.addAttribute("listOpn",  temp.get(0));
            } break;

            case "severityOnEachScale": {
                Map<String, List<Integer>> map = statisticService.getSeverityOnEachScale();
                model.addAttribute("listProc", Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100));
                model.addAttribute("nameSchemes", map.keySet().toArray());
                model.addAttribute("schemes", map.values());
            } break;

            default: {
                List temp = statisticService.getColClientInOPN();
                model.addAttribute("listValue", temp.get(1));
                model.addAttribute("listOpn",  temp.get(0));
            }break;
        }

        model.addAttribute("selChart", selectedField);
        return "/page/for_statistic/statisticData";
    }
}
