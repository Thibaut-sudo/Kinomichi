package org.example.kinomichi.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.kinomichi.model._User;
import org.example.kinomichi.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@Slf4j
public class SignUpController {

    @Autowired
    ProductRepo repo ;


    @RequestMapping(path = "/inscription", method = RequestMethod.POST)
    public String SigneUpFormSub(@Valid @ModelAttribute  _User user) {
        log.info("Received values: {}", user);
        saveUser(user);
        return "redirect:succes.html";
    }
    public void saveUser(_User user){
        try {
            repo.save(user);
        }catch (Exception e){
            log.error("Error while saving user: {}", e.getMessage());
        }
    }
}
