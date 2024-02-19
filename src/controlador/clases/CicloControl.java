/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import controlador.dao.AdaptadorDao;
import controlador.ed.listas.ListaEnlazada;
import controlador.ed.listas.NodoLista;
import controlador.utiles.Utiles;
import java.lang.reflect.Field;
import modelo.Ciclo;
import modelo.Cursa;

/**
 *
 * @author mrbingus
 */
public class CicloControl extends AdaptadorDao<Ciclo> {

    private ListaEnlazada<Ciclo> ciclos;
    private Ciclo ciclo;

    public CicloControl() {
        super(Ciclo.class);
    }

    public ListaEnlazada<Ciclo> getCiclos() {
        ciclos = listar();
        return ciclos;
    }

    
    public void setCiclos(ListaEnlazada<Ciclo> ciclos) {
        this.ciclos = ciclos;
    }

    public Ciclo getCiclo() {
        if (ciclo == null) {
            ciclo = new Ciclo();
        }
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public Boolean persist() {
        try {
            ciclo.setId(getCiclos().size() + 1);
            return guardar(ciclo) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ListaEnlazada<Ciclo> shellsort(Integer tipo, String field, ListaEnlazada<Ciclo> ciclos1) {
        try {
            return shellsort(tipo, field, getCiclos());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ListaEnlazada<Ciclo> busquedaLineal(String texto, String criterio) {
        ListaEnlazada<Ciclo> lista = new ListaEnlazada<>();
        try {
            ListaEnlazada<Ciclo> aux = shellsort(0, criterio, getCiclos());
            NodoLista<Ciclo> nodo = aux.getCabecera();

            while (nodo != null) {
                Field nombreAtributo = Utiles.getField(Ciclo.class, criterio);
                if (nombreAtributo != null) {
                    nombreAtributo.setAccessible(true);
                    Object getter = nombreAtributo.get(nodo.getInfo());

                    if (getter != null && getter.toString().toLowerCase().contains(texto.toLowerCase())) {
                        lista.insertar(nodo.getInfo());
                    }
                }
                nodo = nodo.getSig();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
}
