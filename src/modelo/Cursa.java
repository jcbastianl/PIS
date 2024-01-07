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
    private Integer ciclo;
//    private Integer id_Docente;
    private Docente docente;

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

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }
    
    public Boolean compare(Cursa p, String field, Integer type){
        switch (type) {
            case 0:
        if(field.equalsIgnoreCase("letra")){
            return getLetra().compareTo(p.getLetra()) < 0;
        }else if(field.equalsIgnoreCase("aula")){
            return getAula().compareTo(p.getAula()) < 0;
        }else if(field.equalsIgnoreCase("ciclo")){
            return getCiclo().compareTo(p.getCiclo()) < 0;
        }else if(field.equalsIgnoreCase("id")){
            return (id.intValue() < p.getId().intValue());
        }
            case 1:
        if(field.equalsIgnoreCase("letra")){
            return getLetra().compareTo(p.getLetra()) > 0;
        }else if(field.equalsIgnoreCase("aula")){
            return getAula().compareTo(p.getAula()) > 0;
        }else if(field.equalsIgnoreCase("ciclo")){
            return getCiclo().compareTo(p.getCiclo()) > 0;
        }else if(field.equalsIgnoreCase("id")){
            return (id.intValue() < p.getId().intValue());
        }
        default:
        return null;
        }
    } 

    @Override
    public String toString() {
        return ciclo+" "+letra;
    }
}
