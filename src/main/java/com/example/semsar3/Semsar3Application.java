package com.example.semsar3;

import com.example.semsar3.Controllers.LogementController;
import com.example.semsar3.Controllers.UserController;
import com.example.semsar3.repositories.LogementRepository;
import com.example.semsar3.securite.entities.Rolle;
import com.example.semsar3.securite.entities.User;
import com.example.semsar3.services.ServiceAppImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;

@SpringBootApplication
public class Semsar3Application  implements CommandLineRunner{

     @Autowired
     LogementRepository logementRepository;
     @Autowired
     ServiceAppImpl serviceApp;


    public static void main(String[] args) {


        new File(UserController.uploadDirUserProfiles).mkdir();
        new File(LogementController.uploadDirLogementImages).mkdir();
        SpringApplication.run(Semsar3Application.class, args);

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    @Override
    public void run(String... args) throws Exception {



    }



}
