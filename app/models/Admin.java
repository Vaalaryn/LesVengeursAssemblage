package models;

import play.db.jpa.Model;

public class Admin extends Model {
    public Admin(String nom) {
        this.nom = nom;
    }

    public String nom;

}
