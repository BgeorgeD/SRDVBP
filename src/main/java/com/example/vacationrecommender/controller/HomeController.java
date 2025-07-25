/*package com.example.vacationrecommender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String showHomePage() {
        System.out.println("Accessing home page..."); // Log pentru debug
        return "home";
    }
    @GetMapping("/mountain")
    public String showMountainPage() {
        return "home_subdivisions/mountain";
    }

    @GetMapping("/sea")
    public String showSeaPage() {
        return "home_subdivisions/sea";
    }

    @GetMapping("/city")
    public String showCityPage() {
        return "home_subdivisions/city";
    }

    @GetMapping("/jungle")
    public String showJunglePage() {
        return "home_subdivisions/jungle";
    }
}*/
/*package com.example.vacationrecommender.controller;

import ch.qos.logback.core.model.Model;
import com.example.vacationrecommender.service.CommentService;
import com.example.vacationrecommender.service.DestinationService;
import com.example.vacationrecommender.service.RatingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private final DestinationService destinationService;
    private final CommentService commentService;
    private final RatingService ratingService;

    public HomeController(DestinationService destinationService, CommentService commentService, RatingService ratingService) {
        this.destinationService = destinationService;
        this.commentService = commentService;
        this.ratingService = ratingService;
    }

    @GetMapping("/home")
    public String showHomePage() {
        System.out.println("Accessing home page..."); // Log pentru debug
        return "home";
    }

    @GetMapping("/mountain")
    public String showMountainPage(ModelMap model) {
        Long mountainDestinationId = 1L; // ID-ul destinației montane
        populateModelWithDestinationData(mountainDestinationId, model);
        return "home_subdivisions/mountain";
    }

    @GetMapping("/sea")
    public String showSeaPage(ModelMap model) {
        Long seaDestinationId = 2L; // ID-ul destinației la mare
        populateModelWithDestinationData(seaDestinationId, model);
        return "home_subdivisions/sea";
    }

    @GetMapping("/city")
    public String showCityPage(ModelMap model) {
        Long cityDestinationId = 3L; // ID-ul destinației culturale
        populateModelWithDestinationData(cityDestinationId, model);
        return "home_subdivisions/city";
    }

    @GetMapping("/jungle")
    public String showJunglePage(ModelMap model) {
        Long jungleDestinationId = 4L; // ID-ul destinației în natură
        populateModelWithDestinationData(jungleDestinationId, model);
        return "home_subdivisions/jungle";
    }
    @GetMapping("/ai")
    public String showAiRecommendationPage() {
        return "ai_recommendation";
    }






    /**
     * Metodă auxiliară pentru a popula modelul cu datele necesare.
     */
    /*private void populateModelWithDestinationData(Long destinationId, ModelMap model) {
        var destination = destinationService.findDestinationById(destinationId);
        if (destination == null) {
            throw new IllegalArgumentException("Destinația cu ID-ul " + destinationId + " nu există.");
        }

        model.put("destination", destination); // Destinația specifică
        model.put("comments", commentService.getCommentsByDestination(destinationId)); // Comentariile
        model.put("averageRating", ratingService.calculateAverageRating(destinationId)); // Ratingul mediu
    }




    @GetMapping("/destinations/{id}")
    public String showDestinationPage(@PathVariable Long id, ModelMap model) {
        // Populează modelul cu datele necesare
        populateModelWithDestinationData(id, model);

        // Alege pagina HTML corespunzătoare bazată pe ID-ul destinației
        String page;
        switch (id.intValue()) {
            case 1:
                page = "home_subdivisions/mountain"; // Munții Bucegi
                break;
            case 2:
                page = "home_subdivisions/sea"; // Grecia
                break;
            case 3:
                page = "home_subdivisions/city"; // Londra
                break;
            case 4:
                page = "home_subdivisions/jungle"; // O altă locație
                break;
            default:
                throw new IllegalArgumentException("Destinația cu ID-ul " + id + " nu există.");
        }



        return page;
    }



}*/

package com.example.vacationrecommender.controller;

import com.example.vacationrecommender.dto.VacationDTO;
import com.example.vacationrecommender.service.ApiDestinationService;
import com.example.vacationrecommender.service.CommentService;
import com.example.vacationrecommender.service.RatingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.vacationrecommender.service.VacationService;


import java.util.List;

@Controller
public class HomeController {

    private final ApiDestinationService apiDestinationService;
    private final CommentService commentService;
    private final RatingService ratingService;




    public HomeController(ApiDestinationService apiDestinationService,
                          CommentService commentService,
                          RatingService ratingService
                          ) {
        this.apiDestinationService = apiDestinationService;
        this.commentService = commentService;
        this.ratingService = ratingService;
    }



    @GetMapping("/home")
    public String showHomePage() {
        System.out.println("Accessing home page...");
        return "home";
    }

    @GetMapping("/choice_page")
    public String showchoicepage() {
        System.out.println("Accessing choice page...");
        return "choice_page";
    }

    @GetMapping("/vacante")
    public String showVacantePage() {
        return "vacante";
    }




    @GetMapping("/mountain")
    public String showMountainPage(ModelMap model) {
        var hotels = apiDestinationService.getDestinationsByQuery("mountain");
        model.put("hotels", hotels);
        return "home_subdivisions/mountain";
    }

    @GetMapping("/sea")
    public String showSeaPage(ModelMap model) {
        var hotels = apiDestinationService.getDestinationsByQuery("sea");
        model.put("hotels", hotels);
        return "home_subdivisions/sea";
    }

    @GetMapping("/city")
    public String showCityPage(ModelMap model) {
        var hotels = apiDestinationService.getDestinationsByQuery("city");
        model.put("hotels", hotels);
        return "home_subdivisions/city";
    }

    @GetMapping("/jungle")
    public String showJunglePage(ModelMap model) {
        var hotels = apiDestinationService.getDestinationsByQuery("nature");
        model.put("hotels", hotels);
        return "home_subdivisions/jungle";
    }

    @GetMapping("/ai")
    public String showAiRecommendationPage() {
        return "ai_recommendation";
    }



    @GetMapping("/destinations/{id}")
    public String showDestinationPage(@PathVariable Long id, ModelMap model) {
        // Acesta rămâne dacă vrei să păstrezi rutele bazate pe ID
        // Recomand să le folosești doar pe cele bazate pe query (de sus)

        var hotels = apiDestinationService.getDestinationsByQuery("sea"); // fallback
        model.put("hotels", hotels);

        String page;
        switch (id.intValue()) {
            case 1:
                page = "home_subdivisions/mountain";
                break;
            case 2:
                page = "home_subdivisions/sea";
                break;
            case 3:
                page = "home_subdivisions/city";
                break;
            case 4:
                page = "home_subdivisions/jungle";
                break;
            default:
                throw new IllegalArgumentException("Destinația cu ID-ul " + id + " nu există.");
        }

        return page;
    }
}



