package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserAccountController {

    private UserDao userDao;


    @RequestMapping(path="/user", method= RequestMethod.GET)
    public String showLoginPage() {
        return "redirect:/login";
   }

    @RequestMapping(path="/login", method=RequestMethod.GET)
    public String goToLoginPage() {
        return "login";
    }

   // @RequestMapping(path="/users/{userName}", method=RequestMethod.GET)
   //public String displayDashboard(Map<String, Object> model, @PathVariable String userName) {
     //   return "userDashboard";
    //}
}


