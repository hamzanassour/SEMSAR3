package com.example.semsar3.securite.service;

import com.example.semsar3.securite.entities.Client;
import com.example.semsar3.services.ServiceAppImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    ServiceAppImpl serviceApp;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Client client= serviceApp.loadAppUserByUsername(username);




       return new User(
               client.getUsername(),
               client.getPassword(),
               client.getRolles().stream()
                       .map(rolle ->  new  SimpleGrantedAuthority(rolle.getNom())).collect(Collectors.toList())
       );



    }

}
