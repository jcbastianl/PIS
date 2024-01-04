/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jsbal
 */
public class Docente extends Persona {
    private String titulo;
    private Integer id;

    public Docente() {
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
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
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Boolean compare(Docente p, String field, Integer type){
        switch (type) {
            case 0:
        if(field.equalsIgnoreCase("apellido")){
            return getApellido().compareTo(p.getApellido()) < 0;
        }else if(field.equalsIgnoreCase("nombre")){
            return getNombre().compareTo(p.getNombre()) < 0;
        }else if(field.equalsIgnoreCase("titulo")){
            return getTitulo().compareTo(p.getTitulo()) < 0;
        }else if(field.equalsIgnoreCase("dni")){
            return getDni().compareTo(p.getDni()) < 0;
        }else if(field.equalsIgnoreCase("id")){
            return (id.intValue() < p.getId().intValue());
        }
            case 1:
        if(field.equalsIgnoreCase("apellido")){
            return getApellido().compareTo(p.getApellido()) > 0;
        }else if(field.equalsIgnoreCase("nombre")){
            return getNombre().compareTo(p.getNombre()) > 0;
        }else if(field.equalsIgnoreCase("titulo")){
            return getTitulo().compareTo(p.getTitulo()) > 0;
        }else if(field.equalsIgnoreCase("dni")){
            return getDni().compareTo(p.getDni()) > 0;
        }else if(field.equalsIgnoreCase("id")){
            return (id.intValue() > p.getId().intValue());
        }   
        
        default:
        return null;

        }
    }  
    
}
