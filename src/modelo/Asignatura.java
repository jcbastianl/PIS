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
public class Asignatura {
    private String nombre;
    private String codigo;
    private Integer id;

    public Asignatura() {
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
    public Boolean compare(Asignatura a, String field, Integer type) {
    switch (type) {
        case 0: // Ascendente
            if (field.equalsIgnoreCase("nombre")) {
                return getNombre().compareTo(a.getNombre()) < 0;
            } else if (field.equalsIgnoreCase("codigo")) {
                return getCodigo().compareTo(a.getCodigo()) < 0;
            } else if (field.equalsIgnoreCase("id")) {
                return (getId().intValue() < a.getId().intValue());
            }
            break;
        case 1: // Descendente
            if (field.equalsIgnoreCase("nombre")) {
                return getNombre().compareTo(a.getNombre()) > 0;
            } else if (field.equalsIgnoreCase("codigo")) {
                return getCodigo().compareTo(a.getCodigo()) > 0;
            } else if (field.equalsIgnoreCase("id")) {
                return (getId().intValue() > a.getId().intValue());
            }
            break;
    }
    return null;
}
    @Override
    public String toString() {
        return getNombre();
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


}
