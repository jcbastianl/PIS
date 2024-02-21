/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.DAO.implementaciones.JustificativoImplementacion;
import controlador.TDA.listas.DynamicList;
import modelo.Justificativo;

/**
 *
 * @author Usuario iTC
 */
public class JustificativoControl extends DaoImplement<Justificativo> {

    private JustificativoImplementacion aux = new JustificativoImplementacion();
    private Justificativo justificativo;
    private DynamicList<Justificativo> listaJustificativos;

    public JustificativoControl() {
        super(Justificativo.class);
    }

    public Justificativo getJustificativo() {
        if (justificativo == null) {
            justificativo = new Justificativo();
        }
        return justificativo;
    }

    public void setJustificativo(Justificativo justificativo) {
        this.justificativo = justificativo;
    }

    public DynamicList<Justificativo> getListaJustificativos() {
        listaJustificativos = aux.all();
        return listaJustificativos;
    }

    public void setListaJustificativos(DynamicList<Justificativo> listaJustificativos) {
        this.listaJustificativos = listaJustificativos;
    }

    public Boolean persist() {
        justificativo.setId(all().getLenght() + 1);
        return aux.persist(justificativo);
    }

    public Boolean merge(Justificativo a, Integer index) {
        return aux.merge(a, index + 1);
    }

    public Boolean remove(Integer s) {
        return aux.remove(s + 1);
    }

}
