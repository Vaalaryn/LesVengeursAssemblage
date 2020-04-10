package models;

import play.db.jpa.Model;


@javax.persistence.Entity
public class Pouvoirs extends Model {
    /**
     * Constructeur du model Pouvoirs
     */
    public Pouvoirs(){

    }



    public long id_categorie;
    public String nom;
    public String description;

    /**
     * Constructeur du model Pouvoirs
     */
    public Pouvoirs(long id_categorie, String nom, String description) {
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.description = description;
    }


}
