package com.example.semsar3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
    //@NotEmpty
    //@Size(min = 3 , max = 10)
    private String nom ;
    //@NotEmpty
    //@Size(min = 3 , max = 10)
    private String prenom;
    //@NotEmpty
    //@Size(min = 10 )
    private String email;
    //@NotEmpty
    //@Size(min = 10 )
    private String phone;
    //@NotEmpty
    private String username;
    //@NotEmpty
    private String password;
    private String img;
    @OneToMany(mappedBy = "user")
    private List<Demande> demandes;
    @OneToMany(mappedBy = "user")
    private List<Logement> logements;
    @ManyToMany
    private List<Rolle> rolles ;

}
