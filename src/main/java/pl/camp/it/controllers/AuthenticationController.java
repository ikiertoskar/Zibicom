package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import pl.camp.it.database.Database;
import pl.camp.it.model.User;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AuthenticationController {

    @Autowired
    Database database;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        return "login";
    }

    @RequestMapping (value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password) {
        User user = database.authenticate(login, password);
        if(user != null) {
            sessionObject.setUser(user);
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping (value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.sessionObject.logoutUser();
        return "redirect:/";
    }
}
