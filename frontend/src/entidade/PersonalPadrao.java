package entidade;

import interfaces.IPersonal;
import util.Endereco;
import util.Pessoa;

public class PersonalPadrao extends Pessoa implements IPersonal{

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getId_personal() {
        return id_personal;
    }

    public void setId_personal(int id_personal) {
        this.id_personal = id_personal;
    }
    
    public Integer getCref() {
        return cref;
    }
    
    public void setCref(int cref) {
        this.cref = cref;
    }
    
    public boolean estouLogado() {
        return (this.getId_usuario() != null);
    }
    
    private int id_personal;
    private int cref;
    private Endereco endereco;
    private String telefone;
    private String celular;
    private String senha;

    public PersonalPadrao() {};

   
}
