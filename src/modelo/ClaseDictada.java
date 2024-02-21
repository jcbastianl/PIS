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
public class ClaseDictada {
    private Integer id;
    private String tema;
    private Date fecha;
    private Integer id_cursa;
    private Integer id_horario;
   

    public ClaseDictada() {
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


    
    
}
