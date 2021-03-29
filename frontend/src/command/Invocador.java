/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.util.LinkedList;

/**
 *
 * @author vivi
 */
public class Invocador {
    public static int Apagado = 1;
    //public static int Italico = 2;
    LinkedList<Comando> comandos;
    static Invocador invocador;
    
    private Invocador(){
        comandos = new  LinkedList<Comando>();
    }
    
    public static Invocador getInstace(){
        if(invocador == null){
            invocador = new Invocador();
        }
        return invocador;
    }
    
    public String executaComando(int tipoComando, String textoInput, String novoTexto){
        Comando comando = null;
        if(tipoComando == Invocador.Apagado){
            comando = new TextoApagadoComando(textoInput, novoTexto);
            this.comandos.add(comando);
        }
        
        if(comando != null){
            return comando.executa();
        }else{
            //return new Font("Garamond", Font.PLAIN, 32);
            return "";
        }
    }
    
    public String desfaz(String novoTexto){
        Comando comando = this.comandos.pollLast();
        if(comando != null){
            return comando.desfaz(novoTexto);
        }else{
            return "";
        }
    }
}
