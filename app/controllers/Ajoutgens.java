package controllers;

import play.mvc.Controller;

import java.util.Date;

public class Ajoutgens extends Controller {

    public static void index() {
        try {
            new models.SuperH("SHK001001HNKDEFR","SuperMan","Kryptonite",'H',"toor").save();
            new models.SuperH("SVK001001HBEAPGQ","Général Zod","Kryptonite verte",'V',"toor").save();
        }catch (Exception eSuper){
            System.out.println("Erreur Création Super -> ");
            System.out.println(eSuper);
        }
        try {
            new models.Civil("CIT001245HEFDCHQ",0,"Bartholomew Olsen","James","h","2605963358","The Daily Planet","Metropolis","62960","j.olsen@thedailyplanet.com",null,null,"USA", new Date(),"toor",new Date()).save();
            new models.Civil("CIT001245FYPCYHM",1,"Joanne Lane","Lois","f","2605646358","The Daily Planet","Metropolis","62960","l.lane@thedailyplanet.com",null,null,"USA", new Date(),"toor",new Date()).save();
            new models.Civil("CIT001245FEHQEKO",2,"Elizabeth Lang","Lana","f","2605887586","Smallville","Metropolis","62960","l.lang@lex.corp",null,null,"USA", new Date(),"toor",new Date()).save();
        }catch (Exception eCivil){
            System.out.println("Erreur Création Civil -> ");
            System.out.println(eCivil);
        }
        render();
    }
}
