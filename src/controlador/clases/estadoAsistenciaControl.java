package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.EstadoAsistencia;

public class EstadoAsistenciaControl extends DaoImplement<EstadoAsistencia> {

        private DynamicList<EstadoAsistencia> listAT = new DynamicList<>();
        private EstadoAsistencia estadoasistencia;

        public EstadoAsistenciaControl() {
                super(EstadoAsistencia.class);
        }

        public EstadoAsistenciaControl(EstadoAsistencia estadoasistencia, Class<EstadoAsistencia> clazz) {
                super(clazz);
                this.estadoasistencia = estadoasistencia;
        }

        public DynamicList<EstadoAsistencia> getList() {
                listAT = all();
                return listAT;
        }

        public void setListAT(DynamicList<EstadoAsistencia> listAT) {
                this.listAT = listAT;
        }

        public EstadoAsistencia getEstadoAsistencia() {
                if (estadoasistencia == null) {
                        estadoasistencia = new EstadoAsistencia();
                }
                return estadoasistencia;
        }

        public void setEstadoAsistencia(EstadoAsistencia estadoasistencia) {
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
