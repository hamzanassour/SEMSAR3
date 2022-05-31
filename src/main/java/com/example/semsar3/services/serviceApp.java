package com.example.semsar3.services;



import com.example.semsar3.entities.User;
import com.example.semsar3.entities.Rolle;

import java.util.List;

public interface serviceApp {
    User addAppUser(User appUser);
    Rolle addRolle(Rolle rolle);
    void addRolleToUser(String username , String rolleName );
    User LoadAppUsertByUsername(String username);
    List<User> listUsers();
}
