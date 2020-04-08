package models;

import play.db.jpa.Model;
import javax.persistence.Entity;

@Entity
public class Rapport extends Model {

    public int id_mission;
    public String id_super;
    public String id_civil;

    public String getRapportHero() {
        return rapportHero;
    }

    public void setRapportHero(String rapportHero) {
        this.rapportHero = rapportHero;
    }

    public String getRapportAdmin() {
        return rapportAdmin;
    }

    public void setRapportAdmin(String rapportAdmin) {
        this.rapportAdmin = rapportAdmin;
    }

    public String rapportHero;

    public Rapport(int id_mission, String id_super) {
        this.id_mission = id_mission;
        this.id_super = id_super;
    }

    public String rapportAdmin;


}
