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
public class ClaseDictada {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_clasedictada;
    
    @Basic
    private String tema;
    private Integer id_cursa;
   
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public ClaseDictada() {
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id_clasedictada;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id_clasedictada = id;
    }

    /**
     * @return the tema
     */
    public String getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(String tema) {
        this.tema = tema;
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

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    
    
}
