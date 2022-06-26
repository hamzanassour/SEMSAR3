package com.example.semsar3.Controllers;

import com.example.semsar3.securite.entities.Client;
import com.example.semsar3.securite.repositories.UserRepository;
import com.example.semsar3.services.ServiceAppImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UserController {


    @Autowired
    UserRepository userRepository ;
    @Autowired
    ServiceAppImpl serviceApp;


    public static  String uploadDirUserProfiles = System.getProperty("user.dir")+"\\target\\classes\\static\\userProfiles";

    @RequestMapping("/signup")
    public String createAccount(@ModelAttribute("user") Client user)
    {
        return "s_inscrire";
    }

    @RequestMapping("/ajouterUser")
    public String save(@ModelAttribute("user") Client user , @RequestParam("pdp") MultipartFile multipartFile) throws IOException {
        String name =StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Path filenameAndPath = Paths.get(uploadDirUserProfiles,name);
        Files.write(filenameAndPath , multipartFile.getBytes());
        name="userProfiles/"+name;
        user.setImg(name);
        serviceApp.addAppUser(user);
        return "test";
    }
    @RequestMapping("/login")
    public String login()
    {
        return "se_connecter";
    }



}
