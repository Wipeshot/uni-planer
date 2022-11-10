package de.digitra.uniplaner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//Start request management
@Controller
@RequestMapping("/")
public class StartController {

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/dashboard")
    public String user()    {
        return "user-list.html";
    }

}
