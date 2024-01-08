/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.Asistencia;
import modelo.Estudiante;

/**
 *
 * @author Usuario iTC
 */
public class ModeloTablaVerAsistencia extends AbstractTableModel {
        
        private DynamicList<Estudiante> estudiantes;
        private DynamicList<Asistencia> asistencias;

        @Override
        public int getRowCount() {
                return getEstudiantes().getLenght();
        }

        @Override
        public int getColumnCount() {
                return 4;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
                try {
                        Asistencia a = getAsistencias().getInfo(rowIndex);
                        Estudiante e = getEstudiantes().getInfo(rowIndex);
                        switch (columnIndex) {
                                case 0:
                                        return (a != null) ? e.getNombre() + " " + e.getApellido() : " ";
                                case 1:
                                        return (a != null) ? a.getEstadoAsistencia().getNombre() : " ";
                                case 2:
                                        return (a != null) ? a.getClaseDictada().getTema(): " ";
                                case 3:
                                        return (a != null) ? a.getFecha(): " ";
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
                                return "Nombre y Apellido";
                        case 1:
                                return "Estado";
                        case 2:
                                return "Clase";
                        case 3:
                                return "Fecha";
                        default:
                                return null;
                }
        }

        public DynamicList<Asistencia> getAsistencias() {
                if (asistencias == null) {
                        asistencias = new DynamicList<>();
                }
                return asistencias;
        }

        public void setAsistencias(DynamicList<Asistencia> asistencias) {
                this.asistencias = asistencias;
        }
        
        public DynamicList<Estudiante> getEstudiantes() {
                if (estudiantes == null) {
                        estudiantes = new DynamicList<>();
                }
                return estudiantes;
        }

        public void setEstudiantes(DynamicList<Estudiante> estudiantes) {
                this.estudiantes = estudiantes;
        }
}
