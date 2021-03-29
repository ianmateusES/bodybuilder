/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author vivi
 */
import java.util.ArrayList;
import utils.ExercicioList;
import model.TreinoAluno;
import serverRequest.ResquestTreinoAluno;

public class TreinoAlunoController {
    private static TreinoAluno[] treinos_sistema = null; // Varivável que guardará a lista vinda do BD
    
    public static void atualizarDadosTreino(String id_aluno){
       treinos_sistema = ResquestTreinoAluno.listeTreinosAluno(id_aluno); // pega o Json do BD em tempo real
    }
    
     // Pega a lista enviada do BD e manda uma String com os alunos para view ou null
    public static ArrayList<String> listaTreinoAluno(String aluno_id){
        atualizarDadosTreino(aluno_id);
        ArrayList<String> treinos = new ArrayList<String>();
        
        if(treinos_sistema != null){
           for(int i = 0; i < treinos_sistema.length; i++){
                treinos.add(treinos_sistema[i].getId()+"/"+ treinos_sistema[i].getObjective());
            }
        }
        return treinos;
    }
    
     // Pega a lista enviada do BD e manda uma String com os alunos para view ou null
    public static boolean cadastrarTreinoAluno(String aluno_id, String objetivo,  ArrayList<ExercicioList> exer){
       
        return ResquestTreinoAluno.inserirTreino(aluno_id, objetivo, exer);
    }
    
    
    
}
