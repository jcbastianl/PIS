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
import modelo.PeriodoAcademico;

/**
 *
 * @author mrbingus
 */
public class PeriodoAcademicoModeloTabla extends AbstractTableModel{
    
    private ListaEnlazada<PeriodoAcademico>periodos;
    
        @Override
        public int getRowCount() {
                return getPeriodos().getLength();
        }

        @Override
        public int getColumnCount() {
                return 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
                PeriodoAcademico e = new PeriodoAcademico();
            try {
                    e = getPeriodos().getInfo(rowIndex);
            } catch (PosicionException ex) {
                    Logger.getLogger(PeriodoAcademicoModeloTabla.class.getName()).log(Level.SEVERE, null, ex);
            }
                switch (columnIndex) {
                        case 0:
                                return (e != null) ? e.getMesInicio()+ " " + e.getYearInicio() + " - " +e.getMesFin() + " " + e.getYearFin(): " "+e.getModalidad();
                        default:
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
    public ListaEnlazada<PeriodoAcademico> getPeriodos() {
        return periodos;
    }

    /**
     * @param periodos the periodos to set
     */
    public void setPeriodos(ListaEnlazada<PeriodoAcademico> periodos) {
        this.periodos = periodos;
    }
            
        
}
