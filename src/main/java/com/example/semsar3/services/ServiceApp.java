package com.example.semsar3.services;



import com.example.semsar3.securite.entities.Client;
import com.example.semsar3.securite.entities.Rolle;

import java.util.List;

public interface ServiceApp {

    Client addAppUser(Client appUser);
    Rolle addRolle(Rolle rolle);
    void addRolleToUser(String username , String rolleName );
    Client loadAppUserByUsername(String username);
    List<Client> listUsers();

}
