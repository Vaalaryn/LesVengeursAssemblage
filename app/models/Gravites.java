package models;

import play.db.jpa.Model;
import javax.persistence.Entity;


@Entity
public class Gravites extends Model {
    /**
     * Constructeur du model Gravites
     */
    public Gravites(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public String nom;
    public String description;
}
