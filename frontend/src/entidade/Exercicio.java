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

    /**
     * @return the id_exercicio
     */
    public int getId_exercicio() {
        return id_exercicio;
    }

    /**
     * @param id_exercicio the id_exercicio to set
     */
    public void setId_exercicio(int id_exercicio) {
        this.id_exercicio = id_exercicio;
    }

    /**
     * @return the exercicio
     */
    public String getExercicio() {
        return exercicio;
    }

    /**
     * @param exercicio the exercicio to set
     */
    public void setExercicio(String exercicio) {
        this.exercicio = exercicio;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    private int id_exercicio;
    private String exercicio;
    private String categoria;
}
