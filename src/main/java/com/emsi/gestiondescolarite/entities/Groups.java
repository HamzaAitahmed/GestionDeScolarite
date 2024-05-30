package com.emsi.gestiondescolarite.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor @Entity
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    private String nom;

    private String matricule;

    @OneToMany(mappedBy = "groups")
    @JsonManagedReference
    private Collection<Etudiant> etudiants;

    @OneToMany(mappedBy = "groups")
    @JsonManagedReference
    private Collection<Cours> cours;


    @Override
    public String toString() {
        return " Groups { " +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", matricule='" + this.getMatricule() + '\'' + '\n'+
//                "   '----> cours = " + this.getCours() + '\n'+
//                "   '----> etudiants = " + this.getEtudiants() +
                '\n'+ " } ";
    }
}
