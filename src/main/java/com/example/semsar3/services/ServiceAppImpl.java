package com.example.semsar3.services;


import com.example.semsar3.securite.entities.Client;
import com.example.semsar3.securite.entities.Rolle;
import com.example.semsar3.securite.repositories.RolleRepository;
import com.example.semsar3.securite.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ServiceAppImpl implements ServiceApp {
    UserRepository appUserRepository;
    RolleRepository rolleRepository;
    PasswordEncoder passwordEncoder;

    public ServiceAppImpl(PasswordEncoder passwordEncoder, RolleRepository rolleRepository, UserRepository appUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.rolleRepository = rolleRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Client addAppUser(Client appUser) {
        String password=appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(password));
        return   appUserRepository.save(appUser);
    }

    public boolean isRolleAlreadyExist(String rolleName) {
        return rolleRepository.findByNom(rolleName) != null;
    }

    @Override
    public Rolle addRolle(Rolle rolle) {
        if(isRolleAlreadyExist(rolle.getNom())) throw new RuntimeException("Rolle existe deja");
        return rolleRepository.save(rolle);
    }

    @Override
    public void addRolleToUser(String username, String rolleName) {
       Client user = appUserRepository.findClientByUsername(username);
       if(user == null) throw  new RuntimeException("User n existe pas ");

       Rolle rolle = rolleRepository.findByNom(rolleName);
       if(rolle == null) throw  new RuntimeException("Rolle n existe pas");

       user.getRolles().forEach(userRolle -> {
           if (userRolle.getNom().equals(rolleName)) throw  new RuntimeException("user a deja ce role");
       });

       user.getRolles().add(rolle);
    }

    public void deleteRolleFromUser(String username, String rolleName) {
        Client user= appUserRepository.findClientByUsername(username);
        if(user == null) throw  new RuntimeException("User existe deja");

        Rolle rolle = rolleRepository.findByNom(rolleName);
        if(rolle == null) throw  new RuntimeException("Rolle n existe pas");

        user.getRolles().remove(rolle);
    }

    @Override
    public Client loadAppUserByUsername(String username) {
        return appUserRepository.findClientByUsername(username);
    }

    @Override
    public List<Client> listUsers() {
        return appUserRepository.findAll() ;
    }
}
