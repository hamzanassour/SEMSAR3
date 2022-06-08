package com.example.semsar3.Controllers;

import com.example.semsar3.entities.User;
import com.example.semsar3.repositories.UserRepository;
import com.example.semsar3.services.ServiceAppImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    UserRepository userRepository ;
    @Autowired
    ServiceAppImpl serviceApp;

    @RequestMapping("/signup")
    public String createAccount(@ModelAttribute("user") User user)
    {
        return "s_inscrire";
    }

    @RequestMapping("/ajouterUser")
    public String save(User user){
        serviceApp.addAppUser(user);
        return "redirect:login";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "se_connecter";
    }



}
