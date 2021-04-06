/*
 * 
 */
package interfaces.entidades;

import util.Endereco;

/**
 *
 * @author Vivi
 */
public interface IPersonal{
    
    public Integer getId_personal();
    public void setId_personal(int id_personal);
    
    public Integer getCref();
    public void setCref(int cref);
    
    public Endereco getEndereco();
    public void setEndereco(Endereco endereco);
    
    public boolean estouLogado();
    
     public String getTelefone();
    public void setTelefone(String telefone);

    public String getCelular();
    public void setCelular(String celular);
    
    public String getSenha();
    public void setSenha(String senha);
    
    public Integer getId_usuario();
    public void setId_usuario(int id_usuario);
    
    public abstract String getNome();
    public abstract void setNome(String nome);

    public abstract String getDataNascimento();
    public abstract void setDataNascimento(String dataNascimento);

    public abstract void setEmail(String email);
    public abstract String getEmail();
    
}
