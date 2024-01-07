/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.utiles.Utiles;
import modelo.Estudiante;
import java.lang.reflect.Field;
/**
 *
 * @author jsbal
 */
public class EstudianteControl extends DaoImplement<Estudiante> {
    
    private DynamicList<Estudiante> listaEstudiantes;
    private Estudiante estudiante;

    public EstudianteControl() {
        super(Estudiante.class);
    }

    public DynamicList<Estudiante> getListaEstudiantes() {
        listaEstudiantes = all();
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
        return persist(estudiante);
    }

    public DynamicList<Estudiante> shellsort( Integer tipo, String field) throws EmptyException, Exception {

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
}

