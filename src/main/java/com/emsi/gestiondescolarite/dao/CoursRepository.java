package com.emsi.gestiondescolarite.dao;

import com.emsi.gestiondescolarite.entities.Cours;
import com.emsi.gestiondescolarite.entities.Etudiant;
import jakarta.transaction.Transactional;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface CoursRepository extends JpaRepository<Cours, Integer> {
    List<Cours> findByTitre(String name);

    List<Cours> findByTitreContains(String name);
    List<Cours> findCoursById(int id);

    Cours findById(int id);

    List<Cours> findByGroups_Id(int id);

    Page<Cours> findByTitreContains(String name, PageRequest pageable);

}
