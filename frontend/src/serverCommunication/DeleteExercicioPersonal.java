/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverCommunication;

import java.net.HttpURLConnection;
import java.net.URL;
import utils.PersonalLogado;

/**
 *
 * @author vivi
 */
public class DeleteExercicioPersonal {
   static String baseURL = "http://localhost:3334";
    static int codSuccess = 200;
    
    public static boolean deletar(String _id) throws Exception {
        String req = baseURL + "/exercicio/me/" + _id;
        try {
            URL url = new URL(req);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("authorization", "Bearer " + PersonalLogado.getInstance().getToken());
            con.setRequestMethod("DELETE");
            con.connect();
            
            if(con.getResponseCode() != codSuccess) throw new RuntimeException("HTTP error code: " + con.getResponseCode());
            /*
            BufferedReader res = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String json = Util.converteJsonEmString(res);
            Gson gson = new Gson();
            Exercicio exercicio = gson.fromJson(json, Exercicio.class);
            */
            return true;
            
        } catch(Exception e) {
            throw new Exception("Error: " + e);
        }
    }
}