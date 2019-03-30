package com.takeHospital.controller;

import com.takeHospital.repository.OpnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OpnController {
    @Autowired
    private OpnRepository opnRepository;

    @GetMapping("/department")
    public String showDepartment(
            Model model
    ){
        return "";
    }
}
