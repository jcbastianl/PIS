package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.Estudiante;

public class ModeloTablaAsistencia extends AbstractTableModel {

        private DynamicList<Estudiante> estudiantes;

        @Override
        public int getRowCount() {
                return getEstudiantes().getLenght();
        }

        @Override
        public int getColumnCount() {
                return 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
                try {
                        Estudiante e = getEstudiantes().getInfo(rowIndex);
                        switch (columnIndex) {
                                case 0:
                                        return (e != null) ? e.getNombre() + " " + e.getApellido() : " ";
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
                        default:
                                return null;
                }
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
