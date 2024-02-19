/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import controlador.dao.AdaptadorDao;
import controlador.ed.listas.ListaEnlazada;
import modelo.ClaseDictada;

/**
 *
 * @author mrbingus
 */
public class ClaseDictadaControl extends AdaptadorDao<ClaseDictada> {

    private ListaEnlazada<ClaseDictada> listaClases;
    private ClaseDictada clase;

    public ClaseDictadaControl() {
        super(ClaseDictada.class);
    }

    public ListaEnlazada<ClaseDictada> getListaClases() {
        listaClases = listar();
        return listaClases;
    }

    public void setListaClases(ListaEnlazada<ClaseDictada> listaClases) {
        this.listaClases = listaClases;
    }

    public ClaseDictada getClase() {
        if (clase == null) {
            clase = new ClaseDictada();
        }
        return clase;
    }

    public void setClase(ClaseDictada clase) {
        this.clase = clase;
    }

    public Boolean persist() {
        try {
            clase.setId(getListaClases().getLength() + 1);
            return guardar(clase) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}