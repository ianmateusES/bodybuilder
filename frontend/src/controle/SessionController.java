/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import serverCommunication.PostLoginPersonal;

public class SessionController {
    public static void login(String email, String senha) throws Exception{
        PostLoginPersonal.login(email, senha);
    }
}