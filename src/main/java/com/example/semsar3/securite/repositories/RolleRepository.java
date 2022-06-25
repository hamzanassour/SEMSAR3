package com.example.semsar3.securite.repositories;


import com.example.semsar3.securite.entities.Rolle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolleRepository extends JpaRepository<Rolle, Long> {
    Rolle findByNom(String rolleName);
}
