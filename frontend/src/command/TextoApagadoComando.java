/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

/**
 *
 * @author vivi
 */
public class TextoApagadoComando implements Comando{
    private String textoInput;
    private String novoTexto;
    
    public TextoApagadoComando(String textoInput, String novoTexto){
        this.textoInput = textoInput;
        this.novoTexto = novoTexto;
    }
    
    public String executa(){
        if(novoTexto.equalsIgnoreCase(textoInput)){
            return "";
        }
        return novoTexto;
    }
    
    public String desfaz(String texto){
        if(texto.equals("")){
            return textoInput;
        }
        return texto;
    }
}