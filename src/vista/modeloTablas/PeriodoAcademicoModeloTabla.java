/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.PeriodoAcademico;

/**
 *
 * @author mrbingus
 */
public class PeriodoAcademicoModeloTabla extends AbstractTableModel{
    
    private DynamicList<PeriodoAcademico>periodos;
    
        @Override
        public int getRowCount() {
                return getPeriodos().getLenght();
        }

        @Override
        public int getColumnCount() {
                return 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
                try {
                        PeriodoAcademico e = getPeriodos().getInfo(rowIndex);
                        switch (columnIndex) {
                                case 0:
                                        return (e != null) ? e.getMesInicio()+ " " + e.getYearInicio() + " - " +e.getMesFin() + " " + e.getYearFin(): " "+e.getModalidad();
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
                                return "Periodo Academico";
                        default:
                                return null;
                }
        }

    /**
     * @return the periodos
     */
    public DynamicList<PeriodoAcademico> getPeriodos() {
        return periodos;
    }

    /**
     * @param periodos the periodos to set
     */
    public void setPeriodos(DynamicList<PeriodoAcademico> periodos) {
        this.periodos = periodos;
    }
            
        
}
