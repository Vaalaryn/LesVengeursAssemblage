package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Ajoutgens extends Controller {

    public static void index() {
        new models.Civil("CI-T001976F-7I5H4W",0,"Jean","Civil","h","06667634","10 rue de la Motte","Raccourcis-Ville","66600","jean.c@gouv.fr",null,null,"France",null,"toor");
        new models.SuperH("SH-T001245H-LQ65XF","SuperMan","Cryptonite",'g',"toor");
        render();
    }
}
