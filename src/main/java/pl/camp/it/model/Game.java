package pl.camp.it.model;

public class Game {
    private String title;
    private String studio;
    private String genre;
    private String platform;
    private double price;
    private Integer pieces;
    private String code;

    public Game(String title, String studio, String genre, String platform, double price, Integer pieces, String code) {
        this.title = title;
        this.studio = studio;
        this.genre = genre;
        this.platform = platform;
        this.price = price;
        this.pieces = pieces;
        this.code = code;
    }

    public Game() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getPieces() {
        return pieces;
    }

    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}