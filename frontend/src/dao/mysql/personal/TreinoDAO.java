/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql.personal;

import dao.ConexaoSqlDAO;
import entidade.AlunoPadrao;
import entidade.Exercicio;
import entidade.ExercicioTreino;
import entidade.Treino;
import interfaces.entidades.IAluno;
import interfaces.entidades.IPersonal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ianma
 */
public class TreinoDAO {
    ConexaoSqlDAO con;
    IPersonal personal;
    IAluno aluno;
    Treino treino;
    
    public TreinoDAO(IPersonal personal, IAluno aluno, Treino treino){
        this.personal = personal;
        this.aluno = aluno;
        this.treino = treino;
        con = new ConexaoSqlDAO(); 
    }
    
    public boolean cadastroTreino() {
        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            String sql = "insert into treino(id_personal, id_aluno, objetivo) values (?, ?, ?);";
            conector = con.AbrirConexao();
            
            pst = conector.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, treino.getPersonal().getId_personal());
            pst.setInt(2, treino.getAluno().getId_aluno());
            pst.setString(3, treino.getObjetivo());
            
            if(!(pst.executeUpdate() > 0)) return false;
            
            rs = pst.getGeneratedKeys();
            rs.next();
            int id_treino = rs.getInt(1);
            
            treino.setId_treino(id_treino);
            
            sql = "insert into exercicio_treino(id_treino, id_exercicio, divisao, series, repeticoes, descricao) values (?, ?, ?, ?, ?, ?);";
            for(ExercicioTreino exercioTreino : treino.getExercicioTreino()) {
                pst = conector.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pst.setInt(1, id_treino);
                pst.setInt(2, exercioTreino.getExercicio().getId_exercicio());
                pst.setString(3, exercioTreino.getDivisao());
                pst.setInt(4, exercioTreino.getSeries());
                pst.setInt(5, exercioTreino.getRepeticoes());
                pst.setString(6, exercioTreino.getDescricao());
                if(!(pst.executeUpdate() > 0)) return false;
                
                rs = pst.getGeneratedKeys();
                rs.next();
                int id_exercicioTreino = rs.getInt(1);
                exercioTreino.setId_exercicioTreino(id_exercicioTreino);
            }
            
