package controllers;

import play.data.validation.Valid;

public class Rapport extends ConnectionController {
    public static void Create(){

        render();
    }
    public static void Save(@Valid Rapport rapport){
        if(validation.hasErrors()){
            params.flash();
            validation.keep();
            Create();
        }
        Rapport.Save(rapport);
        render();
    }
}
