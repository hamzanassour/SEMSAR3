package com.example.semsar3.securite.repositories;


import com.example.semsar3.securite.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Client, Long> {
    Client findClientByUsername(String username);
}
