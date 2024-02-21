/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.ed.ecepciones.PosicionException;
import controlador.utiles.Utiles;
import javax.swing.table.AbstractTableModel;
import modelo.ClaseDictada;
import controlador.ed.listas.ListaEnlazada;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrbingus
 */
public class ClaseDictadaModeloTabla extends AbstractTableModel{
    
    private ListaEnlazada<ClaseDictada>listaClases;


    @Override
    public int getRowCount() {
        return getListaClases().getLength();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
            ClaseDictada e = new ClaseDictada();
            try {
                    e = getListaClases().getInfo(rowIndex);
            } catch (PosicionException ex) {
                    Logger.getLogger(ClaseDictadaModeloTabla.class.getName()).log(Level.SEVERE, null, ex);
            }
            switch (columnIndex) {
                case 0:
                    return (e != null) ? e.getTema() : " ";
                case 1:
                    return (e != null) ? Utiles.formaterarFecha(e.getFecha()) : " ";
                default:
                    return null;
            }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "TEMA";
            case 1:
                return "FECHA";
              
            default:
                return null;
        }
    }
    
    /**
     * @return the listaClases
     */
    public ListaEnlazada<ClaseDictada> getListaClases() {
        return listaClases;
    }

    /**
     * @param listaClases the listaClases to set
     */
    public void setListaClases(ListaEnlazada<ClaseDictada> listaClases) {
        this.listaClases = listaClases;
    }
    
    
}
