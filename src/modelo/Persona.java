/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jsbal
 */
public class Persona {

    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private Integer num_let;
    private Integer id_cuenta;

    public Persona() {
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
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the num_let
     */
    public Integer getNum_let() {
        return num_let;
    }

    /**
     * @param num_let the num_let to set
     */
    public void setNum_let(Integer num_let) {
        this.num_let = num_let;
    }

    /**
     * @return the id_cuenta
     */
    public Integer getId_cuenta() {
        return id_cuenta;
    }

    /**
     * @param id_cuenta the id_cuenta to set
     */
    public void setId_cuenta(Integer id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
