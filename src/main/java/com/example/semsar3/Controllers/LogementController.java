package com.example.semsar3.Controllers;

import com.example.semsar3.entities.Logement;
import com.example.semsar3.entities.Media;
import com.example.semsar3.repositories.LogementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LogementController {

    @Autowired
    LogementRepository logementRepository ;

    public  static  String uploadDirLogementImages = System.getProperty("user.dir")+"\\target\\classes\\static\\LogementImages";

    @RequestMapping("/find")

    public String  findLogement(Model model  , @RequestParam(value = "page"  , defaultValue = "0" )  int page ,
                                @RequestParam(value = "size" ,defaultValue = "6") int size ,
                                @RequestParam(value = "ville" , defaultValue = "Choisiser") String ville ,
                                @RequestParam(value = "type" , defaultValue = "Choisiser") String type){

        // test coming ville , type variables
        //System.out.println(ville);
        //System.out.println(type);
        Page<Logement> logements = logementRepository.findLogementsByVilleAndType(ville , type , PageRequest.of(page,size));
        model.addAttribute("logements" , logements);
        model.addAttribute("ville" , ville);
        model.addAttribute("type" , type);
        model.addAttribute("pages" , new int[logements.getTotalPages()]);
        model.addAttribute("pageCourant" , page);
        return  "resultats";
    }


    @RequestMapping(value = "/detail")
    public String detail(@RequestParam Long id  , Model model ){

    Logement logement= logementRepository.findLogementsById(id);
    model.addAttribute("logement" , logement);

        return "result-details";
    }



    @RequestMapping("/addLogementForm")
    public String logementForm(@ModelAttribute("Logement") Logement logement){
        return "ajouter-logement";
    }

    @RequestMapping("/ajouterLogement")
    public String save(@ModelAttribute("logement") Logement logement , @RequestParam("images") MultipartFile[] images,
                       @RequestParam("imageP") MultipartFile imageP) throws IOException {
        Logement savedLogement = logementRepository.save(logement);
        List<Media> medias = new ArrayList<>();
        String name =savedLogement.getId()+"p"+StringUtils.cleanPath(imageP.getOriginalFilename());
        Path filenameAndPath = Paths.get(uploadDirLogementImages, name);
        Files.write(filenameAndPath , imageP.getBytes());
        name="LogementImages/"+name;
        medias.add(new Media(null , name));
        for (MultipartFile image : images){
            String name1 =savedLogement.getId()+ StringUtils.cleanPath(image.getOriginalFilename());
            Path filenameAndPath1 = Paths.get(uploadDirLogementImages,name1);
            Files.write(filenameAndPath1 , image.getBytes());
            name1="LogementImages/"+name1;
            medias.add(new Media(null , name1));
        }
        logement.setMedias(medias);
        logementRepository.save(savedLogement);
        return "ajouter-logement";
    }


    @RequestMapping("/deleteLogement")
    public  String delete(@RequestParam Long id ){
        logementRepository.deleteById(id);
        // apres la suppression il faut redirect l'utilisateur ves le meme page pour voir le changement
        return "redirect:/find";
    }
    @RequestMapping("/updateLogementForm")
    public  String update(Model model , Long id ){
        model.addAttribute("logement" ,logementRepository.findById(id) );
        // apres la suppression il faut redirect l'utilisateur ves le meme page pour voir le changement
        return "updateLogementForm";
    }








}
