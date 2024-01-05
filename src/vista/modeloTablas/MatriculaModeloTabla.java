/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.utiles.Utiles;
import javax.swing.table.AbstractTableModel;
import modelo.Matricula;

/**
 *
 * @author mrbingus
 */
public class MatriculaModeloTabla extends AbstractTableModel{

    private DynamicList<Matricula>matriculas;
    
    @Override
    public int getRowCount() {
        return getMatriculas().getLenght();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Matricula d = getMatriculas().getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (d != null) ? d.getEstudiante().getNombre() +" "+d.getEstudiante().getApellido() : " ";
                case 1:
                    return (d != null) ? d.getCursa().getCiclo()+""+d.getCursa().getLetra() : " ";
                case 2:
                    return (d != null) ? d.getNumero() : " ";
                case 3:
                    return (d != null) ? d.getEstadoMatricula() : " ";
                case 4:
                    return (d != null) ? Utiles.formaterarFecha(d.getFechaRegistro()) : " ";
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
                return "ESTUDIANTE";
            case 1:
                return "CURSO";
            case 2:
                return "Nro MATRICULA";     
            case 3:
                return "ESTADO";   
            case 4:
                return "FECHA"; 
            default:
                return null;
        }
    }

    public DynamicList<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(DynamicList<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
    
    
}
