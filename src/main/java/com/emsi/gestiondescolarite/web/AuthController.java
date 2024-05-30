package com.emsi.gestiondescolarite.web;

import com.emsi.gestiondescolarite.dao.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.emsi.gestiondescolarite.entities.User;
import com.emsi.gestiondescolarite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes({"connectedUser"})
@RequestMapping("")
public class AuthController {
    private final UserService userService;

    @Autowired UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){ return "redirect:/indexEtudiant"; }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response, Model model){ return "Auth/home"; }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){ return "Auth/Login";    }


    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession();
        session.invalidate();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(null);

        return "redirect:/login";
    }


    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "Auth/Register";
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("email" ) String email,@ModelAttribute("password") String password, Model model){
        model.addAttribute("message","Login XXX");
        System.out.println("Login Post ");
        System.out.println("email : "+email+" password : "+password);

        User OldUser = userRepository.findUserByEmail(email);
        if(OldUser == null){
            return "redirect:/login?error";
        }

        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,securityContext);

            session.setAttribute("connectedUser",OldUser);

            return "redirect:/";

        } catch (Exception e){
            return "redirect:/login?error";
        }

    }



    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String createNewUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user")User user){

        try {
            user.setRole("USER");
            User userTest = userRepository.findUserByEmail(user.getEmail());

            if(userTest != null){
                return "redirect:/register?error";
            }

            User newUser = userService.createUser(user);
            if(newUser == null){
                return "redirect:/register?error";
            }
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,securityContext);

            return "redirect:/login";

        } catch (Exception e){
            return "redirect:/register?error";
        }

    }


}

