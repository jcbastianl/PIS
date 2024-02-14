/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.clases.CicloControl;
import controlador.clases.DocenteControl;
import controlador.utiles.Utiles;
import javax.swing.table.AbstractTableModel;
import modelo.Ciclo;
import modelo.Cursa;
import modelo.Docente;

/**
 *
 * @author mrbingus
 */
public class EstudianteCursaModeloTabla extends AbstractTableModel{
    
    private DynamicList<Cursa>listaCursos;
    private int variableColumnas = 4;

    @Override
    public int getRowCount() {
        return getListaCursos().getLenght();
    }

    @Override
    public int getColumnCount() {
        return getVariableColumnas();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Cursa d = getListaCursos().getInfo(rowIndex);
            Docente o = new DocenteControl().all().getInfo(Utiles.encontrarPosicion("docente", d.getCiclo()));
            Ciclo cc = new CicloControl().getCiclos().getInfo(Utiles.encontrarPosicion("ciclo", d.getCiclo()));
            
            switch (columnIndex) {
                case 0:
                    return (d != null) ? d.getAsignatura().getNombre() : " ";
                case 1:
                    return (d != null) ? o.getNombre() +" "+o.getApellido() : " ";
                case 2:
                    return (d != null) ? Utiles.formaterarFecha(d.getFechaInicio()) : " ";
                case 3:
                    return (d != null) ? Utiles.formaterarFecha(d.getFechaFin()) : " ";                    
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
                return "ASIGNATURA";
            case 1:
                return "DOCENTE"; 
            case 2:
                return "INICIO"; 
            case 3:
                return "FIN";             
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

    /**
     * @return the variableColumnas
     */
    public int getVariableColumnas() {
        return variableColumnas;
    }

    /**
     * @param variableColumnas the variableColumnas to set
     */
    public void setVariableColumnas(int variableColumnas) {
        this.variableColumnas = variableColumnas;
    }

    
    
}
