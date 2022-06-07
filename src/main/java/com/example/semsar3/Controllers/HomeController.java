package com.example.semsar3.Controllers;


import com.example.semsar3.repositories.TypeRepository;
import com.example.semsar3.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {


    @Autowired
    VilleRepository villeRepository ;
    @Autowired
    TypeRepository typeRepository;

    @RequestMapping("/index")
    public  String index(Model model ,
                         @RequestParam(value = "ville" , defaultValue = "Choisiser") String ville ,
                         @RequestParam(value = "type" , defaultValue = "Choisiser") String type) {
        model.addAttribute("villes" ,villeRepository.findAll() );
        model.addAttribute("types" , typeRepository.findAll() );
        model.addAttribute("ville" , ville);
        model.addAttribute("type" , type);
        return "acceuil";

    }

}
