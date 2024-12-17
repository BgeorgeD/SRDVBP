/*package com.example.vacationrecommender.service;

import com.example.vacationrecommender.entity.Destination;
import com.example.vacationrecommender.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository repository;

    public List<Destination> getAllDestinations() {
        return repository.findAll();
    }

    public void saveDestination(Destination destination) {
        repository.save(destination);
    }

    public void deleteDestination(Long id) {
        repository.deleteById(id);
    }
}*/

package com.example.vacationrecommender.service;

import com.example.vacationrecommender.entity.Destination;
import com.example.vacationrecommender.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public List<Destination> findAllDestinations() {
        return destinationRepository.findAll();
    }

    public void saveDestination(Destination destination) {
        destinationRepository.save(destination);
    }

    public void deleteDestinationById(Long id) {
        destinationRepository.deleteById(id);
    }
}


