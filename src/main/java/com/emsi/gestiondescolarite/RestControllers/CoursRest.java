package com.emsi.gestiondescolarite.RestControllers;

import com.emsi.gestiondescolarite.dao.UserRepository;
import com.emsi.gestiondescolarite.entities.Cours;
import com.emsi.gestiondescolarite.entities.Etudiant;
import com.emsi.gestiondescolarite.entities.Groups;
import com.emsi.gestiondescolarite.entities.User;
import com.emsi.gestiondescolarite.services.CoursService;
import com.emsi.gestiondescolarite.services.EtudiantService;
import com.emsi.gestiondescolarite.services.GroupService;
import com.emsi.gestiondescolarite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Rest")
public class CoursRest {
    @Autowired UserRepository userRepository;
    @Autowired private UserService userService;
    @Autowired private GroupService groupService;
    @Autowired private CoursService coursService;
    @Autowired private EtudiantService etudiantService;

//    @GetMapping("/")
//    public ResponseEntity<?> ebHealth() {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @GetMapping("/health")
//    public ResponseEntity<?> health() {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


    @GetMapping(path="/getAllCours")
    public List<Cours> getAllCours() { return coursService.getCours(); }

    @DeleteMapping("/DeleteCours/{id}")
    public ResponseEntity<Cours> DeleteCours(@PathVariable int id)
    {
        Cours deletedCours = coursService.DeleteCours(id);
        return new ResponseEntity<Cours>(deletedCours, HttpStatus.OK);
    }

}
