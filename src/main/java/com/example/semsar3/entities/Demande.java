package com.example.semsar3.entities;


import com.example.semsar3.securite.entities.Client;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date_de_demande;
    private double proposition_de_prix;
    @ManyToOne
    Logement logement;
    @ManyToOne
    private Client user ;
}
