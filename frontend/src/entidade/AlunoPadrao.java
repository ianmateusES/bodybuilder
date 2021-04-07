package entidade;

import util.Pessoa;
import interfaces.entidades.IAluno;

public class AlunoPadrao extends Pessoa implements IAluno{

    public Anamnese getAnamnese() {
        return anamnese;
    }
    public void setAnamnese(Anamnese anamnese) {
        this.anamnese = anamnese;
    }
    
    public AlunoPadrao() {};
    
    private int id_aluno;
    private Anamnese anamnese;    

    
    public int getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }
}
