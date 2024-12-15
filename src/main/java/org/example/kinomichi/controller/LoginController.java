package org.example.kinomichi.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.kinomichi.model._User;
import org.example.kinomichi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/index")
    public String handleLogin(
            @RequestParam String email,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        // Vérifier si l'email est non nul ou vide
        if (email == null || email.trim().isEmpty()) {
            redirectAttributes.addAttribute("error", "L'adresse e-mail est requise.");
            log.warn("Tentative de connexion sans adresse e-mail.");
            return "redirect:/?error=emailRequired";
        }

        // Authentification
        if (userService.authenticate(email)) {
            log.info("Connexion réussie pour l'utilisateur : {}", email);

            // Récupérer l'utilisateur depuis la base de données
            _User user = userService.getUserByEmail(email);

            // Vérifier si l'utilisateur existe
            if (user == null) {
                redirectAttributes.addAttribute("error", "Utilisateur introuvable.");
                log.error("Aucun utilisateur trouvé avec l'e-mail : {}", email);
                return "redirect:/?error=userNotFound";
            }

            // Ajouter l'utilisateur à la session
            session.setAttribute("currentUser", user);
            log.info("Utilisateur {} ajouté à la session.", user.getFirstName());

            // Redirection en fonction du rôle
            if (userService.isAdmin(email)) {
                return "redirect:/accueilAdmin";
            } else {
                return "redirect:/accueil";
            }
        } else {
            redirectAttributes.addAttribute("error", "Identifiants invalides.");
            log.error("Tentative de connexion avec des identifiants invalides pour : {}", email);
            return "redirect:/?error=invalid Credentials";
        }
    }
}
