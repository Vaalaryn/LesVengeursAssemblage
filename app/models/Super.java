package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Super extends Model {

    public String id;
    public String nom;
    public String pointFaibles;
    public char type;
    public String mdp;

    public Super(String id, String nom, String pointFaibles, char type, String mdp){
        this.id = id;
        this.nom = nom;
        this.pointFaibles = pointFaibles;
        this.type = type;
        this.mdp = mdp;
    }
}