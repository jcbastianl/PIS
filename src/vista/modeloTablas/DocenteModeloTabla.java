/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.Docente;

/**
 *
 * @author mrbingus
 */
public class DocenteModeloTabla extends AbstractTableModel {

    private DynamicList<Docente> docentes;

    @Override
    public int getRowCount() {
        return getDocentes().getLenght();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Docente d = getDocentes().getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (d != null) ? d.getNombre() : " ";
                case 1:
                    return (d != null) ? d.getApellido() : " ";
                case 2:
                    return (d != null) ? d.getDni() : " ";
                case 3:
                    return (d != null) ? d.getTitulo() : " ";
                case 4:
                    return (d != null) ? d.getTelefono() : " ";                    
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
                return "TITULO";
            case 4:
                return "TELEFONO";                 
            default:
                return null;
        }
    }
    
    /**
     * @return the docentes
     */
    public DynamicList<Docente> getDocentes() {
        return docentes;
    }

    /**
     * @param docentes the docentes to set
     */
    public void setDocentes(DynamicList<Docente> docentes) {
        this.docentes = docentes;
    }

}
