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

    public static void info(long id_mission){
        List<Mission> mission = Mission.find("from Mission where id=?1", id_mission).fetch(0,1);

        String missionTitre = mission.get(0).titre;
        char missionReuss = mission.get(0).reussite;
        String missionRayon = mission.get(0).rayon;
        boolean missionUrg = mission.get(0).urgence;
        List<Gravites> gravites = Gravites.find("from Gravites").fetch(0,10);
        String nomGravite = null;
        for (Gravites gravite : gravites){
            if (gravite.id == mission.get(0).id_gravite){
                nomGravite = gravite.nom;
            }
        }
        List<NatureMission> natures = NatureMission.find("from NatureMission").fetch(0,10);
        String nomNature = null;
        for (NatureMission nature : natures){
            if (nature.id == mission.get(0).id_nature){
                nomNature = nature.nom;
            }
        }
        render(missionTitre, id_mission, nomGravite, nomNature, missionReuss, missionRayon, missionUrg);
    }
}
