/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.TDA.listas.DynamicList;

/**
 *
 * @author jsbal
 */
public class Estudiante extends Persona {

    private String colegioProcedencia;
    private String provinciaOrigen;
    private DynamicList<Matricula>matriculas;
    private Integer id;


    public Estudiante() {
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


    public Boolean compare(Estudiante e, String field, Integer type) {
        switch (type) {
            case 0: // Ascendente
                if (field.equalsIgnoreCase("nombre")) {
                    return getNombre().compareTo(e.getNombre()) < 0;
                } else if (field.equalsIgnoreCase("apellido")) {
                    return getApellido().compareTo(e.getApellido()) < 0;
                } else if (field.equalsIgnoreCase("dni")) {
                    return getDni().compareTo(e.getDni()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return getId().compareTo(e.getId()) < 0;
                }
                break;
            case 1: // Descendente
                if (field.equalsIgnoreCase("nombre")) {
                    return getNombre().compareTo(e.getNombre()) > 0;
                } else if (field.equalsIgnoreCase("apellido")) {
                    return getApellido().compareTo(e.getApellido()) > 0;
                } else if (field.equalsIgnoreCase("dni")) {
                    return getDni().compareTo(e.getDni()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return getId().compareTo(e.getId()) > 0;
                }
                break;
        }
        return null;
    }

    /**
     * @return the colegioProcedencia
     */
    public String getColegioProcedencia() {
        return colegioProcedencia;
    }

    /**
     * @param colegioProcedencia the colegioProcedencia to set
     */
    public void setColegioProcedencia(String colegioProcedencia) {
        this.colegioProcedencia = colegioProcedencia;
    }

    /**
     * @return the provinciaOrigen
     */
    public String getProvinciaOrigen() {
        return provinciaOrigen;
    }

    /**
     * @param provinciaOrigen the provinciaOrigen to set
     */
    public void setProvinciaOrigen(String provinciaOrigen) {
        this.provinciaOrigen = provinciaOrigen;
    }


    @Override
    public String toString() {
        return getNombre()+" "+getApellido();
    }    

    /**
     * @return the matriculas
     */
    public DynamicList<Matricula> getMatriculas() {
        return matriculas;
    }

    /**
     * @param matriculas the matriculas to set
     */
    public void setMatriculas(DynamicList<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
}
