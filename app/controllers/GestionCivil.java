package controllers;
import models.Civil;
import play.*;
import play.data.validation.Valid;
import play.mvc.*;

import java.util.Date;
import java.util.List;


public class GestionCivil extends Controller {

    public static void Create(){
        render();
    }
    public static void Save(@Valid models.Civil civil){
        if (validation.hasErrors()){
            params.flash();
            validation.keep();
            Create();
        }
        civil.save();
        List();
    }
    public static void List(){
        render(models.Civil.all());
    }
    public static void Show(String id){
        models.Civil model = models.Civil.em().find(models.Civil.class,id);

        render(model);
    }
    public  static void modify(models.Civil civil){
        if (validation.hasErrors()){
            params.flash();
            validation.keep();
            Show(civil.ID_CIVIL);
        }
        models.Civil civilToModify = models.Civil.em().find(models.Civil.class, civil.ID_CIVIL);
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
