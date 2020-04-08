package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
import play.libs.Codec;

@Entity
public class SuperH extends GenericModel {

    @Id
    public String id;
    public String nom;
    public String pointFaibles;
    public char type;
    public String mdp;

    public SuperH(String id, String nom, String pointFaibles, char type, String mdp){
        this.id = id;
        this.nom = nom;
        this.pointFaibles = pointFaibles;
        this.type = type;
        this.mdp = Codec.hexMD5(mdp);
    }

    public static SuperH connect(String id, String mdp) {
        return find("byIdAndMdp", id, Codec.hexMD5(mdp)).first();
    }
}