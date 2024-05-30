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
public class GroupController {
    @Autowired GroupsRepository     groupsRepository;
    @Autowired EtudiantRepository   etudiantRepository;
    @Autowired CoursRepository      coursRepository;


    @GetMapping(path="/indexGroups")
    public String allGroups(Model model,@ModelAttribute("connectedUser" ) User user ,
                            @RequestParam(name = "page"         , defaultValue = "0") int page,
                            @RequestParam(name = "size"         , defaultValue = "3") int size,
                            @RequestParam(name = "searchCour"   , defaultValue = "" ) String searchCour,
                            @RequestParam(name = "searchName"  , defaultValue = "" ) String searchName,
                            @RequestParam(name = "searchEtudiant", defaultValue = "") String searchEtudiant)
    {

        Page<Groups> AllGroups = groupsRepository.findByNomContains(searchName , PageRequest.of(page,size));
        long lenght = AllGroups.getTotalElements();

        int[] GroupsPages = new int[AllGroups.getTotalPages()];
        for(int i=0; i<GroupsPages.length; i++)
            GroupsPages[i]=i;

        model.addAttribute("pageGroupsAll"  , AllGroups.getContent());
        model.addAttribute("pagesGroups"    , GroupsPages);
        model.addAttribute("searchName"    , searchName);
        model.addAttribute("size"       , size);
        model.addAttribute("currentPage", page);
        model.addAttribute("user", user);
        model.addAttribute("lenght", lenght);

        return "Main/GroupPages/group";
    }


    @PostMapping(path = "/saveGroups")
    public String saveGroups(Model model, Groups s,String search,
                               @RequestParam(name="id", defaultValue = "-1") int id,
                               @RequestParam(name="currentPage") int page,
                               @RequestParam(name="idEtudiant", defaultValue = "-1") int idEtudiant)
    {
        System.out.println("#### 2nd After group ---->  "+s);

        groupsRepository.save(s);
        return "redirect:/indexGroups?page="+page;
    }





    @GetMapping(path="/deleteGroups")
    public String deleteGroups( int page, int size, String search,
                                @RequestParam(name="id") int id)
    {
        Groups grp = groupsRepository.findById(id);

        List<Etudiant> etds = etudiantRepository.findByGroups_Id(id);
        for (Etudiant x : etds)
            x.setGroups(null);

        List<Cours> crs = coursRepository.findByGroups_Id(id);
        for (Cours x : crs)
            x.setGroups(null);

        groupsRepository.deleteById(id);
        return "redirect:/indexGroups?page="+page+"&size="+size+"&search="+search;
    }

    @GetMapping(path="/AjouteEtudiantGroup")
    public String AjouteEtudiantGroup(Model model ,int currentPage,Groups grp, int size, String searchName,
                                @ModelAttribute("connectedUser" ) User user ,
                                @RequestParam(name="idEtudiantAjouter", defaultValue = "-1") int idEtudiantAjouter,
                                @RequestParam(name="Nom", defaultValue = "xxxx") String Nom,
                                @RequestParam(name="matricule", defaultValue = "xxxx") String matricule,
                                @RequestParam(name="id", defaultValue = "-1") int id)
    {
        Etudiant etd = etudiantRepository.findById(idEtudiantAjouter);
        if (etd != null) {
            etd.setGroups(grp);
            etudiantRepository.save(etd);
        }
        model.addAttribute("user", user);
        return "redirect:/editGroups?page="+currentPage+"&id="+id+"&size="+size+"&search="+searchName;
    }




    @GetMapping(path="/DeleteEtudiantGroup")
    public String DeleteEtudiantGroup(int currentPage,Groups grp,int size,String searchName,
                                      @RequestParam(name="idEtudiantSupprimer", defaultValue = "-1") int idEtudiant,
                                      @RequestParam(name="id", defaultValue = "-1") int id)
    {
        Etudiant etd = etudiantRepository.findById(idEtudiant);
        if (etd != null) {
            etd.setGroups(null);
            etudiantRepository.save(etd);
        }
        return "redirect:/editGroups?page="+currentPage+"&id="+id+"&size="+size+"&search="+searchName;
    }

    @GetMapping(path="/AjouteCoursGroup")
    public String AjouteCoursGroup(int currentPage, Groups grp, int size, String searchName,
                                      @RequestParam(name="idCoursAjouter", defaultValue = "-1") int idCours,
                                      @RequestParam(name="id", defaultValue = "-1") int id)
    {
        Cours crs = coursRepository.findById(idCours);
        if (crs != null)
        {
            crs.setGroups(grp);
            coursRepository.save(crs);
        }
        return "redirect:/editGroups?page="+currentPage+"&id="+id+"&size="+size+"&search="+searchName;
    }



