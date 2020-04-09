package controllers;

import models.CategoriesPouvoir;
import play.mvc.Controller;

public class GestionCategoriePouvoir extends Controller {
    public static void GetCategorie(long id){
        renderJSON(CategoriesPouvoir.em().find(CategoriesPouvoir.class,id));
    }
}
