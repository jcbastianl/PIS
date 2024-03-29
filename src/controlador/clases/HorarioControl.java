/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.DAO.implementaciones.HorarioImplementacion;
import controlador.TDA.listas.DynamicList;
import modelo.Horario;

/**
 *
 * @author Usuario iTC
 */
public class HorarioControl extends DaoImplement<Horario> {

    private HorarioImplementacion aux = new HorarioImplementacion();
    private DynamicList<Horario> listH = new DynamicList<>();
    private Horario horario = new Horario();

    public HorarioControl() {
        super(Horario.class);
    }

    public HorarioControl(Horario horario, Class<Horario> clazz) {
        super(clazz);
        this.horario = horario;
    }

    public DynamicList<Horario> getListH() {
        listH = aux.all();
        return listH;
    }

    public void setListH(DynamicList<Horario> listH) {
        this.listH = listH;
    }

    public Horario getHorario() {
        if (horario == null) {
            horario = new Horario();
        }
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Boolean persist() {
        horario.setId(all().getLenght() + 1);
        return aux.persist(horario);
    }

    public Boolean merge(Horario a, Integer index) {
        return aux.merge(a, index + 1);
    }

    public Boolean remove(Integer s) {
        return aux.remove(s + 1);
    }

    /*public static void main(String[] args) {
                HorarioControl hc = new HorarioControl();
                System.out.println(hc.all().toString());
                hc.getHorario().setHora("7:30-9:30");
                hc.persist();
                hc.setHorario(null);
                hc.getHorario().setHora("9:30-11:30");
                hc.persist();
                hc.setHorario(null);
                hc.getHorario().setHora("11:30-13:30");
                hc.persist();
                hc.setHorario(null);
        }*/
}
