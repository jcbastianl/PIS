/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.utiles.Utiles;
import javax.swing.table.AbstractTableModel;
import modelo.Asignatura;

/**
 *
 * @author jsbal
 */
public class AsignaturaModeloTabla extends AbstractTableModel {

    private DynamicList<Asignatura> asignaturas;

    @Override
    public int getRowCount() {
        return asignaturas.getLenght();
    }

    @Override
    public int getColumnCount() {
        return 4; // Cuatro columnas: nombre, fechaInicio, fechaFin, id
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Asignatura a = asignaturas.getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (a != null) ? a.getNombre() : " ";
                case 1:
                    return (a != null) ? Utiles.formaterarFecha(a.getFechaInicio()) : " ";
                case 2:
                    return (a != null) ? Utiles.formaterarFecha(a.getFechaFin() ): " ";
                case 3:
                    return (a != null) ? a.getId() : " ";
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
                return "FECHA INICIO";
            case 2:
                return "FECHA FIN";
            case 3:
                return "ID";
            default:
                return null;
        }
    }

    // Getters y Setters para asignaturas

    public DynamicList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(DynamicList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
}