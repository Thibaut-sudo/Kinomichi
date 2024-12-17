package org.example.kinomichi.controller;

import org.example.kinomichi.model._User;
import org.example.kinomichi.model._Club;
import org.example.kinomichi.model._Event;  // Assuming you have this model for events
import org.example.kinomichi.service.ClubService;
import org.example.kinomichi.service.EventService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpSession;

import java.util.Collections;
import java.util.List;
@Controller
public class EventListController {
    private final ClubService clubService;
    private final EventService eventService;

    public EventListController(ClubService clubService, EventService eventService) {
        this.clubService = clubService;
        this.eventService = eventService;
    }

    @GetMapping("/eventList/{clubId}")
    public String showEvents(@PathVariable Long clubId, Model model, HttpSession session) {
        // Fetch the current user from session
        _User currentUser = (_User) session.getAttribute("currentUser");
        if (currentUser == null) {
            // Redirige vers la page de connexion si l'utilisateur n'est pas connecté
            return "redirect:/";
        }
        model.addAttribute("currentUser", currentUser);


        // Fetch the club by ID
        _Club club = clubService.getClubById(clubId);

        // Fetch the events of the selected club
        List<_Event> events = eventService.getListEventByIdClub(clubId);  // Assuming this method exists

        // Add the club and events to the model
        model.addAttribute("club", club);
        model.addAttribute("events", events);
        model.addAttribute("currentUser", currentUser); // Pass the current user if needed

        return "eventList"; // The view that will render the events
    }
}
