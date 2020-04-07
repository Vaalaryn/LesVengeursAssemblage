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
        try {
            new models.Gravites("bénin", null);
            new models.Gravites("dangereux", null);
            new models.Gravites("grave", null);
            new models.Gravites("effroyable", null);
            new models.Gravites("maléfique", null);
        }catch (Exception eGrav){
            System.out.println("Erreur Création Gravité -> ");
            System.out.println(eGrav);
        }
        render();
    }
}


