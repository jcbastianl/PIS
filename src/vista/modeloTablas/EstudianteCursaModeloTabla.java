/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.clases.CicloControl;
import controlador.clases.DocenteControl;
import controlador.ed.listas.ListaEnlazada;
import controlador.utiles.Utiles;
import javax.swing.table.AbstractTableModel;
import modelo.Asignatura;
import modelo.Ciclo;
import modelo.Cursa;
import modelo.Docente;

/**
 *
 * @author mrbingus
 */
public class EstudianteCursaModeloTabla extends AbstractTableModel {

    private ListaEnlazada<Cursa> listaCursos;

    @Override
    public int getRowCount() {
        return listaCursos.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Cursa c = listaCursos.obtenerElementoEnPosicion(rowIndex);
            Asignatura a = c.getAsignatura();
            Docente d = new Docente();
            Ciclo ci = new Ciclo();

            // Obtener el docente y el ciclo de la clase
            if (c != null) {
                d = new DocenteControl().all().obtenerElementoEnPosicion(Utiles.encontrarPosicion("docente", c.getCiclo()));
                ci = new CicloControl().getCiclos().obtenerElementoEnPosicion(Utiles.encontrarPosicion("ciclo", c.getCiclo()));
            }

            switch (columnIndex) {
                case 0:
                    return (a != null) ? a.getNombre() : " ";
                case 1:
                    return (d != null) ? d.getNombre() + " " + d.getApellido() : " ";
                case 2:
                    return (c != null) ? Utiles.formaterarFecha(c.getFechaInicio()) : " ";
                case 3:
                    return (c != null) ? Utiles.formaterarFecha(c.getFechaFin()) : " ";
                default:
                    return null;
            }
        } catch (IndexOutOfBoundsException ex) {
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

    /**
     * @return the listaCursos
     */
    public ListaEnlazada<Cursa> getListaCursos() {
        return listaCursos;
    }

    /**
     * @param listaCursos the listaCursos to set
     */
    public void setListaCursos(ListaEnlazada<Cursa> listaCursos) {
        this.listaCursos = listaCursos;
    }
}
