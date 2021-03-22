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
import utils.Util;

/**
 *
 * @author vivi
 */
public class PostCadastroPersonal {
    static String baseUrl = "http://localhost:3334";
    static int codSuccess = 200;
    
    public static Personal cadastro(String name, String email, String password, String password_confirmation,String birthday, String telephone, String cref, String address) throws Exception {

        String req = baseUrl + "/personais";
        try {
            
            URL url = new URL(req);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");
            con.connect();

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("name", name);
            jsonParam.put("email", email);
            jsonParam.put("password", password);
            jsonParam.put("password_confirmation", password_confirmation);
            jsonParam.put("birthday", birthday);
            jsonParam.put("telephone", telephone);
            jsonParam.put("cref", cref);
            jsonParam.put("address", address);
            
            OutputStream out = con.getOutputStream();
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            write.write(jsonParam.toString());
            write.close();
            out.close();
            
            if(con.getResponseCode() != codSuccess) throw new RuntimeException("HTTP error code: " + con.getResponseCode());
            
            BufferedReader res = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String json = Util.converteJsonEmString(res);


            Gson gson = new Gson();
            Personal personal = gson.fromJson(json, Personal.class);
            
            return personal;
        } catch(Exception e) {
            throw new Exception("Error: " + e);
        }
    }
}
