/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.Ciclo;

/**
 *
 * @author mrbingus
 */
public class CicloModeloTabla extends AbstractTableModel{
    
    private DynamicList<Ciclo>listaCiclos;
    
    @Override
    public int getRowCount() {
        return getListaCiclos().getLenght();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Ciclo d = getListaCiclos().getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (d != null) ? d.getCiclo() +" "+ d.getParalelo(): " ";               
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
                return "CICLO";                
            default:
                return null;
        }
    }    

    /**
     * @return the listaCiclos
     */
    public DynamicList<Ciclo> getListaCiclos() {
        return listaCiclos;
    }

    /**
     * @param listaCiclos the listaCiclos to set
     */
    public void setListaCiclos(DynamicList<Ciclo> listaCiclos) {
        this.listaCiclos = listaCiclos;
    }
}
