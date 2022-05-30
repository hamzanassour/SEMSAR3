package com.example.semsar3.repositories;

import com.example.semsar3.entities.Logement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogementRepository extends JpaRepository<Logement, Long> {
}
