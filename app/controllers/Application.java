package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;


public class Application extends ConnectionController {

    /**
     * Redirection vers la page de déclaration d'incident
     */
    public static void index() {
        redirect("/incident/new");
    }

    /**
     * Affichage de la page de profil
     * @param id_profil (String)
     */
    public static void profil(String id_profil) {
        switch (id_profil.substring(0, 2)){
            case "SH":
                SuperH superh = SuperH.find("byId", id_profil).first();
                List<Mission> missions = Mission.find("from Mission where id in (select id_mission from Assigner where id_super LIKE ?1)", id_profil).fetch(0, 10);
                List<Pouvoirs> pouvoirs = Mission.find("from Pouvoirs where id in (select id_pouvoir from Posseder where id_super LIKE ?1)", id_profil).fetch();
                renderArgs.put("pointFaible", superh.pointFaibles);
                renderArgs.put("type", superh.type);
                renderArgs.put("missions", missions);
                renderArgs.put("pouvoirs", pouvoirs);
                renderArgs.put("id_profil", id_profil);
                break;
            case "CI":
                Civil civil = Civil.find("byId", id_profil).first();
                renderArgs.put("civil", civil);
                break;
        }
        render();
    }
}