            pst.close();
            con.FecharConexao(conector);
        } catch (Exception e) {
            System.err.println("Banco: "+e.getMessage());
            return false;
        }
        
        return true;
    }
    
    public boolean atualiarTreino() {
        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "update treino "
                 + "set objetivo = ? "
                 + "where id_treino = ?;";
        try {
            conector = con.AbrirConexao();
            
            pst = conector.prepareStatement(sql);
            pst.setString(1, treino.getObjetivo());
            pst.setInt(2, treino.getId_treino());
            
            if(pst.execute()) return false;
            
            sql = "update exercicio_treino "
                 + "set id_exercicio = ?, divisao = ?, series = ?, repeticoes = ?, descricao = ? "
                 + "where id_exerciciotreino = ? and id_treino = ?;";
            
            for(ExercicioTreino exercioTreino : treino.getExercicioTreino()) {
                pst = conector.prepareStatement(sql);
                pst.setInt(1, exercioTreino.getExercicio().getId_exercicio());
                pst.setString(2, exercioTreino.getDivisao());
                pst.setInt(3, exercioTreino.getSeries());
                pst.setInt(4, exercioTreino.getRepeticoes());
                pst.setString(5, exercioTreino.getDescricao());
                pst.setInt(6, exercioTreino.getId_exercicioTreino());
                pst.setInt(7, treino.getId_treino());
                
                if(pst.execute()) return false;
            }
            
            pst.close();
            con.FecharConexao(conector);
        } catch (Exception e) {
            System.err.println("Banco: " + e.getMessage());
            return false;
        }
        
        return true;
    }
    
    public ArrayList<Treino> listaTreinoAluno() {
        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        PreparedStatement pstExercicio = null;
        ResultSet rsExercicio = null;
        
        ArrayList<Treino> treinos = new ArrayList<Treino>();
        try {
            conector = con.AbrirConexao();
            String sql = "select * from treino t where t.id_personal = ? and t.id_aluno = ? order by t.id_treino desc;";
            pst = conector.prepareStatement(sql);
            pst.setInt(1, personal.getId_personal());
            pst.setInt(2, aluno.getId_aluno());
            rs = pst.executeQuery();
            
            Treino newTreino;
            ArrayList<ExercicioTreino> exercicioTreino;
            ExercicioTreino newExercicioTreino;
            Exercicio newExercicio;
            while(rs.next()) {
                newTreino = new Treino();
                newTreino.setId_treino(rs.getInt("id_treino"));
                newTreino.setAluno(aluno);
                newTreino.setObjetivo(rs.getString("objetivo"));

                sql = "select * from exercicio_treino et join exercicio e on (et.id_exercicio = e.id_exercicio) where et.id_treino = ?;";
                pstExercicio = conector.prepareStatement(sql);
                pstExercicio.setInt(1, newTreino.getId_treino());
                rsExercicio = pstExercicio.executeQuery();
                exercicioTreino = new ArrayList<ExercicioTreino>();
                while(rsExercicio.next()) {
                    newExercicioTreino = new ExercicioTreino();
                    newExercicio = new Exercicio();
                    
                    newExercicioTreino.setId_exercicioTreino(rsExercicio.getInt("id_exercicioTreino"));
                    newExercicioTreino.setDivisao(rsExercicio.getString("divisao"));
                    newExercicioTreino.setSeries(rsExercicio.getInt("series"));
                    newExercicioTreino.setRepeticoes(rsExercicio.getInt("repeticoes"));
                    newExercicioTreino.setDescricao(rsExercicio.getString("descricao"));
                    
                    newExercicio.setId_exercicio(rsExercicio.getInt("id_exercicio"));
                    newExercicio.setExercicio(rsExercicio.getString("nome"));
                    newExercicio.setCategoria(rsExercicio.getString("categoria"));
                    
                    newExercicioTreino.setExercicio(newExercicio);
                    
                    exercicioTreino.add(newExercicioTreino);
                }
                pstExercicio.close();
                rsExercicio.close();
                
                newTreino.setExercicioTreino(exercicioTreino);
                
                treinos.add(newTreino);
            }
            
            pst.close();
            rs.close();
        } catch (Exception e) {
            System.err.println("Banco: " + e.getMessage());
            return null;
        }
        
        return treinos;
    }
    
    public ArrayList<Treino> listaTreinoPersonal() {
        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        PreparedStatement pstExercicio = null;
        ResultSet rsExercicio = null;
        
        ArrayList<Treino> treinos = new ArrayList<Treino>();
        try {
            conector = con.AbrirConexao();
            String sql = "select t.*, u.name from treino t join aluno a on (t.id_aluno = a.id_aluno) join usuario u on (a.id_usuario = u.id_usuario) where t.id_personal = ? order by t.id_treino desc;";
            pst = conector.prepareStatement(sql);
            pst.setInt(1, personal.getId_personal());
            rs = pst.executeQuery();
            
            Treino newTreino;
            ArrayList<ExercicioTreino> exercicioTreino;
            ExercicioTreino newExercicioTreino;
            Exercicio newExercicio;
            aluno = new AlunoPadrao();
            while(rs.next()) {
                newTreino = new Treino();
                newTreino.setId_treino(rs.getInt("id_treino"));
                aluno.setNome(rs.getString("nome"));
                aluno.setId_aluno(rs.getInt("id_aluno"));
                newTreino.setAluno(aluno);
                newTreino.setObjetivo(rs.getString("descricao"));

                sql = "select * from exercicio_treino et join exercicio e on (et.id_exercicio = e.id_exercicio) where et.id_treino = ?;";
                pstExercicio = conector.prepareStatement(sql);
                pstExercicio.setInt(1, newTreino.getId_treino());
                rsExercicio = pstExercicio.executeQuery();
                exercicioTreino = new ArrayList<ExercicioTreino>();
                while(rsExercicio.next()) {
                    newExercicioTreino = new ExercicioTreino();
                    newExercicio = new Exercicio();
                    
                    newExercicioTreino.setId_exercicioTreino(rsExercicio.getInt("id_exercicioTreino"));
                    newExercicioTreino.setDivisao(rsExercicio.getString("divisao"));
                    newExercicioTreino.setSeries(rsExercicio.getInt("series"));
                    newExercicioTreino.setRepeticoes(rsExercicio.getInt("repeticoes"));
                    newExercicioTreino.setDescricao(rsExercicio.getString("descricao"));
                    
                    newExercicio.setId_exercicio(rsExercicio.getInt("id_exercicio"));
                    newExercicio.setExercicio(rsExercicio.getString("nome"));
                    newExercicio.setCategoria(rsExercicio.getString("categoria"));
                    
                    newExercicioTreino.setExercicio(newExercicio);
                    
                    exercicioTreino.add(newExercicioTreino);
                }
                pstExercicio.close();
                rsExercicio.close();
                
                newTreino.setExercicioTreino(exercicioTreino);
                
                treinos.add(newTreino);
            }
            
            pst.close();
            rs.close();
        } catch (Exception e) {
            System.err.println("Banco: " + e.getMessage());
            return null;
        }
        
        return treinos;
    }
}
