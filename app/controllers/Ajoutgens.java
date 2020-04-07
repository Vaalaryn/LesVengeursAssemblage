package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Ajoutgens extends Controller {

    public static void index() {
        try {
            new models.Civil("CI-T001996F-7I5H4W",0,"Jean","Civil","h","06667634","10 rue de la Motte","Raccourcis-Ville","66660","jean.c@gouv.fr",null,null,"France",null,"toor").save();
        }catch (Exception e){
            System.out.println("DEBUG : ERREUR ->");
            System.out.println(e);
        }
        new models.SuperH("SH-T001245H-LQ65XF","SuperMan","Cryptonite",'g',"toor");
        render();
    }
}
