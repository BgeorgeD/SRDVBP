package com.example.vacationrecommender.service;

import com.example.vacationrecommender.entity.Destination;
import com.example.vacationrecommender.entity.Rating;
import com.example.vacationrecommender.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    // Adaugă un rating legat de o destinație
    public void addRating(Long destinationId, int stars) {
        Rating rating = new Rating();
        Destination destination = new Destination();
        destination.setId(destinationId); // Setează ID-ul destinației

        rating.setDestination(destination);
        rating.setStars(stars);
        ratingRepository.save(rating);
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


