package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Mission extends Model {


    public long id_nature;
    public long id_gravite;
    public String titre;
    public boolean urgence;
    public Date dateDebut;
    public Date dateFin;
    public String longitude;
    public String latitude;
    public String rayon;
    public char reussite;

    /**
     * constructeur du model Mission
     * @param id_nature (long)
     * @param id_gravite (long)
     * @param titre (String)
     * @param urgence (urgence)
     * @param dateDebut (Date)
     * @param dateFin (dateFin)
     * @param longitude (String)
     * @param latitude (String)
     * @param rayon (String)
     * @param reussite (char)
     */
    public Mission(long id_nature, long id_gravite, String titre, boolean urgence, Date dateDebut, Date dateFin, String longitude, String latitude, String rayon, char reussite) {
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
