package controllers;


import java.util.*;

public class Incidents extends ConnectionController {
    static void getData() {
        String date = params.get("date");
        String description = params.get("description");
        String longitude = params.get("longitude");
        String latitude = params.get("latitude");
    }
}
