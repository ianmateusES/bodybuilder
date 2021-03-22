/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverCommunication;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import modelo.Exercicio;
import utils.PersonalLogado;
import utils.Util;

/**
 *
 * @author vivi
 */
public class GetExercicioPersonal {
   static String baseUrl = "http://localhost:3334";
    static int codSuccess = 200;
    
    public static Exercicio[] listaExercicios() throws Exception {
        String req = baseUrl + "/exercicio/me";
        
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
            Exercicio[] exercicios = gson.fromJson(json, Exercicio[].class);
            
            return exercicios;
        } catch(Exception e) {
            throw new Exception("Error: " + e);
        }
    }
}
