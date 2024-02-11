/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author jsbal
 */
public class Asistencia {

    private Integer id;
    private Boolean estadoAsistencia;
    private Integer id_estudiante;
    private Integer id_claseDictada;
    private Integer id_justificativo;

    public Asistencia() {
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the estadoAsistencia
     */
    public Boolean getEstadoAsistencia() {
        return estadoAsistencia;
    }

    /**
     * @param estadoAsistencia the estadoAsistencia to set
     */
    public void setEstadoAsistencia(Boolean estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }

    /**
     * @return the id_estudiante
     */
    public Integer getId_estudiante() {
        return id_estudiante;
    }

    /**
     * @param id_estudiante the id_estudiante to set
     */
    public void setId_estudiante(Integer id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    /**
     * @return the id_claseDictada
     */
    public Integer getId_claseDictada() {
        return id_claseDictada;
    }

    /**
     * @param id_claseDictada the id_claseDictada to set
     */
    public void setId_claseDictada(Integer id_claseDictada) {
        this.id_claseDictada = id_claseDictada;
    }

    /**
     * @return the id_justificativo
     */
    public Integer getId_justificativo() {
        return id_justificativo;
    }

    /**
     * @param id_justificativo the id_justificativo to set
     */
    public void setId_justificativo(Integer id_justificativo) {
        this.id_justificativo = id_justificativo;
    }

    @Override
    public String toString() {
        return estadoAsistencia.toString();
    }
    
    


}
