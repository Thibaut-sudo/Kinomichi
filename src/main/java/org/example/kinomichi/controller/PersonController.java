package org.example.kinomichi.controller;

import org.example.kinomichi.model.Person;
import org.example.kinomichi.model._User;
import org.example.kinomichi.repository.BD;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class PersonController {

    @GetMapping("/people")
    public String getPeople(Model model) throws IOException {

        BD repo = new BD();
        try {
            List<_User> user = repo.getAllUsers();
            model.addAttribute("people", user); // Ajoute la liste au modèle
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier 'users.json' est introuvable : " + e.getMessage());
        }





        //model.addAttribute("people", user); // Ajoute la liste au modèle
        return "people"; // Correspond à people.html
    }
}

