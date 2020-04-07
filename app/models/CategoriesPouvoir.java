package models;

import play.db.jpa.Model;

public class CategoriesPouvoir extends Model {
    public String nom;
    public String description;

    public CategoriesPouvoir(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }


}
