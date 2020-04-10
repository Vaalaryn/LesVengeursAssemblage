package models;


import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Incidents extends Model {

    @Column(nullable = true)
    public long id_mission;
    public String civil;
    public boolean etat;
    @Required
    public String type;
    @Required
    public String description;
    @Required
    public Date date;
    @Required
    public String longitude;
    @Required
    public String latitude;

    @Required
    public String adresse;

    /**
     * Constructeur du model Civil
     */
    public Incidents(String civil,
                     String type,
                     String description,
                     String latitude,
                     String longitude,
                     String adresse,
                     Date date,
                     Boolean etat) {
        this.civil = civil;
        this.type = type;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.adresse = adresse;
        this.date = date;
        this.etat = etat;
    }

    /**
     * setter de etat
     * @param value (boolean)
     */
    public void setEtat(boolean value){
        this.etat = value;
    }

    /**
     * setter de l'id mission
     * @param id_mission (long)
     */
    public void setId_mission(long id_mission) {
        this.id_mission = id_mission;
    }
}