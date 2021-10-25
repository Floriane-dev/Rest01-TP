package fr.diginamic.Rest01.controllers.advice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    public HomeController(){

    }
    @GetMapping
    public String home(Model model){
     String dtitre = "Welcome";
     model.addAttribute("titre",dtitre);
     return "home";
    }
}
