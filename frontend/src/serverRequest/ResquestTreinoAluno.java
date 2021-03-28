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
import model.ExercicioList;
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

    /*public static boolean cadastrarTreino(String aluno, ExercicioList[] exercicios, String objective) throws Exception {
        String pastaReq = "/treino";
        String metodo = "POST";
        String [] argumentos = {"aluno","objective", "exercise_list"};
        String [] valores = {exercicio.getAuthor(),exercicio.getName(), exercicio.getGroup()};

        String parametros = Util.converteStringEmJson(argumentos, valores);
        String json = new Json().pegarJsonDB(pastaReq, metodo, parametros);
        TreinoAluno personal = new Gson().fromJson(json, TreinoAluno.class);
        
    }
    
     public static boolean deletarTreino(String aluno, ExercicioList[] exercicios, String objective) throws Exception {
        String pastaReq = "/treino";
        String metodo = "POST";
        String [] argumentos = {"aluno","objective", "exercise_list"};
        String [] valores = {exercicio.getAuthor(),exercicio.getName(), exercicio.getGroup()};

        String parametros = Util.converteStringEmJson(argumentos, valores);
        String json = new Json().pegarJsonDB(pastaReq, metodo, parametros);
        TreinoAluno personal = new Gson().fromJson(json, TreinoAluno.class);
        
    }
     
      public static boolean listarTreino(String aluno, ExercicioList[] exercicios, String objective) throws Exception {
        String pastaReq = "/treino";
        String metodo = "POST";
        String [] argumentos = {"aluno","objective", "exercise_list"};
        String [] valores = {exercicio.getAuthor(),exercicio.getName(), exercicio.getGroup()};

        String parametros = Util.converteStringEmJson(argumentos, valores);
        String json = new Json().pegarJsonDB(pastaReq, metodo, parametros);
        TreinoAluno personal = new Gson().fromJson(json, TreinoAluno.class);
        
    }
      
      
       public static boolean atualizarTreino(String aluno, ExercicioList[] exercicios, String objective) throws Exception {
        String pastaReq = "/treino";
        String metodo = "POST";
        String [] argumentos = {"aluno","objective", "exercise_list"};
        String [] valores = {exercicio.getAuthor(),exercicio.getName(), exercicio.getGroup()};

        String parametros = Util.converteStringEmJson(argumentos, valores);
        String json = new Json().pegarJsonDB(pastaReq, metodo, parametros);
        TreinoAluno personal = new Gson().fromJson(json, TreinoAluno.class);
        
    }*/
}
