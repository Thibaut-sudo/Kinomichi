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
public class SignUpController {

    @Autowired
    private UserService userService;



    // Méthode d'inscription - route POST
    @RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public String signUp(@Valid @ModelAttribute _User user,RedirectAttributes redirectAttributes) {

        if (userService.existsByEmail(user.getEmail())) {
            redirectAttributes.addAttribute("error", "Email déjà utilisé.");
            return "redirect:/inscription.html";

        }else {
            user.setId(generateUUID());
            user.setHashedPassword(user.getPassword());



            // Enregistrer l'utilisateur via le service
            userService.saveUser(user);

            // Redirige vers la page d'accueil après inscription
            return "redirect:/";
        }



        }
    //generate a uuid for the user
    public Long generateUUID() {
        return java.util.UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
