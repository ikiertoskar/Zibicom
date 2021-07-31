package pl.camp.it.database;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import pl.camp.it.model.Game;
import pl.camp.it.model.Order;
import pl.camp.it.model.User;
import pl.camp.it.session.SessionObject;

import java.util.ArrayList;
import java.util.List;

@Component
public class Database {
    private List<Game> games = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    public boolean logged = false;

    public Database() {
        games.add(new Game(
                "Witcher 3: Wild Hunt",
                "CD Projekt Red",
                "Action RPG",
                "PC",
                99.9,
                10,
                "111111"
        ));
        games.add(new Game(
                "Cyberpunk 2077",
                "CD Projekt Red",
                "Action RPG",
                "Xbox",
                199.9,
                20,
                "222222"
        ));
        games.add(new Game(
                "Horizon: Zero Dawm",
                "Guerrilla Games",
                "Action RPG",
                "PlayStation",
                59.9,
                15,
                "333333"
        ));
        games.add(new Game(
                "Call Of Duty: Modern Warfare",
                "Activision",
                "FPS",
                "XBOX",
                239.9,
                5,
                "444444"
        ));
        games.add(new Game(
                "Sims 4",
                "Electronic Arts",
                "Simulator",
                "PC",
                39.9,
                25,
                "555555"
        ));
        games.add(new Game(
                "FIFA 21",
                "Electronic Arts",
                "Sports",
                "PlayStation",
                69.9,
                30,
                "666666"
        ));
        games.add(new Game(
                "Mass Effect: Legendary Edition",
                "Electronic Arts",
                "RPG",
                "PlayStation",
                249.9,
                9,
                "777777"
        ));
        games.add(new Game(
                "Assassin's Creed: Valhalla",
                "Ubisoft",
                "Action RPG",
                "XBOX",
                179.9,
                14,
                "888888"
        ));
        games.add(new Game(
                "Shadow of the Tomb Raider",
                "Crystal Dynamics",
                "Action",
                "PlayStation",
                79.9,
                6,
                "999999"
        ));
        games.add(new Game(
                "Days Gone",
                "Sony Interactive Entertainment",
                "Action",
                "PlayStation",
                149.9,
                15,
                "000000"
        ));

        users.add(new User("Oskar", "Ikiert", "oskar", DigestUtils.md5Hex("oskar"), User.Role.ADMIN));
        users.add(new User("Paweł", "Wróbel", "pablo", DigestUtils.md5Hex("pablo"),User.Role.USER));
    }

    public List<Game> getAllGames() {
        return this.games;
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public Game findGameByCode(String code) {
        for(Game game : this.games) {
            if (game.getCode().equals(code)) {
                return game;
            }
        }
        return null;
    }

    public User authenticate(String login, String password) {
        for(User user : this.users) {
            if(user.getLogin().equals(login) && user.getPassword().equals(DigestUtils.md5Hex(password))) {
                return user;
            }
        }

        return null;
    }

    public void addOrder (Order order) {
        this.orders.add(order);
    }

    public List<Order> getOrdersForUser(User user) {
        List<Order> result = new ArrayList<>();
        for (Order order : this.orders) {
            if (order.getUser().getLogin().equals(user.getLogin())){
                result.add(order);
            }
        }

        return result;
    }
}
