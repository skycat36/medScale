package com.takeHospital.controller;

import com.takeHospital.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
