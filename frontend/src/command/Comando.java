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
public interface Comando {
    public String executa();
    public String desfaz(String novoTexto);
}