    @GetMapping(path="/DeleteCoursGroup")
    public String DeleteCoursGroup(int currentPage,Groups grp, int size,String searchName,
                                    @RequestParam(name="idCoursSupprimer", defaultValue = "-1") int idCours,
                                    @RequestParam(name="id", defaultValue = "-1") int id)
    {
        Cours crs = coursRepository.findById(idCours);
        if (crs != null)
        {
            crs.setGroups(null);
            coursRepository.save(crs);
        }
        return "redirect:/editGroups?page="+currentPage+"&id="+id+"&size="+size+"&search="+searchName;
    }

    @GetMapping(path="/createGroups")
    public String createGroups(Model model,@ModelAttribute("connectedUser" ) User user ,
                                        @RequestParam(name = "page"         , defaultValue = "0") int page,
                                        @RequestParam(name = "size"         , defaultValue = "3") int size,
                                        @RequestParam(name = "searchName" , defaultValue = "") String searchName,
                                        @RequestParam(name = "id" , defaultValue = "-1")  Integer id  ) {
        Groups GroupsAdd = new Groups();
//        Groups GroupsAdd = new Groups(" -------- "," ----- ",null,null);
//        groupsRepository.save(GroupsAdd);

        System.out.println(" !!!!!!!!!!!!!!!! ");
        System.out.println(GroupsAdd);
        System.out.println(" !!!!!!!!!!!!!!!! ");

        List<Etudiant> etds = etudiantRepository.findAll();
        model.addAttribute("ListEtudiants", etds);

        List<Etudiant> ListEtudiant = null;
        List<Cours> ListCours = null;
        if (GroupsAdd.getId() != null)
        {
            ListEtudiant = etudiantRepository.findByGroups_Id(GroupsAdd.getId());
            ListCours = coursRepository.findByGroups_Id(GroupsAdd.getId());
        }


        List<Etudiant> AllEtudiant = etudiantRepository.findAll();
        List<Cours> AllCours = coursRepository.findAll();

        model.addAttribute("GroupsAdd", GroupsAdd);
        model.addAttribute("ListEtudiant", ListEtudiant);
        model.addAttribute("ListCours", ListCours);
        model.addAttribute("AllEtudiant", AllEtudiant);
        model.addAttribute("AllCours", AllCours);
        model.addAttribute("user", user);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", searchName);

        System.out.println(" editGroups page : "+page+" size : "+size+" searchName : "+searchName);

        System.out.println("id Group : "+id);

        return "Main/GroupPages/formAddGroup";
    }

    @GetMapping(path = "/editGroups")
    public String editGroups(Model model,@ModelAttribute("connectedUser" ) User user ,
                                        @RequestParam(name = "page"         , defaultValue = "0") int page,
                                        @RequestParam(name = "size"         , defaultValue = "3") int size,
                                        @RequestParam(name = "idEtudiant"         , defaultValue = "-1") int idEtudiant,
                                        @RequestParam(name = "idCours"         , defaultValue = "-1") int idCours,
                                        @RequestParam(name = "searchName" , defaultValue = "") String searchName,
                                        @RequestParam(name = "id" , defaultValue = "-1")  Integer id  )
    {
        Groups grp = groupsRepository.findById(id).orElse(null);
        List<Etudiant> DeleteEtudiant = etudiantRepository.findByGroups_Id(id);
        List<Cours> DeleteCours = coursRepository.findByGroups_Id(id);

        List<Etudiant> AddEtudiant = etudiantRepository.findAll();
        List<Cours> AddCours = coursRepository.findAll();

        AddEtudiant.removeAll(DeleteEtudiant);
        AddCours.removeAll(DeleteCours);

        if(grp == null) throw new RuntimeException("Erreur");
        System.out.println("   #####################################################   ");

        model.addAttribute("GroupsEdit", grp);

        model.addAttribute("DeleteEtudiant", DeleteEtudiant);
        model.addAttribute("DeleteCours", DeleteCours);

        model.addAttribute("AddEtudiant", AddEtudiant);
        model.addAttribute("AddCours", AddCours);

        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", searchName);
        model.addAttribute("user", user);

        return "Main/GroupPages/formEditGroup";
    }

    @GetMapping(path="/formEtudiantGroup")
    public String formEtudiantGroup(Model model,@ModelAttribute("connectedUser" ) User user , @RequestParam(name="id", defaultValue = "-1") int id)
    {

        List<Etudiant> EtudiantDelete = etudiantRepository.findByGroups_Id(id);
        System.out.println("After  EtudiantDelete --->"+EtudiantDelete);
        List<Etudiant> EtudiantAdd = etudiantRepository.findAll();

        System.out.println("Before  EtudiantAdd --->"+EtudiantAdd);

        EtudiantAdd.removeAll(EtudiantDelete);

        System.out.println("After  EtudiantAdd --->"+EtudiantAdd);

        model.addAttribute("EtudiantAdd",EtudiantAdd);
        model.addAttribute("EtudiantDelete",EtudiantDelete);
        model.addAttribute("id",id);
        model.addAttribute("user", user);
        return "group/FormUpdateEtudiant";
    }
}
