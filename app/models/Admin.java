package models;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import javax.persistence.*;

@Entity
@Table(name = "t_admin")
public class Admin extends GenericModel {
    @Id
    @GeneratedValue
    @Column(name = "ID_ADMIN")
    private int ID;
    @Column(name = "ADMIN_NOM")
    public String Nom;
}
