/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.personal;

import dao.mysql.personal.TreinoDAO;
import entidade.Treino;
import interfaces.entidades.IAluno;
import interfaces.entidades.IPersonal;
import java.util.ArrayList;

/**
 *
 * @author ianma
 */
public class TreinoControle {
    
    private IPersonal personal;
    private IAluno aluno;
    private Treino treino;
    private TreinoDAO treinoDAO;
    
    public TreinoControle(IPersonal personal, IAluno aluno, Treino treino) {
        this.personal = personal;
        this.aluno = aluno;
        this.treino = treino;
        treinoDAO = new TreinoDAO(personal, aluno, treino);
    }
    
    public String cadastroTreino() {
        String mensagem = "sucesso";
        
        if(!treinoDAO.cadastroTreino()){
            mensagem = "Erro: Erro ao cadastrar no Banco de Dados";
            return mensagem;
        }
        return mensagem;
    }
    
    public ArrayList<Treino> listaTreinoAluno() {
        ArrayList<Treino> treinos = treinoDAO.listaTreinoAluno();
        if(treinos == null) {
            System.err.println("Treino voltou null do banco");
        }
        
        return treinos;
    }
    
    public ArrayList<Treino> listaTreinoPersonal() {
        ArrayList<Treino> treinos = treinoDAO.listaTreinoPersonal();
        if(treinos == null) {
            System.err.println("Treino voltou null do banco");
        }
        
        return treinos;
    }
    
    public String atualizarTreino() {
        String mensagem = "sucesso";
        
        if(!treinoDAO.atualiarTreino()){
            mensagem = "Erro: Erro ao alterar treino no Banco de Dados";
        }
        
        return mensagem;
    }
    
    
}
