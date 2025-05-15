package com.example.vacationrecommender.service;

import com.example.vacationrecommender.dto.VacationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestVacationService {

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

}

