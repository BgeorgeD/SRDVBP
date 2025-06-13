package com.example.vacationrecommender.controller;

import com.example.vacationrecommender.dto.GlobDTO;
import com.example.vacationrecommender.service.GlobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping("/vacations")
@RequiredArgsConstructor
public class GlobController {

    private final GlobService globService;

    /**
     * 1) Servește pagina cu globul interactiv.
     *    URL: GET  /vacations/game
     */
    @GetMapping("/game")
    public String gamePage() {
        // Thymeleaf va căuta fișierul:
        // src/main/resources/templates/glob_de_vacante.html
        return "glob_de_vacante";
    }

    /**
     * 2) API REST pentru a căuta vacanțe după coordonate.
     *    URL: GET  /vacations/by-coordinates?lat={lat}&lon={lon}&radius={radius}
     *    Produce JSON
     */
    @GetMapping(path = "/by-coordinates", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GlobDTO> findByCoordinates(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam(defaultValue = "5000") int radius
    ) {
        return globService.findNear(lat, lon, radius);
    }
}

