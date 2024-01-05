/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.Cursa;

/**
 *
 * @author mrbingus
 */
public class CursaModeloTabla extends AbstractTableModel{
    
    private DynamicList<Cursa>listaCursos;

    @Override
    public int getRowCount() {
        return getListaCursos().getLenght();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Cursa d = getListaCursos().getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (d != null) ? d.getDocente().getNombre() +" "+d.getDocente().getApellido() : " ";
                case 1:
                    return (d != null) ? d.getLetra()  : " ";
                case 2:
                    return (d != null) ? d.getAula() : " ";
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
                return "DOCENTE";
            case 1:
                return "LETRA";
            case 2:
                return "AULA"; 
            default:
                return null;
        }
    }    
    public DynamicList<Cursa> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(DynamicList<Cursa> listaCursos) {
        this.listaCursos = listaCursos;
    }

    
    
}
