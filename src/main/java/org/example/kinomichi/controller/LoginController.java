package org.example.kinomichi.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.kinomichi.model._User;
import org.example.kinomichi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;




    @PostMapping("/index")
    public String handleLogin(@RequestParam String email, RedirectAttributes redirectAttributes) {

        if (userService.authenticate(email)) {

            log.error("Connexion réussie");
            if(userService.isAdmin(email)){
                return "redirect:/accueilAdmin";
            }else
            {
            return "redirect:/accueil";
            }


        } else {
            redirectAttributes.addAttribute("error", "Identifiants invalides.");
            log.error("Identifiants invalides");
            return "redirect:/";

        }

    }
}
