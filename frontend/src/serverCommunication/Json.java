/*
 * Classe responsável por enviar e receber Jsons do backend
 */
package serverCommunication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import utils.Util;

/**
 *
 * @author vivi
 */
public class Json {
    private static int codSuccess = 200; // Esse é o código mandado pelo backend caso a requisição tenha sido realizada com sucesso
    
    // Recebe a pasta da requisição, o método e os paramêtros caso existam, se não existirem recebe null
    public String pegarJsonDB(String pastaReq, String metodo, String parametros){
        String json = ""; // Json que será retornada
         try {
            HttpURLConnection con = Connection.BuscarConexao(pastaReq, metodo); // Método da Classe Connection
            //Caso existam parametros a serem mandados eles são enviados pelo bloco abaixo
            if(parametros != null){
                OutputStream out = con.getOutputStream();
                BufferedWriter write = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                write.write(parametros);
                write.close();
                out.close();
            }
            
            // Compara se o código de resposta foi bem sucedido, senão dá erro na tela
            if(con.getResponseCode() != this.codSuccess) throw new RuntimeException("HTTP error code: " + con.getResponseCode()); 
            
            //Pega a resposta enviada pelo backend
            BufferedReader res = new BufferedReader(new InputStreamReader(con.getInputStream()));
            json = Util.converteJsonEmString(res);
            
            Connection.EncerrarConexao(con); // Encerra a conexão
        } catch(Exception e) {
            System.out.println("Error em pegarJsonBD na classe Json: " + e); //Caso algo dê errado
        }
         
        return json; // String que representa o Json enviado pelo backend
    }
    
    
}
