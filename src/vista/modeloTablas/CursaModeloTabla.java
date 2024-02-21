/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.ed.ecepciones.PosicionException;
import controlador.ed.ecepciones.VacioException;
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
    private ListaEnlazada<Asignatura> listaAsignaturas;
    private ListaEnlazada<Docente> listaDocentes;
    private ListaEnlazada<Ciclo> listaCiclos;
    

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
        Asignatura a = listaAsignaturas.obtenerElementoEnPosicion(c.getAsignaturaId());
        Docente d = listaDocentes.obtenerElementoEnPosicion(c.getDocenteId());
        Ciclo ci = listaCiclos.obtenerElementoEnPosicion(c.getCicloId() - 1); 

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

        public ListaEnlazada<Asignatura> getListaAsignaturas() {
                return listaAsignaturas;
        }

        public void setListaAsignaturas(ListaEnlazada<Asignatura> listaAsignaturas) {
                this.listaAsignaturas = listaAsignaturas;
        }

        public ListaEnlazada<Docente> getListaDocentes() {
                return listaDocentes;
        }

        public void setListaDocentes(ListaEnlazada<Docente> listaDocentes) {
                this.listaDocentes = listaDocentes;
        }

        public ListaEnlazada<Ciclo> getListaCiclos() {
                return listaCiclos;
        }

        public void setListaCiclos(ListaEnlazada<Ciclo> listaCiclos) {
                this.listaCiclos = listaCiclos;
        }
}