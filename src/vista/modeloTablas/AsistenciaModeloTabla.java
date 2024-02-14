package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import modelo.Asistencia;
import modelo.Estudiante;
import controlador.clases.EstudianteControl;
import controlador.utiles.Utiles;

public class AsistenciaModeloTabla extends AbstractTableModel {

    private DynamicList<Asistencia> asistencias;
    private final String[] columnNames = {"ESTUDIANTE", "ESTADO"};
    private final Class[] columnClasses = {String.class, Boolean.class};

    public void setAsistencias(List<Asistencia> asistencias) {
        this.setAsistencias(asistencias);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return getAsistencias().getLenght();
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
        asistencia = getAsistencias().getInfo(rowIndex);            
        } catch (Exception e) {
        }

        EstudianteControl estudianteControl = new EstudianteControl();
        Estudiante estudiante = new Estudiante();
        try {
            estudiante = estudianteControl.getListaEstudiantes().getInfo(
                    Utiles.encontrarPosicion("estudiante", asistencia.getId_estudiante()));
        } catch (Exception e) {
        }
        switch (columnIndex) {
            case 0:
                return estudiante.getNombre() + " " + estudiante.getApellido();
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
            try {
                getAsistencias().getInfo(rowIndex).setEstadoAsistencia((boolean) value);
  
            } catch (Exception e) {
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }

    /**
     * @return the asistencias
     */
    public DynamicList<Asistencia> getAsistencias() {
        return asistencias;
    }

    /**
     * @param asistencias the asistencias to set
     */
    public void setAsistencias(DynamicList<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }
}
