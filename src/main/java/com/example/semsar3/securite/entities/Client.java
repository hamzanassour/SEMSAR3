package com.example.semsar3.securite.entities;

import com.example.semsar3.entities.Demande;
import com.example.semsar3.entities.Logement;
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
public class Client {

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
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Demande> demandes;
    @OneToMany(mappedBy = "user")
    private List<Logement> logements;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Rolle> rolles ;

}
