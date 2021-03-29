/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverRequest;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import utils.ExercicioList;
import model.Exercicio;
import model.TreinoAluno;
import serverCommunication.Json;
import utils.PersonalLogado;
import utils.Util;

/**
 *
 * @author vivi
 */
public class ResquestTreinoAluno {

    public static TreinoAluno[] listeTreinosAluno(String id_aluno){
        String pastaReq = "/treino-aluno/"+id_aluno;
        String metodo = "GET";

        String json = new Json().pegarJsonDB(pastaReq, metodo, null);
        
        TreinoAluno[] treinoAluno = new Gson().fromJson(json, TreinoAluno[].class);
        System.out.println(json);

        return treinoAluno;
    }
    
    public static boolean deletarTreinoAluno(String id_aluno, String id_treino){
        String pastaReq = "/treino-aluno/"+id_aluno+"/treino/"+id_treino;
        String metodo = "DELETE";

        String json = new Json().pegarJsonDB(pastaReq, metodo, null);
        
        return new Gson().fromJson(json, boolean.class);
    }
    
    
    public static boolean inserirTreino(String aluno, String objetivo, ArrayList<ExercicioList> exercicio){
        String pastaReq = "/treinos";
        String metodo = "POST";
        
        String parametros = Util.converteObjetoExercicioEmJson(aluno, objetivo,exercicio);
        System.out.println(parametros);
        String json = new Json().pegarJsonDB(pastaReq, metodo, parametros);
        new Gson().fromJson(json, TreinoAluno[].class);
        return false;
    }

}
