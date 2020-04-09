package controllers;

import java.util.Date;
import java.util.List;

public class Incidents extends ConnectionController {

    public static void index() {
        render();
    }


    public static void postIndex(Date date) throws Exception {

        String description = params.get("description");
        String type = params.get("type");
        String longitude = params.get("lon");
        String latitude = params.get("lat");
        String adresse = params.get("adresse");




        new models.Incidents(Security.connected(), type, description, latitude, longitude, adresse, date, false).save();

        redirect("/incident/new");
    }

    public static void suivi() {
        List<models.Incidents> incidents = models.Incidents.find("byCivil", Security.connected()).fetch();
        renderArgs.put("incidents", incidents);
        render();
    }

    @Check("Admin")
    public static void manage() {
        List<models.Incidents> incidents = models.Incidents.find("byEtat", false).fetch();
        renderArgs.put("incidents", incidents);
        render();
    }

    @Check("Admin")
    public static void manageIncident(int id_incident) {
        models.Incidents incidents = models.Incidents.find("byId", (long) id_incident).first();
        incidents.setEtat(true);
        incidents.setId_mission((long) 0);
        incidents.save();
        redirect("/incident/manage");
    }



}
