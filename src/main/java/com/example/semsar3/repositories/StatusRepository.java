package com.example.samsar.repositories;

import com.example.samsar.entities.StatusLogement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusLogement ,Long> {
}
