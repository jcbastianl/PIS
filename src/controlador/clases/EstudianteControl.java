/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.DAO.implementaciones.EstudianteImplementacion;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.utiles.Utiles;
import modelo.Estudiante;
import java.lang.reflect.Field;
import modelo.Matricula;

/**
 *
 * @author jsbal
 */
public class EstudianteControl extends DaoImplement<Estudiante> {

    private EstudianteImplementacion aux = new EstudianteImplementacion();
    private DynamicList<Estudiante> listaEstudiantes;
    private Estudiante estudiante;

    public EstudianteControl() {
        super(Estudiante.class);
    }

    public DynamicList<Estudiante> getListaEstudiantes() {
        listaEstudiantes = aux.all();
        return listaEstudiantes;
    }

    public void setListaEstudiantes(DynamicList<Estudiante> listaEstudiantes) {
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
        estudiante.setId(all().getLenght() + 1);
        return aux.persist(estudiante);
    }

    public Boolean merge(Estudiante a, Integer index) {
        return aux.merge(a, index + 1);
    }

    public Boolean remove(Integer s) {
        return aux.remove(s + 1);
    }

    public DynamicList<Estudiante> shellsort(Integer tipo, String field) throws EmptyException, Exception {

        if (tipo == 0) {
            tipo = 1;
        } else {
            tipo = 0;
        }

        int longitudLista = getListaEstudiantes().getLenght();
        Estudiante[] arrEstudiantes = getListaEstudiantes().toArray();

        int tamanoPedazo = longitudLista / 2;

        while (tamanoPedazo > 0) {
            for (int i = tamanoPedazo; i < longitudLista; i++) {
                Estudiante temp = arrEstudiantes[i];
                int j = i;

                while (j >= tamanoPedazo && arrEstudiantes[j - tamanoPedazo].compare(temp, field, tipo)) {
                    arrEstudiantes[j] = arrEstudiantes[j - tamanoPedazo];
                    j -= tamanoPedazo;
                }

                arrEstudiantes[j] = temp;
            }

            tamanoPedazo = tamanoPedazo / 2;
        }
        return getListaEstudiantes().toList(arrEstudiantes);
    }

    public DynamicList<Estudiante> busquedaLineal(String texto, String criterio) {
        DynamicList<Estudiante> lista = new DynamicList<>();
        try {
            Estudiante[] aux = shellsort(0, criterio).toArray();
            lista.removerAll();

            for (Estudiante e : aux) {
                Field atributo = Utiles.getField(Estudiante.class, criterio);

                if (atributo != null) {
                    atributo.setAccessible(true);
                    Object valor = atributo.get(e);

                    if (valor.toString().toLowerCase().contains(texto.toLowerCase())) {
                        lista.add(e);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public DynamicList<Estudiante> busquedaBinaria(String texto, String criterio) {
        DynamicList<Estudiante> lista = new DynamicList<>();
        int fin = getListaEstudiantes().getLenght() - 1;
        int mitad = fin / 2;
        Field nombreAtributo = Utiles.getField(Estudiante.class, criterio);
        nombreAtributo.setAccessible(true);
        try {
            Estudiante[] aux = shellsort(0, criterio).toArray();
            Object getterAtributo = nombreAtributo.get(aux[mitad]);
            lista.removerAll();
            if (getterAtributo != null) {
                if (getterAtributo.toString().compareToIgnoreCase(texto) > 0) {
                    for (int i = 0; i <= mitad; i++) {
                        if (nombreAtributo.get(aux[i]).toString().toLowerCase().contains(texto.toLowerCase())) {
                            lista.add(aux[i]);
                        }
                    }
                } else {
                    for (int j = mitad + 1; j <= fin; j++) {
                        if (nombreAtributo.get(aux[j]).toString().toLowerCase().contains(texto.toLowerCase())) {
                            lista.add(aux[j]);
                        }
                    }
                }
            }
            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
