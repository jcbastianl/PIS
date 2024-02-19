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
import modelo.Cuenta;
import modelo.Docente;

/**
 *
 * @author mrbingus
 */
public class CuentaControl extends AdaptadorDao<Cuenta> {

    private ListaEnlazada<Cuenta> listaCuentas;
    private Cuenta cuenta;

    public CuentaControl() {
        super(Cuenta.class);
    }

    /**
     * @return the listaCuentas
     */
    public ListaEnlazada<Cuenta> getListaCuentas() {
        listaCuentas = listar();
        return listaCuentas;
    }

    /**
     * @param listaCuentas the listaCuentas to set
     */
    public void setListaCuentas(ListaEnlazada<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    /**
     * @return the Cuenta
     */
    public Cuenta getCuenta() {
        if (cuenta == null) {
            cuenta = new Cuenta();
        }
        return cuenta;
    }

    /**
     * @param Cuenta the Cuenta to set
     */
    public void setCuenta(Cuenta Cuenta) {
        this.cuenta = Cuenta;
    }

    public Boolean persist() {
        try {
            cuenta.setId(getListaCuentas().getLength() + 1);
            return guardar(cuenta) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ListaEnlazada<Cuenta> shellsort(Integer tipo, String field, ListaEnlazada<Cuenta> listaCuentas1) {
        try {
            return shellsort(tipo, field, getListaCuentas());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ListaEnlazada<Cuenta> busquedaLineal(String texto, String criterio) {
        ListaEnlazada<Cuenta> lista = new ListaEnlazada<>();
        try {
            ListaEnlazada<Cuenta> aux = shellsort(0, criterio, getListaCuentas());
            NodoLista<Cuenta> nodo = aux.getCabecera();
            listaCuentas.deleteAll();
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

    public ListaEnlazada<Cuenta> busquedaBinaria(String texto, String criterio) {
        ListaEnlazada<Cuenta> lista = new ListaEnlazada<>();
        int fin = getListaCuentas().size() - 1;
        int mitad = fin / 2;
        Field nombreAtributo = Utiles.getField(Cuenta.class, criterio);
        nombreAtributo.setAccessible(true);
        try {
            ListaEnlazada<Cuenta> aux = shellsort(0, criterio, getListaCuentas());
            NodoLista<Cuenta> nodo = aux.getCabecera();
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
}