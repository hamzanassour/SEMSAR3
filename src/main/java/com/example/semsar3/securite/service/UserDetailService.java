package com.example.semsar3.securite.service;

import com.example.semsar3.securite.MyUserDetails;
import com.example.semsar3.securite.entities.User;
import com.example.semsar3.services.ServiceAppImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    ServiceAppImpl serviceApp;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user= serviceApp.LoadAppUsertByUsername(username);
        return new MyUserDetails(user);
    }

}
