/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author jsbal
 */
public class Justificativo {

     @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_justificativo;
     @Basic
    private String motivo;
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
        return id_justificativo;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id_justificativo = id;
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


}
