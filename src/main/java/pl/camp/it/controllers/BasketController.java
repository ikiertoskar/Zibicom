package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.database.Database;
import pl.camp.it.model.Basket;
import pl.camp.it.model.Game;
import pl.camp.it.model.Order;
import pl.camp.it.session.SessionObject;

import java.awt.print.Book;
import java.util.Iterator;
import java.util.List;


@Controller
public class BasketController {

    @Autowired
    Database database;

    @Autowired
    SessionObject sessionObject;

    @RequestMapping(value = "/addToBasket/{code}", method = RequestMethod.GET)
    public String addGameToBasket(@PathVariable String code){
        Game game = database.findGameByCode(code);
        if(game == null) {
            return "redirect:/main";
        }

        this.sessionObject.getBasket().addGame(game);

        return "redirect:/main";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String basket(Model model) {
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role",
                this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);
        model.addAttribute("basket", this.sessionObject.getBasket());

        double sum = 0;
        for (Basket.BasketPosition basketPosition : this.sessionObject.getBasket().getBasketPositions()) {
            sum = sum + (basketPosition.getGame().getPrice() * basketPosition.getPieces());
        }

        model.addAttribute("sum", sum);
        return "/basket";
    }

    @RequestMapping(value = "/removeGameFromBasket/{code}", method = RequestMethod.GET)
    public String removeFromBasket(@PathVariable String code){
        Iterator<Basket.BasketPosition> iterator = this.sessionObject.getBasket().getBasketPositions().iterator();

        while (iterator.hasNext()) {
            if (iterator.next().getGame().getCode().equals(code)) {
                iterator.remove();
                break;
            }
        }
        return "redirect:/basket";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order() {
        List<Game> gamesFromDB = this.database.getAllGames();

        for (Game game : gamesFromDB) {
            Iterator<Basket.BasketPosition> iterator = this.sessionObject.getBasket().getBasketPositions().iterator();
           while (iterator.hasNext()){
                Basket.BasketPosition actualBasketPosition = iterator.next();
                if (game.getCode().equals(actualBasketPosition.getGame().getCode()) && game.getPieces() < actualBasketPosition.getPieces()){
                    iterator.remove();
                    return "redirect:/basket";
                }
            }
        }

        this.database.addOrder(new Order(this.sessionObject.getUser(), sessionObject.getBasket()));

        for (Game game : gamesFromDB) {
            for (Basket.BasketPosition position : this.sessionObject.getBasket().getBasketPositions()) {
                if (game.getCode().equals(position.getGame().getCode())) {
                    game.setPieces(game.getPieces() - position.getPieces());
                }
            }
        }

        this.sessionObject.createNewBasket();

        return "redirect:/basket";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String orders(Model model) {
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("role",
                this.sessionObject.getUser() != null ? this.sessionObject.getUser().getRole() : null);

        model.addAttribute("orders", this.database.getOrdersForUser(this.sessionObject.getUser()));
        return "orders";
    }

}
