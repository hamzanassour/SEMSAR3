package com.example.semsar3.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping("/index")
    public  String index() {
        return "acceuil";
    }
    @RequestMapping("/")
    public  String home() {
        return "espaceUser";
    }

}
