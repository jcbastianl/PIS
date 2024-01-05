package vista.modeloTablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import modelo.Estudiante;

public class ModeloTablaAsistencia extends AbstractTableModel {

        private DynamicList<Estudiante> estudiantes;
        private DefaultTableModel modelo = new DefaultTableModel();
        Integer numeroColumnas = 0;

        public void agregarColumnas(int numeroColumnas) {
                this.numeroColumnas = numeroColumnas;
                for (int i = 1; i <= numeroColumnas; i++) {
                        String nombreColumna = "Hora " + i;
                        modelo.addColumn(nombreColumna);
                }
                fireTableStructureChanged();
        }

        @Override
        public int getRowCount() {
                return getEstudiantes().getLenght();
        }

        @Override
        public int getColumnCount() {
                return numeroColumnas + 1;
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
                                return "NOMBRE";
                        default:
                                return null;
                }
        }

        public DynamicList<Estudiante> getEstudiantes() {
                if (estudiantes == null) {
                        estudiantes = new DynamicList<Estudiante>();
                }
                return estudiantes;
        }

        public void setEstudiantes(DynamicList<Estudiante> estudiantes) {
                this.estudiantes = estudiantes;
        }

}
