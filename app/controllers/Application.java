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
            Civils user = Civils.find("byId", Security.connected()).first();
            renderArgs.put("user", user.id);
        }
    }

    public static void index() {
        render();
    }
}