/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.DAO.implementaciones.CuentaImplementacion;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.utiles.Utiles;
import java.lang.reflect.Field;
import modelo.Cuenta;

/**
 *
 * @author mrbingus
 */
public class CuentaControl extends DaoImplement<Cuenta>{
    private CuentaImplementacion aux = new CuentaImplementacion();
    private  Cuenta cuenta;
    private DynamicList<Cuenta> listaCuentas;
    
    public CuentaControl() {
        super(Cuenta.class);
    }

    public Cuenta getCuenta() {
        if (cuenta == null) {
            cuenta = new Cuenta();
        }
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public DynamicList<Cuenta> getListaCuentas() {
        listaCuentas = aux.all();
        return listaCuentas;
    }

    public void setListaCuentas(DynamicList<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }
    
    public Boolean persist() {
        cuenta.setId(all().getLenght() + 1);
        return aux.persist(cuenta);
    }      
    
    public Boolean merge(Cuenta a, Integer index) {
        return aux.merge(a, index + 1);
    }

    public Boolean remove(Integer s) {
        return aux.remove(s + 1);
    }    
    
    public DynamicList<Cuenta> shellsort(Integer tipo, String field) throws EmptyException, Exception {
        if (tipo == 0) {
            tipo = 1;
        } else {
            tipo = 0;
        }

        int longitudLista = getListaCuentas().getLenght();
        Cuenta[] arrCensadores = getListaCuentas().toArray();

        int tamanoPedazo = longitudLista / 2;

        while (tamanoPedazo > 0) {
            for (int i = tamanoPedazo; i < longitudLista; i++) {
                Cuenta temp = arrCensadores[i];
                int j = i;

                while (j >= tamanoPedazo && arrCensadores[j - tamanoPedazo].compare(temp, field, tipo)) {
                    arrCensadores[j] = arrCensadores[j - tamanoPedazo];
                    j -= tamanoPedazo;
                }

                arrCensadores[j] = temp;
            }

            tamanoPedazo = tamanoPedazo / 2;
        }
        return getListaCuentas().toList(arrCensadores);
    }

    public Cuenta busquedaLineal(String texto) {
        System.out.println("Estas usando busqueda lineal");

        try {
            Cuenta[] aux = new CuentaImplementacion().all().toArray();

            for (Cuenta p : aux) {
                Field nombreAtributo = Utiles.getField(Cuenta.class, "correo");

                if (nombreAtributo != null) {
                    nombreAtributo.setAccessible(true);
                    Object getter = nombreAtributo.get(p);

                    if (getter.equals(texto)) {
                        return p;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }      
}
