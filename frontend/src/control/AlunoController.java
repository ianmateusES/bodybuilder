/**
 * Essa classe é o controlador de Aluno, responsável por ser o intermediário entre as classes que peguam
 * Jsons do backend, o model e a view
 */
package control;

import java.util.ArrayList;
import model.Aluno;
import serverRequest.RequestAlunoPersonal;
import utils.Util;

/**
 *
 * @author vivi
 */
public class AlunoController {
    private static Aluno[] alunos_sistema = null; // Varivável que guardará a lista vinda do backend
    
    private static void atualizarDadosAlunos(){
       alunos_sistema = RequestAlunoPersonal.listeAlunos(); // pega o Json do backend em tempo real
    }
    
    // Pega a lista enviada do backend e manda uma String com os alunos para view ou null
    public static ArrayList<String> listaAlunosUsuario(){
        atualizarDadosAlunos(); 
        ArrayList<String> alunos = new ArrayList<String>();
        
        if(alunos_sistema != null){
           for(int i = 0; i < alunos_sistema.length; i++){
               if(alunos_sistema[i].getStatus().equalsIgnoreCase("true")){
                   alunos_sistema[i].setStatus("Ativo");
               }else{
                    alunos_sistema[i].setStatus("Inativo");
               }
                alunos.add(alunos_sistema[i].getId() + "/"+ alunos_sistema[i].getName() +"/"+ alunos_sistema[i].getStatus());
            }
        }
        
        return alunos;
    }
    
    // Calcula o número de aniversáriantes da lista
    public static int numAniversariantes(){
        atualizarDadosAlunos(); 

        int aniversariantes = 0;
        
        if(alunos_sistema != null){
            long millis = System.currentTimeMillis();  
            java.sql.Date data = new java.sql.Date(millis);  

            String[] data_hoje = data.toString().split("-");
            String mes_dia_hoje = data_hoje[1]+data_hoje[2];
        
            for(int i = 0; i < alunos_sistema.length; i++){
                String[] data_aniversario = alunos_sistema[i].getBirthday().split("-");
                String mes_dia_aniversario = data_aniversario[1] + data_aniversario[2];
                    
                if(mes_dia_hoje.equals(mes_dia_aniversario)){
                    aniversariantes++;
                }
            }
        }
        
        return aniversariantes;
    }
    
    // Calcula o número de aniversáriantes da lista
    public static int numAlunosAtivos(){
        atualizarDadosAlunos(); 

        int alunos_ativos = 0;
        
        if(alunos_sistema != null){
        
            for(int i = 0; i < alunos_sistema.length; i++){
                    
                if(alunos_sistema[i].getStatus().equalsIgnoreCase("true")){
                    alunos_ativos++;
                }
            }
        }
        
        return alunos_ativos;
    }
    
    //pega os dados mandados pela view, coloca-os no model Aluno e faz a requisição de cadastro com RequestAlunoPersonal 
    public static boolean inserirAlunoUsuario(String name, String email, String password, String password_confirmation, String birthday){
        Aluno aluno = new Aluno("",  name,  email,  password,  password_confirmation,  Util.converteData(birthday),  "");

        return RequestAlunoPersonal.cadastrarAluno(aluno);
    }
    
  
}
