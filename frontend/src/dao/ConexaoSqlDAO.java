/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Vivi
 */
public class ConexaoSqlDAO{

    public java.sql.Connection AbrirConexao() {
       
       java.sql.Connection cnx = null;
       
       String driver = "org.postgresql.Driver";
       String url = "jdbc:postgresql://localhost:5432/body";
       String user = "body";
       String password = "123456";
        
        try {
            
            Class.forName(driver);
            cnx = DriverManager.getConnection(url, user, password);

            return cnx;
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados:\n"+e.getMessage(), "Mensagem do Sistema", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public void FecharConexao(java.sql.Connection con) {
        try {
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão com o banco de dados:\n"+e.getMessage(), "Mensagem do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
