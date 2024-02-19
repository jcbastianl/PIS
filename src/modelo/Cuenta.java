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
    private Docente docente;
    private Boolean estado;
    

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


   public Boolean compare(Cuenta p, String field, Integer type) {
    switch (type) {
        case 0:
            if (field.equalsIgnoreCase("correo")) {
                return this.correo.compareTo(p.getCorreo()) < 0;
            } else if (field.equalsIgnoreCase("id")) {
                return (this.id < p.getId());
            }
            break;
        case 1:
            if (field.equalsIgnoreCase("correo")) {
                return this.correo.compareTo(p.getCorreo()) > 0;
            } else if (field.equalsIgnoreCase("id")) {
                return (this.id > p.getId());
            }
            break;
        default:
            return null;
    }
    return null;
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

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
    this.docente = docente;
}


    


    
}
