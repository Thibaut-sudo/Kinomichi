package org.example.kinomichi.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.kinomichi.model.MTMEventToUser;
import org.example.kinomichi.model._Club;
import org.example.kinomichi.model._Event;
import org.example.kinomichi.model._User;
import org.example.kinomichi.service.ClubService;
import org.example.kinomichi.service.EventService;
import org.example.kinomichi.service.UserToEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
public class addEvent {




    @Autowired
    private EventService eventService;

    @Autowired
    private ClubService clubService;
    @Autowired
    private UserToEventService userToEventService;

    @GetMapping("/addEvent")
    public String showAddEventForm(Model model) throws IOException {

        // Récupérer la liste des clubs
        List<_Club> clubs = clubService.getAllClub();
        model.addAttribute("clubs", clubs); // Ajouter la liste au modèle

        // Ajouter également nbClub au modèle
        int nbClub = clubs.size();
        model.addAttribute("nbClub", nbClub); // Ajouter nbClub au modèle

        return "addEvent"; // Renvoie la vue "addEvent"
    }

    @PostMapping("/addEvent")
    public String addEvent(@ModelAttribute @Valid _Event event,
                           @RequestParam Long id,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           HttpSession session) {
        _User currentUser = (_User) session.getAttribute("currentUser");
        if (bindingResult.hasErrors()) {
            // Ajoutez un message d'erreur si la validation échoue
            redirectAttributes.addAttribute("error", "Please fill out all required fields.");
            return "redirect:/addEvent";
        }

        // Vérifier si l'événement existe déjà
        if (eventService.existsByName(event.getName())) {
            redirectAttributes.addAttribute("error", "Event name already exists.");
            return "redirect:/addEvent"; // Rediriger si l'événement existe déjà
        }

        // Récupérer le club à partir de l'ID
         _Club selectedClub = clubService.getClubById(id);
        if (selectedClub == null) {
            // Si le club n'existe pas, retourner une erreur
            redirectAttributes.addAttribute("error", "Selected club not found.");
            return "redirect:/addEvent";
        }

        // Associer le club à l'événement
        event.setClub(selectedClub);

        // Sauvegarder l'événement
        event.setId(generateUUID());
        eventService.saveEvent(event);
        MTMEventToUser mtmEventToTUser = new MTMEventToUser(generateUUID(),event.getId(), currentUser.getId());
        userToEventService.saveUserToEvent(mtmEventToTUser);


        return "redirect:/accueil"; // Rediriger vers la page d'accueil après la création
    }
    public Long generateUUID() {
        return java.util.UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
    }
