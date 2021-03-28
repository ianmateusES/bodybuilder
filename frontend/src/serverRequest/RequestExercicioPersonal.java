/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverRequest;

import com.google.gson.Gson;
import model.Exercicio;
import serverCommunication.Json;
import utils.Util;

/**
 *
 * @author vivi
 */
public class RequestExercicioPersonal {
    
    public static Exercicio[] listarExercicios(){
        String pastaReq = "/exercicio/me";
        String metodo = "GET";

        String json = new Json().pegarJsonDB(pastaReq, metodo, null);
        Exercicio[] exercicio = new Gson().fromJson(json, Exercicio[].class);
            
        return exercicio;
    }
    
    public static boolean cadastrarExercicio(Exercicio exercicio){
        String pastaReq = "/exercicio";
        String metodo = "POST";
        String [] argumentos = {"name", "group"};
        String [] valores = {exercicio.getName(), exercicio.getGroup()};

        String parametros = Util.converteStringEmJson(argumentos, valores);

        String json = new Json().pegarJsonDB(pastaReq, metodo, parametros);
        return (new Gson().fromJson(json, Exercicio.class) != null);
    }
    
    
    public static boolean deletarExercicios(String id){
        String pastaReq = "/exercicio/me/" + id;
        String metodo = "DELETE";
       
        String json = new Json().pegarJsonDB(pastaReq, metodo, null);
        System.out.println(json);
        
        new Gson().fromJson(json, String.class);
        return true;
    }
}
