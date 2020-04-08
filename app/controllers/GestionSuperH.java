package controllers;

import models.SuperH;

import play.data.validation.Valid;
import play.libs.Codec;


public class GestionSuperH extends ConnectionController {

    public static void Create(){

        render();
    }
    public static void Save(@Valid SuperH superH,String typeS){
        superH.type = typeS.toCharArray()[0];
        if (validation.hasErrors()){
            params.flash();
            validation.keep();
            Create();
        }
        if(superH.type == 'V')
        {
            String[] idVilain = superH.id.split("-");
            idVilain[0] = "SV";
            superH.id = idVilain[0] + "-" + idVilain[1] + "-" + idVilain[2];
        }

        superH.mdp = Codec.hexMD5(superH.mdp);
        superH.save();
        List();
    }
    public static void List(){
        render(SuperH.all());
    }
    public static void Show(String id){
        SuperH model = SuperH.em().find(SuperH.class,id);

        render(model);
    }
    public  static void Modify(@Valid SuperH superH, String type){
        if (validation.hasErrors()){
            params.flash();
            validation.keep();
            Show(superH.id);
        }
        superH.type = type.toCharArray()[0];
        SuperH superHToModify = SuperH.em().find(SuperH.class, superH.id);
        //Réucp des anciennes infos
        superH.mdp = superHToModify.mdp;


        superHToModify = superH;
        superHToModify.save();

        List();
    }
    public static void Delete(String id){
        SuperH superHToDelete = SuperH.em().find(SuperH.class, id);
        superHToDelete.delete();
    }
}
