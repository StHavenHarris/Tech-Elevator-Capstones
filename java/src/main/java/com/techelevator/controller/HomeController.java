package com.techelevator.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {

    @RequestMapping(path="/", method= RequestMethod.GET)
    public String showHomePage() {
        return "index";
    }



}
