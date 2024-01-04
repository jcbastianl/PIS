/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.Asistencia;

/**
 *
 * @author Usuario iTC
 */
public class AsistenciaControl extends DaoImplement<Asistencia> {

        private Asistencia asistencia;
        private DynamicList<Asistencia> listAsistencias;

        public AsistenciaControl() {
                super(Asistencia.class);
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

        public DynamicList<Asistencia> getListAsistencias() {
                listAsistencias = all();
                return listAsistencias;
        }

        public void setListAsistencias(DynamicList<Asistencia> listAsistencias) {
                this.listAsistencias = listAsistencias;
        }

        public Boolean persist() {
                asistencia.setId(all().getLenght() + 1);
                return persist(asistencia);
        }
}
