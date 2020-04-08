package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
import play.libs.Codec;

@Entity
public class Civil extends GenericModel {

    public Civil(){
        //Génération aléatoire du code civil (Uniquement terrien pour le moment)
        String id = "CI-T001";
        this.id = id +=  CodeNation() + "-" + generateAlphanum();
        dateCreation = new Date();
    }

    private String generateAlphanum() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString.toUpperCase();
    }

    private String CodeNation(){
        StringBuilder nation = new StringBuilder();
        int max = 9;
        int min = 0;
        int range = max - min + 1;
        for (int i = 0; i < 3; i++) {
            int rand = (int)(Math.random() * range);
            nation.append(rand);
        }
        return  nation.toString();
    }

    @Id
    public String id;
    public String mdp;
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
    public Date dateCreation;


    public Civil(String id,
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
                  String mdp,
                  Date dateCreation) {
        this.id = id;
        this.id_admin = id_admin;
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
        this.mdp = Codec.hexMD5(mdp);
        this.dateCreation = dateCreation;
    }

    public static Civil connect(String id, String mdp) {
        return find("byIdAndMdp", id, Codec.hexMD5(mdp)).first();
    }
}