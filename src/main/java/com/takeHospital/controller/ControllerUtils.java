package com.takeHospital.controller;

import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class ControllerUtils {
    static Map<String, String> getErrors(BindingResult bindingResult) {
        Map<String, String> errorMap = new HashMap<>();


        for (int i = 0; i < bindingResult.getAllErrors().size(); i++){
            errorMap.put(bindingResult.getFieldErrors().get(i).getField() + "Error",
                    bindingResult.getFieldErrors().get(i).getDefaultMessage());
        }
        return errorMap;
    }

    static int getBallsFromStringArr(String[] arr){
        int ball = 0;
        for (int i=0; i<arr.length; i++){
            ball += Integer.parseInt(arr[i]);
        }
        return ball;
    }
}
