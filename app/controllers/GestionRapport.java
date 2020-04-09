package controllers;

import models.Mission;
import models.Rapport;
import play.data.validation.Valid;

public class GestionRapport extends ConnectionController {
    public static void Create(long idMission, long idRapport) {
        Mission mission = Mission.em().find(Mission.class, idMission);
        if(idRapport != 0){
            Rapport rapport = Rapport.em().find(Rapport.class, idRapport);
            render(mission, rapport);
        }
        else {
            render(mission);
        }
    }
    //Rédiger le deuxième rapport (héro et admin donc et édite celui existant dans la bdd)
    public static void Save(@Valid Rapport rapport, String idRapport, String rapportText, String idRedacteur, String droitRedacteur) {
        if(idRapport == null)
        {
            if (validation.hasErrors()) {
                params.flash();
                validation.keep();
                Create(rapport.id_mission,0);
            }

            rapport.save();

            redirect("Application.index");
        }
        Long idRapportInt = Long.parseLong(idRapport);
        Rapport oldRapport = Rapport.em().find(Rapport.class,idRapportInt);
        if(droitRedacteur.equals("2")){
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


}
