/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.personal;

import dao.mysql.personal.PersonalExercicioDAO;
import entidade.Exercicio;
import interfaces.entidades.IPersonal;
import java.util.ArrayList;

/**
 *
 * @author Vivi
 */
public class PersonalExercicioControle{
    
    private IPersonal personal;
    private PersonalExercicioDAO personal_dao;
    Exercicio exercicio;
    
    public PersonalExercicioControle(IPersonal personal, Exercicio exercicio){
        this.personal = personal;
        this.exercicio = exercicio;
        personal_dao = new PersonalExercicioDAO(personal, exercicio);
    }
    
    public String cadastrarExercicio() {
        String mensagem = "sucesso";
        
        if(personal == null){
            mensagem = "Erro: personal nulo";
            return mensagem;
        }
        if(exercicio == null){
            mensagem = "Erro: exercicio nulo";
            return mensagem;
        }
        if(personal.getId_personal() == null){
            mensagem = "Erro: id personal nulo";
            return mensagem;
        }
        
        if(exercicio.getExercicio().isEmpty()){
            mensagem = "Erro: nome exercicio em branco";
            return mensagem;
        }
        
        if(exercicio.getCategoria().isEmpty()){
            mensagem = "Erro: categoria exercicio em branco";
            return mensagem;
        }
        
        if(!personal_dao.cadastrar()){
            mensagem = "Erro: cadastrar exercicio no banco de dados";
            return mensagem;
        }
        
        return mensagem;
    }

    public ArrayList<Exercicio> visualizarExercicios() {
        ArrayList<Exercicio> exercicios = personal_dao.visualizar();
        if(exercicios == null){
            System.err.println("Erro ao visualizar, lista de exercicios nula");
        }
        return exercicios;
    }

    public String alterarExercicio() {
        String mensagem = "sucesso";
        
        if(personal == null){
            mensagem = "Erro: personal nulo";
            return mensagem;
        }
        if(exercicio == null){
            mensagem = "Erro: exercicio nulo";
            return mensagem;
        }
        if(personal.getId_personal() == null){
            mensagem = "Erro: id personal nulo";
            return mensagem;
        }
        
        if(exercicio.getExercicio().isEmpty()){
            mensagem = "Erro: nome exercicio em branco";
            return mensagem;
        }
        
        if(exercicio.getCategoria().isEmpty()){
            mensagem = "Erro: categoria exercicio em branco";
            return mensagem;
        }
        
        if(!personal_dao.alterar()){
            mensagem = "Erro: alterar exercicio no banco de dados";
            return mensagem;
        }
        return mensagem;
    }
    
}
