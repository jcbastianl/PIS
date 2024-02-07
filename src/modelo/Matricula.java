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

    private Date fechaRegistro;
    private Integer codigo;
    private Integer id;
//    private Integer id_cursa;
    private Cursa cursa;
    //private Integer id_estudiante;
    private Estudiante estudiante;
    private String estadoMatricula;
    private Integer idPeriodoAcademico;

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
    
    

    public Boolean compare(Matricula p, String field, Integer type){
        switch (type) {
            case 0:
        if(field.equalsIgnoreCase("ciclo")){
            return getCursa().getCiclo().toString().compareTo(p.getCursa().getCiclo().toString()) < 0;
        }else if(field.equalsIgnoreCase("nombre")){
            return getEstudiante().getNombre().compareTo(p.getEstudiante().getNombre()) < 0;
        }else if(field.equalsIgnoreCase("fecharegistro")){
            return getFechaRegistro().compareTo(p.getFechaRegistro()) < 0;
        }else if(field.equalsIgnoreCase("apellido")){
            return getEstudiante().getApellido().compareTo(p.getEstudiante().getApellido()) < 0;
        }else if(field.equalsIgnoreCase("id")){
            return (getId().intValue() < p.getId().intValue());
        }
            case 1:
        if(field.equalsIgnoreCase("ciclo")){
            return getCursa().getCiclo().toString().compareTo(p.getCursa().getCiclo().toString()) > 0;
        }else if(field.equalsIgnoreCase("nombre")){
            return getEstudiante().getNombre().compareTo(p.getEstudiante().getNombre()) > 0;
        }else if(field.equalsIgnoreCase("fecharegistro")){
            return getFechaRegistro().compareTo(p.getFechaRegistro()) > 0;
        }else if(field.equalsIgnoreCase("apellido")){
            return getEstudiante().getApellido().compareTo(p.getEstudiante().getApellido()) > 0;
        }else if(field.equalsIgnoreCase("id")){
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
    public Integer getIdPeriodoAcademico() {
        return idPeriodoAcademico;
    }

    /**
     * @param idPeriodoAcademico the idPeriodoAcademico to set
     */
    public void setIdPeriodoAcademico(Integer idPeriodoAcademico) {
        this.idPeriodoAcademico = idPeriodoAcademico;
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

    /**
     * @return the cursa
     */
    public Cursa getCursa() {
        return cursa;
    }

    /**
     * @param cursa the cursa to set
     */
    public void setCursa(Cursa cursa) {
        this.cursa = cursa;
    }

    /**
     * @return the estudiante
     */
    public Estudiante getEstudiante() {
        return estudiante;
    }

    /**
     * @param estudiante the estudiante to set
     */
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}
