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
            String sql = "INSERT INTO TB_AvaliacaoAluno VALUES(0,? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?, ?, ?, ?, ?, ?, ?)";
            
            conector = con.AbrirConexao();
            pst = conector.prepareStatement(sql);
            
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
            
            pst.executeUpdate();
            
            sql = "INSERT INTO TB_Aluno_AvaliacaoAluno(id_avaliacao, id_aluno, data) VALUES (LAST_INSERT_ID(), ?, NOW())";
            pst = conector.prepareStatement(sql);
            pst.setInt(1, aluno.getId_aluno());
            pst.executeUpdate();
            
            pst.close();
            con.FecharConexao(conector);
            retorno = false;
            
        } catch(Exception e) {
            System.err.println("" + e.getMessage());
        }
        
        return retorno;
    }
    
    public AvaliacaoFisicaAluno visualizarAvaliacao(){ // TA: id_avaliacaoAluno; TAV: id_avaliacao
        String sql = "SELECT * FROM TB_AvaliacaoAluno INNER JOIN TB_Aluno_AvaliacaoAluno ON TB_AvaliacaoAluno.id_avaliacaoAluno = TB_Aluno_AvaliacaoAluno.id_avaliacao INNER JOIN TB_Aluno ON TB_Aluno.id_aluno = TB_Aluno_AvaliacaoAluno.id_aluno WHERE TB_Aluno.id_aluno='"+aluno.getId_aluno()+"' ORDER BY TB_Aluno_AvaliacaoAluno.data DESC";
        
        java.sql.Connection conector = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        AvaliacaoFisicaAluno avaliacao = new AvaliacaoFisicaAluno();

        try {
            conector = con.AbrirConexao();
            pst = conector.prepareStatement(sql);
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
        }
        
        return avaliacao;
    }

}
