package org.example.kinomichi.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.kinomichi.model.MTMClubToUser;
import org.example.kinomichi.model._Club;

import org.example.kinomichi.model._User;
import org.example.kinomichi.service.ClubService;
import org.example.kinomichi.service.ClubToUserService;
import org.example.kinomichi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@Slf4j
public class addClub {

    @Autowired
    private ClubService clubService;

    @Autowired
    private ClubToUserService clubToUserService;

    @GetMapping("/addClub")
    public String showAddClubForm() {
        return "addClub"; // Renvoie une vue (fichier HTML ou Thymeleaf)
    }

    @RequestMapping(value = "/addClub", method = RequestMethod.POST)
    public String addClubInDb(@ModelAttribute _Club club, RedirectAttributes redirectAttributes, HttpSession session) {
        // Récupérer l'utilisateur connecté depuis la session
        _User currentUser = (_User) session.getAttribute("currentUser");

        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "You must be logged in to add a club.");
            return "redirect:/login"; // Redirige vers la page de connexion si l'utilisateur n'est pas connecté
        }

        if (clubService.existsByName(club.getName())) {
            redirectAttributes.addFlashAttribute("error", "Club name already exists.");
            return "redirect:/addClub";
        } else {


            clubService.saveClub(club);

            MTMClubToUser mtmClubToUser = new MTMClubToUser(generateUUID(),club.getId(), currentUser.getId());

            clubToUserService.saveClubToUser(mtmClubToUser);


            return "redirect:/accueil"; // Redirige vers la page d'accueil
        }
    }
    public Long generateUUID() {
        return java.util.UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}

