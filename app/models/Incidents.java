package models;

import play.db.jpa.Model;

import jdk.internal.jline.internal.Nullable;
import play.db.jpa.*;

@Entity
public class Incidents extends Model {

    @Nullable
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
}