package com.example.vacationrecommender.service;

import com.example.vacationrecommender.entity.Destination;
import com.example.vacationrecommender.entity.Rating;
import com.example.vacationrecommender.repository.DestinationRepository;
import com.example.vacationrecommender.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final DestinationRepository destinationRepository;

    public RatingService(RatingRepository ratingRepository, DestinationRepository destinationRepository) {
        this.ratingRepository = ratingRepository;
        this.destinationRepository = destinationRepository;
    }


    // Adaugă un rating legat de o destinație
    public void addRating(Long destinationId, int stars) {
        Rating rating = new Rating();

        // Găsește destinația din baza de date
        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new IllegalArgumentException("Destinația cu ID-ul " + destinationId + " nu există."));

        rating.setDestination(destination); // Setează destinația găsită
        rating.setStars(stars); // Setează numărul de stele
        ratingRepository.save(rating); // Salvează rating-ul
    }


    // Calculează rating-ul mediu pentru o destinație
    public double calculateAverageRating(Long destinationId) {
        List<Rating> ratings = ratingRepository.findByDestinationId(destinationId);
        return ratings.stream().mapToInt(Rating::getStars).average().orElse(0);
    }

    // Salvează un rating
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    // Găsește toate rating-urile
    public List<Rating> findAllRatings() {
        return ratingRepository.findAll();
    }
}


