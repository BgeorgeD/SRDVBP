package com.example.vacationrecommender.controller;

import com.example.vacationrecommender.dto.GlobDTO;
import com.example.vacationrecommender.service.GlobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/vacations")
@RequiredArgsConstructor
public class GlobController {

    private final GlobService globService;

    @Value("${cesium.ion.token}")
    private String cesiumToken;

    /**
     * 1) Servește pagina cu globul interactiv.
     *    URL: GET  /vacations/game
     */
    @GetMapping("/game")
    public String gamePage(Model model) {
        model.addAttribute("cesiumToken", cesiumToken);
        return "glob_de_vacante";
    }

    /**
     * 2) API REST pentru a căuta vacanțe după coordonate.
     *    URL: GET  /vacations/by-coordinates?lat={lat}&lon={lon}&radius={radius}
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

    @GetMapping(path = "/by-bbox", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GlobDTO> findByBBox(
            @RequestParam double latMin,
            @RequestParam double latMax,
            @RequestParam double lonMin,
            @RequestParam double lonMax
    ) {
        return globService.findByBoundingBox(latMin, latMax, lonMin, lonMax);
    }


    // În GlobController.java

    @GetMapping("/details/{xid}")
    public ResponseEntity<?> getAttractionDetails(@PathVariable String xid) {
        if (xid == null || xid.isBlank() || xid.equals("null")) {
            return ResponseEntity.badRequest().body("XID invalid");
        }

        try {
            return ResponseEntity.ok(globService.getDetailsByXid(xid));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Eroare la obținerea detaliilor: " + e.getMessage());
        }
    }





}
