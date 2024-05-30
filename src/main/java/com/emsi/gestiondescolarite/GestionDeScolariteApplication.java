package com.emsi.gestiondescolarite;

import com.emsi.gestiondescolarite.entities.*;
import com.emsi.gestiondescolarite.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;


@SpringBootApplication
public class GestionDeScolariteApplication implements CommandLineRunner {
//    @Autowired EtudiantRepository etudiantRepository;
//    @Autowired GroupsRepository groupsRepository;
//    @Autowired CoursRepository coursRepository;

    public static void main(String[] args) {
        SpringApplication.run(GestionDeScolariteApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {

//        List<Etudiant> etdName = etudiantRepository.findByNomContains("test");
//        for(Etudiant x : etdName)
//            etudiantRepository.deleteById(x.getId());

//        Groups G1 = groupsRepository.save(new Groups(null, "G1", "G1-OC", null , null ) );
//        Groups G2 = groupsRepository.save(new Groups(null, "G2", "G2-OC" , null , null ) );
//        Groups G3 = groupsRepository.save(new Groups(null, "G3", "G3-OC", null , null ) );
//        Groups G4 = groupsRepository.save(new Groups(null, "G4", "G4-OC" , null , null ) );
//        Groups G5 = groupsRepository.save(new Groups(null, "G5", "G5-OC", null , null ) );
//        Groups G6 = groupsRepository.save(new Groups(null, "G6", "G6-OC" , null , null ) );
//        Groups G7 = groupsRepository.save(new Groups(null, "G7", "G7-OC", null , null ) );
//        Groups G8 = groupsRepository.save(new Groups(null, "G8", "G8-OC" , null , null ) );
//        Groups G9 = groupsRepository.save(new Groups(null, "G1", "G9-OC", null , null ) );
//        Groups G10 = groupsRepository.save(new Groups(null, "G2", "G10-OC" , null , null ) );
//        Groups G11 = groupsRepository.save(new Groups(null, "G3", "G11-OC", null , null ) );
//        Groups G12 = groupsRepository.save(new Groups(null, "G4", "G12-OC" , null , null ) );
//        Groups G13 = groupsRepository.save(new Groups(null, "G5", "G13-OC", null , null ) );
//        Groups G14 = groupsRepository.save(new Groups(null, "G6", "G14-OC" , null , null ) );
//        Groups G15 = groupsRepository.save(new Groups(null, "G7", "G15-OC" , null , null ) );
//        Groups G16 = groupsRepository.save(new Groups(null, "G8", "G16-OC", null , null ) );

//        Cours C1 = coursRepository.save(new Cours(null, "Titre1", "Description1" , null ) );
//        Cours C2 = coursRepository.save(new Cours(null, "Titre2", "Description2" , null  ) );
//        Cours C3 = coursRepository.save(new Cours(null, "Titre3", "Description3" , null ) );
//        Cours C4 = coursRepository.save(new Cours(null, "Titre4", "Description4" , null ) );

//
//
//
//        Etudiant E1 = etudiantRepository.save(new Etudiant(null, "Mouhcine", "data","mouhcine_password"  , G1)  );
//        Etudiant E2 = etudiantRepository.save(new Etudiant(null, "hamza", "aitahmed" ,"hamza_password", G1) );
//        Etudiant E3 = etudiantRepository.save(new Etudiant(null, "Imad", "xxx" ,"Imad_password", G1) );
//        Etudiant E4 = etudiantRepository.save(new Etudiant(null, "salah", "ramadan" ,"salah_password", G1 ) );
//        Etudiant E6 = etudiantRepository.save(new Etudiant(null, "sunkay", "cristiano" , "sunkay_password", G2) );
//        Etudiant E7 = etudiantRepository.save(new Etudiant(null, "ayoub", "messi" ,"ayoub_password", G2) );
//        Etudiant E8 = etudiantRepository.save(new Etudiant(null, "roudani", "haise", "roudani_password", G2) );
//        Etudiant E9 = etudiantRepository.save(new Etudiant(null, "test", "haise", "test", null) );

//        Cours C5 = coursRepository.save(new Cours(null, "Titre5", "Description5" , G1 ) );
//        Cours C6 = coursRepository.save(new Cours(null, "Titre6", "Description6" , G1  ) );
//        Cours C7 = coursRepository.save(new Cours(null, "Titre7", "Description7" , G2 ) );
//        Cours C8 = coursRepository.save(new Cours(null, "Titre8", "Description8" , G2 ) );

//        Etudiant et = etudiantRepository.findById(1).orElse(null);
//        System.out.println("To String Function : "+et); // j'ai mis  seances Collection en commentaire
//        et.afficher();

//        groupRepository.save(new Group(null, "G7", "G7-OC"  ) );
//        groupRepository.save(new Group(null, "G8", "G8-OC"  ) );
//        groupRepository.save(new Group(null, "G9", "G9-OC"  ) );

//        List<Groups> grp = groupsRepository.findAll();
//        for(Groups x : grp)
//        {
//            x.setCours(null);
//            x.setEtudiants(null);
//            groupsRepository.deleteById(x.getId() );
//        }
//
//        Etudiant etdName = etudiantRepository.findByNom("test99");
//        etudiantRepository.deleteById(etdName.getId());
//
//        List<Etudiant> etd = etudiantRepository.findAll();
//        for(Etudiant x : etd)
//        {
//            x.setGroups(null);
//            etudiantRepository.deleteById(x.getId() );
//        }

//        List<Cours> crs = coursRepository.findAll();
//        for(Cours x : crs)
//        {
//            x.setGroups(null);
//            coursRepository.deleteById(x.getId() );
//        }


//        List<Student> std = studentRepository.findAll();
//        for(Student x : std)
//            System.out.println(x.toString());
//
//        List<Cours> crs = coursRe


//        List<Etudiant> etd0 = etudiantRepository.findAll();
//        for(Etudiant x : etd0)
//        {
//            x.setGroups(null);
//            etudiantRepository.deleteById(x.getId() );
//        }
//        System.out.println("#######################");
//
//        List<Etudiant> etd2 = etudiantRepository.findAll();
//        for(Etudiant x : etd2)
//            System.out.println(x.toString());
//
//        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");

//        List<Etudiant> etd = etudiantRepository.findByNomContains(" ");
//        for(Etudiant x : etd)
//        {
//            x.setGroups(null);
//            etudiantRepository.deleteById(x.getId() );
//        }
//        System.out.println("#######################");
//
//        List<Etudiant> etd3 = etudiantRepository.findAll();
//        for(Etudiant x : etd3)
//            System.out.println(x.toString());
//
//        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

}
