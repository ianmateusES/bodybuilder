package dao.mysql.personal;

import java.sql.*;
import Entidade.AvaliacaoFisicaAluno;
import dao.ConexaoSqlDAO;
import interfaces.entidades.IAluno;


public class PersonalAlunoAvaliacaoFisicaDAO {
    ConexaoSqlDAO con;
    IAluno aluno;
    AvaliacaoFisicaAluno avaliacao;
    
    public PersonalAlunoAvaliacaoFisicaDAO(IAluno aluno, AvaliacaoFisicaAluno avaliacao){
        this.aluno = aluno;
        this.avaliacao = avaliacao;
        this.con = new ConexaoSqlDAO(); 
    }
    
    public boolean cadastrarAvaliacao(){
        boolean retorno = true;
        
        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            String sql = "insert into avaliacao(altura, peso, femur, punho, peitoral, cintura, abdomen, quadril, coxas, panturrilha, ombro, braco_relaxado, braco_contraido, antebraco, biceps, triceps, abdominal, auxiliarmedio, suprailiaca, panturrilhamedial, subespular, torax, coxa) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            conector = con.AbrirConexao();
            pst = conector.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setFloat(1, avaliacao.getAltura());
            pst.setFloat(2, avaliacao.getPeso());
            pst.setFloat(3, avaliacao.getFemur());
            pst.setFloat(4, avaliacao.getPunho());
            pst.setFloat(5, avaliacao.getPeitoral());
            pst.setFloat(6, avaliacao.getCintura());
            pst.setFloat(7, avaliacao.getAbdomen());
            pst.setFloat(8, avaliacao.getQuadril());
            pst.setFloat(9, avaliacao.getCoxas());
            pst.setFloat(10, avaliacao.getPanturrilha());
            pst.setFloat(11, avaliacao.getOmbro());
            pst.setFloat(12, avaliacao.getBraco_relaxado());
            pst.setFloat(13, avaliacao.getBraco_contraido());
            pst.setFloat(14, avaliacao.getAntebraco());
            pst.setFloat(15, avaliacao.getBiceps());
            pst.setFloat(16, avaliacao.getTriceps());
            pst.setFloat(17, avaliacao.getAbdominal());
            pst.setFloat(18, avaliacao.getAuxiliarMedio());
            pst.setFloat(19, avaliacao.getSupraIliaca());
            pst.setFloat(20, avaliacao.getPanturrilhaMedial());
            pst.setFloat(21, avaliacao.getSubespular());
            pst.setFloat(22, avaliacao.getTorax());
            pst.setFloat(23, avaliacao.getCoxa());
            
            if(!(pst.executeUpdate() > 0)) return false;
            
            rs = pst.getGeneratedKeys();
            rs.next();
            int id_avaliacao = rs.getInt(1);
            
            sql = "insert into aluno_avaliacao(id_avaliacao, id_aluno) values (?, ?);";
            pst = conector.prepareStatement(sql);
            pst.setInt(1, id_avaliacao);
            pst.setInt(2, aluno.getId_aluno());
            
            if(!(pst.executeUpdate() > 0)) return false;
            
            pst.close();
            con.FecharConexao(conector);
            retorno = false;
            
        } catch(Exception e) {
            System.err.println("" + e.getMessage());
        }
        
        return retorno;
    }
    
    public boolean visualizarAvaliacao(){ // TA: id_avaliacaoAluno; TAV: id_avaliacao       
        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            String sql = "select a2.*, aa.data from aluno a join aluno_avaliacao aa on (a.id_aluno = aa.id_aluno) join avaliacao a2 on (aa.id_avaliacao = a2.id_avaliacao) where a.id_aluno = ? order by a2.id_avaliacao desc limit 1;";
            
            conector = con.AbrirConexao();
            
            pst = conector.prepareStatement(sql);
            pst.setInt(1, aluno.getId_aluno());
            
            rs = pst.executeQuery();
            rs.next();
            
            avaliacao.setAltura(rs.getFloat("altura"));
            avaliacao.setPeso(rs.getFloat("peso"));
            avaliacao.setFemur(rs.getFloat("femur"));
            avaliacao.setPunho(rs.getFloat("punho"));
            avaliacao.setPeitoral(rs.getFloat("peitoral"));
            avaliacao.setCintura(rs.getFloat("cintura"));
            avaliacao.setAbdomen(rs.getFloat("abdomen"));
            avaliacao.setQuadril(rs.getFloat("quadril"));
            avaliacao.setCoxas(rs.getFloat("coxas"));
            avaliacao.setPanturrilha(rs.getFloat("panturrilha"));
            avaliacao.setOmbro(rs.getFloat("ombro"));
            avaliacao.setBraco_relaxado(rs.getFloat("braco_relaxado"));
            avaliacao.setBraco_contraido(rs.getFloat("braco_contraido"));
            avaliacao.setAntebraco(rs.getFloat("antebraco"));
            avaliacao.setBiceps(rs.getFloat("biceps"));
            avaliacao.setTriceps(rs.getFloat("triceps"));
            avaliacao.setAbdominal(rs.getFloat("abdominal"));
            avaliacao.setAuxiliarMedio(rs.getFloat("auxiliarMedio"));
            avaliacao.setSupraIliaca(rs.getFloat("supraIliaca"));
            avaliacao.setPanturrilhaMedial(rs.getFloat("panturrilhaMedial"));
            avaliacao.setSubespular(rs.getFloat("subespular"));
            avaliacao.setTorax(rs.getFloat("torax"));
            avaliacao.setCoxa(rs.getFloat("coxa"));
            avaliacao.setData(rs.getTimestamp("data") + "");
            
            pst.close();
            rs.close();
            con.FecharConexao(conector);
            
        } catch(Exception e){
            System.err.println("" + e.getMessage());
            return false;
        }
        
        return true;
    }

}
