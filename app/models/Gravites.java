package models;

import play.db.jpa.Model;

public class Gravites extends Model {
    public Gravites(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public String nom;
    public String description;
}
