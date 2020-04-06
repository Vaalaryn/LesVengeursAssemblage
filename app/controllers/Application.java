package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Secure.class)
public class Application extends Controller {

    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            switch (Security.connected().substring(0, 2)){
                case "SH":
                    SuperH superh = SuperH.find("byId", Security.connected()).first();
                    renderArgs.put("id", superh.id);
                    renderArgs.put("username", superh.nom);
                    renderArgs.put("admin", 2);
                    break;
                case "CI":
                    Civil civil = Civil.find("byId", Security.connected()).first();
                    renderArgs.put("id", civil.id);
                    renderArgs.put("username", civil.nom + " " + civil.prenom);
                    renderArgs.put("admin", civil.id_admin);
                    break;
            }
        }
    }

    public static void index() {
        render();
    }
}