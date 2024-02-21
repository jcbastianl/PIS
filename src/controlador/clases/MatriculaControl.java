/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.DAO.implementaciones.MatriculaImplementacion;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.utiles.Utiles;
import java.lang.reflect.Field;
import java.util.Date;
import modelo.ClaseDictada;
import modelo.Matricula;

/**
 *
 * @author mrbingus
 */
public class MatriculaControl extends DaoImplement<Matricula> {
    private MatriculaImplementacion aux = new MatriculaImplementacion();
    private DynamicList<Matricula> listaMatriculas;
    private Matricula matricula;

    public MatriculaControl() {
        super(Matricula.class);
    }

    public DynamicList<Matricula> getListaMatriculas() {
        listaMatriculas = aux.all();
        return listaMatriculas;
    }

    public void setListaMatriculas(DynamicList<Matricula> listaMatriculas) {
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
        matricula.setId(all().getLenght() + 1);
        return aux.persist(matricula);
    }
    
    public Boolean merge(Matricula a, Integer index) {
        return aux.merge(a, index + 1);
    }

    public Boolean remove(Integer s) {
        return aux.remove(s + 1);
    }    

    public DynamicList<Matricula> shellsort(Integer tipo, String field) throws EmptyException, Exception {
        if (tipo == 0) {
            tipo = 1;
        } else {
            tipo = 0;
        }

        int longitudLista = getListaMatriculas().getLenght();
        Matricula[] arrCensadores = getListaMatriculas().toArray();

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
        return getListaMatriculas().toList(arrCensadores);
    }

    public DynamicList<Matricula> busquedaLineal(String texto, String criterio) {
        //System.out.println("Estas usando busqueda lineal");
        DynamicList<Matricula> lista = new DynamicList<>();
        try {
            Matricula[] aux = shellsort(0, criterio).toArray();
            lista.removerAll();

            for (Matricula p : aux) {
                Field nombreAtributo = Utiles.getField(Matricula.class, criterio);

                if (nombreAtributo != null) {
                    nombreAtributo.setAccessible(true);
                    Object getter = nombreAtributo.get(p);

                    if (criterio == "fecharegistro") {
                        if (Utiles.formaterarFecha((Date)getter).contains(texto.toLowerCase())) {
                            lista.add(p);
                        }
                    }

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
    
//    public DynamicList<Matricula> busquedaBinaria(String texto, String criterio) {
//        DynamicList<Matricula> lista = new DynamicList<>();
//        int fin = getListaMatriculas().getLenght() - 1;
//        int mitad = fin / 2;
//        Field nombreAtributo = Utiles.getField(Matricula.class, criterio);
//        nombreAtributo.setAccessible(true);
//        try {
//            Matricula[] aux = shellsort(0, criterio).toArray();
//            Object getterAtributo = nombreAtributo.get(aux[mitad]);
//            lista.removerAll();
//            if (getterAtributo != null) {
//                if (getterAtributo.toString().compareToIgnoreCase(texto) > 0) {
//                    for (int i = 0; i <= mitad; i++) {
//                        if (nombreAtributo.get(aux[i]).toString().toLowerCase().contains(texto.toLowerCase())) {
//                            lista.add(aux[i]);
//                        }
//                    }
//                } else {
//                    for (int j = mitad + 1; j <= fin; j++) {
//                        if (nombreAtributo.get(aux[j]).toString().toLowerCase().contains(texto.toLowerCase())) {
//                            lista.add(aux[j]);
//                        }
//                    }
//                }
//            }
//            return lista;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }   
}
