package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import modelo.Asistencia;
import modelo.Estudiante;
import controlador.clases.EstudianteControl;
import controlador.utiles.Utiles;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AsistenciaModeloTabla extends AbstractTableModel {

    private List<Asistencia> asistencias;
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
        Asistencia asistencia = asistencias.get(rowIndex);
        Estudiante estudiante = null;

        try {
            estudiante = new EstudianteControl().getListaEstudiantes().getInfo(
                    Utiles.encontrarPosicion("estudiante", asistencia.getId_estudiante()));
        } catch (Exception e) {
            e.printStackTrace();
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
            Asistencia asistencia = asistencias.get(rowIndex);
            asistencia.setEstadoAsistencia((boolean) value);
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }

    /**
     * @return the asistencias
     */
    public List<Asistencia> getAsistencias() {
        return asistencias;
    }

    /**
     * @param asistencias the asistencias to set
     */
    public void setAsistencias(List<Asistencia> asistencias) {
        this.asistencias = asistencias;
        fireTableDataChanged();
    }
}
