package com.example.semsar3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom ;
    private String prenom;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String img;
    @OneToMany(mappedBy = "user")
    private List<Demande> demandes;
    @OneToMany(mappedBy = "user")
    private List<Logement> logements;
    @ManyToMany
    private List<Rolle> rolles ;

}
