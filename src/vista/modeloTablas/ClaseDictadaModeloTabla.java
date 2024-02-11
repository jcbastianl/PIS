/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.utiles.Utiles;
import javax.swing.table.AbstractTableModel;
import modelo.ClaseDictada;

/**
 *
 * @author mrbingus
 */
public class ClaseDictadaModeloTabla extends AbstractTableModel{
    
    private DynamicList<ClaseDictada>listaClases;


    @Override
    public int getRowCount() {
        return getListaClases().getLenght();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            ClaseDictada e = getListaClases().getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (e != null) ? e.getTema() : " ";
                case 1:
                    return (e != null) ? Utiles.formaterarFecha(e.getFecha()) : " ";
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
    public DynamicList<ClaseDictada> getListaClases() {
        return listaClases;
    }

    /**
     * @param listaClases the listaClases to set
     */
    public void setListaClases(DynamicList<ClaseDictada> listaClases) {
        this.listaClases = listaClases;
    }
    
    
}
