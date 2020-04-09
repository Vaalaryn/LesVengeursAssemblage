package controllers;

import models.*;
import net.bytebuddy.implementation.bind.annotation.Super;
import play.Logger;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class Missions extends ConnectionController {


    public static void index() {
        List<Mission> missions = Mission.find("from Mission").fetch(0, 50);
        List<NatureMission> natures = NatureMission.find("from NatureMission").fetch(0, 10);
        List<Gravites> gravites = Gravites.find("from Gravites").fetch(0, 10);

        //Afficher Nature & Gravité de chaque Mission
        /*
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
        */

        render(missions, natures, gravites);
    }

    public static void info(long id_mission) {
        Mission mission = Mission.find("byId", id_mission).first();

        //Afficher Nature & Gravité
        List<Gravites> gravites = Gravites.find("from Gravites").fetch(0, 10);
        String nomGravite = null;
        for (Gravites gravite : gravites) {
            if (gravite.id == mission.id_gravite) {
                nomGravite = gravite.nom;
            }
        }
        List<NatureMission> natures = NatureMission.find("from NatureMission").fetch(0, 10);
        String nomNature = null;
        for (NatureMission nature : natures) {
            if (nature.id == mission.id_nature) {
                nomNature = nature.nom;
            }
        }

        //Afficher Heros & Vilains
        List<SuperH> supers = SuperH.find("id in (select id_super from Assigner where id_mission LIKE ?1)", 10).fetch();
        List<SuperH> supersVilainsPresents = new ArrayList<>();
        List<SuperH> supersHerosPresents = new ArrayList<>();
        for (SuperH superSupers : supers) {
            //SH262763 Superman
            //SV262763 Superman
            if (superSupers.id.substring(0, 2).equals("SV")) {
                Logger.debug("Vilain");
                supersVilainsPresents.add(0, superSupers);
            } else if (superSupers.id.substring(0, 2).equals("SH")) {
                Logger.debug("Hero");
                Logger.debug(superSupers.id);
                supersHerosPresents.add(0,superSupers);
            }
        }
        render(mission, id_mission, nomGravite, nomNature, supersHerosPresents, supersVilainsPresents);
    }
}
