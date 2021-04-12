/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrica;

import entidade.PersonalPadrao;
import interfaces.IPersonal;

/**
 *
 * @author Vivi
 */
public class PersonalFabrica {
    String tipo_responsabilidade;
    
    public PersonalFabrica(String tipo_responsabilidade){
        this.tipo_responsabilidade = tipo_responsabilidade;
    }
    
    public PersonalFabrica(){
        this.tipo_responsabilidade = "";
    }
    
    public IPersonal getPersonal(){
        IPersonal personal;
        switch(tipo_responsabilidade){
            case "padrao": personal = new PersonalPadrao();
            default: personal = new PersonalPadrao();
        }
        return personal;
    }
}
