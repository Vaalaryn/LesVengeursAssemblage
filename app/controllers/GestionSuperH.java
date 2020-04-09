package controllers;

import models.Posseder;
import models.SuperH;
import play.data.validation.Valid;
import play.libs.Codec;

import javax.persistence.TypedQuery;
import java.util.List;


public class GestionSuperH extends ConnectionController {

    public static void Create(){
        render();
    }
    public static void Save(@Valid SuperH superH,String typeS,int[] pouvoirsId){

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

        for(int i =0; i < pouvoirsId.length; i++){
            Posseder posseder = new Posseder();
            posseder.id_pouvoir = pouvoirsId[i];
            posseder.id_super = superH.id;
            posseder.save();
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
    public  static void Modify(@Valid SuperH superH, String type,List<Integer> pouvoirsId){
        if (validation.hasErrors()){
            params.flash();
            validation.keep();
            Show(superH.id);
        }
        superH.type = type.toCharArray()[0];
        SuperH superHToModify = SuperH.em().find(SuperH.class, superH.id);
        //Réucp des anciennes infos
        superH.mdp = superHToModify.mdp;

        String hql = "select p.id_pouvoir from Posseder p where p.id_super = :id";
        TypedQuery<Integer> query = Posseder.em().createQuery(hql,Integer.class);
        query.setParameter("id",superH.id);

        List<Integer> superPouvoirs = query.getResultList();
        for (int i = 0; i < pouvoirsId.size(); i++){
            if(!superPouvoirs.contains(pouvoirsId.get(i))){
                Posseder posseder = new Posseder();
                posseder.id_pouvoir = pouvoirsId.get(i);
                posseder.id_super = superH.id;
                posseder.save();
            }
        }


        superHToModify = superH;
        superHToModify.save();

        List();
    }
    public static void Delete(String id){
        SuperH superHToDelete = SuperH.em().find(SuperH.class, id);
        superHToDelete.delete();
    }
}
