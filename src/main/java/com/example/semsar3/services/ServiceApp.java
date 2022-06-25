package com.example.semsar3.services;



import com.example.semsar3.securite.entities.User;
import com.example.semsar3.securite.entities.Rolle;

import java.util.List;

public interface ServiceApp {

    User addAppUser(User appUser);
    Rolle addRolle(Rolle rolle);
    void addRolleToUser(String username , String rolleName );
    User LoadAppUsertByUsername(String username);
    List<User> listUsers();

}
