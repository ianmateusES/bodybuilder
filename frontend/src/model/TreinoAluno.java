/**
* Classe TreinoAluno com todos os campos que o backend possuí, visto que o java
* é o frontend do sistema
*/
package model;

public class TreinoAluno {
    
    private String id;
    private String aluno;
    private String objective;
    private String personal;
    private String exercise_list[];

    public TreinoAluno(){}
    
    public TreinoAluno(String id, String exercise_list[], String aluno, String objective, String personal) {
        this.id = id;
        this.exercise_list = exercise_list;
        this.aluno = aluno;
        this.objective = objective;
        this.personal = personal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getExercise_list() {
        return exercise_list;
    }

    public void setExercise_list(String[] exercise_list) {
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
    
}
