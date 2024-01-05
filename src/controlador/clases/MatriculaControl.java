/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.utiles.Utiles;
import java.lang.reflect.Field;
import modelo.Matricula;

/**
 *
 * @author mrbingus
 */
public class MatriculaControl extends DaoImplement<Matricula>{
    
    private DynamicList<Matricula>listaMatriculas;
    private Matricula matricula;

    public MatriculaControl() {
        super(Matricula.class);
    }

    public DynamicList<Matricula> getListaMatriculas() {
        listaMatriculas = all();
        return listaMatriculas;
    }

    public void setListaMatriculas(DynamicList<Matricula> listaMatriculas) {
        this.listaMatriculas = listaMatriculas;
    }

    public Matricula getMatricula() {
        if(matricula == null){
            matricula = new Matricula();
        }
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
    
    public Boolean persist() {
        matricula.setId(all().getLenght() + 1);
        return persist(matricula);
    }    
    
    public DynamicList<Matricula> shellsort(DynamicList<Matricula> lista, Integer tipo, String field) throws EmptyException, Exception {
        System.out.println("Estas usando shellsort");
        if (tipo == 0) {
            tipo = 1;
        } else {
            tipo = 0;
        }

        int longitudLista = lista.getLenght();
        Matricula[] arrCensadores = lista.toArray();

        int tamanoPedazo = longitudLista / 2;

        while (tamanoPedazo > 0) {
            for (int i = tamanoPedazo; i < longitudLista; i++) {
                Matricula temp = arrCensadores[i];
                int j = i;

                while (j >= tamanoPedazo && arrCensadores[j - tamanoPedazo].compare(temp, field, tipo)) {
                    arrCensadores[j] = arrCensadores[j - tamanoPedazo];
                    j -= tamanoPedazo;
                }

                arrCensadores[j] = temp;
            }

            tamanoPedazo = tamanoPedazo / 2;
        }
        return lista.toList(arrCensadores);
    }

    public DynamicList<Matricula> busquedaLineal(String texto, DynamicList<Matricula> personas, String criterio) {
        //System.out.println("Estas usando busqueda lineal");
        DynamicList<Matricula> lista = new DynamicList<>();
        try {
            Matricula[] aux = shellsort(personas, 0, criterio).toArray();
                        lista.removerAll();

            for (Matricula p : aux) {
                Field nombreAtributo = Utiles.getField(Matricula.class, criterio);

                if (nombreAtributo != null) {
                    nombreAtributo.setAccessible(true);
                    Object getter = nombreAtributo.get(p);

                    if (getter.toString().toLowerCase().contains(texto.toLowerCase())) {
                        lista.add(p);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }        
}
