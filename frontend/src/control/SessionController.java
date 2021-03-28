/**
 * Essa classe é o controlador da Sessão do usuário, responsável por ser o intermediário entre as classes que peguam
 * Jsons do backend, o PersonalLogado no folder Util e a view
 */
package control;

import serverRequest.ResquestSessionPersonal;
import utils.PersonalLogado;

public class SessionController {
    //Tentativa de login do usuário
    public static boolean login(String profissao, String email, String senha){
        if(profissao.equalsIgnoreCase("personal")){ // Caso seja um personal
            ResquestSessionPersonal.login(email, senha);
            return (PersonalLogado.getInstance().getToken() != null);
        }
        return false;
    }
    
    public static String getNomeUsuario(){
        if(PersonalLogado.getInstance().getToken()!= null){ // Caso seja um personal
            return PersonalLogado.getInstance().getUsuario().getName();
        }
        return null;
    }
    
    public static void setLogout(){
        if(PersonalLogado.getInstance().getToken()!= null){ // Caso o token do personal seja diferente de null
            PersonalLogado.getInstance().setLogout(); //Desloga sem precisar fazer requisição ao BD
        }
    }
}