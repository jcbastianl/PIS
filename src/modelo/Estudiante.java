/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jsbal
 */
public class Estudiante extends Persona {
    private Double porcentajeAsistencias;
    private Integer id;
    private Integer id_cursa;

    public Estudiante() {
    }

    /**
     * @return the porcentajeAsistencias
     */
    public Double getPorcentajeAsistencias() {
        return porcentajeAsistencias;
    }

    /**
     * @param porcentajeAsistencias the porcentajeAsistencias to set
     */
    public void setPorcentajeAsistencias(Double porcentajeAsistencias) {
        this.porcentajeAsistencias = porcentajeAsistencias;
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
     * @return the id_cursa
     */
    public Integer getId_cursa() {
        return id_cursa;
    }

    /**
     * @param id_cursa the id_cursa to set
     */
    public void setId_cursa(Integer id_cursa) {
        this.id_cursa = id_cursa;
    }
    
    
}
