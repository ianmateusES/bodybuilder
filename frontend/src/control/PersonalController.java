/**
 * Essa classe é o controlador de Personal, responsável por ser o intermediário entre as classes que pegam
 * Jsons do backend, o model e a view
 */
package control;

import model.Personal;
import serverRequest.ResquestPersonal;
import utils.Util;

/**
 *
 * @author vivi
 */
public class PersonalController {
    public static boolean cadastro(String name, String email, String password, String password_confirmation,String birthday, String telephone, String cref, String address){
        Personal personal = ResquestPersonal.cadastro(name, email, password, password_confirmation,  Util.converteData(birthday), telephone, cref, address);
        
        return (personal != null);
    }
}
