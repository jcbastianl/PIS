package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.ed.ecepciones.PosicionException;
import controlador.ed.listas.ListaEnlazada;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import modelo.Estudiante;

public class ModeloTablaAsistencia extends AbstractTableModel {

    private ListaEnlazada<Estudiante> estudiantes;

    @Override
    public int getRowCount() {
        return (estudiantes != null) ? estudiantes.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Estudiante e = estudiantes.obtenerElementoEnPosicion(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (e != null) ? e.getNombre() + " " + e.getApellido() : " ";
                default:
                    return null;
            }
        } catch (IndexOutOfBoundsException ex) {
            return null;
        } catch (PosicionException ex) {
            Logger.getLogger(ModeloTablaAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nombre y Apellido";
            default:
                return null;
        }
    }

    public ListaEnlazada<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ListaEnlazada<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
        fireTableDataChanged();
    }
}