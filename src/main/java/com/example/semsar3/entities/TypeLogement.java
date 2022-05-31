package com.example.semsar3.entities;

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
public class TypeLogement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeLog;
    @OneToMany(mappedBy = "type")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Logement> logements;
}
