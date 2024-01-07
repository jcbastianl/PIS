/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.utiles;

import controlador.TDA.listas.Exception.EmptyException;
import controlador.clases.CursaControl;
import controlador.clases.DocenteControl;
import controlador.clases.EstadoMatriculaControl;
import controlador.clases.AsignaturaControl;
import controlador.clases.EstadoAsistenciaControl;
import controlador.clases.EstudianteControl;
import controlador.clases.HorarioControl;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author mrbingus
 */
public class UtilVista {

    public static void cargarcomboBoxDocente(JComboBox cbx) throws EmptyException {
        DocenteControl dc = new DocenteControl();
        cbx.removeAllItems();
        for (Integer i = 0; i < dc.getListaDocentes().getLenght(); i++) {
            cbx.addItem(dc.getListaDocentes().getInfo(i));
        }
    }

    public static void cargarcomboBoxEstudiante(JComboBox cbx) throws EmptyException {
        EstudianteControl ec = new EstudianteControl();
        cbx.removeAllItems();
        for (Integer i = 0; i < ec.getListaEstudiantes().getLenght(); i++) {
            cbx.addItem(ec.getListaEstudiantes().getInfo(i));
        }
    }

    public static void cargarcomboBoxCursa(JComboBox cbx) throws EmptyException {
        CursaControl curc = new CursaControl();
        cbx.removeAllItems();
        for (Integer i = 0; i < curc.getListaCursas().getLenght(); i++) {
            cbx.addItem(curc.getListaCursas().getInfo(i));
        }
    }

    public static void cargarcomboBoxEstadosMatricula(JComboBox cbx) throws EmptyException {
        EstadoMatriculaControl curc = new EstadoMatriculaControl();
        cbx.removeAllItems();
        for (Integer i = 0; i < curc.getListaEstados().getLenght(); i++) {
            cbx.addItem(curc.getListaEstados().getInfo(i));
        }
    }

    public static void cargarcomboBoxAsignatura(JComboBox cbx) throws EmptyException {
        AsignaturaControl curc = new AsignaturaControl();
        cbx.removeAllItems();
        for (Integer i = 0; i < curc.getListaAsignaturas().getLenght(); i++) {
            cbx.addItem(curc.getListaAsignaturas().getInfo(i));
        }
    }

    public static void cargarcomboBoxHorario(JComboBox cbx) throws EmptyException {
        HorarioControl curc = new HorarioControl();
        cbx.removeAllItems();
        for (Integer i = 0; i < curc.getListH().getLenght(); i++) {
            cbx.addItem(curc.getListH().getInfo(i));
        }
    }

    public static void cargarcomboBoxEstadoAsistencia(JComboBox cbx) throws EmptyException {
        EstadoAsistenciaControl curc = new EstadoAsistenciaControl();
        cbx.removeAllItems();
        for (Integer i = 0; i < curc.getListaEstadoAsistencia().getLenght(); i++) {
            cbx.addItem(curc.getListaEstadoAsistencia().getInfo(i));
        }
    }
//Metodo para que no escriban barbaridades en otra cosa que no sea letras sirve para nombres ,apellidos , etc.
    public static void soloLetras(JTextField campoTexto){

    campoTexto.addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent e){
            char c = e.getKeyChar();
            if (!(Character.isLetter(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                JOptionPane.showMessageDialog(null, "Solo se permiten letras"); 
                e.consume();
            }
        }
    });

}
}
