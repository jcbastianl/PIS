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
//    private Integer id_persona ;
    private Integer persona;
    private Boolean estado;
    private Integer tipoCuenta;

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


    public Boolean compare(Cuenta p, String field, Integer type){
        switch (type) {
            case 0:
        if(field.equalsIgnoreCase("id")){
            return (getId().intValue() < p.getId().intValue());
        }
            case 1:
        if(field.equalsIgnoreCase("id")){
            return (getId().intValue() < p.getId().intValue());
        }  
        
        default:
        return null;

        }
    } 

    /**
     * @return the estado
     */
    public Boolean getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    

    /**
     * @return the tipoCuenta
     */
    public Integer getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * @param tipoCuenta the tipoCuenta to set
     */
    public void setTipoCuenta(Integer tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * @return the persona
     */
    public Integer getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Integer persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "correo=" + correo + ", contrase\u00f1a=" + contraseña + '}';
    }


    
}
