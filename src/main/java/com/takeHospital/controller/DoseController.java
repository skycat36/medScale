package com.takeHospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/dose")
public class DoseController {

    @GetMapping("/rate")
    public String showRate(
            Model model
    ){
        model.addAttribute("infution", "rate");
        return "calculateDose";
    }

    @GetMapping("/calc_dose")
    public String showDose(
            Model model
    ){
        model.addAttribute("infution", "calc_dose");
        return "calculateDose";
    }

    @GetMapping("/concentr")
    public String showConcentr(
            Model model
    ){
        model.addAttribute("infution", "concentr");
        return "calculateDose";
    }

    @PostMapping
    public String calculateDose(
            @RequestParam(required = false, defaultValue = "") String infution,
            @RequestParam(required = false, defaultValue = "") String dose_field,
            @RequestParam(required = false, defaultValue = "") String sel_dose_weight,
            @RequestParam(required = false, defaultValue = "") String sel_dose_time,
            @RequestParam(required = false, defaultValue = "") String rate_field,
            @RequestParam(required = false, defaultValue = "") String sel_rate_dose,
            @RequestParam(required = false, defaultValue = "") String weight_field,
            @RequestParam(required = false, defaultValue = "") String field_concentr_weight,
            @RequestParam(required = false, defaultValue = "") String sel_concentr_weight,
            @RequestParam(required = false, defaultValue = "") String dose_concentr_field,
            @RequestParam(required = false, defaultValue = "") String sel_concentr_dose,
            Model model
    ){
        double weight = 1;
        Map<String, String> mapError= new HashMap<>();

        if (!weight_field.equals("")){
            weight = Double.parseDouble(weight_field);
        }

        model.addAttribute("infution", infution);
        model.addAttribute("sel_dose_weight", sel_dose_weight);
        model.addAttribute("sel_dose_time", sel_dose_time);
        model.addAttribute("sel_rate_dose", sel_rate_dose);
        model.addAttribute("sel_concentr_weight", sel_concentr_weight);
        model.addAttribute("sel_concentr_dose", sel_concentr_dose);

        if (infution.equals("rate")){
            if (dose_field.equals("")){
                mapError.put("dose_fieldError", "Заполните поле.");
            }

            if (field_concentr_weight.equals("")){
                mapError.put("field_concentr_weightError", "Заполните поле.");
            }

            if (dose_concentr_field.equals("")){
                mapError.put("dose_concentr_fieldError", "Заполните поле.");
            }

            model.addAttribute("dose_field", dose_field);
            model.addAttribute("field_concentr_weight", field_concentr_weight);
            model.addAttribute("dose_concentr_field", dose_concentr_field);

            if (mapError.isEmpty()){
                double result = rate(Double.parseDouble(dose_field),
                        Double.parseDouble(sel_dose_weight) / Double.parseDouble(sel_dose_time),
                        weight, Double.parseDouble(field_concentr_weight) / Double.parseDouble(dose_concentr_field),
                        Double.parseDouble(sel_concentr_weight) / Double.parseDouble(sel_concentr_dose));
                model.addAttribute("result", new BigDecimal(result / Double.parseDouble(sel_rate_dose)).setScale(1, RoundingMode.UP).doubleValue());
            }
        }

        if (infution.equals("calc_dose")){
            if (rate_field.equals("")){
                mapError.put("rate_fieldError", "Заполните поле.");
            }

            if (field_concentr_weight.equals("")){
                mapError.put("field_concentr_weightError", "Заполните поле.");
            }

            if (dose_concentr_field.equals("")){
                mapError.put("dose_concentr_fieldError", "Заполните поле.");
            }

            model.addAttribute("rate_field", rate_field);
            model.addAttribute("field_concentr_weight", field_concentr_weight);
            model.addAttribute("dose_concentr_field", dose_concentr_field);

            if (mapError.isEmpty()){
                double result = dose(Double.parseDouble(rate_field),
                        Integer.parseInt(sel_rate_dose),
                        weight, Double.parseDouble(field_concentr_weight) / Double.parseDouble(dose_concentr_field),
                        Double.parseDouble(sel_concentr_weight) / Double.parseDouble(sel_concentr_dose));
                model.addAttribute("result", new BigDecimal(result / Double.parseDouble(sel_dose_weight)).setScale(1, RoundingMode.UP).doubleValue());
            }
        }
        if (infution.equals("concentr")){
            if (dose_field.equals("")){
                mapError.put("dose_fieldError", "Заполните поле.");
            }

            if (rate_field.equals("")){
                mapError.put("rate_fieldError", "Заполните поле.");
            }

            model.addAttribute("dose_field", dose_field);
            model.addAttribute("rate_field", rate_field);
            model.addAttribute("dose_concentr_field", dose_concentr_field);

            if (mapError.isEmpty()){
//                double resWeight = Double.parseDouble(dose_field) *
//                        (Integer.parseInt(sel_dose_weight) / Integer.parseInt(sel_dose_time)) *
//                        weight * 60;
//                double resDose = Double.parseDouble(rate_field) * Integer.parseInt(sel_rate_dose) * 1000;
//                model.addAttribute("resultWeight", resWeight);
//                model.addAttribute("resultDose", resDose);
                double result = concentration(Double.parseDouble(dose_field),
                        Integer.parseInt(sel_dose_weight) / Integer.parseInt(sel_dose_time),
                        weight, Double.parseDouble(rate_field), Integer.parseInt(sel_rate_dose) );
                model.addAttribute("resultWeight", new BigDecimal(result / Double.parseDouble(sel_concentr_weight)).setScale(1, RoundingMode.UP).doubleValue());
                model.addAttribute("resultDose", "100");
            }
        }

        if (!mapError.isEmpty()){
            model.mergeAttributes(mapError);
            model.addAttribute("formError", "Заполните поля");
        }

        return "calculateDose";
    }

    private double rate(double dose, double dose_par, double weight, double concentr, double concentr_par){

        return (dose * dose_par * weight * 60) / (concentr * concentr_par * 1000);
    }

    private double dose(double rate, double rate_par, double weight, double concentr, double concentr_par){

        return (rate * rate_par * concentr * concentr_par * 1000) / (weight * 60);
    }

    private double concentration(double dose, double dose_par, double weight, double rate, double rate_par){
        return (dose * weight * 60) / (rate * rate_par * dose_par * 1000);
    }

}
