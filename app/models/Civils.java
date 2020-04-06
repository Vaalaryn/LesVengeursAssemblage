package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Civils extends Model {

    public String id;
    public String id_super;
    public int id_admin;
    public String nom;
    public String prenom;
    public String civilite;
    public String tel;
    public String adresse;
    public String ville;
    public String cp;
    public String mail;
    public Date dateNaissance;
    public Date dateMort;
    public String nation;
    public Date dateMaj;
    public String mdp;


    public Civils(String id,
                  String id_super,
                  int id_admin,
                  String nom,
                  String prenom,
                  String civilite,
                  String tel,
                  String adresse,
                  String ville,
                  String cp,
                  String mail,
                  Date dateNaissance,
                  Date dateMort,
                  String nation,
                  Date dateMaj,
                  String mdp) {
        this.id = id;
        this.id_admin = id_admin;
        this.id_super = id_super;
        this.nom = nom;
        this.prenom = prenom;
        this.civilite = civilite;
        this.tel = tel;
        this.adresse = adresse;
        this.ville = ville;
        this.cp = cp;
        this.mail = mail;
        this.dateNaissance = dateNaissance;
        this.dateMort = dateMort;
        this.nation = nation;
        this.dateMaj = dateMaj;
        this.mdp = mdp;
    }
}