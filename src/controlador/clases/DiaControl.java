/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.Dia;

/**
 *
 * @author Usuario iTC
 */
public class DiaControl extends DaoImplement<Dia> {
        private DynamicList<Dia> listD = new DynamicList<>();
        private Dia dia;

        public DiaControl() {
                super(Dia.class);
        }

        public DiaControl(Dia dia, Class<Dia> clazz) {
                super(clazz);
                this.dia = dia;
        }

        public DynamicList<Dia> getListD() {
                listD = all();
                return listD;
        }

        public void setListD(DynamicList<Dia> listD) {
                this.listD = listD;
        }

        public Dia getDia() {
                if (dia == null) {
                        dia = new Dia();
                }
                return dia;
        }

        public void setDia(Dia dia) {
                this.dia = dia;
        }

        public Boolean persist() {
                dia.setId(all().getLenght() + 1);
                return persist(dia);
        }
        
        /*public static void main(String[] args) {
                DiaControl dc = new DiaControl();
                System.out.println(dc.all().toString());
                dc.getDia().setNombre("Lunes");
                dc.persist();
                dc.setDia(null);
                dc.getDia().setNombre("Martes");
                dc.persist();
                dc.setDia(null);
        dc.getDia().setNombre("Miercoles");
                dc.persist();
                dc.setDia(null);
        dc.getDia().setNombre("Jueves");
                dc.persist();
                dc.setDia(null);
        dc.getDia().setNombre("Viernes");
                dc.persist();
                dc.setDia(null);
        }*/
}
