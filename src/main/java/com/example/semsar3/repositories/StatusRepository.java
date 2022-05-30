package com.example.semsar3.repositories;


import com.example.semsar3.entities.StatusLogement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusLogement,Long> {
}
