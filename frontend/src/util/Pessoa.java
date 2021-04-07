/**
 * 
 */
package util;

/**
 *
 * @author Vivi
 */
public class Pessoa{
    
    void Pessoa(){}
    
    private int id_usuario;
    private String nome;
    private String dataNascimento;
    private String email;

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
