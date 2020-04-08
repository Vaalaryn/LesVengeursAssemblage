package controllers;

import models.Gravites;
import models.Mission;
import models.NatureMission;
import play.mvc.Controller;

import java.util.List;

public class Missions extends ConnectionController {


    public static void index(){
        List<Mission> missions = Mission.find("from Mission").fetch(0, 50);
        List<NatureMission> natures = NatureMission.find("from NatureMission").fetch(0,10);
        List<Gravites> gravites = Gravites.find("from Gravites").fetch(0,10);
        render(missions,natures,gravites);
    }

    public static void info(){
        render();
    }
}
