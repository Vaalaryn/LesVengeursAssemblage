package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class CategoriesPouvoir extends Model {
    /**
     * Constructeur du model CategoriesPouvoir
     */
    public CategoriesPouvoir(){

    }

    public String nom;
    public String description;

    public CategoriesPouvoir(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }


}
