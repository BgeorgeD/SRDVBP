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
import java.util.Optional;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }
    public Optional<Destination> findById(Long id) {
        return destinationRepository.findById(id);
    }

    public Destination getById(Long id) {
        return destinationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Destinația cu ID-ul " + id + " nu există."));
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

    public Destination findDestinationById(Long id) {
        Optional<Destination> optionalDestination = destinationRepository.findById(id);
        return optionalDestination.orElse(null); // Returnează null dacă destinația nu există
    }
}


