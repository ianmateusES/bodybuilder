package controle.personal;

import dao.mysql.personal.PersonalDAO;
import interfaces.entidades.IPersonal;

/**
 *
 * @author Vivi
 */
public class PersonalControle{
    
    private IPersonal personal;
    private PersonalDAO personal_dao;
    
    public PersonalControle(IPersonal personal){
        this.personal = personal;
        personal_dao = new PersonalDAO(personal);
    }
    
    public String entrarConta(){
        String mensagem = "sucesso";
        
        if(personal.getEmail().isEmpty()){
            mensagem = "Erro: Variavel email vazia";
            return mensagem;
        }
        
        if(personal.getSenha().isEmpty()){
            mensagem = "Erro: Variavel senha vazia";
            return mensagem;
        }
        
        if(!personal_dao.entrarConta()){
            mensagem = "Erro: Erro ao entrar no banco de dados \n Cheque os usuários\n";
            return mensagem;
        }
        
        return mensagem;
    }
    public String cadastrarConta(){
        String mensagem = "sucesso";

        if(personal.getNome().isEmpty()){
            mensagem = "Erro: Variavel nome vazia";
            return mensagem;
        }
        
        if(personal.getDataNascimento().isEmpty()){
            mensagem = "Erro: Variavel data de nascimento vazia";
            return mensagem;
        }
        
        if(personal.getCelular().isEmpty()){
            mensagem = "Erro: Variavel celular vazia";
            return mensagem;
        }
        
        if(personal.getCref().toString().isEmpty()){
            mensagem = "Erro: Variavel cref vazia";
            return mensagem;
        }
        
        if(personal.getEmail().isEmpty()){
            mensagem = "Erro: Variavel email vazia";
            return mensagem;
        }
        
        if(personal.getSenha().isEmpty()){
            mensagem = "Erro: Variavel senha vazia";
            return mensagem;
        }
        
        if(personal.getEndereco().getEstado().isEmpty()){
            mensagem = "Erro: Variavel estado vazia";
            return mensagem;
        }
        
        if(personal.getEndereco().getCidade().isEmpty()){
            mensagem = "Erro: Variavel cidade vazia";
            return mensagem;
        }
        
        if(personal.getEndereco().getCep().isEmpty()){
            mensagem = "Erro: Variavel cep vazia";
            return mensagem;
        }
        
        if(personal.getEndereco().getBairro().isEmpty()){
            mensagem = "Erro: Variavel bairro vazia";
            return mensagem;
        }
        
        if(personal.getEndereco().getRua().isEmpty()){
            mensagem = "Erro: Variavel rua vazia";
            return mensagem;
        }
        
        if(personal.getEndereco().getNumero().isEmpty()){
            mensagem = "Erro: Variavel numero vazia";
            return mensagem;
        }

        if(!personal_dao.cadastrarConta()){
            mensagem = "Erro: Erro ao cadastrar no Banco de Dados";
            return mensagem;
        }
        return mensagem;
    }
    public IPersonal visualizarConta(){
        personal_dao.visualizarConta();
        if(personal == null){
            System.err.println("Personal voltou null do banco");
        }
        return personal;
    }
    public String alterarConta() {
        String mensagem = "sucesso";

        if(personal.getNome().isEmpty()){
            mensagem = "Erro: Variavel nome vazia";
            return mensagem;
        }
        
        if(personal.getDataNascimento().isEmpty()){
            mensagem = "Erro: Variavel data de nascimento vazia";
            return mensagem;
        }
        
        if(personal.getCelular().isEmpty()){
            mensagem = "Erro: Variavel celular vazia";
            return mensagem;
        }
        
        if(personal.getCref().toString().isEmpty()){
            mensagem = "Erro: Variavel cref vazia";
            return mensagem;
        }
        
        if(personal.getEmail().isEmpty()){
            mensagem = "Erro: Variavel email vazia";
            return mensagem;
        }
        
        if(personal.getSenha().isEmpty()){
            mensagem = "Erro: Variavel senha vazia";
            return mensagem;
        }
        
        if(personal.getEndereco().getEstado().isEmpty()){
            mensagem = "Erro: Variavel estado vazia";
            return mensagem;
        }
        
        if(personal.getEndereco().getCidade().isEmpty()){
            mensagem = "Erro: Variavel cidade vazia";
            return mensagem;
        }
        
        if(personal.getEndereco().getCep().isEmpty()){
            mensagem = "Erro: Variavel cep vazia";
            return mensagem;
        }
        
        if(personal.getEndereco().getBairro().isEmpty()){
            mensagem = "Erro: Variavel bairro vazia";
            return mensagem;
        }
        
        if(personal.getEndereco().getRua().isEmpty()){
            mensagem = "Erro: Variavel rua vazia";
            return mensagem;
        }
        
        if(personal.getEndereco().getNumero().isEmpty()){
            mensagem = "Erro: Variavel numero vazia";
            return mensagem;
        }
        
        if(!personal_dao.alterarConta()){
            mensagem = "Erro: Erro ao alterar conta no Banco de Dados";
            return mensagem;
        }
        
        return mensagem; 
    }
    
}
