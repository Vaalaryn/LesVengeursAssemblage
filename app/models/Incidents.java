package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Incidents extends Model {

    public int id_mission;
    public String civil;
    public String type;
    public String description;
    public String longitude;
    public String latitude;
    public Date date;
    public boolean etat;

    public Incidents(String civil,
                     String type,
                     String description,
                     String latitude,
                     String longitude,
                     Date date,
                     Boolean etat) {
        this.civil = civil;
        this.type = type;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
        this.etat = etat;
    }
}