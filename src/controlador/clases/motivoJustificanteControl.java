package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.MotivoJustificante;

/**
 *
 * @author Usuario iTC
 */
public class MotivoJustificanteControl extends DaoImplement<MotivoJustificante> {

        private DynamicList<MotivoJustificante> listMJ = new DynamicList<>();
        private MotivoJustificante motivojustificante;

        public MotivoJustificanteControl() {
                super(MotivoJustificante.class);
        }

        public MotivoJustificanteControl(MotivoJustificante motivojustificante, Class<MotivoJustificante> clazz) {
                super(clazz);
                this.motivojustificante = motivojustificante;
        }

        public DynamicList<MotivoJustificante> getListMJ() {
                listMJ = all();
                return listMJ;
        }

        public void setListMJ(DynamicList<MotivoJustificante> listMJ) {
                this.listMJ = listMJ;
        }

        public MotivoJustificante getmotivoJustificante() {
                if (motivojustificante == null) {
                        motivojustificante = new MotivoJustificante();
                }
                return motivojustificante;
        }

        public void setEstadoAsistencia(MotivoJustificante motivojustificante) {
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
