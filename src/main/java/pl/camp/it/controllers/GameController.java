package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.camp.it.database.Database;
import pl.camp.it.model.Game;
import pl.camp.it.session.SessionObject;
import pl.camp.it.validators.GameValidator;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Objects;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class GameController {

    @Autowired
    Database database;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/addGame", method = RequestMethod.GET)
    public String addGameForm(Model model){
        model.addAttribute("game", new Game());
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role",
                this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        return "addGame";
    }

    @RequestMapping(value = "/addGame", method = RequestMethod.POST)
    public String addGame(@ModelAttribute Game game, @RequestParam MultipartFile filename) {

        if (!GameValidator.validateBasics(game)){
            return "redirect:/addGame";
        }
        File fileOnDisk = null;

        if(filename.getOriginalFilename().contains(".png")) {
            fileOnDisk = new File("C:\\Projects\\Zibicom\\covers\\" + game.getCode() + ".png");
        } else if(filename.getOriginalFilename().contains(".jpg")) {
            fileOnDisk = new File("C:\\Projects\\Zibicom\\covers\\" + game.getCode() + ".jpg");
        }

        try {
            OutputStream os = new FileOutputStream(fileOnDisk);
            os.write(filename.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Game gameFromDB = this.database.findGameByCode(game.getCode());
        if (gameFromDB != null) {
            gameFromDB.setPieces(gameFromDB.getPieces() + game.getPieces());
        } else {
            if(!GameValidator.validateFull(game)){
                return "redirect:/addGame";
            }
            this.database.addGame(game);
        }
        return "redirect:/";
    }


}
