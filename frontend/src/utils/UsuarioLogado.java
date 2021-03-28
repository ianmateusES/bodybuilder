/**
 * Classe Abstrata para usuário, futuramente o software pode ser expandido
 * para comportar mais tipos de usuário e todos eles terão premissas bem parecidas quando
 * logados
 */
package utils;

import model.Usuario;

public abstract class UsuarioLogado {
    public abstract void setLogin(Usuario usuario, String token);
    public abstract void setUpdateUser(Usuario usuario);
    public abstract void setLogout();
    public abstract Usuario getUsuario();
    public abstract void setToken(String token);
    public abstract String getToken();
}