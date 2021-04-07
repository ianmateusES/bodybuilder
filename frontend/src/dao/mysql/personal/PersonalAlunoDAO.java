/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql.personal;

import java.sql.*;
import dao.ConexaoSqlDAO;
import entidade.AlunoPadrao;
import interfaces.entidades.IAluno;
import java.util.ArrayList;

/**
 *
 * @author Vivi
 */
public class PersonalAlunoDAO{
    ConexaoSqlDAO con;
    IAluno aluno;
    
    public PersonalAlunoDAO(IAluno aluno){
        this.aluno = aluno;
        this.con = new ConexaoSqlDAO(); 
    }

    public boolean preCadastrarAluno() {
        boolean retorno = true;

        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String sql = "INSERT INTO TB_Endereco VALUES(0,'vazio', 'vazio', 'vazio', 'vazio', 'vazio', 'vazio', 'vazio') ";

            conector = con.AbrirConexao();
            pst = conector.prepareStatement(sql);

            if(!(pst.executeUpdate() > 0)) return false;
            
            sql = "INSERT INTO TB_Usuario(id_endereco, nome, dataNascimento, telefone, celular, email, senha) VALUES (LAST_INSERT_ID(),?, ?, '', '', ?, '');";
            
            pst = conector.prepareStatement(sql);
            pst.setString(1, aluno.getNome());
            pst.setString(2, aluno.getDataNascimento());
            pst.setString(3, aluno.getEmail());
            
            System.out.println(aluno.getNome());
            
             if(!(pst.executeUpdate() > 0)) return false;
            System.out.println("oi");

            sql = "INSERT INTO TB_Aluno(id_usuario) VALUES (LAST_INSERT_ID());";
            pst = conector.prepareStatement(sql);
           
            if(!(pst.executeUpdate() > 0)) return false;
            
            sql = "INSERT INTO TB_Anamnese(id_aluno, genero, alergias, cirurgias, medicamentos, vicios, observacoes) VALUES(LAST_INSERT_ID(), ?, ?, ?, ?, ?, ?)";
            pst = conector.prepareStatement(sql);

            pst.setString(1, aluno.getAnamnese().getGenero());
            pst.setString(2, aluno.getAnamnese().getAlergias());
            pst.setString(3, aluno.getAnamnese().getCirurgias());
            pst.setString(4, aluno.getAnamnese().getMedicamentos());
            pst.setString(5, aluno.getAnamnese().getVicios());
            pst.setString(6, aluno.getAnamnese().getObservacoes());
            
            if(!(pst.executeUpdate() > 0)) return false;

            pst.close();
            con.FecharConexao(conector);

        } catch(Exception e) {
            System.err.println("" + e.getMessage());
        }

        return retorno;
    }

    public ArrayList<IAluno> listarAlunos() {
        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArrayList<IAluno> alunos = new ArrayList<IAluno>();
        String sql = "SELECT * FROM TB_Usuario INNER JOIN TB_Aluno ON TB_Usuario.id_usuario = TB_Aluno.id_usuario INNER JOIN TB_Endereco ON TB_Usuario.id_endereco = TB_Endereco.id_endereco";
        try {
            conector = con.AbrirConexao();
            pst = conector.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                AlunoPadrao aluno = new AlunoPadrao();
                
                aluno.setId_usuario(rs.getInt("id_usuario"));
                aluno.setNome(rs.getString("nome"));

                aluno.setDataNascimento(rs.getString("dataNascimento"));
                aluno.setEmail(rs.getString("email"));
                aluno.setId_aluno(rs.getInt("id_aluno"));
                
                alunos.add(aluno);
            }

            pst.close();
            rs.close();
            con.FecharConexao(conector);
        } catch(Exception e) {
            System.err.println("" + e.getMessage());
        }

        return alunos;
    }
}
