/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.ed.ecepciones.PosicionException;
import controlador.ed.listas.ListaEnlazada;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import modelo.Docente;

/**
 *
 * @author mrbingus
 */
public class DocenteModeloTabla extends AbstractTableModel {

    private ListaEnlazada<Docente> docentes;

    @Override
    public int getRowCount() {
        return docentes.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Docente d = docentes.obtenerElementoEnPosicion(rowIndex);
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
        } catch (IndexOutOfBoundsException ex) {
            return null;
        } catch (PosicionException ex) {
            Logger.getLogger(DocenteModeloTabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
    public ListaEnlazada<Docente> getDocentes() {
        return docentes;
    }

    /**
     * @param docentes the docentes to set
     */
    public void setDocentes(ListaEnlazada<Docente> docentes) {
        this.docentes = docentes;
    }

}
