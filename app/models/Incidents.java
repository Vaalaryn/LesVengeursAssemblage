package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Incidents extends Model {
    public int id_incident;
    public int id_mission;
    public String civil;
    public String type;
    public String description;
    public String longitude;
    public String latitude;
    public Date date;

    public Incidents(int id_incident,
                     int id_mission,
                     String civil,
                     String type,
                     String description,
                     String latitude,
                     String longitude,
                     Date date) {
        this.id_incident = id_incident;
        this.id_mission = id_mission;
        this.civil = civil;
        this.type = type;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
    }
}