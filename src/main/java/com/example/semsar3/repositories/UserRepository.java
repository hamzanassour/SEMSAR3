package com.example.samsar.repositories;

import com.example.samsar.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser , Long> {
}
