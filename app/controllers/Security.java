package controllers;

import models.*;
import play.Logger;

public class Security extends Secure.Security {

    /**
     * Connexion d'un utilisateur
     * @param id (String)
     * @param password (String)
     * @return (boolean)
     */
    static boolean authenticate(String id, String password) {
        switch (id.substring(0, 2)) {
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

    /**
     * Vérifie que l'utilisateur connecté à les droits d'accès
     * @param profile (String)
     * @return (boolean)
     */
    static boolean check(String profile) {
        switch (connected().substring(0, 2)) {
            case "SH":
                return profile.equals("Super") || profile.equals("Admin");
            case "CI":
                int admin = Civil.find("byId", connected()).<Civil>first().id_admin;
                if(admin == 3){
                    return true;
                }else if(admin == 1){
                    return profile.equals("Admin");
                }
        }
        return false;
    }
}