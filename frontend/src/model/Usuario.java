/**
 * Classe abstrata Usuário, essa classe serve como um molde com os atributos principais que
 * um usuário deve ter, dependendo do usuário talvez ele precise de mais campos, porém
 * aqui estão os dados essênciais
 * Serve como um molde para futuras extensões de mais usuários
 */
package model;

/**
 *
 * @author vivi
 */
public abstract class Usuario {
    public abstract String getId();
    public abstract void setId(String _id);
    public abstract String getName();
    public abstract void setName(String name);
    public abstract String getEmail();
    public abstract void setEmail(String email);
    public abstract String getPassword();
    public abstract void setPassword(String password);
    public abstract String getTelephone();
    public abstract void setTelephone(String telephone);
    public abstract String getAddress();
    public abstract void setAddress(String city);
    public abstract String getBirthday();
    public abstract void setBirthday(String birthday);
}
