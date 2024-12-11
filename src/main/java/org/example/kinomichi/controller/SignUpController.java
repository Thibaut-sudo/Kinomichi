package org.example.kinomichi.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.kinomichi.model._User;


import org.example.kinomichi.repository.BD;
import org.example.kinomichi.repository.Repo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class SignUpController {
    BD repo = new BD();

        @RequestMapping(value = "/inscription", method = RequestMethod.POST)
        public String signUp(@Valid @ModelAttribute _User user) {
            log.info("User: " + user);
            user.setId();
            repo.insertUser(user);
            return "redirect:/people";
        }





}
