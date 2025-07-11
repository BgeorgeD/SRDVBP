package com.example.vacationrecommender.controller;

import org.springframework.ui.Model;

import com.example.vacationrecommender.dto.VacationDTO;
import com.example.vacationrecommender.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private VacationService vacationService;

    @GetMapping("/test-vacante")
    public String showTestVacante(@RequestParam(defaultValue = "Oradea") String locatie, Model model) {
        List<VacationDTO> vacante = vacationService.getVacanteCuPoze(locatie);
        model.addAttribute("vacante", vacante);
        model.addAttribute("locatie", locatie);
        return "test_vacante";
    }

}

