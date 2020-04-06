package controllers;

import models.*;
import play.Logger;

public class Security extends Secure.Security {

    // TODO : auth qui va en bdd
    static boolean authenticate(String id, String password) {
        switch (id.substring(0, 2)){
            case "SH":
                Logger.debug("HERO");
                return SuperH.connect(id, password) != null;
            case "CI":
                Logger.debug("Civil");
                return Civil.connect(id, password) != null;
            default:
                Logger.debug("Autres");
                return false;
        }
    }

}