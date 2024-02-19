/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


/**
 *
 * @author jsbal
 */
public class Docente  {
   
    private String titulo;
    private String preparacion;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private Integer rol;
    private Cuenta cuenta;
    private Integer id;

    public Docente() {
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
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

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    
   
 
   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the id
     */
    
    
    public Boolean compare(Docente e, String field, Integer type) {
    switch (type) {
        case 0: // Ascendente
            if (field.equalsIgnoreCase("nombre")) {
                return this.nombre.compareToIgnoreCase(e.getNombre()) < 0;
            } else if (field.equalsIgnoreCase("apellido")) {
                return this.apellido.compareToIgnoreCase(e.getApellido()) < 0;
            } else if (field.equalsIgnoreCase("dni")) {
                return this.dni.compareToIgnoreCase(e.getDni()) < 0;
            }
            break;
        case 1: // Descendente
            if (field.equalsIgnoreCase("nombre")) {
                return this.nombre.compareToIgnoreCase(e.getNombre()) > 0;
            } else if (field.equalsIgnoreCase("apellido")) {
                return this.apellido.compareToIgnoreCase(e.getApellido()) > 0;
            } else if (field.equalsIgnoreCase("dni")) {
                return this.dni.compareToIgnoreCase(e.getDni()) > 0;
            }
            break;
    }
    return null;
}


    /**
     * @return the preparacion
     */
    public String getPreparacion() {
        return preparacion;
    }

    /**
     * @param preparacion the preparacion to set
     */
    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }
    
    
    @Override
    public String toString() {
        return getNombre() + " " + getApellido();
    }

    
}
