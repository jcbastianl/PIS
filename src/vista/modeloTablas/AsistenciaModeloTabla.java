package vista.modeloTablas;



import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.clases.ClaseDictadaControl;
import controlador.clases.EstudianteControl;
import controlador.utiles.Utiles;
import javax.swing.table.DefaultTableModel;

import modelo.Asistencia;
import modelo.ClaseDictada;
import modelo.Estudiante;

public class AsistenciaModeloTabla extends DefaultTableModel {
    
    private DynamicList<Asistencia> asistencias;

    public AsistenciaModeloTabla() {
        super(new Object[][]{}, new String[]{"ESTUDIANTE", "ESTADO"});
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnIndex == 1 ? Boolean.class : super.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 1; // Solo la columna de estado será editable (checkboxes)
    }

    public void setAsistencias(DynamicList<Asistencia> asistencias) {
        this.asistencias = asistencias;
        fireTableDataChanged(); // Notificar a la tabla que los datos han cambiado
    }

    @Override
    public int getRowCount() {
        return getAsistencias() != null ? getAsistencias().getLenght(): 0;
    }

@Override
public Object getValueAt(int rowIndex, int columnIndex) {
    try {
        Asistencia a = getAsistencias().getInfo(rowIndex);
        
        Estudiante e = new EstudianteControl().getListaEstudiantes().getInfo(
                Utiles.encontrarPosicion("estudiante", a.getId_estudiante()));
        
        switch (columnIndex) {
            case 0:
                return (a != null) ? e.getNombre()+" "+e.getApellido() : " ";
            case 1:
                return (a != null) ? a.getEstadoAsistencia() : false; // Devolver un Boolean
            default:
                return null;
        }
    } catch (EmptyException ex) {
        return null;
    }
}

    /**
     * @return the asistencias
     */
    public DynamicList<Asistencia> getAsistencias() {
        return asistencias;
    }

}
