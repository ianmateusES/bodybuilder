/**
 * Classe reponsável pela conexão com o backend, que está em contato com o MongoDB
 */
package serverCommunication;

import java.net.HttpURLConnection;
import java.net.URL;
import utils.PersonalLogado;

/**
 *
 * @author vivi
 */
public class Connection {
    static String baseUrl = "http://localhost:3334"; //local onde o MongoDB está rodando

    //Essa classe tenta se conectar com o backend, ela recebe o método da rota do Json e a pasta que o método do backend se encontra
    public static HttpURLConnection BuscarConexao(String pastaReq, String metodo){
        HttpURLConnection con = null; // Objeto da conection
        try {
            URL url = new URL(baseUrl + pastaReq); // Juntamos a url onde o BD está rodado e a pasta da requisição do backend
            con = (HttpURLConnection) url.openConnection(); // Abrindo conexão
            con.setDoOutput(true); // Caso algo precise ser mandado para o backend para retornar os dados
            
            // Seta o tipo de conexão
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            
            //Caso o personal esteja logado, isso significa que ele precisa de autorização enviando seu token pro backend
            if(PersonalLogado.getInstance().getToken() != null){
                System.out.println("passando o token");
                con.setRequestProperty("authorization", "Bearer " + PersonalLogado.getInstance().getToken());
            }
            
            con.setRequestMethod(metodo); // Instancia o método que pegará o Json, geralmente "POST" ou "GET"
            con.connect(); // Realiza a conexão

        } catch(Exception e) {
            System.out.println("Error BuscarConexao na classe Connection: " + e); // Erro caso algo dê errado
        }
        return con; // Objeto conexão ou null
    }
    
    // Tenta encerrar a Conexão, recebendo a conexão que está aberta
    public static void EncerrarConexao(HttpURLConnection con){
        try {
            con.disconnect(); // Método para encerar a conexão
        } catch(Exception e) {
            System.out.println("Error em EncerrarConexao na classe Connection: " + e); // Erro caso algo dê errado
        }
    }
}
