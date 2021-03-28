
import control.AlunoController;
import control.ExercicioController;
import control.SessionController;
import java.util.ArrayList;
import model.Personal;
import model.Usuario;

/**
 *
 * @author vivi
 */
public class Test {
    public static void main(String[] args) {
        boolean logado = SessionController.login("personal", "ian@gmail.com", "123456");
        if(!logado){
            System.out.println("Deu ruim");
        }else{
            System.out.println("Voce esta logado");
            ArrayList<String> alunos = AlunoController.listaAlunosUsuario();
            
            if(alunos != null){
                for(int i = 0; i < alunos.size(); i++){
                    System.out.println(alunos.get(i));
                }
            }else{
                System.out.println("Sem exercicios");
            }
            
           // alunos = AlunoController.procuraAlunoUsuario("a");
            
             if(alunos != null){
                for(int i = 0; i < alunos.size(); i++){
                    System.out.println(alunos.get(i));
                }
            }else{
                System.out.println("Sem exercicios");
            }
           
            SessionController.setLogout();
        }
    }
    
}
