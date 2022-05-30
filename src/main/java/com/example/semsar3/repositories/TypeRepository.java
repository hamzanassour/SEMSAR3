package com.example.semsar3.repositories;


import com.example.semsar3.entities.TypeLogement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<TypeLogement, Long> {
}
