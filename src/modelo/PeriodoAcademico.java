/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author mrbingus
 */
public class PeriodoAcademico {
    private Integer id;
    private String mesInicio;
    private String mesFin;
    private String yearInicio;
    private String yearFin;
    private String modalidad;
    
    public PeriodoAcademico() {
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
     * @return the mesInicio
     */
    public String getMesInicio() {
        return mesInicio;
    }

    /**
     * @param mesInicio the mesInicio to set
     */
    public void setMesInicio(String mesInicio) {
        this.mesInicio = mesInicio;
    }

    /**
     * @return the mesFin
     */
    public String getMesFin() {
        return mesFin;
    }

    /**
     * @param mesFin the mesFin to set
     */
    public void setMesFin(String mesFin) {
        this.mesFin = mesFin;
    }

    /**
     * @return the yearInicio
     */
    public String getYearInicio() {
        return yearInicio;
    }

    /**
     * @param yearInicio the yearInicio to set
     */
    public void setYearInicio(String yearInicio) {
        this.yearInicio = yearInicio;
    }

    /**
     * @return the yearFin
     */
    public String getYearFin() {
        return yearFin;
    }

    /**
     * @param yearFin the yearFin to set
     */
    public void setYearFin(String yearFin) {
        this.yearFin = yearFin;
    }

    /**
     * @return the modalidad
     */
    public String getModalidad() {
        return modalidad;
    }

    /**
     * @param modalidad the modalidad to set
     */
    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    @Override
    public String toString() {
        return getMesInicio()+ " " + getYearInicio() + " - " +getMesFin() + " " + getYearFin()+" "+getModalidad();
    }
    
    
    
   
}
