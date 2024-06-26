package com.emsi.gestiondescolarite.web;

import com.emsi.gestiondescolarite.dao.*;
import com.emsi.gestiondescolarite.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SessionAttributes({"connectedUser"})
@Controller
public class CoursController {

    @Autowired CoursRepository coursRepository;
    @Autowired GroupsRepository groupsRepository;

    @GetMapping(path="/indexCours")
    public String allStudents(Model model,
                              @ModelAttribute("connectedUser" ) User user ,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "3") int size,
                              @RequestParam(name="search", defaultValue = "") String searchName)
    {

//        Page<Cours> pageStudents2 = coursRepository.findAll(PageRequest.of(page,size));
        Page<Cours> pageCours = coursRepository.findByTitreContains(searchName, PageRequest.of(page,size));

        long lenght = pageCours.getTotalElements();
//        System.out.println("getTotalElements : "+pageCours.getTotalElements());
//        System.out.println("getNumber : "+pageCours.getNumber());
//        System.out.println("getTotalPages : "+pageCours.getTotalPages());
//        System.out.println("getNumberOfElements : "+pageCours.getNumberOfElements());
//        System.out.println("getSize : "+pageCours.getSize());
//        System.out.println("nextOrLastPageable : "+pageCours.nextOrLastPageable());


        int[] pages = new int[pageCours.getTotalPages()];
        for(int i=0; i<pages.length; i++)
            pages[i]=i;

        model.addAttribute("pageCours",pageCours.getContent());
        model.addAttribute("tabPages", pages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("user", user);
        model.addAttribute("lenght", lenght);
        model.addAttribute("searchName", searchName);
        return "Main/CoursPages/cours";
//        return "cours/cours";
    }

    @GetMapping(path="/createCours")
    public String createCours(Model model,@ModelAttribute("connectedUser" ) User user) {
        List<Groups> grps = groupsRepository.findAll();
        Cours coursAdd = new Cours();

        model.addAttribute("coursAdd", coursAdd);
        model.addAttribute("AllGroups", grps);
        model.addAttribute("user", user);
        return "Main/CoursPages/formAddCours";
    }

    @PostMapping(path = "/saveCours")
    public String savecours(Model model, Cours s,
                               @RequestParam(name="currentPage", defaultValue = "0") int page,
                               @RequestParam(name="IdGroup", defaultValue = "-1") int IdGroup,
                               @RequestParam(name="size", defaultValue = "3") int size,
                               @RequestParam(name="searchName", defaultValue = "") String search)
    {
        s.setGroups(groupsRepository.findById(IdGroup));
        coursRepository.save(s);

        return "redirect:/indexCours?page="+page+"&size="+size+"&search="+search;
    }
    
    @GetMapping(path="/deleteCours")
    public String deletecours(int page, int size, String search,
            @RequestParam(name="id") Integer id)
    {
        coursRepository.deleteById(id);
        return "redirect:/indexCours?page="+page+"&size="+size+"&search="+search;
    }


    @GetMapping(path = "/editCours")
    public String editcours(Model model ,@ModelAttribute("connectedUser" ) User user , int page ,int size ,String search ,int id  )
    {
        Cours objEditCours = coursRepository.findById(id);
        List<Groups> AllGroups = groupsRepository.findAll();
        if(objEditCours == null) throw new RuntimeException("!!!! Erreur !!!!");
        model.addAttribute("coursEdit", objEditCours);
        model.addAttribute("AllGroups", AllGroups);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", search);
        model.addAttribute("user", user);

        return "Main/CoursPages/formEditcours";
    }
}