package controllers;

import models.NatureMission;
import play.mvc.Controller;

import java.util.Date;

public class FakeMissions extends Controller{
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
        try{
            new models.Mission(2,9,"Invasion de Chitauri à New York", true,null,null,"40.692031","73.987000","12",'r').save();
            new models.Mission(3,7,"Meurtre prêt du cinéma Monarch", true,null,null,"40.730113","74.000709","0.150",'e').save();
        }catch(Exception eMission){
            System.out.println("Erreur Création Mission -> ");
            System.out.println(eMission);
        }
        render();
    }
}


