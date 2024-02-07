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
public class Justificativo {

    private Integer id;
    private String motivo;
    private String motivoJustificante;
    private String descripcion;
    private Integer id_asistencia;

    public Justificativo() {
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
     * @return the id_asistencia
     */
    public Integer getId_asistencia() {
        return id_asistencia;
    }

    /**
     * @param id_asistencia the id_asistencia to set
     */
    public void setId_asistencia(Integer id_asistencia) {
        this.id_asistencia = id_asistencia;
    }

    /**
     * @return the motivoJustificante
     */
    public String getMotivoJustificante() {
        return motivoJustificante;
    }

    /**
     * @param motivoJustificante the motivoJustificante to set
     */
    public void setMotivoJustificante(String motivoJustificante) {
        this.motivoJustificante = motivoJustificante;
    }

}
