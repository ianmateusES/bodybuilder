/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

/**
 *
 * @author Vivi
 */
public class Exercicio {

    public int getId_exercicio() {
        return id_exercicio;
    }


    public void setId_exercicio(int id_exercicio) {
        this.id_exercicio = id_exercicio;
    }

    public String getExercicio() {
        return exercicio;
    }

    public void setExercicio(String exercicio) {
        this.exercicio = exercicio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    private int id_exercicio;
    private String exercicio;
    private String categoria;
}
