package models;

import controllers.CRUD;
import org.hibernate.annotations.GenericGenerator;
import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import javax.persistence.*;
import java.nio.charset.Charset;
import java.util.Random;

@Entity
@Table(name = "t_supers")
public class Super extends GenericModel {
    public Super(){
        ID_SUPER = generateID();
    }

    @Id
    @GeneratedValue
    @CRUD.Hidden
    private String ID_SUPER;
    public String SUPER_PSEUDO;
    public String SUPER_PFAIBLES;
    public String SUPER_TYPE;
    public String SUPER_MDP;

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
}

