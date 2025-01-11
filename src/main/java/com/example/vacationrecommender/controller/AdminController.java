/*package com.example.vacationrecommender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @GetMapping
    public String admin_Dashboard() {
        return "admin_dashboard"; // Afișează dashboard-ul adminului
    }

    @GetMapping("/destinations")
    public String manageDestinations() {
        return "admin_destinations"; // Pagină pentru adăugare/modificare destinații
    }
}*/

package com.example.vacationrecommender.controller;



import com.example.vacationrecommender.entity.Destination;
import com.example.vacationrecommender.entity.User;
import com.example.vacationrecommender.service.CommentService;
import com.example.vacationrecommender.service.DestinationService;
import com.example.vacationrecommender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final DestinationService destinationService;
    private final UserService userService;
    private CommentService commentService;

    @Autowired
    public AdminController(DestinationService destinationService, UserService userService, CommentService commentService) {
        this.destinationService = destinationService;
        this.userService = userService;
        this.commentService = commentService;
    }

    // Dashboard Admin
    @GetMapping
    public String admin_Dashboard() {
        return "admin_dashboard"; // Afișează dashboard-ul adminului
    }

    // Gestionare Destinații
    @GetMapping("/destinations")
    public String showDestinations(Model model) {
        model.addAttribute("destinations", destinationService.findAllDestinations());
        model.addAttribute("destination", new Destination()); // Obiect gol pentru formular
        return "admin_destinations";
    }

    @PostMapping("/destinations/add")
    public String addDestination(@ModelAttribute Destination destination) {
        destinationService.saveDestination(destination);
        return "redirect:/admin/destinations";
    }

    @PostMapping("/destinations/delete/{id}")
    public String deleteDestination(@PathVariable Long id) {
        destinationService.deleteDestinationById(id);
        return "redirect:/admin/destinations";
    }

    // Gestionare Utilizatori
    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("user", new User()); // Obiect gol pentru formular
        return "admin_users";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }
    // Vizualizează toate comentariile
    @GetMapping("/reviews")
    public String showReviews(Model model) {
        model.addAttribute("comments", commentService.getAllComments());
        return "admin_reviews"; // Pagina HTML pentru gestionarea recenziilor
    }

    @PostMapping("/reviews/delete")
    public String deleteReview(@RequestParam("commentId") Long commentId) {
        commentService.deleteCommentById(commentId);
        return "redirect:/admin/reviews"; // Redirecționează la pagina de recenzii
    }


}



