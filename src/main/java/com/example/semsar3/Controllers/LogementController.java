package com.example.semsar3.Controllers;

import com.example.semsar3.entities.Demande;
import com.example.semsar3.entities.Logement;
import com.example.semsar3.entities.Media;
import com.example.semsar3.repositories.DemandeRepository;
import com.example.semsar3.repositories.LogementRepository;

import com.example.semsar3.securite.entities.Client;
import com.example.semsar3.securite.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
public class LogementController {

    @Autowired
    LogementRepository logementRepository ;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DemandeRepository demandeRepository;

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
    model.addAttribute("logements" , logement);
        return "details-resultat";
    }



    @RequestMapping("/addLogementForm")
    public String logementForm( Logement logement , Authentication authentication , Model model){
        model.addAttribute("logement");
        Client client= userRepository.findClientByUsername(authentication.getName());
        model.addAttribute("client" , client);
        return "ajouter-logement";
    }
    @RequestMapping("/ajouterLogement")
    public String save( Logement logement , @RequestParam("images") MultipartFile[] images,
                       @RequestParam("imageP") MultipartFile imageP , Authentication authentication , Model model) throws IOException {

         Client client=  userRepository.findClientByUsername( authentication.getName());
         model.addAttribute("client" ,client );
        Logement savedLogement = logementRepository.save(logement);
        savedLogement.setUser(client);
        List<Media> medias = new ArrayList<>();
        String name =savedLogement.getId()+"p"+StringUtils.cleanPath(imageP.getOriginalFilename());
        Path filenameAndPath = Paths.get(uploadDirLogementImages, name);
        Files.write(filenameAndPath , imageP.getBytes());
        name="LogementImages/"+name;
        medias.add(new Media(null, name));

        for (MultipartFile image : images){
            String name1 =savedLogement.getId()+ StringUtils.cleanPath(image.getOriginalFilename());
            Path filenameAndPath1 = Paths.get(uploadDirLogementImages,name1);
            Files.write(filenameAndPath1 , image.getBytes());
            name1="LogementImages/"+name1;
            medias.add(new Media(null , name1));
        }

        savedLogement.setMedias(medias);
        return "redirect:/mes-annonces";
    }
    @RequestMapping("/updateLogement")
    public String update( Model model , Logement logement , @RequestParam("images") MultipartFile[] images,
                        @RequestParam("imageP") MultipartFile imageP , Authentication authentication ) throws IOException {


        List<Media> list = logementRepository.findLogementsById(logement.getId()).getMedias();
        Client client=  userRepository.findClientByUsername( authentication.getName());
        model.addAttribute("client" ,client );
        Logement savedLogement = logementRepository.save(logement);
        savedLogement.setUser(client);
        List<Media> medias = new ArrayList<>();
        if (imageP.isEmpty()){
            medias.add(list.get(0));
        }
        else {
            String name =savedLogement.getId()+"p"+StringUtils.cleanPath(imageP.getOriginalFilename());
            Path filenameAndPath = Paths.get(uploadDirLogementImages, name);
            Files.write(filenameAndPath , imageP.getBytes());
            name="LogementImages/"+name;
            medias.add(new Media(null, name));
        }

        for (MultipartFile image : images){
            String name1 =savedLogement.getId()+ StringUtils.cleanPath(image.getOriginalFilename());
            Path filenameAndPath1 = Paths.get(uploadDirLogementImages,name1);
            Files.write(filenameAndPath1 , image.getBytes());
            name1="LogementImages/"+name1;
            medias.add(new Media(null , name1));
        }
        savedLogement.setMedias(medias);
        return "redirect:/mes-annonces";
    }


    @RequestMapping("/deleteLogement")
    public  String delete(@RequestParam Long id ){
        logementRepository.deleteById(id);
        // apres la suppression il faut redirect l'utilisateur ves le meme page pour voir le changement
        return "redirect:/mes-annonces";
    }
    @RequestMapping("/updateLogementForm")
    public  String update(Model model , Long id  ,Authentication authentication  ){
        Client client = userRepository.findClientByUsername(authentication.getName());
        model.addAttribute("client" , client);
        Logement logement = logementRepository.findLogementsById(id);
        model.addAttribute("logement" , logement);
        // apres la suppression il faut redirect l'utilisateur ves le meme page pour voir le changement
        return "update-logement";
    }


    @RequestMapping("/mes-annonces")
    public  String chercherMesAnnonces(Authentication authentication , Model model){
        Client client = userRepository.findClientByUsername(authentication.getName());
        List<Logement > logements = new ArrayList<>();
        logements = client.getLogements();
        model.addAttribute("logements" , logements);
        model.addAttribute("client" , client);
        return "mes-annonces";
    }
    @RequestMapping("/reserver")
    public  String reserverLogement(Authentication authentication , Model model , Long id ){
        Client client = userRepository.findClientByUsername(authentication.getName());
        Logement logement = logementRepository.findLogementsById(id);
        Demande demande = new Demande(null ,new Date() , 0.0 , logement , client);
        client.getDemandes().add(demande);
        logement.getDemandes().add(demande);
        return "redirect:/index";
    }
    @RequestMapping("/demandes")
    public  String demandes(Long id , Model model  , Authentication authentication){
        Client client = userRepository.findClientByUsername(authentication.getName());
        Logement logement= logementRepository.findLogementsById(id);
        List<Demande> demandes =logement.getDemandes();
        model.addAttribute("demandes" , demandes);
        model.addAttribute("client" , client);
        model.addAttribute("logement" , logement);
        return "Annonce-demandes";
    }
}
















