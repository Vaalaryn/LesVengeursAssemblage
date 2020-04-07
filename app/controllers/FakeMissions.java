package controllers;

import play.mvc.Controller;

import java.util.Date;

public class FakeMissions {
    public static void index() {
        try {
            new models.NatureMission("sauvetage",null).save();
            new models.NatureMission("invasion",null).save();
            new models.NatureMission("assassinat",null).save();
            new models.NatureMission("cambriolage",null).save();
        }catch (Exception eNature){
            System.out.println("Erreur Création Nature de mission -> ");
            System.out.println(eNature);
        }
        try {
            new models.Gravites("bénin", null).save();
            new models.Gravites("dangereux", null).save();
            new models.Gravites("grave", null).save();
            new models.Gravites("effroyable", null).save();
            new models.Gravites("maléfique", null).save();
        }catch (Exception eGrav){
            System.out.println("Erreur Création Gravité -> ");
            System.out.println(eGrav);
        }
        render();
    }
}


