package controle;

import java.util.ArrayList;
import java.util.Arrays;
import modelo.Aluno;
import serverCommunication.GetAlunoPersonal;

/**
 *
 * @author vivi
 */
public class AlunoController {
    public static ArrayList<Aluno> pegarAlunos(){
        try{
            Aluno[] alunos_sistema = GetAlunoPersonal.listeAlunos();
            ArrayList<Aluno> alunos = new ArrayList<Aluno>(Arrays.asList(alunos_sistema));
            return alunos;

        }catch(Exception e){
            System.out.println("Erro: "+e);
        }
        
        return null;
    }
}
