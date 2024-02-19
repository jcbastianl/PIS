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
import modelo.Ciclo;

/**
 *
 * @author mrbingus
 */
public class CicloModeloTabla extends AbstractTableModel {

    private ListaEnlazada<Ciclo> listaCiclos;

    @Override
    public int getRowCount() {
        return listaCiclos.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Ciclo c = listaCiclos.obtenerElementoEnPosicion(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (c != null) ? c.getCiclo() + " " + c.getParalelo() : " ";
                default:
                    return null;
            }
        } catch (IndexOutOfBoundsException ex) {
            return null;
        } catch (PosicionException ex) {
            Logger.getLogger(CicloModeloTabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "CICLO";
            default:
                return null;
        }
    }

    /**
     * @return the listaCiclos
     */
    public ListaEnlazada<Ciclo> getListaCiclos() {
        return listaCiclos;
    }

    /**
     * @param listaCiclos the listaCiclos to set
     */
    public void setListaCiclos(ListaEnlazada<Ciclo> listaCiclos) {
        this.listaCiclos = listaCiclos;
    }
}