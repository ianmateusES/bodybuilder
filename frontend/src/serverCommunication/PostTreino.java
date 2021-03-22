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
import modelo.ExecucaoExercicio;
import modelo.Treino;
import org.json.JSONObject;
import utils.PersonalLogado;
import utils.Util;

/**
 *
 * @author vivi
 */
public class PostTreino {

    static String baseURL = "http://localhost:3334";
    static int codSuccess = 200;

    public static Treino cadastrar(String aluno, ExecucaoExercicio[] exercicios, String objective) throws Exception {

        String req = baseURL + "/treino";

        try {
            URL url = new URL(req);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("authorization", "Bearer " + PersonalLogado.getInstance().getToken());
            con.setRequestMethod("POST");
            con.connect();

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("aluno", aluno);
            jsonParam.put("objective", objective);
            jsonParam.put("exercise_list", exercicios);
            jsonParam.put("personal", PersonalLogado.getInstance().getPersonal().getId());

            OutputStream out = con.getOutputStream();
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            write.write(jsonParam.toString());
            write.close();
            out.close();

            if (con.getResponseCode() != codSuccess) {
                throw new RuntimeException("HTTP error code: " + con.getResponseCode());
            }

            BufferedReader res = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String json = Util.converteJsonEmString(res);

            Gson gson = new Gson();
            Treino personal = gson.fromJson(json, Treino.class);

            return personal;

        } catch (Exception e) {
            throw new Exception("Erro: " + e);
        }

    }
}
