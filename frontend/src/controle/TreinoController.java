/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

/**
 *
 * @author vivi
 */
import modelo.ExecucaoExercicio;
import modelo.Treino;
import serverCommunication.PostTreino;

public class TreinoController {
    public static Treino cadastrar(String aluno, ExecucaoExercicio[] exercicios, String objective) {
        try {
            Treino treino = PostTreino.cadastrar(aluno, exercicios, objective);
            return treino;
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        
        return null;
    }
}
