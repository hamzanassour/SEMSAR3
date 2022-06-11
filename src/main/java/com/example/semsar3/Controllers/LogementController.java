package com.example.semsar3.Controllers;

import com.example.semsar3.entities.Logement;
import com.example.semsar3.entities.Media;
import com.example.semsar3.repositories.LogementRepository;
import com.example.semsar3.repositories.TypeRepository;
import com.example.semsar3.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Autowired
    VilleRepository villeRepository;
    @Autowired
    TypeRepository typeRepository;

    public  static  String uploadDirLogementImages = System.getProperty("user.dir")+"\\target\\classes\\static\\LogementImages";

    @RequestMapping("/find")

    public String  findLogement(Model model  , @RequestParam(value = "page"  , defaultValue = "0" )  int page ,
                                @RequestParam(value = "size" ,defaultValue = "6") int size ,
                                @RequestParam(value = "ville" , defaultValue = "Choisiser") String ville ,
                                @RequestParam(value = "type" , defaultValue = "Choisiser") String type){

        // test coming ville , type variables
        //System.out.println(ville);
        //System.out.println(type);

        model.addAttribute("villes" ,villeRepository.findAll() );
        model.addAttribute("types" , typeRepository.findAll() );
        Page<Logement> logements = logementRepository.findLogementsByVilleNomAndTypeTypeLog(ville , type , PageRequest.of(page,size));
        model.addAttribute("logements" , logements);
        model.addAttribute("ville" , ville);
        model.addAttribute("type" , type);
        model.addAttribute("pages" , new int[logements.getTotalPages()]);
        model.addAttribute("pageCourant" , page);
        return  "resultats";


    }


    @RequestMapping(value = "/detail")
    public String detail(){
        return "result-details";
    }



    @RequestMapping("/addLogementForm")
    public String logementForm(@ModelAttribute("Logement") Logement logement){

        return "addLogement";
    }
    @RequestMapping("/ajouterLogement")
    public String save(@ModelAttribute("logement") Logement logement , @RequestParam("images") MultipartFile[] images,
                       @RequestParam("imageP") MultipartFile imageP) throws IOException {

        List<Media> medias = new ArrayList<>();
        String name = StringUtils.cleanPath(imageP.getOriginalFilename());
        Path filenameAndPath = Paths.get(uploadDirLogementImages, name);
        Files.write(filenameAndPath , imageP.getBytes());
        name="LogementImages/"+name;
        medias.add(new Media(null , name));

        for (MultipartFile image : images){
            String name1 = StringUtils.cleanPath(image.getOriginalFilename());
            Path filenameAndPath1 = Paths.get(uploadDirLogementImages,name1);
            Files.write(filenameAndPath1 , image.getBytes());
            name1="LogementImages/"+name1;
            medias.add(new Media(null , name1));
        }
        logement.setMedias(medias);
        logementRepository.save(logement);
        return "test2";
    }





}
