package controllers;

import java.util.Date;
import java.util.List;

import play.Logger;
import play.data.validation.Valid;
import play.db.jpa.Model;
import play.libs.Codec;

public class Incidents extends ConnectionController {

    /**
     * Render la page de la création d'incident
     */
    public static void index() {
        render();
    }

    /**
     * Création d'un incident
     * @param incidents (Incidents)
     */
    public static void postIndex(@Valid models.Incidents incidents) {
        incidents.civil = Security.connected();
        if (validation.hasErrors()){
            params.flash();
            validation.keep();
            index();
        }
        incidents.save();
        redirect("/incident/new");
    }

    /**
     * Affiche les incidents déclaré
     */
    public static void suivi() {
        List<models.Incidents> incidents = models.Incidents.find("byCivil", Security.connected()).fetch();
        renderArgs.put("incidents", incidents);
        render();
    }

    /**
     * render la page de gestion des incidents
     */
    @Check("Admin")
    public static void manage() {
        List<models.Incidents> incidents = models.Incidents.find("byEtat", false).fetch();
        renderArgs.put("incidents", incidents);
        render();
    }

    /**
     * defini un incident comme sans suite
     * @param id_incident (int)
     */
    @Check("Admin")
    public static void manageIncident(int id_incident) {
        models.Incidents incidents = models.Incidents.find("byId", (long) id_incident).first();
        incidents.setEtat(true);
        incidents.setId_mission((long) 0);
        incidents.save();
        redirect("/incident/manage");
    }



}
