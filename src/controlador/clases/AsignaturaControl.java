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
import modelo.Asignatura;
import controlador.utiles.Utiles;
import java.lang.reflect.Field;
/**
 *
 * @author jsbal
 */
public class AsignaturaControl extends AdaptadorDao<Asignatura> {

    private ListaEnlazada<Asignatura> listaAsignaturas;
    private Asignatura asignatura;

    public AsignaturaControl() {
        super(Asignatura.class);
    }

    public ListaEnlazada<Asignatura> getListaAsignaturas() {
        listaAsignaturas = listar();
        return listaAsignaturas;
    }

    public void setListaAsignaturas(ListaEnlazada<Asignatura> listaAsignaturas) {
        this.listaAsignaturas = listaAsignaturas;
    }

    public Asignatura getAsignatura() {
        if (asignatura == null) {
            asignatura = new Asignatura();
        }
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Boolean persist() {
        try {
            asignatura.setId(getListaAsignaturas().getLength() + 1);
            return guardar(asignatura) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ListaEnlazada<Asignatura> shellsortAsignatura(Integer tipo, String field) {
        try {
            return shellsort(tipo, field, getListaAsignaturas());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ListaEnlazada<Asignatura> busquedaLineal(String texto, String criterio) {
        ListaEnlazada<Asignatura> lista = new ListaEnlazada<>();
        try {
            ListaEnlazada<Asignatura> aux = shellsortAsignatura(0, criterio);
            NodoLista<Asignatura> nodo = aux.getCabecera();
            lista.deleteAll();

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

    public ListaEnlazada<Asignatura> busquedaBinaria(String texto, String criterio) {
        ListaEnlazada<Asignatura> lista = new ListaEnlazada<>();
        int fin = getListaAsignaturas().size() - 1;
        int mitad = fin / 2;
        Field nombreAtributo = Utiles.getField(Asignatura.class, criterio);
        nombreAtributo.setAccessible(true);

        try {
            ListaEnlazada<Asignatura> aux = shellsortAsignatura(0, criterio);
            NodoLista<Asignatura> nodo = aux.getCabecera();
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

    private ListaEnlazada<Asignatura> shellsort(Integer tipo, String field, ListaEnlazada<Asignatura> listaAsignaturas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
