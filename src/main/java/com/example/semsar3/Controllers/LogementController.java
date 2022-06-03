package com.example.semsar3.Controllers;

import com.example.semsar3.entities.Logement;
import com.example.semsar3.repositories.LogementRepository;
import com.example.semsar3.repositories.TypeRepository;
import com.example.semsar3.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LogementController {

    @Autowired
    LogementRepository logementRepository ;

    @Autowired
    VilleRepository villeRepository;
    @Autowired
    TypeRepository typeRepository;

    @RequestMapping("/find")
    public String  findLogement(Model model , String ville , String type){
        model.addAttribute("villes" ,villeRepository.findAll() );
        model.addAttribute("types" , typeRepository.findAll() );
        List<Logement> logements = logementRepository.findLogementsByVilleNomAndTypeTypeLog(ville , type);
        model.addAttribute("logements" , logements);
        return  "resultats";

    }







}
