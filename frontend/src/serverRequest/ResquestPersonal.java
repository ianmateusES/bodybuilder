/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverRequest;

import com.google.gson.Gson;
import model.Personal;
import serverCommunication.Json;
import utils.Util;

/**
 *
 * @author vivi
 */
public class ResquestPersonal {
    
    public static Personal cadastro(String name, String email, String password, String password_confirmation,String birthday, String telephone, String cref, String address){
        String pastaReq = "/personais";
        String metodo = "POST";
        String [] argumentos = {"name","email", "password","password_confirmation","birthday", "telephone","cref","address"};
        String [] valores = {name, email, password, password_confirmation, birthday, telephone, cref, address};

        String parametros = Util.converteStringEmJson(argumentos, valores);

        String json = new Json().pegarJsonDB(pastaReq, metodo, parametros);

        Personal personal = new Gson().fromJson(json, Personal.class);
        
        return personal;   
    }
    
}
