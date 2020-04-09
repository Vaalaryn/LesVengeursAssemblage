package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Assigner extends Model {

    public long id_mission;
    public String id_super;

    public Assigner(long id_mission, String id_super) {
        this.id_mission = id_mission;
        this.id_super = id_super;
    }
}
