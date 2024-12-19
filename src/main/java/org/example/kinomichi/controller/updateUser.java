package org.example.kinomichi.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.kinomichi.model._User;
import org.example.kinomichi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class updateUser {

    private final UserService userService;

    public updateUser(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/updateUser")
    public String showUpdateForm(HttpSession session, Model model) {
        // Récupérer l'utilisateur depuis la session
        _User currentUser = (_User) session.getAttribute("currentUser");

        if (currentUser == null) {
            log.warn("Utilisateur non connecté. Redirection vers la page de connexion.");
            return "redirect:/"; // Rediriger vers la page de connexion
        }

        // Ajouter l'utilisateur au modèle pour pré-remplir le formulaire
        model.addAttribute("user", currentUser);
        return "updateUser"; // Affiche le formulaire
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") _User userForm,
                             BindingResult result,
                             HttpSession session,
                             Model model) {
        if (result.hasErrors()) {
            log.error("Erreur de validation du formulaire : {}", result.getAllErrors());
            return "updateUser"; // Retourne au formulaire si erreurs de validation
        }

        // Récupérer l'utilisateur actuel depuis la session
        _User currentUser = (_User) session.getAttribute("currentUser");

        if (currentUser == null) {
            log.warn("Utilisateur non connecté. Redirection vers la page de connexion.");
            return "redirect:/";
        }

        // Mise à jour des informations de l'utilisateur existant
        currentUser.setFirstName(userForm.getFirstName());
        currentUser.setLastName(userForm.getLastName());
        currentUser.setEmail(userForm.getEmail());

        // Mettre à jour le mot de passe uniquement s'il a été modifié
        if (!userForm.getPassword().isEmpty() && !userForm.getPassword().equals(currentUser.getPassword())) {
            userForm.setHashedPassword(userForm.getPassword());
            currentUser.setPassword(userForm.getPassword());
        }

        // Sauvegarder les modifications dans la base de données
        try{
            userService.updateUser(currentUser);
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour de l'utilisateur : {}", e.getMessage());
            model.addAttribute("error", "Erreur lors de la mise à jour de l'utilisateur.");
            return "updateUser"; // Retourne au formulaire avec un message d'erreur
        }


        // Mettre à jour l'utilisateur dans la session

        session.setAttribute("currentUser", currentUser);

        return "redirect:/accueil"; // Retourne au formulaire avec un message de succès
    }
}
