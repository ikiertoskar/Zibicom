package pl.camp.it.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketPosition> basketPositions = new ArrayList<>();

    public Basket() {
    }

    public List<BasketPosition> getBasketPositions() {
        return basketPositions;
    }

    public void addGame(Game game) {
        for (BasketPosition basketPosition : this.basketPositions) {
            if (basketPosition.getGame().getCode().equals(game.getCode())) {
                basketPosition.setPieces(basketPosition.getPieces() + 1);
                return;
            }
        }

        BasketPosition basketPosition = new BasketPosition();
        basketPosition.setGame(game);
        basketPosition.setPieces(1);

        this.basketPositions.add(basketPosition);

    }

    public class BasketPosition {
        private Game game;
        private int pieces;

        public BasketPosition() {
        }

        public Game getGame() {
            return game;
        }

        public void setGame(Game game) {
            this.game = game;
        }

        public int getPieces() {
            return pieces;
        }

        public void setPieces(int pieces) {
            this.pieces = pieces;
        }

        public BasketPosition(Game game, int pieces) {
            this.game = game;
            this.pieces = pieces;




        }
    }

}
