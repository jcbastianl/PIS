/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import controlador.dao.AdaptadorDao;
import controlador.ed.listas.ListaEnlazada;
import modelo.Asistencia;

/**
 *
 * @author Usuario iTC
 */
public class AsistenciaControl extends AdaptadorDao<Asistencia> {

    private ListaEnlazada<Asistencia> listaAsistencias;
    private Asistencia asistencia;

    public AsistenciaControl() {
        super(Asistencia.class);
    }

    public ListaEnlazada<Asistencia> getListaAsistencias() {
        listaAsistencias = listar();
        return listaAsistencias;
    }

    public void setListaAsistencias(ListaEnlazada<Asistencia> listaAsistencias) {
        this.listaAsistencias = listaAsistencias;
    }

    public Asistencia getAsistencia() {
        if (asistencia == null) {
            asistencia = new Asistencia();
        }
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    public Boolean persist() {
        try {
            asistencia.setId(getListaAsistencias().getLength() + 1);
            return guardar(asistencia) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ListaEnlazada<Asistencia> shellsortAsistencia(Integer tipo, String field) {
        try {
            return shellsort(tipo, field, getListaAsistencias());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Métodos de búsqueda lineal y binaria omitidos por brevedad

    private ListaEnlazada<Asistencia> shellsort(Integer tipo, String field, ListaEnlazada<Asistencia> listaAsistencias) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ListaEnlazada<Asistencia> all() {
    try {
        return listar();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

}
