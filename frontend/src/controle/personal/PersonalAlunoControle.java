/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.personal;

import dao.mysql.personal.PersonalAlunoDAO;
import interfaces.entidades.IAluno;
import java.util.ArrayList;

/**
 *
 * @author Vivi
 */
public class PersonalAlunoControle{
    private IAluno aluno;
    private PersonalAlunoDAO personal_dao;
    
    public PersonalAlunoControle(IAluno aluno){
        this.aluno = aluno;
        personal_dao = new PersonalAlunoDAO(aluno);
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
        
        if(aluno.getAnamnese().getAlergias().isEmpty()){
            mensagem = "Erro: Variavel alergia vazia";
            return mensagem;
        }
        
        if(aluno.getAnamnese().getCirurgias().isEmpty()){
            mensagem = "Erro: Variavel cirurgias vazia";
            return mensagem;            
        }
     
        if(aluno.getAnamnese().getMedicamentos().isEmpty()){
            mensagem = "Erro: Variavel medicamentos vazia";
            return mensagem;   
        }
        
        if(aluno.getAnamnese().getVicios().isEmpty()){
            mensagem = "Erro: Variavel vicios vazia";
            return mensagem;   
        }
        
        if(!personal_dao.preCadastrarAluno()){
            mensagem = "Erro: Variavel pré cadastrar vazia";
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

    
}
