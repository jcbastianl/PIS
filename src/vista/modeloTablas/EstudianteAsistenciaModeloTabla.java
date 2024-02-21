package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.clases.AsistenciaControl;
import controlador.clases.ClaseDictadaControl;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import modelo.Asistencia;
import controlador.utiles.Utiles;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ClaseDictada;
import controlador.ed.listas.ListaEnlazada;


public class EstudianteAsistenciaModeloTabla extends AbstractTableModel {

        //private DynamicList<ClaseDictada> clases;
        private ClaseDictadaControl claseDicControl = new ClaseDictadaControl();
        private ListaEnlazada<Asistencia> asistencias;
        private final String[] columnNames = {"TEMA CLASE", "ESTADO", "FECHA"};
        private final Class[] columnClasses = {String.class, String.class, Date.class};

        @Override
        public int getRowCount() {
            return asistencias.getLength();
        }

        @Override
        public int getColumnCount() {
                return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
                return columnNames[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
                return columnClasses[columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
                try {
                        Asistencia asistencia = getAsistencias().getInfo(rowIndex);
                        // Procesamiento cuando el estudiante coincide
                        ClaseDictada clasedic = claseDicControl.getListaClases().getInfo(
                                Utiles.encontrarPosicion("clasedictada", asistencia.getId_claseDictada()));
                        switch (columnIndex) {
                                case 0:
                                        return clasedic.getTema();
                                case 1:
                                        return Utiles.traducirEstadoAsistenciaString(asistencia.getEstadoAsistencia());
                                case 2:
                                        return clasedic.getFecha();
                                default:
                                        return null;
                        }
                } catch (Exception e) {
                        Logger.getLogger(EstudianteAsistenciaModeloTabla.class.getName()).log(Level.SEVERE, null, e);
                        return null;
                }
        }

        /**
         * @return the asistencias
         */
        public ListaEnlazada<Asistencia> getAsistencias() {
                return asistencias;
        }

        /**
         * @param asistencias the asistencias to set
         */
        public void setAsistencias(ListaEnlazada<Asistencia> asistencias) {
                this.asistencias = asistencias;
        }

}
