package pl.camp.it.validators;

import pl.camp.it.model.Game;

public class GameValidator {

    public static boolean validateBasics (Game game) {
        if (game.getCode() == null || game.getPieces() <= 0) {
            return false;
        }

        return true;
    }

    public static boolean validateFull (Game game) {
        if(game.getTitle() == null || game.getStudio() == null || game.getPrice() < 0) {
            return false;
        }

        return true;
    }
}
