/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.TDA.listas.DynamicList;
import controlador.clases.CicloControl;
import controlador.utiles.Utiles;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jsbal
 */
public class Cursa {

    
    private Integer id;
    private Integer docenteId;
    private Integer asignaturaId;
    private Integer cicloId;
    private Ciclo ciclo;
    private Docente docente;
    private Asignatura asignatura;
    private Date fechaInicio;
    private Date fechaFin;

    public Cursa() {
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


    public Boolean compare(Cursa p, String field, Integer type) {
        switch (type) {
            case 0:
                if (field.equalsIgnoreCase("asignatura")) {
                    return getAsignatura().toString().compareTo(p.getAsignatura().toString()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return (getId().intValue() < p.getId().intValue());
                }
            case 1:
                if (field.equalsIgnoreCase("asignatura")) {
                    return getAsignatura().toString().compareTo(p.getAsignatura().toString()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return (getId().intValue() > p.getId().intValue());
                }
            default:
                return null;
        }
    }

   

    

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the ciclo
     */
    

    /**
     * @return the asignatura
     */
    public Asignatura getAsignatura() {
        return asignatura;
    }

    /**
     * @param asignatura the asignatura to set
     */
    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public Docente getDocente() {
        return docente;
    }

    public Integer getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(Integer docenteId) {
        this.docenteId = docenteId;
    }

    public Integer getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(Integer asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public Integer getCicloId() {
        return cicloId;
    }

    public void setCicloId(Integer cicloId) {
        this.cicloId = cicloId;
    }

   
    
    /**
     * @return the docente
     */
    

}
