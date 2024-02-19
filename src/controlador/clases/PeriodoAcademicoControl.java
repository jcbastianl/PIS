/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import controlador.dao.AdaptadorDao;
import controlador.ed.listas.ListaEnlazada;
import modelo.PeriodoAcademico;

/**
 *
 * @author mrbingus
 */
public class PeriodoAcademicoControl extends AdaptadorDao<PeriodoAcademico> {

    private ListaEnlazada<PeriodoAcademico> listaPeriodos;
    private PeriodoAcademico periodo;

    public PeriodoAcademicoControl() {
        super(PeriodoAcademico.class);
    }

    public ListaEnlazada<PeriodoAcademico> getListaPeriodos() {
        listaPeriodos = listar();
        return listaPeriodos;
    }

    public void setListaPeriodos(ListaEnlazada<PeriodoAcademico> listaPeriodos) {
        this.listaPeriodos = listaPeriodos;
    }

    public PeriodoAcademico getPeriodo() {
        if (periodo == null) {
            periodo = new PeriodoAcademico();
        }
        return periodo;
    }

    public void setPeriodo(PeriodoAcademico periodo) {
        this.periodo = periodo;
    }

    public Boolean persist() {
        try {
            periodo.setId(getListaPeriodos().getLength() + 1);
            return guardar(periodo) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}