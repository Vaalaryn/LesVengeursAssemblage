package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import sun.rmi.runtime.Log;

public class Application extends ConnectionController {

    public static void index2() {
        render();
    }

    public static void profile(String id) {
        switch (id.substring(0, 2)){
            case "SH":
                SuperH superh = SuperH.find("byId", id).first();
                List<Mission> missions = Mission.find("from Mission where id in (select id_mission from Assigner where id_super LIKE ?1)", id).fetch(0, 10);
                renderArgs.put("pointFaible", superh.pointFaibles);
                renderArgs.put("type", superh.type);
                renderArgs.put("missions", missions);
                break;
            case "CI":
                Civil civil = Civil.find("byId", id).first();
                break;
        }
        render();
    }
}