/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.TDA.listas.DynamicList;

/**
 *
 * @author mrbingus
 */
public class Ciclo {
    private Integer id;
    private Integer ciclo;
    private String paralelo;
    private DynamicList<Cursa>cursas;

    public Ciclo() {
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
     * @return the paralelo
     */
    public String getParalelo() {
        return paralelo;
    }

    /**
     * @param paralelo the paralelo to set
     */
    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    @Override
    public String toString() {
        return getCiclo()+""+getParalelo();
    }

    /**
     * @return the cursas
     */
    public DynamicList<Cursa> getCursas() {
        if(cursas == null){
            cursas = new DynamicList<>();
        }
        return cursas;
    }

    /**
     * @param cursas the cursas to set
     */
    public void setCursas(DynamicList<Cursa> cursas) {
        this.cursas = cursas;
    }
    
    
    
    
}
