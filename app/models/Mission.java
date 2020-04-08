package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Mission extends Model {

    public int id_nature;
    public int id_gravite;
    public String titre;
    public boolean urgence;
    public Date dateDebut;
    public Date dateFin;
    public String longitude;
    public String latitude;
    public String rayon;
    public char reussite;

    public Mission(int id_nature, int id_gravite, String titre, boolean urgence, Date dateDebut, Date dateFin, String longitude, String latitude, String rayon, char reussite) {
        this.id_nature = id_nature;
        this.id_gravite = id_gravite;
        this.titre = titre;
        this.urgence = urgence;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.longitude = longitude;
        this.latitude = latitude;
        this.rayon = rayon;
        this.reussite = reussite;
    }
}
