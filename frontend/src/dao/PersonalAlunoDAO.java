/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.AlunoPadrao;
import entidade.Anamnese;
import interfaces.IAluno;
import interfaces.IPersonal;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Vivi
 */
public class PersonalAlunoDAO{
    ConexaoSqlDAO con;
    IAluno aluno;
    IPersonal personal;
    
    public PersonalAlunoDAO(IPersonal personal, IAluno aluno){
        this.personal = personal;
        this.aluno = aluno;
        this.con = new ConexaoSqlDAO(); 
    }

    public boolean preCadastrarAluno() {
        boolean retorno = true;

        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String sql = "select COUNT(*) from usuario u join personal p on (u.id_usuario = p.id_usuario) where u.email = ?;";
            
            conector = con.AbrirConexao();

            pst = conector.prepareStatement(sql);
            pst.setString(1, aluno.getEmail());
            rs = pst.executeQuery();
            rs.next();

            if(rs.getInt("count") > 0) {
                return false;
            }
            
            sql = "insert into endereco(rua, numero, cep, bairro, cidade, estado, complemento) values ('', '', '', '', '', '', '');";

            pst = conector.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            if(!(pst.executeUpdate() > 0)) return false;
            
            rs = pst.getGeneratedKeys();
            rs.next();
            int id_endereco = rs.getInt(1);
            
            sql = "insert into usuario(id_endereco, email, senha, nome, datanascimento, celular) values (?, ?, '12345', ?, ?, ?);";
            
            pst = conector.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, id_endereco);
            pst.setString(2, aluno.getEmail());
            pst.setString(3, aluno.getNome());
            pst.setString(4, aluno.getDataNascimento());
            pst.setString(5, aluno.getCelular());
            
            if(!(pst.executeUpdate() > 0)) return false;
            
            rs = pst.getGeneratedKeys();
            rs.next();
            int id_usuario = rs.getInt(1);

            sql = "insert into aluno(id_usuario) values (?);";
            pst = conector.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, id_usuario);
           
            if(!(pst.executeUpdate() > 0)) return false;
            
            rs = pst.getGeneratedKeys();
            rs.next();
            int id_aluno = rs.getInt(1);
            
            sql = "insert into anamnese(id_aluno, genero, alergias, cirurgias, vicios, medicamentos, observacoes) values (?, ?, ?, ?, ?, ?, ?);";
            pst = conector.prepareStatement(sql);
            pst.setInt(1, id_aluno);
            pst.setString(2, aluno.getAnamnese().getGenero());
            pst.setString(3, aluno.getAnamnese().getAlergias());
            pst.setString(4, aluno.getAnamnese().getCirurgias());
            pst.setString(5, aluno.getAnamnese().getVicios());
            pst.setString(6, aluno.getAnamnese().getMedicamentos());
            pst.setString(7, aluno.getAnamnese().getObservacoes());
            
            if(!(pst.executeUpdate() > 0)) return false;
            
            sql = "insert into personal_aluno(id_personal, id_aluno) values (?, ?);";
            pst = conector.prepareStatement(sql);
            pst.setInt(1, personal.getId_personal());
            pst.setInt(2, id_aluno);
            
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
        String sql = "select u.id_usuario, u.nome, u.email, u.dataNascimento,  a2.*  from personal_aluno pa join aluno a on (pa.id_aluno = a.id_aluno) join usuario u on (a.id_usuario = u.id_usuario) join anamnese a2 on (a.id_aluno = a2.id_aluno) where pa.id_personal = ? order by a.id_aluno;";
        try {
            conector = con.AbrirConexao();
            pst = conector.prepareStatement(sql);
            pst.setInt(1, personal.getId_personal());
            rs = pst.executeQuery();
            
            AlunoPadrao newAluno;
            Anamnese anmnese;
            while(rs.next()) {
                newAluno = new AlunoPadrao();
                anmnese = new Anamnese();
                
                anmnese.setId_anamnese(rs.getInt("id_anamnese"));
                anmnese.setGenero(rs.getString("genero"));
                anmnese.setAlergias(rs.getString("alergias"));
                anmnese.setCirurgias(rs.getString("cirurgias"));
                anmnese.setVicios(rs.getString("vicios"));
                anmnese.setMedicamentos(rs.getString("medicamentos"));
                anmnese.setObservacoes(rs.getString("observacoes"));
                
                newAluno.setId_usuario(rs.getInt("id_usuario"));
                newAluno.setNome(rs.getString("nome"));
                newAluno.setDataNascimento(rs.getString("dataNascimento"));
                newAluno.setEmail(rs.getString("email"));
                newAluno.setId_aluno(rs.getInt("id_aluno"));
                
                newAluno.setAnamnese(anmnese);
                
                alunos.add(newAluno);
            }

            pst.close();
            rs.close();
            con.FecharConexao(conector);
        } catch(Exception e) {
            System.err.println("" + e.getMessage());
        }

        return alunos;
    }
    
    public boolean atualizarAnamnese() {
        boolean retorno = false;
        
        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
 
        String sql = "update anamnese "
                + "set genero = ?, alergias = ?, cirurgias = ?, vicios = ?, medicamentos = ?, observacoes = ? "
                + "where id_anamnese = ?;";
        try {
            conector = con.AbrirConexao();
            pst = conector.prepareStatement(sql);
            pst.setString(1, aluno.getAnamnese().getGenero());
            pst.setString(2, aluno.getAnamnese().getAlergias());
            pst.setString(3, aluno.getAnamnese().getCirurgias());
            pst.setString(4, aluno.getAnamnese().getVicios());
            pst.setString(5, aluno.getAnamnese().getMedicamentos());
            pst.setString(6, aluno.getAnamnese().getObservacoes());
            pst.setInt(7, aluno.getAnamnese().getId_anamnese());
            
            retorno = !pst.execute();
            
            pst.close();
            rs.close();
            con.FecharConexao(conector);
        } catch(Exception e) {
            System.err.println("" + e.getMessage());
        }
        
        return retorno;
    }
}
