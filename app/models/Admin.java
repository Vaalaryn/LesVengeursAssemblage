package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Admin extends Model {
    public Admin(String nom) {
        this.nom = nom;
    }

    public String nom;

}
