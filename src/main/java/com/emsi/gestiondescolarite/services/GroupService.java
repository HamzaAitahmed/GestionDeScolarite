package com.emsi.gestiondescolarite.services;


import com.emsi.gestiondescolarite.dao.EtudiantRepository;
import com.emsi.gestiondescolarite.dao.GroupsRepository;
import com.emsi.gestiondescolarite.entities.Etudiant;
import com.emsi.gestiondescolarite.entities.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class GroupService {
    @Autowired GroupsRepository groupsRepository;

    public List<Groups> getGroups() { return groupsRepository.findAll();}

}
