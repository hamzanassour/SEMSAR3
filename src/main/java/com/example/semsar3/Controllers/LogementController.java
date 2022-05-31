package com.example.semsar3.Controllers;

import com.example.semsar3.entities.Logement;
import com.example.semsar3.repositories.LogementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/logement")
public class LogementController {

    @Autowired
    LogementRepository logementRepository ;
    @ResponseBody
    @RequestMapping("/find")
    public List<Logement> findLogement( String ville , String type){

        return  logementRepository.findLogementsByVilleNomAndTypeTypeLog(ville , type );
    }

    @RequestMapping("/add")
    public void addLogement( ) {


    }







}
