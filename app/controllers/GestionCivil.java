package controllers;
import models.Civil;
import models.SuperH;
import play.data.validation.Valid;
import play.libs.Codec;

import java.util.Date;


public class GestionCivil extends ConnectionController {

    public static void Create(){
        render();
    }
    public static void Save(@Valid models.Civil civil){
        if (validation.hasErrors()){
            params.flash();
            validation.keep();
            Create();
        }
        civil.mdp = Codec.hexMD5(civil.mdp);
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
            Show(civil.id);
        }
        models.Civil civilToModify = models.Civil.em().find(models.Civil.class, civil.id);
        //Réucp des anciennes infos
        civil.mdp = civilToModify.mdp;
        civil.dateCreation = civilToModify.dateCreation;
        civil.dateMaj = new Date();


        civilToModify = civil;
        civilToModify.save();

        civilToModify.save();

        List();
    }

    public static void Delete(String id){
        Civil civilToDelete = SuperH.em().find(Civil.class, id);
        civilToDelete.delete();
    }
}
