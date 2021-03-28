/**
 * Classe responsável por peguar os dados do BD não relacional
 * na aréa de "session", reponsável pelo login no sistema
 */
package serverRequest;

import com.google.gson.Gson;
import model.Personal;
import serverCommunication.Json;
import utils.PersonalLogado;
import utils.Util;

/**
 *
 * @author vivi
 */
class ResponseLogin {
    Personal personal; // onde os dados do personal serão guardados
    String token; // onde o Token do personal será guardado
}

public class ResquestSessionPersonal {
    
    public static void login(String email, String password){
        String pastaReq = "/sessions";
        String metodo = "POST";
        String [] argumentos = {"email", "password"};
        String [] valores = {email, password};

        String parametros = Util.converteStringEmJson(argumentos, valores);
        String json = new Json().pegarJsonDB(pastaReq, metodo, parametros);

        ResponseLogin reponseLogin = new Gson().fromJson(json, ResponseLogin.class);

        if(reponseLogin != null){
            PersonalLogado.getInstance().setLogin(reponseLogin.personal, reponseLogin.token);
        }
    }
    
    
    
}