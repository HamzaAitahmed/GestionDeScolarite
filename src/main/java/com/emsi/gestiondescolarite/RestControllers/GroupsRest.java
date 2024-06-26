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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Rest")
public class GroupsRest {
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
    public List<Groups> getAllGroups() { return groupService.getGroups(); }

    @DeleteMapping("/DeleteGroup/{id}") // Methode Delete Doesn't Work
//    @RequestMapping(value="/DeleteUser/{id}", method={RequestMethod.DELETE, RequestMethod.GET})
    public ResponseEntity<Groups> DeleteGroup(@PathVariable int id)
    {
        Groups deletedGroups = groupService.DeleteGroups(id);
        return new ResponseEntity<Groups>(deletedGroups, HttpStatus.OK);
    }


}
