package com.emsi.gestiondescolarite.services;

import com.emsi.gestiondescolarite.dao.EtudiantRepository;
import com.emsi.gestiondescolarite.entities.Etudiant;
import com.emsi.gestiondescolarite.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class EtudiantService {
    @Autowired EtudiantRepository etudiantRepository;

    public List<Etudiant> getEtudiant() { return etudiantRepository.findAll();}

    public Etudiant DeleteEtudiant(int id){
        Etudiant etudiant = etudiantRepository.findEtudiantsById(id);
        etudiant.setGroups(null);
        etudiantRepository.deleteById(id);
        return etudiant;
    }
}
