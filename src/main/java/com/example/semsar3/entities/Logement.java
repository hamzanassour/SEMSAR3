package com.example.semsar3.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
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
    private String titre;
    private int superficie;
    private double prix;
    private String description;
    private String adress;
    private int nbr_de_coloc;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Media> medias ;
    @OneToMany(mappedBy = "logement")
    private List<Demande> demandes;
    @ManyToOne
    private TypeLogement type;
    @ManyToOne
    private StatusLogement status;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Ville ville;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

}
