/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.util.ArrayList;
/**
 *
 * @author 55859
 */
public class Treino {
    private int id_treino;
    private ArrayList<ExercicioTreino> exercicioTreino;
    private String objetivo;

    public int getId_treino() {
        return id_treino;
    }

    public void setId_treino(int id_treino) {
        this.id_treino = id_treino;
    }

    public ArrayList<ExercicioTreino> getExercicioTreino() {
        return exercicioTreino;
    }

    public void setExercicioTreino(ArrayList<ExercicioTreino> exercicioTreino) {
        this.exercicioTreino = exercicioTreino;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
    
}
