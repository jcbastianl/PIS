/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.Justificativo;

/**
 *
 * @author Usuario iTC
 */
public class JustificativoControl extends DaoImplement<Justificativo> {

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
                listaJustificativos = all();
                return listaJustificativos;
        }

        public void setListaJustificativos(DynamicList<Justificativo> listaJustificativos) {
                this.listaJustificativos = listaJustificativos;
        }

        public Boolean persist() {
                justificativo.setId(all().getLenght() + 1);
                return persist(justificativo);
        }

}
