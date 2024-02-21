/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.DAO.implementaciones.PeriodoAcademicoImplementacion;
import controlador.TDA.listas.DynamicList;
import modelo.PeriodoAcademico;

/**
 *
 * @author mrbingus
 */
public class PeriodoAcademicoControl extends DaoImplement<PeriodoAcademico> {

    private PeriodoAcademicoImplementacion aux = new PeriodoAcademicoImplementacion();
    private DynamicList<PeriodoAcademico> listaPeriodos;
    private PeriodoAcademico periodo;

    public PeriodoAcademicoControl() {
        super(PeriodoAcademico.class);
    }

    /**
     * @return the listaPeriodos
     */
    public DynamicList<PeriodoAcademico> getListaPeriodos() {
        listaPeriodos = aux.all();
        return listaPeriodos;
    }

    /**
     * @param listaPeriodos the listaPeriodos to set
     */
    public void setListaPeriodos(DynamicList<PeriodoAcademico> listaPeriodos) {
        this.listaPeriodos = listaPeriodos;
    }

    /**
     * @return the periodo
     */
    public PeriodoAcademico getPeriodo() {
        if (periodo == null) {
            periodo = new PeriodoAcademico();
        }
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(PeriodoAcademico periodo) {
        this.periodo = periodo;
    }

    public Boolean persist() {
        periodo.setId(all().getLenght() + 1);
        return aux.persist(periodo);
    }

    public Boolean merge(PeriodoAcademico a, Integer index) {
        return aux.merge(a, index + 1);
    }

    public Boolean remove(Integer s) {
        return aux.remove(s + 1);
    }

}
