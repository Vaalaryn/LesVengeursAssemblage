package controllers;

import java.text.SimpleDateFormat;
import java.util.*;

import play.Logger;

public class Incidents extends ConnectionController {

    public static void index() {
        render();
    }


    public static void postIndex() {
        String date = params.get("date");
        String description = params.get("description");
        String type = params.get("type");
        String longitude = params.get("lon");
        String latitude = params.get("lat");
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");

        try {
            new models.Incidents(Security.connected(), type, description, latitude, longitude, format.parse(date), false).save();
        } catch (java.text.ParseException ex){
            ex.fillInStackTrace();
        }
        redirect("/incident/new");
    }

    public static void suivi() {
        List<models.Incidents> incidents = models.Incidents.find("byCivil", Security.connected()).fetch();
        renderArgs.put("incidents", incidents);
        render();
    }
}
