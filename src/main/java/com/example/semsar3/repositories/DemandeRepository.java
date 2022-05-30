package com.example.samsar.repositories;

import com.example.samsar.entities.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository extends JpaRepository<Demande , Long> {
}
