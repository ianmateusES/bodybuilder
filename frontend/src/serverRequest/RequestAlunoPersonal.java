package serverRequest;

import com.google.gson.Gson;
import model.Aluno;
import serverCommunication.Json;
import utils.Util;

public class RequestAlunoPersonal {
    
    public static Aluno[] listeAlunos(){
        String pastaReq = "/personal-alunos";
        String metodo = "GET";

        String json = new Json().pegarJsonDB(pastaReq, metodo, null);
        Aluno[] alunos = new Gson().fromJson(json, Aluno[].class);

        return alunos;
    }
    
    public static boolean cadastrarAluno(Aluno aluno){
        String pastaReq = "/personal-alunos";
        String metodo = "POST";

        String [] argumentos = {"name", "email", "password", "password_confirmation", "birthday"};
        String [] valores = {aluno.getName(), aluno.getEmail(), aluno.getPassword(), aluno.getPassword_confirmation(), aluno.getBirthday()};
        String parametros = Util.converteStringEmJson(argumentos, valores);

        String json = new Json().pegarJsonDB(pastaReq, metodo, parametros);
        Aluno alunos = new Gson().fromJson(json, Aluno.class);
            
        return true;
    }
}