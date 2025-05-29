package com.example.vacationrecommender.controller;


import com.example.vacationrecommender.dto.VacationDTO;
import com.example.vacationrecommender.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vacante") // <-- acum e clar că doar JSON aici
public class VacationController {

    private final VacationService vacationService;

    @Autowired
    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping
    public List<VacationDTO> getVacanteFiltrate(
            @RequestParam(required = false) String tip,
            @RequestParam(required = false) String tara,
            @RequestParam(required = false) Integer buget,
            @RequestParam(required = false) String search
    ) {
        return vacationService.getVacanteCuFiltru(tip, tara, buget, search);
    }
}





