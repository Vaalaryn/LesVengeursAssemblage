package controllers;

import models.Incidents;
import models.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Check("Admin")
public class Missions extends ConnectionController {

    /**
     * Retourne une mission en fonction de son id
     * @param id (long)
     * @return (Mission)
     */
    public static Mission fetchMissionById(long id) {
        return Mission.find("byId", id).first();
    }

    /**
     * List les 50 premières missions
     */
    public static void index() {
        List<Mission> missions = Mission.find("from Mission").fetch(0, 50);
        List<NatureMission> natures = NatureMission.find("from NatureMission").fetch(0, 10);
        List<Gravites> gravites = Gravites.find("from Gravites").fetch(0, 10);
        render(missions, natures, gravites);
    }

    /**
     * Cloture une mission
     * @param id_mission (long)
     * @param reussite (boolean)
     */
    public static void Cloturer(long id_mission, Boolean reussite) {
        Mission mission = Mission.find("id = ?1", id_mission).first();
        mission.reussite = reussite ? 'r' : 'e';
        mission.dateFin = new Date();

        mission.save();
        redirect("GestionRapport.create", id_mission);
    }

    /**
     * Affiche les missions en cours d'un hero
     * @param id_hero (String)
     */
    public static void flow(String id_hero) {
        List<Mission> missions = Mission.find("from Mission where reussite=?1 and id in (select id_mission from Assigner where id_super LIKE ?2)", 'c', Security.connected()).fetch();
        render(missions);
    }

    /**
     * Affiches le detail des infos d'une mission
     * @param id_mission (long)
     */
    public static void info(long id_mission) {
        Mission mission = Mission.find("byId", id_mission).first();

        //Afficher Nature & Gravité
        List<Gravites> gravites = Gravites.find("from Gravites").fetch();
        String nomGravite = null;
        for (Gravites gravite : gravites) {
            if (gravite.id == mission.id_gravite) {
                nomGravite = gravite.nom;
            }
        }
        List<NatureMission> natures = NatureMission.find("from NatureMission").fetch();
        String nomNature = null;
        for (NatureMission nature : natures) {
            if (nature.id == mission.id_nature) {
                nomNature = nature.nom;
            }
        }

        //Afficher Heros & Vilains
        List<SuperH> supersVilainsPresents = SuperH.find("type = ?1 and id in (select id_super from Assigner where id_mission LIKE ?2)", 'V', id_mission).fetch();
        List<SuperH> supersHerosPresents = SuperH.find("type = ?1 and id in (select id_super from Assigner where id_mission LIKE ?2)", 'H', id_mission).fetch();

        render(mission, id_mission, nomGravite, nomNature, supersHerosPresents, supersVilainsPresents);
    }

    /**
     * Affiche l'historique des missions affectuées par l'utilisateur connecté
     */
    @Check("Super")
    public static void history() {
        List<Mission> missions = Mission.find("from Mission where reussite!=?1 and id in (select id_mission from Assigner where id_super LIKE ?2)", 'c', Security.connected()).fetch();
        render(missions);
    }

    /**
     *
     * @param id_incident
     */
    public static void create(int id_incident) {
        models.Incidents incident = models.Incidents.find("byId", (long) id_incident).first();
        List<Gravites> gravites = Gravites.findAll();
        List<SuperH> superHeros = SuperH.find("id like ?1", "SH%").fetch();
        List<SuperH> superVilains = SuperH.find("id like ?1", "SV%").fetch();
        List<NatureMission> natureMissions = NatureMission.findAll();
        render(id_incident, gravites, natureMissions, incident, superHeros, superVilains);
    }

    /**
     * Render une List d'ID de super en JSON stringifié
     * @param supersListing (List<String>)
     * @return String
     */
    private static String RenderListSuper(List<String> supersListing) {
        String listH = "['";
        for (String fmsuper :
                supersListing) {
            listH += fmsuper + "','";
        }
        return (supersListing.size() > 0) ? listH.substring(0, listH.length() - 3) + "']" : "[]";
    }

    /**
     * Affiche la page pour modifier une mission
     * @param id_mission (long)
     */
    public static void modifier(long id_mission) {
        Mission mission = fetchMissionById(id_mission);

        //Afficher Heros & Vilains
        List<String> supersVilainsPresents = SuperH.find("select id from SuperH where type = ?1 and id in (select id_super from Assigner where id_mission LIKE ?2)", 'V', id_mission).fetch();
        List<String> supersHerosPresents = SuperH.find("select id from SuperH where type = ?1 and id in (select id_super from Assigner where id_mission LIKE ?2)", 'H', id_mission).fetch();

        renderArgs.put("listH", RenderListSuper(supersHerosPresents));
        renderArgs.put("listV", RenderListSuper(supersVilainsPresents));

        List<SuperH> superHeros = SuperH.find("id like ?1", "SH%").fetch();
        List<SuperH> superVilains = SuperH.find("id like ?1", "SV%").fetch();

        List<Gravites> gravites = Gravites.findAll();
        List<NatureMission> natureMissions = NatureMission.findAll();


        render(gravites, natureMissions, mission, superVilains, superHeros);
    }

    /**
     * Sauvegarde des modifications d'une mission
     * @param id_mission (long)
     * @param hero (String[])
     * @param vilain (String[])
     * @param date (Date)
     */
    public static void saveModifier(long id_mission, String[] hero, String[] vilain, Date date) {
        String latitude = params.get("lat");
        String longitude = params.get("lon");
        String rayon = params.get("rayon");
        String gravite = params.get("gravite");
        String nature = params.get("nature");
        String titre = params.get("titre");
        boolean urgence = (params.get("urgence") != null);

        Mission mission = models.Mission.find("byId", id_mission).first();
        mission.latitude = latitude;
        mission.longitude = longitude;
        mission.rayon = rayon;
        mission.id_gravite = Long.parseLong(gravite);
        mission.id_nature = Long.parseLong(nature);
        mission.urgence = urgence;
        mission.titre = titre;

        mission.dateDebut = date;
        mission.save();

        Assigner.delete("id_mission = ?1", mission.id);

        if (hero != null) {
            for (String heroId : hero) {
                new Assigner(mission.id, heroId).save();
            }
        }
        if (vilain != null) {
            for (String vilainId : vilain) {
                new Assigner(mission.id, vilainId).save();
            }
        }
        redirect("/mission/" + mission.id);
    }

    /**
     * Sauvegarde les infos d'une nouvelle mission
     * @param id_incident (long)
     * @param hero (String[])
     * @param vilain (String[])
     * @param date (Date)
     */
    public static void save(long id_incident, String[] hero, String[] vilain, Date date) {
        String latitude = params.get("lat");
        String longitude = params.get("lon");
        String rayon = params.get("rayon");
        String gravite = params.get("gravite");
        String nature = params.get("nature");
        String titre = params.get("titre");
        boolean urgence = (params.get("urgence") != null);

        Mission mission = new models.Mission(Integer.parseInt(nature), Integer.parseInt(gravite), titre, urgence, date, null, longitude, latitude, rayon, 'c').save();
        Incidents incident = Incidents.find("byId", id_incident).first();
        incident.setId_mission(mission.id);
        incident.save();


        if (hero != null) {
            for (String heroId : hero) {
                new Assigner(mission.id, heroId).save();
            }
        }

        if (vilain != null) {
            for (String vilainId : vilain) {
                new Assigner(mission.id, vilainId).save();
            }
        }
        redirect("/incident/manage");
    }
}
