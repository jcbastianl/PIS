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
import modelo.Cursa;

/**
 *
 * @author mrbingus
 */
public class CursaControl extends DaoImplement<Cursa>{
    
    private DynamicList<Cursa>listaCursas;
    private Cursa cursa;
    
    public CursaControl() {
        super(Cursa.class);
    }

    public DynamicList<Cursa> getListaCursas() {
        listaCursas = all();
        return listaCursas;
    }

    public void setListaCursas(DynamicList<Cursa> listaCursas) {
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
        cursa.setId(all().getLenght() + 1);
        return persist(cursa);
    }    
    
    public DynamicList<Cursa> shellsort(Integer tipo, String field) throws EmptyException, Exception {
        if (tipo == 0) {
            tipo = 1;
        } else {
            tipo = 0;
        }

        int longitudLista = getListaCursas().getLenght();
        Cursa[] arrCensadores = getListaCursas().toArray();

        int tamanoPedazo = longitudLista / 2;

        while (tamanoPedazo > 0) {
            for (int i = tamanoPedazo; i < longitudLista; i++) {
                Cursa temp = arrCensadores[i];
                int j = i;

                while (j >= tamanoPedazo && arrCensadores[j - tamanoPedazo].compare(temp, field, tipo)) {
                    arrCensadores[j] = arrCensadores[j - tamanoPedazo];
                    j -= tamanoPedazo;
                }

                arrCensadores[j] = temp;
            }

            tamanoPedazo = tamanoPedazo / 2;
        }
        return getListaCursas().toList(arrCensadores);
    }

    public DynamicList<Cursa> busquedaLineal(String texto, String criterio) {
        //System.out.println("Estas usando busqueda lineal");
        DynamicList<Cursa> lista = new DynamicList<>();
        try {
            Cursa[] aux = shellsort(0, criterio).toArray();
                        lista.removerAll();

            for (Cursa p : aux) {
                Field nombreAtributo = Utiles.getField(Cursa.class, criterio);

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
