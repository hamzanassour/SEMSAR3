package com.example.semsar3.entities;


import com.example.semsar3.securite.entities.Client;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Logement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre ;
    private int superficie;
    private double prix;
    private String description;
    private String adress;
    private int nbr_de_coloc;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Media> medias;
    @OneToMany(mappedBy = "logement"  , cascade = CascadeType.ALL)
    private List<Demande> demandes;
    private String  type;
    private String status;
    private String ville;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Client user;

}
