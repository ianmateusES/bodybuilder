/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import modelo.Personal;
import serverCommunication.PostCadastroPersonal;

/**
 *
 * @author vivi
 */
public class PersonalController {
    public static Personal cadastro(String name, String email, String password, String password_confirmation,String birthday, String telephone, String cref, String address) throws Exception{
        String[] dataSeparada = birthday.split("/");
        String parseDate = dataSeparada[2] + "-" + dataSeparada[1] + "-" + dataSeparada[0];

        Personal personal = PostCadastroPersonal.cadastro(name, email, password, password_confirmation, parseDate, telephone, cref, address);
        return personal;
    }
}
