package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Secure.class)
public class Application extends Controller {
    public static String userId;

    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            Civils user = Civils.find("byId", Security.connected()).first();
            renderArgs.put("user", user.id);
            userId = user.id;
        }
    }

    public static void index() {
        render();
    }

    public static void displayUserInfo(String which){
        switch (which){
            case "id":
                render(userId);
                break;
        }
    }
}