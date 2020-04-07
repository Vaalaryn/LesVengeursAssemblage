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
        }catch (Exception e){
            System.out.println("Erreur Création Super -> ");
            System.out.println(e);
        }
        render();
    }
}
