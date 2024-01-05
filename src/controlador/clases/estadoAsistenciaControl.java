package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.estadoAsistencia;

public class estadoAsistenciaControl extends DaoImplement<estadoAsistencia> {

        private DynamicList<estadoAsistencia> listAT = new DynamicList<>();
        private estadoAsistencia estadoasistencia;

        public estadoAsistenciaControl() {
                super(estadoAsistencia.class);
        }

        public estadoAsistenciaControl(estadoAsistencia estadoasistencia, Class<estadoAsistencia> clazz) {
                super(clazz);
                this.estadoasistencia = estadoasistencia;
        }

        public DynamicList<estadoAsistencia> getList() {
                listAT = all();
                return listAT;
        }

        public void setListAT(DynamicList<estadoAsistencia> listAT) {
                this.listAT = listAT;
        }

        public estadoAsistencia getEstadoAsistencia() {
                if (estadoasistencia == null) {
                        estadoasistencia = new estadoAsistencia();
                }
                return estadoasistencia;
        }

        public void setEstadoAsistencia(estadoAsistencia estadoasistencia) {
                this.estadoasistencia = estadoasistencia;
        }
        public Boolean persist() {
                estadoasistencia.setId(all().getLenght() + 1);
                return persist(estadoasistencia);
        }

        /*public static void main(String[] args) {
                estadoAsistenciaControl ac = new estadoAsistenciaControl();
                System.out.println(ac.all().toString());
                ac.getEstadoAsistencia().setNombre("Presente");
                ac.persist();
                ac.setEstadoAsistencia(null);    
        ac.getEstadoAsistencia().setNombre("Ausente");
                ac.persist();
                ac.setEstadoAsistencia(null);      
        ac.getEstadoAsistencia().setNombre("Justificado");
                ac.persist();
                ac.setEstadoAsistencia(null);      
        }*/
}
