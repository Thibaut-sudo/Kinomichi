package org.example.kinomichi.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.kinomichi.model._Club;
import org.example.kinomichi.service.ClubService;
import org.example.kinomichi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Slf4j
public class addClub {


    @Autowired
    private ClubService clubService;
    @GetMapping("/addClub")
    public String showAddClubForm() {
        return "addClub"; // Renvoie une vue (fichier HTML ou Thymeleaf)
    }

    @RequestMapping(value = "/addClub", method = RequestMethod.POST)
    public String addClubInDb(@ModelAttribute _Club club, RedirectAttributes redirectAttributes) {
        if (clubService.existsByName(club.getName())) {
            redirectAttributes.addAttribute("error", "Club name already exists.");
            return "redirect:/addClub";
        }else {


            clubService.saveClub(club);
            return "redirect:/accueil"; // Redirige vers la page d'accueil
        }


    }
}
