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
    private Integer idEstudiante;
    private Integer id_claseDictada;
    private Integer id_horario;
    private Integer id_justificativo;
    private String estadoAsistencia;
    private Date fecha;
    

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
     * @return the idEstudiante
     */
    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    /**
     * @param idEstudiante the idEstudiante to set
     */
    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
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
     * @return the id_horario
     */
    public Integer getId_horario() {
        return id_horario;
    }

    /**
     * @param id_horario the id_horario to set
     */
    public void setId_horario(Integer id_horario) {
        this.id_horario = id_horario;
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
  
    /**
     * @return the estadoAsistencia
     */
    public String getEstadoAsistencia() {
        return estadoAsistencia;
    }
    
    
    /**
     * @param estadoAsistencia the estadoAsistencia to set
     */
    public void setEstadoAsistencia(String estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the estadoAsistencia
     */
    public Date getFecha() {
        return fecha;
    }    
}
