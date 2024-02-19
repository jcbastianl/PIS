/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.ed.ecepciones.PosicionException;
import controlador.ed.listas.ListaEnlazada;
import controlador.utiles.Utiles;
import javax.swing.table.AbstractTableModel;
import modelo.Asignatura;

/**
 *
 * @author jsbal
 */
public class AsignaturaModeloTabla extends AbstractTableModel {

    private ListaEnlazada<Asignatura> asignaturas;

    @Override
    public int getRowCount() {
        return asignaturas.size();
    }

    @Override
    public int getColumnCount() {
        return 3; // Tres columnas: nombre, código, id
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Asignatura a = asignaturas.obtenerElementoEnPosicion(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (a != null) ? a.getNombre() : " ";
                case 1:
                    return (a != null) ? a.getCodigo() : " ";
                case 2:
                    return (a != null) ? a.getId() : " ";
                default:
                    return null;
            }
        } catch (PosicionException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "NOMBRE";
            case 1:
                return "CÓDIGO";
            case 2:
                return "ID";
            default:
                return null;
        }
    }

    // Getters y Setters para asignaturas

    public ListaEnlazada<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(ListaEnlazada<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
}
