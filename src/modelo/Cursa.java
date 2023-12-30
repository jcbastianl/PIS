/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jsbal
 */
public class Cursa {
    private String letra;
    private String aula;
    private Integer id;
    private Integer id_Docente;
    private Integer id_Matricula;
    private Integer id_claseDictada;

    public Cursa() {
    }

    /**
     * @return the letra
     */
    public String getLetra() {
        return letra;
    }

    /**
     * @param letra the letra to set
     */
    public void setLetra(String letra) {
        this.letra = letra;
    }

    /**
     * @return the aula
     */
    public String getAula() {
        return aula;
    }

    /**
     * @param aula the aula to set
     */
    public void setAula(String aula) {
        this.aula = aula;
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
     * @return the id_Docente
     */
    public Integer getId_Docente() {
        return id_Docente;
    }

    /**
     * @param id_Docente the id_Docente to set
     */
    public void setId_Docente(Integer id_Docente) {
        this.id_Docente = id_Docente;
    }

    /**
     * @return the id_Matricula
     */
    public Integer getId_Matricula() {
        return id_Matricula;
    }

    /**
     * @param id_Matricula the id_Matricula to set
     */
    public void setId_Matricula(Integer id_Matricula) {
        this.id_Matricula = id_Matricula;
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
    
    
}
