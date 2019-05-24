package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

public class ItemBean implements Serializable {
    private int id, price;
    private String title,players,directors,description;
    private Timestamp updated,created;
    private Map validation;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;


    //This constructor is in accordance with textbook.
    public ItemBean(Map RequestParams) {
        this.id = (int) RequestParams.get("id");
        this.price = (int) RequestParams.get("price");
        this.title = (String) RequestParams.get("title");
        this.players = (String) RequestParams.get("players");
        this.directors = (String) RequestParams.get("directors");
        this.description = (String) RequestParams.get("description");
        this.updated = (Timestamp) RequestParams.get("updated");
        this.created = (Timestamp) RequestParams.get("created");
        this.validation = (Map<String, String>) RequestParams.get("validation");
    }

    public ItemBean(int id, int price, String title, String players, String directors, String description, Timestamp updated, Timestamp created, Map validation) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.players = players;
        this.directors = directors;
        this.description = description;
        this.updated = updated;
        this.created = created;
        this.validation = validation;
    }

    public ItemBean(int id, int price, String title, String players, String directors, String description, Timestamp updated, Timestamp created) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.players = players;
        this.directors = directors;
        this.description = description;
        this.updated = updated;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Map getValidation() {
        return validation;
    }

    public void setValidation(Map validation) {
        this.validation = validation;
    }
}
