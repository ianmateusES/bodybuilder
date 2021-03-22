/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverCommunication;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import modelo.Personal;
import org.json.JSONObject;
import utils.PersonalLogado;
import utils.Util;

/**
 *
 * @author vivi
 */
class ResponseLogin {
    Personal personal;
    String token;
}

public class PostLoginPersonal {
    static String baseUrl = "http://localhost:3334";
    static int codSuccess = 200;
    
    public static void login(String email,String password) throws Exception {

        String req = baseUrl + "/sessions";
        try {
            
            URL url = new URL(req);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");
            con.connect();
            
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("email", email);
            jsonParam.put("password", password);
            
            OutputStream out = con.getOutputStream();
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            write.write(jsonParam.toString());
            write.close();
            out.close();
            
            if(con.getResponseCode() != codSuccess) throw new RuntimeException("HTTP error code: " + con.getResponseCode());
            
            BufferedReader res = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String json = Util.converteJsonEmString(res);

            Gson gson = new Gson();
            ResponseLogin reponseLogin = gson.fromJson(json, ResponseLogin.class);
            
            PersonalLogado.getInstance().setLogin(reponseLogin.personal, reponseLogin.token);
     
        } catch(Exception e) {
            throw new Exception("Error: " + e);
        }
    }
}