package com.example.semsar3;

import com.example.semsar3.entities.User;
import com.example.semsar3.entities.Rolle;
import com.example.semsar3.repositories.LogementRepository;
import com.example.semsar3.services.ServiceAppImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class Semsar3Application {

     @Autowired
     LogementRepository logementRepository;

    public static void main(String[] args) {

        SpringApplication.run(Semsar3Application.class, args);

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }



}
