package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Ajoutgens extends Controller {

    public static void index() {
        try {
            new models.SuperH("SHK001001HNKDEFR","SuperMan","Kryptonite",'h',"toor").save();
            new models.SuperH("SVK001001HBEAPGQ","Général Zod","Kryptonite verte",'v',"toor").save();
        }catch (Exception eSuper){
            System.out.println("Erreur Création Super -> ");
            System.out.println(eSuper);
        }
        try {
            new models.Civil("CI-T001996F-7I5H4W",0,"Bartholomew Olsen","James","h","935963358","The Daily Planet","Metropolis","62960","j.olsen@thedailyplanet.com",null,null,"USA", new Date(),"toor").save();
        }catch (Exception eCivil){
            System.out.println("Erreur Création Civil -> ");
            System.out.println(eCivil);
        }
        render();
    }
}
