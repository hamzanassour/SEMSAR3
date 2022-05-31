package com.example.semsar3.services;


import com.example.semsar3.entities.User;
import com.example.semsar3.entities.Rolle;
import com.example.semsar3.repositories.RolleRepository;
import com.example.semsar3.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ServiceAppImpl implements serviceApp {

    UserRepository appUserRepository;
    RolleRepository rolleRepository;
    PasswordEncoder passwordEncoder;


    public ServiceAppImpl(PasswordEncoder passwordEncoder , RolleRepository rolleRepository , UserRepository appUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.rolleRepository=rolleRepository;
        this.appUserRepository=appUserRepository;
    }

    @Override
    public User addAppUser(User appUser) {

        String password=appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(password));
        return   appUserRepository.save(appUser);


    }

    @Override
    public Rolle addRolle(Rolle rolle) {

        return rolleRepository.save(rolle);
    }

    @Override
    public void addRolleToUser(String username, String rolleName) {
       User user= appUserRepository.findUserByUsername(username);
       Rolle rolle = rolleRepository.findByNom(rolleName);
       user.getRolles().add(rolle);

    }

    @Override
    public User LoadAppUsertByUsername(String username) {

        return appUserRepository.findUserByUsername(username);
    }

    @Override
    public List<User> listUsers() {

        return appUserRepository.findAll() ;
    }
}
