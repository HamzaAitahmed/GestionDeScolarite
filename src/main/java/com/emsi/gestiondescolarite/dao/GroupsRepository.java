package com.emsi.gestiondescolarite.dao;

import com.emsi.gestiondescolarite.entities.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Transactional
public interface GroupsRepository extends JpaRepository<Groups, Integer> {
    List<Groups> findByNom(String name);

    Groups findById(int id);

    List<Groups> findByNomContains(String name);
    Page<Groups> findByNomContains(String name, PageRequest pageable);
    Page<Cours> findByCoursContains(String name, PageRequest pageable);
    Page<Etudiant> findByEtudiantsContains(String name, PageRequest pageable);
}
