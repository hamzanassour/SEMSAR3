package com.example.samsar.repositories;

import com.example.samsar.entities.Logement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogementRepository extends JpaRepository<Logement , Long> {
}
