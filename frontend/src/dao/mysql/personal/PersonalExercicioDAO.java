/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql.personal;

import dao.ConexaoSqlDAO;
import entidade.Exercicio;
import interfaces.entidades.IPersonal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Vivi
 */
public class PersonalExercicioDAO {
    ConexaoSqlDAO con;
    IPersonal personal;
    Exercicio exercicio;
    
    public PersonalExercicioDAO(IPersonal personal, Exercicio exercicio){
        this.personal = personal;
        this.exercicio = exercicio;
        this.con = new ConexaoSqlDAO(); 
    }
    
    public boolean cadastrar(){
        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conector = con.AbrirConexao();
            
            String sql = "insert into exercicio(id_personal, nome, categoria) values (?, ?, ?);";
            
            pst = conector.prepareStatement(sql);
            pst.setInt(1, personal.getId_personal());
            pst.setString(2, exercicio.getExercicio());
            pst.setString(3, exercicio.getCategoria());
            
            if(!(pst.executeUpdate() > 0)) return false; 
            
            pst.close();
            con.FecharConexao(conector);
            
        } catch(Exception e) {
            System.err.println("Banco: "+e.getMessage());
        }

        return true;
    }
    public boolean alterar(){
        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        boolean retorno = false;
        
        try {
            conector = con.AbrirConexao();
            String sql = "update exercicio e set e.nome = ?, e.categoria = ? where e.id_exercicio = ? and e.id_personal = ?";
                    
            pst = conector.prepareStatement(sql);
            
            pst.setString(1, exercicio.getExercicio());
            pst.setString(2, exercicio.getCategoria());
            pst.setInt(3, exercicio.getId_exercicio());
            pst.setInt(4, personal.getId_personal());
            
            retorno = pst.execute();
            pst.close();
            con.FecharConexao(conector);
        }catch(Exception e) {
            System.err.println("Banco: " + e.getMessage());
        }
        return retorno;
    }
    public ArrayList<Exercicio> visualizar(){
        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        ArrayList<Exercicio> exercicios = new ArrayList<Exercicio>();
        try {
            conector = con.AbrirConexao();
            
            String sql = "select e.id_exercicio, e.nome, e.categoria from exercicio e where e.id_personal = ?;";
            
            pst = conector.prepareStatement(sql);
            pst.setInt(1, personal.getId_personal());
            
            rs = pst.executeQuery();
            while(rs.next()) {
                Exercicio findExercicio = new Exercicio();
                
                findExercicio.setId_exercicio(rs.getInt("id_exercicio"));
                findExercicio.setExercicio(rs.getString("nome"));
                findExercicio.setCategoria(rs.getString("categoria"));
                
                exercicios.add(findExercicio);
            }
            
            pst.close();
            rs.close();
            con.FecharConexao(conector);
            
        } catch(Exception e) {
            System.err.println("Banco: "+e.getMessage());
        }
        
        return exercicios;
    }

}
