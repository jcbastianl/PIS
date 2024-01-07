/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jsbal
 */
public class Cuenta {
    private Integer id;
    private String correo;
    private String contraseña;
    private Persona persona;

    public Cuenta() {
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
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the contraseña
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseña the contraseña to set
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Boolean compare(Cuenta p, String field, Integer type){
        switch (type) {
            case 0:
        if(field.equalsIgnoreCase("apellido")){
            return getPersona().getApellido().compareTo(p.getPersona().getApellido()) < 0;
        }else if(field.equalsIgnoreCase("nombre")){
            return getPersona().getNombre().compareTo(p.getPersona().getNombre()) < 0;
        }else if(field.equalsIgnoreCase("id")){
            return (id.intValue() < p.getId().intValue());
        }
            case 1:
        if(field.equalsIgnoreCase("apellido")){
            return getPersona().getApellido().compareTo(p.getPersona().getApellido()) > 0;
        }else if(field.equalsIgnoreCase("nombre")){
            return getPersona().getNombre().compareTo(p.getPersona().getNombre()) > 0;
        }else if(field.equalsIgnoreCase("id")){
            return (id.intValue() < p.getId().intValue());
        }  
        
        default:
        return null;

        }
    } 
    
    
}
