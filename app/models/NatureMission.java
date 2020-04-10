package models;

import play.db.jpa.Model;
import javax.persistence.Entity;

@Entity
public class NatureMission extends Model {
    public String nom;
    public String description;

    /**
     * Constructeur du model NatureMission
     * @param nom (String)
     * @param description (String)
     */
    public NatureMission(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
}
