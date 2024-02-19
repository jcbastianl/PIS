/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author jsbal
 */
public class Estudiante  {
   
    private String colegioProcedencia;
    private String provinciaOrigen;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private Integer rol;
    
    private Integer id;
    private Cuenta cuenta;


    public Estudiante() {
        
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
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
                 
                    
                }
                break;
            case 1: // Descendente
                if (field.equalsIgnoreCase("nombre")) {
                    return getNombre().compareTo(e.getNombre()) > 0;
                } else if (field.equalsIgnoreCase("apellido")) {
                    return getApellido().compareTo(e.getApellido()) > 0;
                } else if (field.equalsIgnoreCase("dni")) {
                    return getDni().compareTo(e.getDni()) > 0;
               
                }
                break;
        }
        return null;
    }

   


    public String getColegioProcedencia() {
        return colegioProcedencia;
    }    

    public void setColegioProcedencia(String colegioProcedencia) {
        this.colegioProcedencia = colegioProcedencia;
    }

    public String getProvinciaOrigen() {
        return provinciaOrigen;
    }

    public void setProvinciaOrigen(String provinciaOrigen) {
        this.provinciaOrigen = provinciaOrigen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    

    public Integer getId() {
        return id;
    }

    /**
     * @return the colegioProcedencia
     */
    public void setId(Integer id) {   
        this.id = id;
    }

    @Override
    public String toString() {
        return getNombre()+" "+getApellido();
    }    

   
}
