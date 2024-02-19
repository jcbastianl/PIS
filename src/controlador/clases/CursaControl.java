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
import modelo.Cursa;
import modelo.Matricula;

/**
 *
 * @author mrbingus
 */
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CursaControl extends AdaptadorDao<Cursa> {
    
    private ListaEnlazada<Cursa> listaCursas;
    private Cursa cursa;
    
    public CursaControl() {
        super(Cursa.class);
    }

    public ListaEnlazada<Cursa> getListaCursas() {
        listaCursas = listar();
        return listaCursas;
    }

    public void setListaCursas(ListaEnlazada<Cursa> listaCursas) {
        this.listaCursas = listaCursas;
    }

    public Cursa getCursa() {
        if (cursa == null) {
            cursa = new Cursa();
        }
        return cursa;
    }

    public void setCursa(Cursa cursa) {
        this.cursa = cursa;
    }
    
    public Boolean persist() {
        try {
            cursa.setId(getListaCursas().size() + 1);
            return guardar(cursa) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }    
    
    public ListaEnlazada<Cursa> shellsort(Integer tipo, String field, ListaEnlazada<Cursa> listaCursas1) {
        try {
            return shellsort(tipo, field, getListaCursas());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ListaEnlazada<Cursa> busquedaLineal(String texto, String criterio) {
        ListaEnlazada<Cursa> lista = new ListaEnlazada<>();
        try {
            ListaEnlazada<Cursa> aux = shellsort(0, criterio, getListaCursas());
            NodoLista<Cursa> nodo = aux.getCabecera();

            while (nodo != null) {
                Field nombreAtributo = Utiles.getField(Cursa.class, criterio);
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
