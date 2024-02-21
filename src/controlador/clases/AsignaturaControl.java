/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.DAO.implementaciones.AsignaturaImplementacion;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import modelo.Asignatura;
import controlador.utiles.Utiles;
import java.lang.reflect.Field;
/**
 *
 * @author jsbal
 */
public class AsignaturaControl extends DaoImplement<Asignatura> {

    private DynamicList<Asignatura> listaAsignaturas;
    private Asignatura asignatura;
    AsignaturaImplementacion imple = new AsignaturaImplementacion();

    public AsignaturaControl() {
        super(Asignatura.class);
    }

    public DynamicList<Asignatura> getListaAsignaturas() {
        listaAsignaturas = imple.all();
        return listaAsignaturas;
    }

    public void setListaAsignaturas(DynamicList<Asignatura> listaAsignaturas) {
        this.listaAsignaturas = listaAsignaturas;
    }

    public Asignatura getAsignatura() {
        if (asignatura == null) {
            asignatura = new Asignatura();
        }
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Boolean persist() {
        asignatura.setId(all().getLenght() + 1);
        return imple.persist(asignatura);
         
    }
    
    public Boolean merge(Asignatura a, Integer index){
    return imple.merge(a, index + 1);
    }
    
    public Boolean remove(Integer s){
    return imple.remove(s+1);
    }

    // Aquí puedes agregar métodos adicionales relacionados con el control de Asignaturas, 
    // como el método shellsort, busquedaLineal o cualquier otro método específico para operar con Asignaturas.

    // Por ejemplo, si deseas implementar shellsort o busquedaLineal específicamente para Asignaturas,
    // puedes adaptar los métodos proporcionados para manejar datos de tipo Asignatura.

    // ...
 public DynamicList<Asignatura> shellsortAsignatura(Integer tipo, String field) throws EmptyException, Exception {

    if (tipo == 0) {
        tipo = 1;
    } else {
        tipo = 0;
    }

    int longitudLista = getListaAsignaturas().getLenght();
    Asignatura[] arrAsignaturas = getListaAsignaturas().toArray();

    int tamanoPedazo = longitudLista / 2;

    while (tamanoPedazo > 0) {
        for (int i = tamanoPedazo; i < longitudLista; i++) {
            Asignatura temp = arrAsignaturas[i];
            int j = i;

            while (j >= tamanoPedazo && arrAsignaturas[j - tamanoPedazo].compare(temp, field, tipo)) {
                arrAsignaturas[j] = arrAsignaturas[j - tamanoPedazo];
                j -= tamanoPedazo;
            }

            arrAsignaturas[j] = temp;
        }

        tamanoPedazo = tamanoPedazo / 2;
    }
    return getListaAsignaturas().toList(arrAsignaturas);
}
public DynamicList<Asignatura> busquedaLineal(String texto, String criterio) {
    DynamicList<Asignatura> lista = new DynamicList<>();
    try {
        Asignatura[] aux = shellsortAsignatura( 0, criterio).toArray();
        lista.removerAll();

        for (Asignatura a : aux) {
            Field atributo = Utiles.getField(Asignatura.class, criterio);

            if (atributo != null) {
                atributo.setAccessible(true);
                Object valor = atributo.get(a);

                if (valor.toString().toLowerCase().contains(texto.toLowerCase())) {
                    lista.add(a);
                }
            }
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return lista;
}


}