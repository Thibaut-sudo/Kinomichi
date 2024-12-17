package org.example.kinomichi.controller;

import lombok.Getter;
import lombok.Setter;
import org.example.kinomichi.model._User;
import org.example.kinomichi.model._Club;
import org.example.kinomichi.model._Event;  // Assuming you have this model for events
import org.example.kinomichi.service.ClubService;
import org.example.kinomichi.service.EventService;
import org.example.kinomichi.service.UserToEventService;
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
    private final UserToEventService userToEventService;
    private long clubIdP;

    public EventListController(ClubService clubService, EventService eventService, UserToEventService userToEventService) {
        this.clubService = clubService;
        this.eventService = eventService;
        this.userToEventService = userToEventService;
    }

    @GetMapping("/eventList/{clubId}")
    public String showEvents(@PathVariable Long clubId, Model model, HttpSession session) {
        clubIdP = clubId;
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
        // Assuming this method exists
        List<_Event> events = eventService.getListEventByIdClub(clubId);
        ;
        List<Event> eventsWithRegistration = new java.util.ArrayList<>(Collections.emptyList());
        for (_Event event : events) {
            // Check if the current user is registered for the event
            Boolean isUserRegistered = userToEventService.isLinked(currentUser.getId(), event.getId());
            Event eventWithRegistration = new Event(event, isUserRegistered);
            eventsWithRegistration.add(eventWithRegistration);
        }

        // Add the club and events to the model
        model.addAttribute("club", club);
        model.addAttribute("events", events);
        model.addAttribute("eventsWithRegistration", eventsWithRegistration);
        model.addAttribute("currentUser", currentUser); // Pass the current user if needed

        return "eventList"; // The view that will render the events
    }
    @GetMapping("/joinEvent/{id}")
    public String joinClub(@PathVariable Long id, HttpSession session) {
        _User currentUser = (_User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/";
        }

        userToEventService.joinEvent(id, currentUser.getId());

        return "redirect:/eventList/"+clubIdP; // Redirige vers la page principale
    }
    @Setter
    @Getter
    private static class Event {

        private _Event event;
        private Boolean isUserRegistered;

        Event(_Event event, Boolean isUserRegistered) {
            this.event = event;
            this.isUserRegistered = isUserRegistered;
        }

    }


}
