package com.emsi.gestiondescolarite.dao;

import com.emsi.gestiondescolarite.entities.Etudiant;
import com.emsi.gestiondescolarite.entities.Groups;
import com.emsi.gestiondescolarite.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    Etudiant findByEmail(String email);
    List<Etudiant> findByNom(String name);

    @Query("SELECT u FROM Etudiant u")
    List<Etudiant> findAllEtudiant();

    Etudiant findByPassword(String name);
    Etudiant findById(int id);
    List<Etudiant> findByNomContains(String name);
    Etudiant findEtudiantsById(int id);
    List<Etudiant> findByGroups_Id(int id);
    Page<Etudiant> findByNomContains(String name, PageRequest pageable);
}
