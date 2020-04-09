package models;


import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Incidents extends Model {

    @Column(nullable = true)
    public int id_mission;
    public String civil;
    public String type;
    public String description;
    public String longitude;
    public String latitude;
    public String adresse;
    public Date date;
    public boolean etat;

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

    public void setEtat(boolean value){
        this.etat = value;
    }

    public void setId_mission(int id_mission) {
        this.id_mission = id_mission;
    }
}