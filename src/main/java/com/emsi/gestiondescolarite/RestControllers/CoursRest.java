package com.emsi.gestiondescolarite.RestControllers;

import com.emsi.gestiondescolarite.dao.UserRepository;
import com.emsi.gestiondescolarite.entities.Etudiant;
import com.emsi.gestiondescolarite.entities.Groups;
import com.emsi.gestiondescolarite.entities.User;
import com.emsi.gestiondescolarite.services.EtudiantService;
import com.emsi.gestiondescolarite.services.GroupService;
import com.emsi.gestiondescolarite.services.UserService;
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
public class CoursRest {
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


    @GetMapping(path="/getAllGroups")
    public List<Groups> getAllGroups()
    {
        System.out.println("getAllUser ");
        return groupService.getGroups();
    }

    @DeleteMapping("/DeleteUser/{id}")
    public ResponseEntity<User> DeleteUser(@PathVariable int id)
    {
        User deletedUser = userService.DeleteUser(id);
        return new ResponseEntity<User>(deletedUser, HttpStatus.OK);
    }

}
