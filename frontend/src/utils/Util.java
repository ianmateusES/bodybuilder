/**
 * Classe de utilitários, ela servirá primordialmente pra tranformar os Jsons passados 
 * pela API que se comunica com um BD não relacional no MongoDB em Strings
 */
package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;

public class Util {
    public static String converteJsonEmString(BufferedReader buffereReader) throws IOException {
        String resposta, jsonEmString = "";
        while ((resposta = buffereReader.readLine()) != null) {
            jsonEmString += resposta;
        }
        return jsonEmString;
    }
    //Pega uma data passada pela interface do usuário e tranforma em uma string compatível com o BD
    public static String converteData(String data){
        String[] dataSeparada = data.split("/");
        String parseDate = dataSeparada[2] + "-" + dataSeparada[1] + "-" + dataSeparada[0];
        return parseDate;
    }
    // Pega duas Strings com nomes das linhas no bd "argumentos" e os valores que devem ser passadas para elas "valores" e tranforma em um Json
    public static String converteStringEmJson(String[] argumentos, String[] valores){
        String parametros = null;
        if(argumentos.length > 0 && argumentos.length == valores.length){
            JSONObject jsonParam = new JSONObject();

            for(int i = 0; i < argumentos.length; i++){
                jsonParam.put(argumentos[i], valores[i]);
            }
            parametros = jsonParam.toString();
        }
        return parametros;
    }
    
    public static String converteObjetoExercicioEmJson(String aluno, String objetivo, ArrayList<ExercicioList> exercicio){
        String parametros = null;
        JSONObject jsonParam = new JSONObject();

        jsonParam.put("objective", objetivo);

        jsonParam.put("aluno", aluno);

        jsonParam.put("exercise_list", exercicio);
        parametros = jsonParam.toString();

        return parametros;
    }
}
