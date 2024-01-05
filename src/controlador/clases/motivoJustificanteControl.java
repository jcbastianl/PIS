package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.motivoJustificante;

/**
 *
 * @author Usuario iTC
 */
public class motivoJustificanteControl extends DaoImplement<motivoJustificante> {

        private DynamicList<motivoJustificante> listMJ = new DynamicList<>();
        private motivoJustificante motivojustificante;

        public motivoJustificanteControl() {
                super(motivoJustificante.class);
        }

        public motivoJustificanteControl(motivoJustificante motivojustificante, Class<motivoJustificante> clazz) {
                super(clazz);
                this.motivojustificante = motivojustificante;
        }

        public DynamicList<motivoJustificante> getListMJ() {
                listMJ = all();
                return listMJ;
        }

        public void setListMJ(DynamicList<motivoJustificante> listMJ) {
                this.listMJ = listMJ;
        }

        public motivoJustificante getmotivoJustificante() {
                if (motivojustificante == null) {
                        motivojustificante = new motivoJustificante();
                }
                return motivojustificante;
        }

        public void setEstadoAsistencia(motivoJustificante motivojustificante) {
                this.motivojustificante = motivojustificante;
        }

        public Boolean persist() {
                motivojustificante.setId(all().getLenght() + 1);
                return persist(motivojustificante);
        }

        /*public static void main(String[] args) {
                motivoJustificanteControl mjc = new motivoJustificanteControl();
                System.out.println(mjc.all().toString());
                mjc.getmotivoJustificante().setNombre("Calamidad");
                mjc.persist();
                mjc.setEstadoAsistencia(null);
                mjc.getmotivoJustificante().setNombre("Enfermedad");
                mjc.persist();
                mjc.setEstadoAsistencia(null);
                mjc.getmotivoJustificante().setNombre("Fallecimiento");
                mjc.persist();
                mjc.setEstadoAsistencia(null);
                mjc.getmotivoJustificante().setNombre("Otros");
                mjc.persist();
                mjc.setEstadoAsistencia(null);
        }*/
}
