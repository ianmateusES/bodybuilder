/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabricas;

import entidade.AlunoPadrao;
import interfaces.entidades.IAluno;

/**
 *
 * @author Vivi
 */
public class AlunoFabrica {
    String tipo_mensalidade;
    
    public AlunoFabrica(String tipo_mensalidade){
        this.tipo_mensalidade = tipo_mensalidade;
    }
    
    public AlunoFabrica(){
        this.tipo_mensalidade = "";
    }
      
    public IAluno getAluno(){
        IAluno aluno;
        switch(tipo_mensalidade){
            case "padrao": aluno = new AlunoPadrao();
            default: aluno = new AlunoPadrao();
        }
        return aluno;
    }

    
}
