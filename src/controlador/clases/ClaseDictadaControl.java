/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.ClaseDictada;

/**
 *
 * @author mrbingus
 */
public class ClaseDictadaControl extends DaoImplement<ClaseDictada>{
    
    private DynamicList<ClaseDictada>listaClases;
    private ClaseDictada clase;

    public ClaseDictadaControl() {
        super(ClaseDictada.class);
    }

    /**
     * @return the listaClases
     */
    public DynamicList<ClaseDictada> getListaClases() {
        listaClases = all();
        return listaClases;
    }

    /**
     * @param listaClases the listaClases to set
     */
    public void setListaClases(DynamicList<ClaseDictada> listaClases) {
        this.listaClases = listaClases;
    }

    /**
     * @return the clase
     */
    public ClaseDictada getClase() {
        if (clase == null) {
            clase = new ClaseDictada();
        }
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(ClaseDictada clase) {
        this.clase = clase;
    }
    
    public Boolean persist() {
        clase.setId(all().getLenght() + 1);
        return persist(clase);
    }    
    
    
}
