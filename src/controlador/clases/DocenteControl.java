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
import modelo.Docente;

/**
 *
 * @author mrbingus
 */
public class DocenteControl extends DaoImplement<Docente>{
    
    private DynamicList<Docente>listaDocentes;
    private Docente docente;
    
    
    public DocenteControl() {
        super(Docente.class);
    }

    /**
     * @return the listaDocentes
     */
    public DynamicList<Docente> getListaDocentes() {
        listaDocentes = all();
        return listaDocentes;
    }

    /**
     * @param listaDocentes the listaDocentes to set
     */
    public void setListaDocentes(DynamicList<Docente> listaDocentes) {
        this.listaDocentes = listaDocentes;
    }

    /**
     * @return the Docente
     */
    public Docente getDocente() {
        if(docente == null){
            docente = new Docente();
        }
        return docente;
    }

    /**
     * @param Docente the Docente to set
     */
    public void setDocente(Docente Docente) {
        this.docente = Docente;
    }
    
    public Boolean persist() {
        docente.setId(all().getLenght() + 1);
        return persist(docente);
    }    
    
    public DynamicList<Docente> shellsort( Integer tipo, String field) throws EmptyException, Exception {
        
        if (tipo == 0) {
            tipo = 1;
        } else {
            tipo = 0;
        }

        int longitudLista = getListaDocentes().getLenght();
        Docente[] arrCensadores = getListaDocentes().toArray();

        int tamanoPedazo = longitudLista / 2;

        while (tamanoPedazo > 0) {
            for (int i = tamanoPedazo; i < longitudLista; i++) {
                Docente temp = arrCensadores[i];
                int j = i;

                while (j >= tamanoPedazo && arrCensadores[j - tamanoPedazo].compare(temp, field, tipo)) {
                    arrCensadores[j] = arrCensadores[j - tamanoPedazo];
                    j -= tamanoPedazo;
                }

                arrCensadores[j] = temp;
            }

            tamanoPedazo = tamanoPedazo / 2;
        }
        return getListaDocentes().toList(arrCensadores);
    }

    public DynamicList<Docente> busquedaLineal(String texto, String criterio) {
        //System.out.println("Estas usando busqueda lineal");
        DynamicList<Docente> lista = new DynamicList<>();
        try {
            Docente[] aux = shellsort( 0, criterio).toArray();
                        lista.removerAll();

            for (Docente p : aux) {
                Field nombreAtributo = Utiles.getField(Docente.class, criterio);

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
