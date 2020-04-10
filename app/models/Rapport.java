package models;

import play.db.jpa.Model;
import javax.persistence.Entity;

@Entity
public class Rapport extends Model {

    public long id_mission;
    public String id_super;
    public String id_civil;

    /**
     * Retourne le rapport du hero
     * @return (String)
     */
    public String getRapportHero() {
        return rapportHero;
    }

    /**
     * Sauvegarde le rapport du hero
     * @param rapportHero (String)
     */
    public void setRapportHero(String rapportHero) {
        this.rapportHero = rapportHero;
    }

    /**
     * Retourne le rapport de l'admin
     * @return (String)
     */
    public String getRapportAdmin() {
        return rapportAdmin;
    }

    /**
     * Sauvegarde le rapport du hero
     * @param  rapportAdmin (String)
     */
    public void setRapportAdmin(String rapportAdmin) {
        this.rapportAdmin = rapportAdmin;
    }

    public String rapportHero;

    /**
     * Constructeur du model Rapport
     * @param id_mission (long)
     * @param id_super (String)
     */
    public Rapport(long id_mission, String id_super) {
        this.id_mission = id_mission;
        this.id_super = id_super;
    }

    public String rapportAdmin;


}
