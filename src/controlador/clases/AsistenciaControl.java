/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.DAO.implementaciones.AsistenciaImplementacion;
import controlador.TDA.listas.DynamicList;
import modelo.Asignatura;
import modelo.Asistencia;

/**
 *
 * @author Usuario iTC
 */
public class AsistenciaControl extends DaoImplement<Asistencia> {

    private DynamicList<Asistencia> listAT = new DynamicList<>();
    private Asistencia asistencia = new Asistencia();

    private AsistenciaImplementacion asiimple = new AsistenciaImplementacion();

    public AsistenciaControl() {
        super(Asistencia.class);
    }

    public AsistenciaControl(Asistencia asistencia, Class<Asistencia> clazz) {
        super(clazz);
        this.asistencia = asistencia;
    }

    public DynamicList<Asistencia> getListaEstadoAsistencia() {
        listAT = asiimple.all();
        return listAT;
    }

    public void setListAT(DynamicList<Asistencia> listAT) {
        this.listAT = listAT;
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
        asistencia.setId(all().getLenght() + 1);
        return asiimple.persist(asistencia);
    }

    public Boolean merge(Asistencia a, Integer index) {
        return asiimple.merge(a, index + 1);
    }

    public Boolean remove(Integer s) {
        return asiimple.remove(s + 1);
    }
}
