package org.example.kinomichi.controller;

import org.example.kinomichi.model._User;
import org.example.kinomichi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class AccueilController {
    @Autowired
    private UserService userService;

    @GetMapping("/accueil")
    public void getPeople(Model model) throws IOException {


    }
}


