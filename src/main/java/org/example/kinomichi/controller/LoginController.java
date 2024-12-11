package org.example.kinomichi.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.kinomichi.model._User;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@Slf4j
public class LoginController {

    @RequestMapping(path = "/index", method = RequestMethod.POST)
    public String indexFormSub(@Valid @ModelAttribute  _User user) {
        log.info("Received values: {}", user);
        return "redirect:succes.html";
    }
}
