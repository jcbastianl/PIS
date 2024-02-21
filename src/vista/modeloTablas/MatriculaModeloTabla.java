/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.clases.CursaControl;
import controlador.clases.EstudianteControl;
import controlador.ed.ecepciones.PosicionException;
import controlador.ed.listas.ListaEnlazada;
import controlador.utiles.Utiles;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import modelo.Cursa;
import modelo.Estudiante;
import modelo.Matricula;

/**
 *
 * @author mrbingus
 */
public class MatriculaModeloTabla extends AbstractTableModel {

    private ListaEnlazada<Matricula> matriculas;
    private ListaEnlazada<Estudiante> estudiantes;
    private ListaEnlazada<Cursa> cursas;
    

    @Override
    public int getRowCount() {
        return matriculas.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Matricula m = matriculas.obtenerElementoEnPosicion(rowIndex);
            Estudiante e = estudiantes.obtenerElementoEnPosicion(m.getId_estudiante());
            Cursa c = cursas.obtenerElementoEnPosicion(m.getId_cursa());

            switch (columnIndex) {
                case 0:
                    return (e != null) ? e.getNombre() + " " + e.getApellido() : " ";
                case 1:
                    return (c != null) ? c.toString() : " ";
                case 2:
                    return (m != null) ? m.getCodigo() : " ";
                case 3:
                    return (m != null) ? Utiles.traducirEstadoString(m.getEstadoMatricula()) : " ";
                case 4:
                    return (m != null) ? Utiles.formaterarFecha(m.getFechaRegistro()) : " ";
                default:
                    return null;
            }
        } catch (IndexOutOfBoundsException ex) {
            return null;
        } catch (PosicionException ex) {
            Logger.getLogger(MatriculaModeloTabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ESTUDIANTE";
            case 1:
                return "CURSO";
            case 2:
                return "CODIGO";
            case 3:
                return "ESTADO";
            case 4:
                return "FECHA";
            default:
                return null;
        }
    }

    public ListaEnlazada<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(ListaEnlazada<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

        public ListaEnlazada<Estudiante> getEstudiantes() {
                return estudiantes;
        }

        public void setEstudiantes(ListaEnlazada<Estudiante> estudiantes) {
                this.estudiantes = estudiantes;
        }

        public ListaEnlazada<Cursa> getCursas() {
                return cursas;
        }

        public void setCursas(ListaEnlazada<Cursa> cursas) {
                this.cursas = cursas;
        }
    
    
    
}