/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.ed.ecepciones.PosicionException;
import controlador.ed.listas.ListaEnlazada;
import controlador.utiles.Utiles;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import modelo.Asignatura;
import modelo.Ciclo;
import modelo.Cursa;
import modelo.Docente;




/**
 *
 * @author mrbingus
 */


public class CursaModeloTabla extends AbstractTableModel {

    private ListaEnlazada<Cursa> listaCursos;

    @Override
    public int getRowCount() {
        return listaCursos.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

 @Override
public Object getValueAt(int rowIndex, int columnIndex) {
    try {
        Cursa c = listaCursos.obtenerElementoEnPosicion(rowIndex);
        Asignatura a = c.getAsignatura();
        Docente d = c.getDocente();
        Ciclo ci = c.getCiclo(); 

        switch (columnIndex) {
            case 0:
                return (a != null) ? a.getNombre() : " ";
            case 1:
                return (ci != null) ? ci.getCiclo() + " " + ci.getParalelo() : " ";
            case 2:
                return (d != null) ? d.getNombre() + " " + d.getApellido() : " ";
            case 3:
                return (c != null) ? Utiles.formaterarFecha(c.getFechaInicio()) : " ";
            case 4:
                return (c != null) ? Utiles.formaterarFecha(c.getFechaFin()) : " ";
            default:
                return null;
        }
    } catch (IndexOutOfBoundsException ex) {
        return null;
    } catch (PosicionException ex) {
        Logger.getLogger(CursaModeloTabla.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ASIGNATURA";
            case 1:
                return "CICLO";
            case 2:
                return "DOCENTE";
            case 3:
                return "INICIO";
            case 4:
                return "FIN";
            default:
                return null;
        }
    }

    public ListaEnlazada<Cursa> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(ListaEnlazada<Cursa> listaCursos) {
        this.listaCursos = listaCursos;
    }

    
}