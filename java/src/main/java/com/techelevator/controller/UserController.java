package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private UserDao userDao;


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public User get(@PathVariable int id){
        return userDao.getUserById((long) id);
    }

    @GetMapping("/allusers")
    public List<User> getallUsers(){
        return userDao.findAll();
    }



}
