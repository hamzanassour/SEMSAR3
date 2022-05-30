package com.example.samsar.repositories;

import com.example.samsar.entities.TypeLogement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<TypeLogement , Long> {
}
