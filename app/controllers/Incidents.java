package controllers;


import java.util.*;
import play.Logger;

public class Incidents extends ConnectionController {

    public static void index(){
        render();
    }


    public static void postIndex() {
        String date = params.get("date");
        String description = params.get("description");
        String longitude = params.get("longitude");
        String latitude = params.get("latitude");
        render();
    }
}
