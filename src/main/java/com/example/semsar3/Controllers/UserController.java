package com.example.semsar3.Controllers;

import com.example.semsar3.entities.AppUser;
import com.example.semsar3.repositories.UserRepository;
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

        return userRepository.findAll();
    }
    // when user click sign up the sign up form will be given
    @RequestMapping("/creerCompte")
    public String createAccount()
    {
        return "SignUp";
    }
    //
    @RequestMapping("/enregistrer")
    public String enregestrercompte(@RequestBody AppUser appUser){
        userRepository.save(appUser);
        return "login";
    }



}
