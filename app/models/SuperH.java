package models;

import play.data.validation.Required;
import play.db.jpa.GenericModel;
import play.libs.Codec;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Random;

@Entity
public class SuperH extends GenericModel {
    public SuperH(){
        //Génération aléatoire du code civil (Uniquement terrien pour le moment)
        String id = "SH-T001";
        this.id = id +=  CodeNation() + "-" + generateAlphanum();
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
    @Required
    public String nom;
    public String pointFaibles;

    @Required
    public char type;
    @Required
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