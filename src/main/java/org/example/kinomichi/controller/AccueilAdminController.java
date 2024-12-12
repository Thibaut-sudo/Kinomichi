package org.example.kinomichi.controller;

import org.example.kinomichi.model._User;
import org.example.kinomichi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class AccueilAdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/accueilAdmin")
    public String getPeople(Model model) throws IOException {

        // Récupérer la liste des utilisateurs
        List<_User> user = userService.getAllUsers();
        model.addAttribute("accueil", user); // Ajouter la liste au modèle

        // Ajouter également nbUser au modèle
        int nbUser = user.size(); // ou calculez dynamiquement si nécessaire
        model.addAttribute("nbUser", nbUser); // Ajouter nbUser au modèle

        return "accueilAdmin"; // Retourner la page accueilAdmin.html
    }
}
