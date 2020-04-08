package models;

import play.db.jpa.Model;

public class NatureMission extends Model {
    public String nom;
    public String description;

    public NatureMission(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
}
