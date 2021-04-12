/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.PersonalAlunoDAO;
import interfaces.IAluno;
import interfaces.IPersonal;
import java.util.ArrayList;

/**
 *
 * @author Vivi
 */
public class PersonalAlunoControle{
    private IAluno aluno;
    private IPersonal personal;
    private PersonalAlunoDAO personal_dao;
    
    public PersonalAlunoControle(IPersonal personal, IAluno aluno){
        this.personal = personal;
        this.aluno = aluno;
        personal_dao = new PersonalAlunoDAO(personal, aluno);
    }
    
    public String preCadastrarAluno() {
        String mensagem = "sucesso";
        
        if(aluno.getNome().isEmpty()){
            mensagem = "Erro: Variavel nome vazia";
            return mensagem;
        }
        
        if(aluno.getEmail().isEmpty()){
            mensagem = "Erro: Variavel email vazia";
            return mensagem;
        }
        
        if(aluno.getDataNascimento().isEmpty()){
            mensagem = "Erro: Variavel data de nascimento vazia";
            return mensagem;
        }
        
        if(!personal_dao.preCadastrarAluno()){
            mensagem = "Erro: email já usado";
            return mensagem;  
        }
        return mensagem;
    }

    public ArrayList<IAluno> listarAlunos() {
        ArrayList<IAluno> alunos = personal_dao.listarAlunos();

        if(alunos == null){
            System.err.println("Erro: Erro listar alunos");
            return null;
        }
        
        return alunos;
    }
    
    public String atulizarAnamnese() {
        String mensagem = "sucesso";
        
        if(!personal_dao.atualizarAnamnese()){
            mensagem = "Erro: atualização de anamnese";
            return mensagem;  
        }
        return mensagem;
    }

    
}
