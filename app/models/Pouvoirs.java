package models;

import play.db.jpa.Model;
import javax.persistence.Entity;

@Entity
public class Pouvoirs extends Model {
    public int id_categorie;
    public String nom;
    public String description;

    public Pouvoirs(int id_categorie, String nom, String description) {
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.description = description;
    }


}
