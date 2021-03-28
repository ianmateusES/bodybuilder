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
import model.ExercicioList;
import model.TreinoAluno;
import serverRequest.ResquestTreinoAluno;

public class TreinoAlunoController {
    private static TreinoAluno[] treinos_sistema = null; // Varivável que guardará a lista vinda do BD
    
    public TreinoAlunoController(String id_aluno){
        this.atualizarDadosTreino(id_aluno); 
    }   
    
    public static void atualizarDadosTreino(String id_aluno){
      // treinos_sistema = RequestTreinoAluno.listeTreinosAluno(id_aluno); // pega o Json do BD em tempo real
    }
    
     // Pega a lista enviada do BD e manda uma String com os alunos para view ou null
    public static String[] listaTreinoAluno(String aluno_id){
        String [] alunos = null;
        
        if(treinos_sistema != null){
           for(int i = 0; i < treinos_sistema.length; i++){
                alunos[i] = i+" "+ treinos_sistema[i].getAluno() +" "+ treinos_sistema[i].getObjective();
            }
        }
        
        return alunos;
    }
    
    // Procura os alunos dentro da lista, sem precisar reacesso ao BD
    public static String[] procuraTreino(String aluno){
        String [] treinos = null;
        
        if(treinos_sistema != null){
           for(int i = 0; i < treinos_sistema.length; i++){
               if(treinos_sistema[i].getAluno().contains(aluno)){
                   // treinos[i] = alunos_sistema[i].getId()+" "+ alunos_sistema[i].getName() +" "+ alunos_sistema[i].getEmail();
               }
            }
        }
        
        return treinos;
    }
    
    
}
