package models;

import play.db.jpa.Model;
import javax.persistence.Entity;

@Entity
public class Posseder extends Model {
    public int id_pouvoir;
    public String id_super;

    public Posseder(int id_pouvoir, String id_super) {
        this.id_pouvoir = id_pouvoir;
        this.id_super = id_super;
    }
}
