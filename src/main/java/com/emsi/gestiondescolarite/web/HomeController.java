package com.emsi.gestiondescolarite.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"connectedUser"})
@RequestMapping("")
public class HomeController {
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response, Model model){ return "Auth/home"; }
}
