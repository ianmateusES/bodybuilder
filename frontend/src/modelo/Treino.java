package modelo;

import java.util.List;

public class Treino {

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public List<Exercicio> getExercise_list() {
        return exercise_list;
    }

    public void setExercise_list(List<Exercicio> exercise_list) {
        this.exercise_list = exercise_list;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }
    
    private String _id;
    private List<Exercicio> exercise_list;
    private String aluno;
    private String objective;
    private String personal;
    
    public Treino(String _id, List<Exercicio> exercise_list, String aluno, String objective, String personal) {
        this._id = _id;
        this.exercise_list = exercise_list;
        this.aluno = aluno;
        this.objective = objective;
        this.personal = personal;
    }
    
}
