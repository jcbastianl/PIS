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
public class Matricula {
    private Integer ciclo;
    private Date fechaRegistro;
    private Integer numero;
    private Integer id;
    
    private Integer id_estudiante;
    private String estadoMatricula;

    public Matricula() {
    }

    /**
     * @return the ciclo
     */
    public Integer getCiclo() {
        return ciclo;
    }

    /**
     * @param ciclo the ciclo to set
     */
    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
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
     * @return the estadoMatricula
     */
    public String getEstadoMatricula() {
        return estadoMatricula;
    }

    /**
     * @param estadoMatricula the estadoMatricula to set
     */
    public void setEstadoMatricula(String estadoMatricula) {
        this.estadoMatricula = estadoMatricula;
    }
    
    
}
