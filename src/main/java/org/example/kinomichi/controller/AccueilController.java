package org.example.kinomichi.controller;

import jakarta.servlet.http.HttpSession;
import lombok.Getter;
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
import org.springframework.web.bind.annotation.PathVariable;

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
            int nbUser = clubToUserService.getnbOfUserForAClub(club.getId());
            CLubEvent clubEvent = new CLubEvent(club, nextEvent,nbUser);
            accueilData.add(clubEvent);
        }
        System.out.println(accueilData.size());

        // Ajouter la liste enrichie au modèle
        model.addAttribute("accueil", accueilData);

        // Ajouter le nombre de clubs au modèle
        model.addAttribute("nbClub", userClubs.size());


        List<Long> upcomingEventsForUser = userToEventService.getEventsByUserId(currentUser.getId());

        List<_Event> eventsForUser = eventService.getEventsById(upcomingEventsForUser);
        model.addAttribute("NbEvent", eventsForUser.size());
        List<EventPay> eventPays = new ArrayList<>();
        for (_Event event : eventsForUser) {
            Boolean isPayed = userToEventService.isPayed(currentUser.getId(), event.getId());
            EventPay eventPay = new EventPay(event, isPayed);
            eventPays.add(eventPay);

        }
        model.addAttribute("events", eventPays);

        //fait un stream pour connaitre le nombre d'event a payer
        long nbEventToPay = eventPays.stream().filter(eventPay -> !eventPay.isPayed()).count();
        model.addAttribute("nbEventToPay", nbEventToPay);



        return "accueil"; // Affiche la vue accueil.html
    }
    @GetMapping("/accueil/{id}")
    public String joinClub(@PathVariable Long id, HttpSession session) {
        _User currentUser = (_User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/";
        }

        userToEventService.pay(currentUser.getId(), id);
        return "redirect:/accueil"; // Redirige vers la page principale
    }


    @Setter
    @Getter
    private static class CLubEvent {
        private _Club club;
        private _Event event;
        private int nbUser;


        public CLubEvent(_Club club, _Event event, int nbUser) {
            this.club = club;
            this.event = event;
            this.nbUser = nbUser;
        }

    }
    @Setter
    @Getter
    private static class EventPay {
        private _Event event;
        private Boolean isPayed;

        public EventPay(_Event event, Boolean isPayed) {
            this.event = event;
            this.isPayed = isPayed;
        }

        public boolean isPayed() {
            return isPayed;
        }
    }


}