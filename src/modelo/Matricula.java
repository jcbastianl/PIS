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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jsbal
 */
public class Matricula {

    
    private Integer id;

    
    private Integer codigo;

    private Integer id_cursa;
    private Integer id_estudiante;
    private Boolean estadoMatricula;
    private Integer id_PeriodoAcademico;
 
    private Date fechaRegistro;

    public Matricula() {
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

    public Boolean compare(Matricula p, String field, Integer type) {
        switch (type) {
            case 0:
                if (field.equalsIgnoreCase("fecharegistro")) {
                    return getFechaRegistro().compareTo(p.getFechaRegistro()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return (getId().intValue() < p.getId().intValue());
                }
            case 1:
                if (field.equalsIgnoreCase("fecharegistro")) {
                    return getFechaRegistro().compareTo(p.getFechaRegistro()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return (getId().intValue() < p.getId().intValue());
                }

            default:
                return null;

        }
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the idPeriodoAcademico
     */
   

    /**
     * @return the estadoMatricula
     */
    public Boolean getEstadoMatricula() {
        return estadoMatricula;
    }

    /**
     * @param estadoMatricula the estadoMatricula to set
     */
    public void setEstadoMatricula(Boolean estadoMatricula) {
        this.estadoMatricula = estadoMatricula;
    }

    

    public Integer getId_cursa() {
        return id_cursa;
    }

    public void setId_cursa(Integer id_cursa) {
        this.id_cursa = id_cursa;
    }

    public Integer getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(Integer id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public Integer getId_PeriodoAcademico() {
        return id_PeriodoAcademico;
    }

    public void setId_PeriodoAcademico(Integer id_PeriodoAcademico) {
        this.id_PeriodoAcademico = id_PeriodoAcademico;
    }

    /**
     * @return the cursa
     */
  
}
