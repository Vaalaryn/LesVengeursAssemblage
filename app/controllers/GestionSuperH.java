package controllers;

import models.Posseder;
import models.SuperH;
import play.data.validation.Valid;
import play.libs.Codec;

import javax.persistence.TypedQuery;
import java.util.List;

@Check("MaitreSupreme")
public class GestionSuperH extends ConnectionController {

    /**
     * Page de creation d'un rapport
     */
    public static void Create(){
        render();
    }

    /**
     * Sauvegarde des informations d'un Super
     * @param superH (SuperH)
     * @param typeS (String)
     * @param pouvoirsId (int[])
     */
    public static void Save(@Valid SuperH superH,String typeS,int[] pouvoirsId){

        superH.type = typeS.toCharArray()[0];
        if (validation.hasErrors()){
            params.flash();
            validation.keep();
            Create();
        }
        if(superH.type == 'V')
        {
            char[] idVilain = superH.id.toCharArray();
            idVilain[0] = 'S';
            idVilain[1] = 'V';
            superH.id = idVilain.toString();
        }

        for(int i =0; i < pouvoirsId.length; i++){
            Posseder posseder = new Posseder();
            posseder.id_pouvoir = (long)pouvoirsId[i];
            posseder.id_super = superH.id;
            posseder.save();
        }

        superH.mdp = Codec.hexMD5(superH.mdp);
        superH.save();
        List();
    }

    /**
     * Liste tous les super
     */
    public static void List(){
        render(SuperH.all());
    }

    /**
     * Détail d'un super
     * @param id (String)
     */
    public static void Show(String id){
        SuperH model = SuperH.em().find(SuperH.class,id);

        render(model);
    }

    /**
     * Modifie les infos d'un super
     * @param superH (SuperH)
     * @param type (String)
     * @param pouvoirsId (List<long>)
     */
    public  static void Modify(@Valid SuperH superH, String type,List<Long> pouvoirsId){
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
        TypedQuery<Long> query = Posseder.em().createQuery(hql,Long.class);
        query.setParameter("id",superH.id);

        List<Long> superPouvoirs = query.getResultList();
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

    /**
     * Supprime un Super
     * @param id (String)
     */
    public static void Delete(String id){
        SuperH superHToDelete = SuperH.em().find(SuperH.class, id);
        superHToDelete.delete();
    }
}
