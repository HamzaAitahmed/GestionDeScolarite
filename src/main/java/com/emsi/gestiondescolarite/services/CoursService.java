package com.emsi.gestiondescolarite.services;

import com.emsi.gestiondescolarite.dao.*;
import com.emsi.gestiondescolarite.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursService {
    @Autowired CoursRepository coursRepository;

    public List<Cours> getCours() { return coursRepository.findAll();}

    public Cours DeleteCours(int id){
        Cours cours = coursRepository.findById(id);
        cours.setGroups(null);
        coursRepository.deleteById(id);
        System.out.println(cours);
        return cours;
    }
}
