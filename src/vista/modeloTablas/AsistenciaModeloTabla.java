package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import modelo.Asistencia;
import modelo.Estudiante;
import controlador.clases.EstudianteControl;
import controlador.ed.ecepciones.PosicionException;
import controlador.ed.ecepciones.VacioException;
import controlador.utiles.Utiles;
import controlador.ed.listas.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AsistenciaModeloTabla extends AbstractTableModel {

        private ListaEnlazada<Asistencia> asistencias;
        private final String[] columnNames = {"ESTUDIANTE", "ESTADO"};
        private final Class[] columnClasses = {String.class, Boolean.class};

        @Override
        public int getRowCount() {
                return asistencias.size();
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
                Asistencia asistencia = new Asistencia();
                try {
                        asistencia = asistencias.get(rowIndex);
                } catch (VacioException ex) {
                        Logger.getLogger(AsistenciaModeloTabla.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PosicionException ex) {
                        Logger.getLogger(AsistenciaModeloTabla.class.getName()).log(Level.SEVERE, null, ex);
                }
                Estudiante estudiante = null;

                try {
                        estudiante = new EstudianteControl().getListaEstudiantes().getInfo(
                                Utiles.encontrarPosicion("estudiante", asistencia.getId_estudiante()));
                } catch (PosicionException ex) {
                        Logger.getLogger(AsistenciaModeloTabla.class.getName()).log(Level.SEVERE, null, ex);
                }

                switch (columnIndex) {
                        case 0:
                                if (estudiante != null) {
                                        return estudiante.getNombre() + " " + estudiante.getApellido();
                                } else {
                                        return "";
                                }
                        case 1:
                                return asistencia.getEstadoAsistencia();
                        default:
                                return null;
                }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 1; // Solo la columna de estado será editable (checkboxes)
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
                if (columnIndex == 1 && value instanceof Boolean) {
                        Asistencia asistencia = new Asistencia();
                        try {
                                asistencia = asistencias.get(rowIndex);
                        } catch (VacioException ex) {
                                Logger.getLogger(AsistenciaModeloTabla.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (PosicionException ex) {
                                Logger.getLogger(AsistenciaModeloTabla.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        asistencia.setEstadoAsistencia((boolean) value);
                        fireTableCellUpdated(rowIndex, columnIndex);
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
                fireTableDataChanged();
        }
}
