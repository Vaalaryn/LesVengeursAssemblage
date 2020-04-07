package controllers;

import play.mvc.Controller;

import java.util.Date;

public class FakeMissions {
    public static void index() {
        try {
            new models.NatureMission("sauvetage",null);
            new models.NatureMission("invasion",null);
            new models.NatureMission("assassinat",null);
            new models.NatureMission("cambriolage",null);
        }catch (Exception eNature){
            System.out.println("Erreur Création Nature de mission -> ");
            System.out.println(eNature);
        }
        render();
    }
}


