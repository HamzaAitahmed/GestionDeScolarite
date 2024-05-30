package com.emsi.gestiondescolarite.RestControllers;

import com.emsi.gestiondescolarite.dao.*;
import com.emsi.gestiondescolarite.entities.*;
import com.emsi.gestiondescolarite.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EtudiantRest {

    @Autowired UserRepository userRepository;
    @Autowired private UserService userService;
    @Autowired private GroupService groupService;
    @Autowired private EtudiantService etudiantService;

//    @GetMapping("/")
//    public ResponseEntity<?> ebHealth() {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @GetMapping("/health")
//    public ResponseEntity<?> health() {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


    @GetMapping(path="/getAllEtudiant")
    public List<Etudiant> getAllEtudiant() { return etudiantService.getEtudiant(); }


    @DeleteMapping("/DeleteEtudiant/{id}") // Methode Delete Doesn't Work
    public ResponseEntity<Etudiant>  DeleteEtudiant(@PathVariable int id)
    {
        Etudiant deletedEtudiant = etudiantService.DeleteEtudiant(id);
        return new ResponseEntity<Etudiant>(deletedEtudiant, HttpStatus.OK);
    }


}
