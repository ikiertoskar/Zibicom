package pl.camp.it.database;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import pl.camp.it.model.Game;
import pl.camp.it.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class Database {
    private List<Game> games = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    public boolean logged = false;

    public Database() {
        games.add(new Game(
                "Witcher 3: Wild Hunt",
                "CD Projekt Red",
                "Action RPG",
                "PC",
                99.99,
                10,
                "111111"
        ));
        games.add(new Game(
                "Cyberpunk 2077",
                "CD Projekt Red",
                "Action RPG",
                "PlayStation",
                199.99,
                20,
                "222222"
        ));
        games.add(new Game(
                "Horizon: Zero Dawm",
                "Guerrilla Games",
                "Action RPG",
                "PlayStation",
                59.99,
                15,
                "333333"
        ));
        games.add(new Game(
                "Call Of Duty: Modern Warfare",
                "Activision",
                "FPS",
                "XBOX",
                239.99,
                5,
                "444444"
        ));
        games.add(new Game(
                "Sims 4",
                "Electronic Arts",
                "Simulator",
                "PC",
                39.99,
                25,
                "555555"
        ));
        games.add(new Game(
                "FIFA 21",
                "Electronic Arts",
                "Sports",
                "PlayStation",
                69.99,
                30,
                "666666"
        ));
        games.add(new Game(
                "Mass Effect: Legendary Edition",
                "Electronic Arts",
                "RPG",
                "PlayStation",
                249.99,
                9,
                "777777"
        ));
        games.add(new Game(
                "Assassin's Creed: Valhalla",
                "Ubisoft",
                "Action RPG",
                "XBOX",
                179.99,
                14,
                "888888"
        ));
        games.add(new Game(
                "Shadow of the Tomb Raider",
                "Crystal Dynamics",
                "Action",
                "PlayStation",
                79.99,
                6,
                "999999"
        ));
        games.add(new Game(
                "Days Gone",
                "Sony Interactive Entertainment",
                "Action",
                "PlayStation",
                149.99,
                15,
                "000000"
        ));

        users.add(new User("Oskar", "Ikiert", "oskar", DigestUtils.md5Hex("oskar")));
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

    public boolean authenticate(String login, String password) {
        for(User user : this.users) {
            if(user.getLogin().equals(login) && user.getPassword().equals(DigestUtils.md5Hex(password))) {
                return true;
            }
        }

        return false;
    }
}
