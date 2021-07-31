package pl.camp.it.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.camp.it.model.Basket;
import pl.camp.it.model.User;

@Component
@SessionScope
public class SessionObject {
    private User user = null;
    private Basket basket = new Basket();
    private String findPattern;

    public SessionObject() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void logoutUser() {
        this.user = null;
    }

    public boolean isLogged() {
        return this.user != null;
    }

    public Basket getBasket() {
        return basket;
    }

    public void createNewBasket() {
        this.basket = new Basket();
    }

    public String getFindPattern() {
        return findPattern;
    }

    public void setFindPattern(String findPattern) {
        this.findPattern = findPattern;
    }
}
