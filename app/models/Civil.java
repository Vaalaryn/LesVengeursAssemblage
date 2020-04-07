package models;

import javax.persistence.*;

import play.data.validation.Required;
import play.db.jpa.GenericModel;


import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "t_civils")
public class Civil extends GenericModel {
    public Civil() {
        if(ID_CIVIL == null)
            ID_CIVIL = generateID();
        CIV_DATECRE = new Date();
        CIV_DATEMAJ = new Date();
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
    public String ID_CIVIL;
    @Required
    public int ID_ADMIN;
    @Required
    public String CIV_ADRESSE;
    @Required
    public String CIV_NOM;
    @Required
    public String CIV_PRENOM;
    @Required
    public String CIV_CIVILITE;
    @Required
    public String CIV_TEL;
    @Required
    public String CIV_VILLE;
    @Required
    public String CIV_CP;
    @Required
    public String CIV_MAIL;
    @Required
    public String CIV_MDP;
    @Required
    public Date CIV_DATENAISSANCE;
    public Date CIV_DATEMORT;
    @Required
    public String CIV_NATION;
    public Date CIV_DATECRE;
    public Date CIV_DATEMAJ;


}
