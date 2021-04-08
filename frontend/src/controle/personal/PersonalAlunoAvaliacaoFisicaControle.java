/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.personal;

import Entidade.AvaliacaoFisicaAluno;
import dao.mysql.personal.PersonalAlunoAvaliacaoFisicaDAO;
import interfaces.entidades.IAluno;

/**
 *
 * @author Vivi
 */
public class PersonalAlunoAvaliacaoFisicaControle {
    IAluno aluno;
    AvaliacaoFisicaAluno avaliacao;
    private PersonalAlunoAvaliacaoFisicaDAO personal_dao;
    
    public PersonalAlunoAvaliacaoFisicaControle(IAluno aluno, AvaliacaoFisicaAluno avaliacao){
        this.aluno = aluno;
        this.avaliacao = avaliacao;
        personal_dao = new PersonalAlunoAvaliacaoFisicaDAO(this.aluno, this.avaliacao);
    }
    
    public String cadastrarAvaliacao(){
        String mensagem = "sucesso";
        if(personal_dao.cadastrarAvaliacao()){
            mensagem = "Erro ao cadastrar, Banco de Dados";
            return mensagem;
        }
        return mensagem;
    }
    
    public String visualizarAvaliacao(){
        String mensagem = "sucesso";
        if(!personal_dao.visualizarAvaliacao() || avaliacao.getId_avaliacao() != 0.0){
            mensagem = "Aluno sem avaliação cadastrada";
        }
        return mensagem;
    }
  
}
