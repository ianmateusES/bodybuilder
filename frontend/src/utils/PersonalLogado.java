/**
 * Está classe extende a classe abstrata UsuarioLogado e utiliza o 
 * padrão Singleton para se certificar que apenas um personal
 * esteja logado no sistem
 *
 */
package utils;

import model.Personal;
import model.Usuario;

public final class PersonalLogado extends UsuarioLogado{
    
    private static volatile PersonalLogado personalLogado; // requisito para o Singleton
    private Personal personal; // objeto global da classe Personal
    private String token; // Atributo global de token, ele é gerado automaticamente no login
    
    private PersonalLogado() {} // requisito para o Singleton
    
    // requisito para o Singleton
    public static PersonalLogado getInstance() { 
        if (personalLogado == null) {
            synchronized (PersonalLogado.class) {
                if (personalLogado == null) {
                    personalLogado = new PersonalLogado();
                }
            }
        }
        return personalLogado;
    }
    
    // instância o objeto personal e a variável token
    public void setLogin(Usuario usuario, String token) {
        this.personal = (Personal) usuario;
        this.setToken(token); // Chama o método setToken
    }
    
    // Atualiza o objeto personal
    public void setUpdateUser(Usuario usuario) {
        this.personal = (Personal) usuario;
    }

    // Seta o token e o personal como null
    public void setLogout() {
        this.personal = null;
        this.token = null;
    }

    //Retorna o personal
    public Personal getUsuario() {
        return this.personal;
    }
    
    // instância a variável token
    public void setToken(String token) {
       this.token = token;
    }

    // pega a variável token
    public String getToken() {
        return this.token;
    }
}