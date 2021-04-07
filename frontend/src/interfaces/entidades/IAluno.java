/*
 * 
 */
package interfaces.entidades;

import entidade.Anamnese;

/**
 *
 * @author Vivi
 */
public interface IAluno{
    public int getId_aluno();
    public void setId_aluno(int id_aluno);
    
    public Anamnese getAnamnese();
    public void setAnamnese(Anamnese anamnese);
    
    public Integer getId_usuario();
    public void setId_usuario(int id_usuario);
    
    public abstract String getNome();
    public abstract void setNome(String nome);

    public abstract String getDataNascimento();
    public abstract void setDataNascimento(String dataNascimento);

    public abstract void setEmail(String email);
    public abstract String getEmail();
}
