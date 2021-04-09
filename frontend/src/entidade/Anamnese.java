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
public class Anamnese {

    public int getId_anamnese() {
        return id_anamnese;
    }
    public void setId_anamnese(int id_anamnese) {
        this.id_anamnese = id_anamnese;
    }
    
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }
    public String getCirurgias() {
        return cirurgias;
    }

    public void setCirurgias(String cirurgias) {
        this.cirurgias = cirurgias;
    }
    
    public String getMedicamentos() {
        return medicamentos;
    }
    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getVicios() {
        return vicios;
    }
    public void setVicios(String vicios) {
        this.vicios = vicios;
    }

    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    private int id_anamnese;
    private String genero;
    private String alergias;
    private String cirurgias;
    private String medicamentos;
    private String vicios;
    private String observacoes;
    
}
