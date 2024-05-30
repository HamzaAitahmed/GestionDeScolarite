package com.emsi.gestiondescolarite.services;


import com.emsi.gestiondescolarite.entities.User;
import com.emsi.gestiondescolarite.dao.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User getUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        return user;
    }
    public User createUser(User user){
        User newUser = userRepository.save(user);
        userRepository.flush();
        return newUser;
    }


        public List<User> findUsers() {
            return userRepository.findAllUsers();
    }

//    public User deleteEtudiant(@RequestParam(name="id") int id ) { return userRepository.deleteById(id) ;}
    public User DeleteUser(int id){
        User user = userRepository.findUserById(id);
        userRepository.deleteById(id);
        System.out.println(user);
        return user;
    }
}


