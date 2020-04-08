package controllers;

import models.Mission;
import models.Rapport;
import play.data.validation.Valid;

public class GestionRapport extends ConnectionController {
    public static void Create(long idMission) {
        Mission mission = Mission.em().find(Mission.class, idMission);
        render(mission);
    }

    public static void Create(long idMission, long idRapport) {
        Mission mission = Mission.em().find(Mission.class, idMission);
        Rapport rapport = Rapport.em().find(Rapport.class, idRapport);
        render(mission, rapport);
    }
    //Rédiger le deuxième rapport (héro et admin donc et édite celui existant dans la bdd)
    public static void Save(@Valid Rapport rapport, String idRapport, String rapportText, String idRedacteur) {
        if(idRapport == null)
        {
            if (validation.hasErrors()) {
                params.flash();
                validation.keep();
                Create(rapport.id_mission);
            }

            rapport.save();

            redirect("Application.index");
        }
        Long idRapportInt = Long.parseLong(idRapport);
        Rapport oldRapport = Rapport.em().find(Rapport.class,idRapportInt);
        if(oldRapport.rapportAdmin != null){
            oldRapport.rapportHero = rapportText;
            oldRapport.id_super = idRedacteur;
        }
        else {
            oldRapport.rapportAdmin = rapportText;
            oldRapport.id_civil = idRedacteur;
        }
        oldRapport.save();

        redirect("Application.index");
    }
    //Rédiger un seul rapport (cela créer un nouveau rapport dans la bdd)

}
