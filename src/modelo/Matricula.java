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
    private Integer numero;
    private Integer id;
//    private Integer id_cursa;
    private Cursa cursa;
    //private Integer id_estudiante;
    private Estudiante estudiante;
    private String estadoMatricula;

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

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
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

    public Cursa getCursa() {
        return cursa;
    }

    public void setCursa(Cursa cursa) {
        this.cursa = cursa;
    }


    public Boolean compare(Matricula p, String field, Integer type){
        switch (type) {
            case 0:
        if(field.equalsIgnoreCase("apellido")){
            return estudiante.getApellido().compareTo(p.getEstudiante().getApellido()) < 0;
        }else if(field.equalsIgnoreCase("nombre")){
            return estudiante.getNombre().compareTo(p.getEstudiante().getNombre()) < 0;
        }else if(field.equalsIgnoreCase("fecharegistro")){
            return fechaRegistro.compareTo(p.getFechaRegistro()) < 0;
        }else if(field.equalsIgnoreCase("estadomatricula")){
            return estadoMatricula.compareTo(p.getEstadoMatricula()) < 0;
        }else if(field.equalsIgnoreCase("id")){
            return (id.intValue() < p.getId().intValue());
        }
            case 1:
        if(field.equalsIgnoreCase("apellido")){
            return estudiante.getApellido().compareTo(p.getEstudiante().getApellido()) > 0;
        }else if(field.equalsIgnoreCase("nombre")){
            return estudiante.getNombre().compareTo(p.getEstudiante().getNombre()) > 0;
        }else if(field.equalsIgnoreCase("fecharegistro")){
            return fechaRegistro.compareTo(p.getFechaRegistro()) > 0;
        }else if(field.equalsIgnoreCase("estadomatricula")){
            return estadoMatricula.compareTo(p.getEstadoMatricula()) > 0;
        }else if(field.equalsIgnoreCase("id")){
            return (id.intValue() > p.getId().intValue());
        } 
        
        default:
        return null;

        }
    }      
}
