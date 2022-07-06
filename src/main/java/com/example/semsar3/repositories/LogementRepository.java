package com.example.semsar3.repositories;

import com.example.semsar3.entities.Logement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogementRepository extends JpaRepository<Logement, Long> {

   Page<Logement> findLogementsByVilleAndType(String villeName , String type , Pageable pageable );
   Logement findLogementsById(Long id);
   List<Logement> findLogementById(Long id);

}
