/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import controlador.ed.listas.ListaEnlazada;
import controlador.ed.listas.NodoLista;
import controlador.utiles.Utiles;
import java.lang.reflect.Field;
import modelo.Ciclo;
import modelo.Docente;

/**
 *
 * @author mrbingus
 */
public class DocenteControl extends AdaptadorDao<Docente> {

    private ListaEnlazada<Docente> listaDocentes;
   
    private Docente docente;

    public DocenteControl() {
        super(Docente.class);
    }

    /**
     * @return the listaDocentes
     */
    public ListaEnlazada<Docente> getListaDocentes() {
        listaDocentes = listar();
        return listaDocentes;
    }

    


    /**
     * @param listaDocentes the listaDocentes to set
     */
    public void setListaDocentes(ListaEnlazada<Docente> listaDocentes) {
        this.listaDocentes = listaDocentes;
    }

    /**
     * @return the Docente
     */
    public Docente getDocente() {
        if (docente == null) {
            docente = new Docente();
        }
        return docente;
    }

    /**
     * @param Docente the Docente to set
     */
    public void setDocente(Docente Docente) {
        this.docente = Docente;
    }

    public Boolean persist() {
        try {
            docente.setId(getListaDocentes().getLength() + 1);
            return guardar(docente) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ListaEnlazada<Docente> shellsort(Integer tipo, String field, ListaEnlazada<Docente> listaDocentes1) {
        try {
            return shellsort(tipo, field, getListaDocentes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ListaEnlazada<Docente> busquedaLineal(String texto, String criterio) {
        ListaEnlazada<Docente> lista = new ListaEnlazada<>();
        try {
            ListaEnlazada<Docente> aux = shellsort(0, criterio, getListaDocentes());
            NodoLista<Docente> nodo = aux.getCabecera();
            listaDocentes.deleteAll();

            while (nodo != null) {
                if (nodo.getInfo().toString().toLowerCase().contains(texto.toLowerCase())) {
                    lista.insertar(nodo.getInfo());
                }
                nodo = nodo.getSig();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public ListaEnlazada<Docente> busquedaBinaria(String texto, String criterio) {
        ListaEnlazada<Docente> lista = new ListaEnlazada<>();
        int fin = getListaDocentes().size() - 1;
        int mitad = fin / 2;
        Field nombreAtributo = Utiles.getField(Docente.class, criterio);
        nombreAtributo.setAccessible(true);

        try {
            ListaEnlazada<Docente> aux = shellsort(0, criterio, getListaDocentes());
            NodoLista<Docente> nodo = aux.getCabecera();
            lista.deleteAll();

            while (nodo != null) {
                Object getterAtributo = nombreAtributo.get(nodo.getInfo());
                if (getterAtributo != null) {
                    if (getterAtributo.toString().compareToIgnoreCase(texto) > 0) {
                        for (int i = 0; i <= mitad; i++) {
                            if (nombreAtributo.get(nodo.getInfo()).toString().toLowerCase().contains(texto.toLowerCase())) {
                                lista.insertar(nodo.getInfo());
                            }
                        }
                    } else {
                        for (int j = mitad + 1; j <= fin; j++) {
                            if (nombreAtributo.get(nodo.getInfo()).toString().toLowerCase().contains(texto.toLowerCase())) {
                                lista.insertar(nodo.getInfo());
                            }
                        }
                    }
                }
                nodo = nodo.getSig();
            }
            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ListaEnlazada<Docente> all() {
    return getListaDocentes();
}

}
