package controllers;
import models.Civil;
import play.*;
import play.data.validation.Valid;
import play.mvc.*;

import java.util.Date;
import java.util.List;


public class Administration extends Controller {

    public static void Create(){
        render();
    }
    public static void Save(@Valid Civil civil){
        if (validation.hasErrors()){
            params.flash();
            validation.keep();
            Create();
        }
        civil.save();
        List();
    }
    public static void List(){
        render(Civil.all());
    }
    public static void Show(String id){
        Civil model = Civil.em().find(Civil.class,id);

        render(model);
    }
    public  static void modify(Civil civil){
        if (validation.hasErrors()){
            params.flash();
            validation.keep();
            Show(civil.ID_CIVIL);
        }
        Civil civilToModify = Civil.em().find(Civil.class, civil.ID_CIVIL);
        //Réucp des anciennes infos
        civil.CIV_MDP = civilToModify.CIV_MDP;
        civil.CIV_DATECRE = civilToModify.CIV_DATECRE;
        civil.CIV_DATEMAJ = new Date();


        civilToModify = civil;
        civilToModify.save();

        civilToModify.save();

        List();
    }
}
