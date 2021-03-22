/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.ArrayList;
import java.util.Arrays;
import modelo.Exercicio;
import serverCommunication.DeleteExercicioPersonal;
import serverCommunication.GetExercicioPersonal;
import serverCommunication.PostExercicioPersonal;

/**
 *
 * @author vivi
 */
public class ExercicioController {
    
    public static ArrayList<Exercicio> listaExercicios(){
        try {
            Exercicio[] exercicios_sistema = GetExercicioPersonal.listaExercicios();
            ArrayList<Exercicio> exercicios = new ArrayList<Exercicio>(Arrays.asList(exercicios_sistema));
            
            return exercicios;
            
        } catch(Exception e) {
            System.out.println("Erro"+e);
        } 
        
        return null;
    }

    public static boolean insereExercicio(String name, String group){
        try {
            PostExercicioPersonal.cadastrarExercicio(name, group);
            return true;
        } catch(Exception e) {
            System.out.println("Erro: "+e);
        } 
        return false;
        
    }

    public static boolean deletarExercicio(String _id){
        try {
            DeleteExercicioPersonal.deletar(_id);
            return true;
        } catch(Exception e) {
            System.out.println("Erro: "+e);
        }
        return false;
    }
    
}
