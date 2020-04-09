package controllers;

import models.Pouvoirs;
import play.mvc.Controller;

import java.util.List;

public class GestionPouvoirs extends Controller {
    public static void GetAllPouvoirs(){

        List<Pouvoirs> pouvoirsList = Pouvoirs.findAll();
        renderJSON(pouvoirsList);
    }

    public static Long Create(String nom, Long idCategorie, String description){
        Pouvoirs pouvoirs = new Pouvoirs();
        pouvoirs.nom = nom;
        pouvoirs.description = description;
        pouvoirs.id_categorie = idCategorie;

        pouvoirs.save();

        return pouvoirs.id;
    }
}
