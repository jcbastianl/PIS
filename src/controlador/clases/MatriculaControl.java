/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;



import controlador.dao.AdaptadorDao;
import controlador.ed.listas.ListaEnlazada;
import controlador.ed.listas.NodoLista;
import controlador.utiles.Utiles;
import java.lang.reflect.Field;
import java.util.Date;
import modelo.Matricula;

/**
 *
 * @author mrbingus
 */

    public class MatriculaControl extends AdaptadorDao<Matricula> {

    private ListaEnlazada<Matricula> listaMatriculas;
    private Matricula matricula;

    public MatriculaControl() {
        super(Matricula.class);
    }

    public ListaEnlazada<Matricula> getListaMatriculas() {
        listaMatriculas = listar();
        return listaMatriculas;
    }

    public void setListaMatriculas(ListaEnlazada<Matricula> listaMatriculas) {
        this.listaMatriculas = listaMatriculas;
    }

    public Matricula getMatricula() {
        if (matricula == null) {
            matricula = new Matricula();
        }
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Boolean persist() {
        try {
            matricula.setId(getListaMatriculas().getLength() + 1);
            return guardar(matricula) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ListaEnlazada<Matricula> shellsort(Integer tipo, String field, ListaEnlazada<Matricula> listaMatriculas1) {
        try {
            return shellsort(tipo, field, getListaMatriculas());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ListaEnlazada<Matricula> busquedaLineal(String texto, String criterio) {
        ListaEnlazada<Matricula> lista = new ListaEnlazada<>();
        try {
            ListaEnlazada<Matricula> aux = shellsort(0, criterio, getListaMatriculas());
            NodoLista<Matricula> nodo = aux.getCabecera();
            while (nodo != null) {
                Field nombreAtributo = Utiles.getField(Matricula.class, criterio);
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