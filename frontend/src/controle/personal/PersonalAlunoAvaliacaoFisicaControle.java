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
    
    public AvaliacaoFisicaAluno visualizarAvaliacao(){
        avaliacao = personal_dao.visualizarAvaliacao();
        if(avaliacao == null){
            System.err.println("Erro ao visualizar, Banco de Dados\n");
        }
        return avaliacao;
    }
  
}
