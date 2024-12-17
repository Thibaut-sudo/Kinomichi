package org.example.kinomichi.controller;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;
import org.example.kinomichi.model._Club;
import org.example.kinomichi.model._Event;
import org.example.kinomichi.model._User;
import org.example.kinomichi.service.ClubService;
import org.example.kinomichi.service.ClubToUserService;
import org.example.kinomichi.service.EventService;
import org.example.kinomichi.service.UserToEventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccueilController {

    private final ClubService clubService;
    private final EventService eventService;
    private final ClubToUserService clubToUserService;
    private final UserToEventService userToEventService;

    public AccueilController(ClubService clubService, EventService eventService, ClubToUserService clubToUserService, UserToEventService userToEventService) {
        this.clubService = clubService;
        this.eventService = eventService;
        this.clubToUserService = clubToUserService;
        this.userToEventService = userToEventService;
    }

    @GetMapping("/accueil")
    public String getClubAndEvents(HttpSession session, Model model) {
        // Récupérer l'utilisateur connecté depuis la session
        _User currentUser = (_User) session.getAttribute("currentUser");
        if (currentUser == null) {
            // Redirige vers la page de connexion si l'utilisateur n'est pas connecté
            return "redirect:/";
        }

        // Ajouter l'utilisateur au modèle
        model.addAttribute("currentUser", currentUser);

        // Récupérer la liste des clubs de l'utilisateur
        List<_Club> userClubs = clubToUserService.getClubsByUser(currentUser.getId());

        // Créer une liste de CLubEvent contenant chaque club et son prochain événement
        List<CLubEvent> accueilData = new ArrayList<>();
        for (_Club club : userClubs) {
            _Event nextEvent = eventService.getNextEventByClub(club.getId());
            CLubEvent clubEvent = new CLubEvent(club, nextEvent);
            accueilData.add(clubEvent);
        }
        System.out.println(accueilData.size());

        // Ajouter la liste enrichie au modèle
        model.addAttribute("accueil", accueilData);

        // Ajouter le nombre de clubs au modèle
        model.addAttribute("nbClub", userClubs.size());

        // Récupérer les événements pour la section "Your next Events"
        //todo
        List<Long> upcomingEventsForUser = userToEventService.getEventsByUserId (currentUser.getId());

        List<_Event> eventsForUser = eventService.getEventsById(upcomingEventsForUser);
        model.addAttribute("events", eventsForUser);


        return "accueil"; // Affiche la vue accueil.html
    }


    public static class CLubEvent {
        @Setter
        private _Club club;
        @Setter
        private _Event event;

        public CLubEvent(_Club club, _Event event) {
            this.club = club;
            this.event = event;
        }

        public _Club getClub() {
            return club;
        }

        public _Event getEvent() {
            return event;
        }
    }
}