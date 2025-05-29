package com.example.vacationrecommender.service;

import com.example.vacationrecommender.dto.VacationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationService {

    @Autowired
    private TravelpayoutsHotelService hotelService;

    @Autowired
    private PixabayService pixabayService;

    public List<VacationDTO> getVacanteCuPoze(String locatie) {
        List<VacationDTO> vacante = hotelService.getHotelsForLocation(locatie);

        for (VacationDTO v : vacante) {
            String imagine = pixabayService.getImageUrlForHotel(v.getDestinatie());
            v.setImageUrl(imagine);
        }

        return vacante;
    }
    public List<VacationDTO> getVacanteCuFiltru(String tip, String tara, Integer buget, String search) {
        String locatie = (tara == null || tara.isBlank()) ? "România" : tara;
        List<VacationDTO> vacante = hotelService.getHotelsForLocation(locatie);

        return vacante.stream()
                .filter(v -> tip == null || (v.getTip() != null && v.getTip().equalsIgnoreCase(tip)))
                .filter(v -> buget == null || v.getPret() <= buget)
                .filter(v -> search == null || v.getDestinatie().toLowerCase().contains(search.toLowerCase()))
                .peek(v -> {
                    String imagine = pixabayService.getImageUrlForHotel(v.getDestinatie());
                    v.setImageUrl(imagine);
                })
                .toList();
    }



}

