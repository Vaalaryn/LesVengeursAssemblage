package controllers;

import models.Mission;
import models.Rapport;
import play.data.validation.Valid;

public class GestionRapport extends ConnectionController {

    /**
     * Creation d'un rapport associé à une mission
     * @param idMission (long)
     * @param idRapport (long)
     */
    @Check("Admin")
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

    /**
     * Sauvegarde les infos d'un rapport
     * @param rapport (Rapport)
     * @param idRapport (String)
     * @param rapportText (String)
     * @param idRedacteur (String)
     * @param droitRedacteur (String)
     */
    @Check("Admin")
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
