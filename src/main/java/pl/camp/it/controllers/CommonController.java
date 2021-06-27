package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.database.Database;
import pl.camp.it.model.Game;
import pl.camp.it.model.view.Mail;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CommonController {

    @Autowired
    Database database;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String main(Model model) {
        List<Game> games = this.database.getAllGames();
        model.addAttribute("games", games);
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "main";
    }

    @RequestMapping(value = "/main" , method = RequestMethod.GET)
    public String main2(Model model) {
        return "redirect:/";
    }

    @RequestMapping (value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        model.addAttribute("mail", new Mail());
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "contact";
    }

    @RequestMapping (value = "/contact", method = RequestMethod.POST)
    public String processForm(@ModelAttribute Mail mail) {
        return "redirect:/";
    }
}