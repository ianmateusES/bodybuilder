/**
 * Essa classe é o controlador de Exercicio, responsável por ser o intermediário entre as classes que peguam
 * Jsons do backend, o model e a view
 */
package control;

import java.util.ArrayList;
import model.Exercicio;
import serverRequest.RequestExercicioPersonal;
import utils.PersonalLogado;

/**
 *
 * @author vivi
 */
public class ExercicioController {
    private static Exercicio[] exercicios_sistema;
    
    public static void atualizarDadosExercicios(){
       exercicios_sistema = RequestExercicioPersonal.listarExercicios(); // pega o Json do backend em tempo real
    }

    // Lista os exercicios
    public static ArrayList<String> listaExercicios(){
       atualizarDadosExercicios();
       ArrayList<String> exercicio = new ArrayList<String>();
       
       if(exercicios_sistema != null){
            for(int i = 0; i < exercicios_sistema.length; i++){
                if(!exercicios_sistema[i].getAuthor().equalsIgnoreCase("system")){
                    exercicios_sistema[i].setAuthor("meu exercicio");
                }
                exercicio.add(exercicios_sistema[i].getId()+"/"+ exercicios_sistema[i].getName() +"/"+ exercicios_sistema[i].getGroup() + "/" + exercicios_sistema[i].getAuthor());
            }
        }
        
        return exercicio;
    }
    
    public static String getNomeAlunoByID(String id){
        atualizarDadosExercicios(); 
        String nome_exercicio = null;
        
        if(exercicios_sistema != null){
           for(int i = 0; i < exercicios_sistema.length; i++){
               if(exercicios_sistema[i].getId().equalsIgnoreCase(id)){
                   nome_exercicio = exercicios_sistema[i].getName();
                   break;
               }
            }
        }
        return nome_exercicio;
    }
    
     // Retorna o numero de exercicios que não sao dos sistema, sem precisar reacesso ao BD
    public static int numExerciciosUsuario(){
        atualizarDadosExercicios();
        int exercicios = 0;
        
        if(exercicios_sistema != null){
           for(int i = 0; i < exercicios_sistema.length; i++){
               if(!exercicios_sistema[i].getAuthor().equalsIgnoreCase("system")){
                    exercicios++;
               }
            }
        }
        
        return exercicios;
    }

    public static boolean inserirExercicio(String name, String group){
        Exercicio exercicio = new Exercicio("", PersonalLogado.getInstance().getUsuario().getId(), name, group);
        return RequestExercicioPersonal.cadastrarExercicio(exercicio);
    }
    
    //deleta exercicio, sem precisar reacesso ao BD
    public static boolean deletarExercicio(String id){
        return RequestExercicioPersonal.deletarExercicios(id);
    }
    
}
