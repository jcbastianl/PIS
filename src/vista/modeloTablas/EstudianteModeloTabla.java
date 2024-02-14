/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.Estudiante;

/**
 *
 * @author jsbal
 */
public class EstudianteModeloTabla extends AbstractTableModel {

    private DynamicList<Estudiante> estudiantes;

    @Override
    public int getRowCount() {
        return getEstudiantes().getLenght();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Estudiante e = getEstudiantes().getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (e != null) ? e.getNombre() : " ";
                case 1:
                    return (e != null) ? e.getApellido() : " ";
                case 2:
                    return (e != null) ? e.getDni() : " ";
                case 3:
                    return (e != null) ? e.getProvinciaOrigen() : " ";
                default:
                    return null;
            }
        } catch (EmptyException ex) {
            return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "NOMBRE";
            case 1:
                return "APELLIDO";
            case 2:
                return "DNI";
            case 3:
                return "CIUDAD";                
            default:
                return null;
        }
    }
    
    /**
     * @return the estudiantes
     */
    public DynamicList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    /**
     * @param estudiantes the estudiantes to set
     */
    public void setEstudiantes(DynamicList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}
