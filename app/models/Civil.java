package models;

import controllers.CRUD;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.MaxLength;
import play.data.validation.Required;
import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "t_civils")
public class Civil extends GenericModel {
    public Civil() {

        ID_CIVIL = generateID();
    }

    public String generateID() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 15;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    @Id
    @CRUD.Hidden
    public String ID_CIVIL;
    public int CIV_NIVEAUDROIT;


    public String CIV_NOM;

    public String CIV_PRENOM;


    public String CIV_CIVILITE;
    public String CIV_TEL;

    public String CIV_VILLE;

    public String CIV_CP;

    public String CIV_MAIL;

    public Date CIV_DATENAISSANCE;
    @CRUD.Hidden
    public Date CIV_DATEMORT;

    public String CIV_NATION;
    @CRUD.Hidden
    public Date CIV_DATECRE;
    @CRUD.Hidden
    public Date CIV_DATEMAJ;
    public String CIV_MDP;

}
