package controllers;

import models.*;
import net.bytebuddy.implementation.bind.annotation.Super;
import play.mvc.Controller;

import java.util.List;

public class Missions extends ConnectionController {


    public static void index(){
        List<Mission> missions = Mission.find("from Mission").fetch(0, 50);
        List<NatureMission> natures = NatureMission.find("from NatureMission").fetch(0,10);
        List<Gravites> gravites = Gravites.find("from Gravites").fetch(0,10);

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

        render(missions,natures,gravites);
    }

    public static void info(long id_mission){
        List<Mission> mission = Mission.find("from Mission where id=?1", id_mission).fetch(0,1);
        String missionTitre = mission.get(0).titre;
        char missionReuss = mission.get(0).reussite;
        String missionRayon = mission.get(0).rayon;
        boolean missionUrg = mission.get(0).urgence;

        //Afficher Nature & Gravité
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

        //Afficher Heros & Vilains
        List<Assigner> supersAssignes = Assigner.find("from Assigner where id_mission=?1",id_mission).fetch();
        List<SuperH> supersNoms = SuperH.find("from SuperH").fetch();
        List<SuperH> supersVilainsPresents = null;
        List<SuperH> supersHerosPresents = null;
        for (Assigner assignation : supersAssignes){
            //10 SH262763
            //11 SH262763
            //10 SV262763
            for (SuperH superSupers : supersNoms){
                //SH262763 Superman
                //SV262763 Superman
                if (assignation.id_super.equals(superSupers.id)){
                    //Si Vilain -> ++ <List> SupersVilainsPresents : SupersVilainsPresents
                    if (superSupers.id.substring(0, 2).equals("SV")) {
                        supersVilainsPresents.add(0,superSupers);
                    }else if (superSupers.id.substring(0, 2).equals("SH")){
                        supersVilainsPresents.add(0,superSupers);
                    }
                }
            }
        }
        render(missionTitre, id_mission, nomGravite, nomNature, missionReuss, missionRayon, missionUrg,supersHerosPresents,supersVilainsPresents);
    }
}
