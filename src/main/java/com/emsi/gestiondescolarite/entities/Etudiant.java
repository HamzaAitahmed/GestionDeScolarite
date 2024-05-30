package com.emsi.gestiondescolarite.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.context.annotation.Lazy;

import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor @Entity
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    private String email;

    private String nom,matricule,password;

    @ManyToOne
    @JsonBackReference
    private Groups groups;

    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nom='" + nom + '\'' +
                ", matricule='" + matricule + '\'' +
                ", password='" + password + '\'' +
                ", groups = " + groups +
                '}';
    }
}
