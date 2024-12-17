package org.example.kinomichi.controller;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;
import org.example.kinomichi.model._Club;
import org.example.kinomichi.model._User;
import org.example.kinomichi.service.ClubService;
import org.example.kinomichi.service.ClubToUserService;
import org.example.kinomichi.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class joinClubController {
    private final ClubService clubService;
    private final EventService eventService;
    private final ClubToUserService clubToUserService;


    public joinClubController(ClubService clubService, EventService eventService, ClubToUserService clubToUserService) {
        this.clubService = clubService;
        this.eventService = eventService;
        this.clubToUserService = clubToUserService;

    }

    @GetMapping("/joinClub")
    public String showAddClubForm(HttpSession session, Model model) {
        _User currentUser = (_User) session.getAttribute("currentUser");
        if (currentUser == null) {
            // Redirige vers la page de connexion si l'utilisateur n'est pas connecté
            return "redirect:/";
        }

        model.addAttribute("currentUser", currentUser);

        // Liste de tous les clubs
        List<_Club> listClub = clubService.getAllClub();
        List<CLubEventUser> cLubEventUserList = new ArrayList<>();

        // Liste des clubs auxquels l'utilisateur appartient
        List<Long> userClubIds = clubToUserService.getUserClubIds(currentUser.getId());

        for (_Club club : listClub) {
            boolean isMember = userClubIds.contains(club.getId());
            CLubEventUser cLubEventUser = new CLubEventUser(
                    club,
                    eventService.getnbOfeventForAClub(club.getId()),
                    clubToUserService.getnbOfUserForAClub(club.getId()),
                    isMember
            );
            cLubEventUserList.add(cLubEventUser);
        }

        model.addAttribute("accueil", cLubEventUserList);
        return "joinClub"; // Vue Thymeleaf
    }

    @GetMapping("/joinClub/{id}")
    public String joinClub(@PathVariable Long id, HttpSession session) {
        _User currentUser = (_User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/";
        }

        clubToUserService.joinClub(id, currentUser.getId());
        return "redirect:/joinClub"; // Redirige vers la page principale
    }

    @Setter
    private static class CLubEventUser {
        private _Club club;
        private int event;
        private int user;
        private boolean isMember;

        public CLubEventUser(_Club club, int event, int user, boolean isMember) {
            this.club = club;
            this.event = event;
            this.user = user;
            this.isMember = isMember;
        }

        public _Club getClub() {
            return club;
        }

        public int getEvent() {
            return event;
        }

        public int getUser() {
            return user;
        }

        public boolean isMember() {
            return isMember;
        }
    }


}

