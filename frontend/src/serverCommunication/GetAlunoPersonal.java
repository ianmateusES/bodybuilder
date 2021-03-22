package serverCommunication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import modelo.Aluno;
import utils.PersonalLogado;
import utils.Util;

public class GetAlunoPersonal {
    static String baseUrl = "http://localhost:3334";
    static int codSuccess = 200;
    
    public static Aluno[] listeAlunos() throws Exception {

        String req = baseUrl + "/personal-alunos";
        try {
            
            URL url = new URL(req);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("authorization", "Bearer " + PersonalLogado.getInstance().getToken());
            con.setRequestMethod("GET");
            con.connect();
            
            if(con.getResponseCode() != codSuccess) throw new RuntimeException("HTTP error code: " + con.getResponseCode());
            
            BufferedReader res = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String json = Util.converteJsonEmString(res);

            Gson gson = new Gson();
            Aluno[] alunos = gson.fromJson(json, Aluno[].class);
            
            return alunos;
        } catch(Exception e) {
            throw new Exception("Error: " + e);
        }
    }
}