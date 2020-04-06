package controllers;

import models.*;

public class Security extends Secure.Security {

    // TODO : auth qui va en bdd
    static boolean authenticate(String id, String password) {
        return Civils.connect(id, password) != null;
    }

}