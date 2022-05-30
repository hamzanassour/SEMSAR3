package com.example.samsar.Controllers;

import com.example.samsar.entities.AppUser;
import com.example.samsar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserRepository userRepository ;
    @ResponseBody
    @RequestMapping("/all")
    public List<AppUser> users(){

        return (List<AppUser>) userRepository.findAll();
    }
    // when user click sign up the sign up form will be given
    @RequestMapping("/creerCompte")
    public String createAccount(){
        return "SignUp";
    }
    //
    @RequestMapping("/enregistrer")
    public String enregestrercompte(@RequestBody AppUser appUser){
        userRepository.save(appUser);
        return "login";
    }



}
