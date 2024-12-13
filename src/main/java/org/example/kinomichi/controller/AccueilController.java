package org.example.kinomichi.controller;

import org.example.kinomichi.model._Club;
import org.example.kinomichi.model._User;
import org.example.kinomichi.service.ClubService;
import org.example.kinomichi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class AccueilController {


    @Autowired
    private ClubService clubService;

    @GetMapping("/accueil")
    public String getClub(Model model) throws IOException {

        // Récupérer la liste des utilisateurs
        List<_Club> club = clubService.getAllClub();
        model.addAttribute("accueil", club); // Ajouter la liste au modèle

        // Ajouter également nbUser au modèle
        int nbClub = club.size(); // ou calculez dynamiquement si nécessaire
        model.addAttribute("nbClub", nbClub); // Ajouter nbUser au modèle

        return "accueil"; // Retourner la page accueilAdmin.html
    }
}


