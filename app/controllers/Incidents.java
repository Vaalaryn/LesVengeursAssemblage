package controllers;

import java.text.SimpleDateFormat;
import java.util.*;

import play.Logger;
import play.data.validation.Valid;
import play.db.jpa.Model;
import play.libs.Codec;

public class Incidents extends ConnectionController {

    public static void index() {
        render();
    }


    public static void postIndex(@Valid models.Incidents incidents) {
        incidents.civil = Security.connected();
        if (validation.hasErrors()){
            params.flash();
            validation.keep();
            index();
        }
        redirect("/incident/new");
    }

    public static void suivi() {
        List<models.Incidents> incidents = models.Incidents.find("byCivil", Security.connected()).fetch();
        renderArgs.put("incidents", incidents);
        render();
    }

    @Check("Admin")
    public static void manage(){
        List<models.Incidents> incidents = models.Incidents.find("byEtat", false).fetch();
        renderArgs.put("incidents", incidents);
        render();
    }

    @Check("Admin")
    public static void manageIncident(int id_incident){
        models.Incidents incidents =  models.Incidents.find("byId", (long)id_incident).first();
        incidents.setEtat(true);
        incidents.setId_mission(0);
        incidents.save();
        redirect("/incident/manage");
    }
}
