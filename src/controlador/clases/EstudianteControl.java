/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;



import controlador.dao.AdaptadorDao;
import controlador.ed.listas.ListaEnlazada;
import controlador.ed.listas.NodoLista;
import controlador.utiles.Utiles;
import modelo.Estudiante;
import java.lang.reflect.Field;
import modelo.Matricula;
/**
 *
 * @author jsbal
 */
public class EstudianteControl extends AdaptadorDao<Estudiante> {

    private ListaEnlazada<Estudiante> listaEstudiantes;
    private Estudiante estudiante;

    public EstudianteControl() {
        super(Estudiante.class);
    }

    public ListaEnlazada<Estudiante> getListaEstudiantes() {
        listaEstudiantes = listar();
        return listaEstudiantes;
    }

    public void setListaEstudiantes(ListaEnlazada<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    public Estudiante getEstudiante() {
        if (estudiante == null) {
            estudiante = new Estudiante();
        }
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Boolean persist() {
        try {
            estudiante.setId(getListaEstudiantes().size() + 1);
            return guardar(estudiante) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ListaEnlazada<Estudiante> shellsort(Integer tipo, String field, ListaEnlazada<Estudiante> listaEstudiantes1) {
        try {
            return shellsort(tipo, field, getListaEstudiantes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   public ListaEnlazada<Estudiante> busquedaLineal(String texto, String criterio) {
    ListaEnlazada<Estudiante> lista = new ListaEnlazada<>();
    try {
        ListaEnlazada<Estudiante> aux = shellsort(0, criterio, getListaEstudiantes());
        NodoLista<Estudiante> nodo = aux.getCabecera();
        listaEstudiantes.deleteAll();

        while (nodo != null) {
            Field atributo = Utiles.getField(Estudiante.class, criterio);
            if (atributo != null) {
                atributo.setAccessible(true);
                Object valor = atributo.get(nodo.getInfo());
                if (valor != null && valor.toString().toLowerCase().contains(texto.toLowerCase())) {
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


    public ListaEnlazada<Estudiante> busquedaBinaria(String texto, String criterio) {
        ListaEnlazada<Estudiante> lista = new ListaEnlazada<>();
        int fin = getListaEstudiantes().size() - 1;
        int mitad = fin / 2;
        Field nombreAtributo = Utiles.getField(Estudiante.class, criterio);
        nombreAtributo.setAccessible(true);

        try {
            ListaEnlazada<Estudiante> aux = shellsort(0, criterio, getListaEstudiantes());
            NodoLista<Estudiante> nodo = aux.getCabecera();
            listaEstudiantes.deleteAll();

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
