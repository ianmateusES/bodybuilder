/*
 * Responsável pelo controle da conta do personal no banco de dados
 */
package dao;

import interfaces.IPersonal;
import java.sql.*;
import util.Endereco;
/**
 *
 * @author Vivi
 */
public class PersonalDAO{
    ConexaoSqlDAO con;
    IPersonal personal;
    
    public PersonalDAO(IPersonal personal){
        this.personal = personal;
        con = new ConexaoSqlDAO(); 
    }
    
    public boolean entrarConta() {
        
        boolean retorno = false;
        java.sql.Connection conector = null;
        ResultSet rs = null;

        try{
            String sql = "select COUNT(*) from usuario u join personal p on (u.id_usuario = p.id_usuario) where u.email = ? and u.senha = ?;";
           
            conector = con.AbrirConexao();
            PreparedStatement pst;

            pst = conector.prepareStatement(sql);
            pst.setString(1, personal.getEmail());
            pst.setString(2, personal.getSenha());
            rs = pst.executeQuery();
            rs.next();

            if(rs.getInt("count") == 1) {
                retorno = true;
            }
            
            pst.close();
            rs.close();
            con.FecharConexao(conector);

        }catch(Exception e){
            System.err.println("Banco: "+e.getMessage());
        }
        return retorno;
    }
    public boolean cadastrarConta() {
        boolean retorno = false;
        
        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String sql = "select COUNT(*) from usuario u join personal p on (u.id_usuario = p.id_usuario) where u.email = ?;";
            
            conector = con.AbrirConexao();

            pst = conector.prepareStatement(sql);
            pst.setString(1, personal.getEmail());
            rs = pst.executeQuery();
            rs.next();

            if(rs.getInt("count") > 0) {
                return false;
            }
            
            sql = "insert into endereco(rua, numero, cep, bairro, cidade, estado, complemento) values (?, ?, ?, ?, ?, ?, ?);";
            
            pst = conector.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, personal.getEndereco().getRua());
            pst.setString(2, personal.getEndereco().getNumero());
            pst.setString(3, personal.getEndereco().getCep());
            pst.setString(4, personal.getEndereco().getBairro());
            pst.setString(5, personal.getEndereco().getCidade());
            pst.setString(6, personal.getEndereco().getEstado());
            pst.setString(7, personal.getEndereco().getComplemento());
            
            
            if(!(pst.executeUpdate() > 0)) return false; 
            
            rs = pst.getGeneratedKeys();
            rs.next();
            int id_endereco = rs.getInt(1);

            sql = "insert into usuario(id_endereco, email, senha, nome, datanascimento, celular, telefone) values (?, ?, ?, ?, ?, ?, ?);";

            pst = conector.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, id_endereco);
            pst.setString(2, personal.getEmail());
            pst.setString(3, personal.getSenha());
            pst.setString(4, personal.getNome());
            pst.setString(5, personal.getDataNascimento());
            pst.setString(6, personal.getCelular());
            pst.setString(7, personal.getTelefone());
            
            
            if(!(pst.executeUpdate() > 0)) return false;
            
            rs = pst.getGeneratedKeys();
            rs.next();
            int id_usuario = rs.getInt(1);
            
            sql = "insert into personal(id_usuario, cref) values (?, ?);";

            pst = conector.prepareStatement(sql);
            pst.setInt(1, id_usuario);
            pst.setInt(2, personal.getCref());

            if((pst.executeUpdate() > 0)) retorno = true;
            
            pst.close();
            con.FecharConexao(conector);
            
        } catch(Exception e) {
            System.err.println("Banco: "+e.getMessage());
        }

        return retorno;
    }
    public IPersonal visualizarConta() {
        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
            
        try {
            String sql = "select * from usuario u join personal p on (u.id_usuario = p.id_usuario) join endereco e on (u.id_endereco = e.id_endereco) where u.email = ?;";
            
            conector = con.AbrirConexao();
            pst = conector.prepareStatement(sql);
            pst.setString(1, personal.getEmail());
            
            rs = pst.executeQuery();
            rs.next();
            
            personal.setId_usuario(rs.getInt("id_usuario"));
            personal.setNome(rs.getString("nome"));
            personal.setDataNascimento(rs.getString("dataNascimento"));
            personal.setTelefone(rs.getString("telefone"));
            personal.setCelular(rs.getString("celular"));
            personal.setEmail(rs.getString("email"));
            personal.setSenha(rs.getString("senha"));
            personal.setId_personal(rs.getInt("id_personal"));
            personal.setCref(rs.getInt("cref"));
            
            Endereco endereco_personal = new Endereco();
            
            endereco_personal.setId_endereco(rs.getInt("id_endereco"));
            endereco_personal.setNumero(rs.getString("numero"));
            endereco_personal.setComplemento(rs.getString("complemento"));
            endereco_personal.setRua(rs.getString("rua"));
            endereco_personal.setCep(rs.getString("cep"));
            endereco_personal.setBairro(rs.getString("bairro"));
            endereco_personal.setCidade(rs.getString("cidade"));
            endereco_personal.setEstado(rs.getString("estado"));
            
            personal.setEndereco(endereco_personal);
            
            pst.close();
            rs.close();
            con.FecharConexao(conector);
        } catch(Exception e){
            System.err.println("Banco: " + e.getMessage());
        }
        
        return personal;
    }
    public boolean alterarConta() {         
        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        boolean retorno = false;
        
        String sql = "update usuario "
                 + "set nome = ?, dataNascimento = ?, telefone = ?, celular = ?, email = ?, senha = ? "
                 + "WHERE id_usuario = ?;";
        
        try {
            conector = con.AbrirConexao();
            pst = conector.prepareStatement(sql);
            
            pst.setString(1, personal.getNome());
            pst.setString(2, personal.getDataNascimento());
            pst.setString(3, personal.getTelefone());
            pst.setString(4, personal.getCelular());
            pst.setString(5, personal.getEmail());
            pst.setString(6, personal.getSenha());
            pst.setInt(7, personal.getId_usuario());
            
            if(pst.execute()){
                return false;
            }
            
            sql = "update endereco "
                + "set estado = ?, cidade = ?, cep = ?, bairro = ?, rua = ?, numero = ?, complemento = ? "
                + "where id_endereco = ?;";
            
            pst = conector.prepareStatement(sql);
            
            pst.setString(1, personal.getEndereco().getEstado());
            pst.setString(2, personal.getEndereco().getCidade());
            pst.setString(3, personal.getEndereco().getCep());
            pst.setString(4, personal.getEndereco().getBairro());
            pst.setString(5, personal.getEndereco().getRua());
            pst.setString(6, personal.getEndereco().getNumero());
            pst.setString(7, personal.getEndereco().getComplemento());
            pst.setInt(8, personal.getEndereco().getId_endereco());
            
            retorno = !pst.execute();
            pst.close();
            con.FecharConexao(conector);
        }catch(Exception e) {
            System.err.println("Banco: " + e.getMessage());
        }
        return retorno;
    }
}
