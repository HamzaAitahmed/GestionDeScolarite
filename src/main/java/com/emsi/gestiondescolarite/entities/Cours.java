package com.emsi.gestiondescolarite.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor @Entity
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titre;
    private String description;

    @ManyToOne
    @JsonBackReference
    private Groups groups;

}
