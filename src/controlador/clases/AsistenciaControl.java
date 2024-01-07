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

        private DynamicList<Asistencia> listAT = new DynamicList<>();
        private Asistencia asistencia = new Asistencia();

        public AsistenciaControl() {
                super(Asistencia.class);
        }

        public AsistenciaControl(Asistencia asistencia, Class<Asistencia> clazz) {
                super(clazz);
                this.asistencia = asistencia;
        }

        public DynamicList<Asistencia> getListaEstadoAsistencia() {
                listAT = all();
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
                return persist(asistencia);
        }
}