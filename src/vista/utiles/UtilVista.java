/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.utiles;

import controlador.TDA.listas.Exception.EmptyException;
import controlador.clases.CursaControl;
import controlador.clases.DocenteControl;
import controlador.clases.AsignaturaControl;
import controlador.clases.CicloControl;
import controlador.clases.EstadoAsistenciaControl;
import controlador.clases.EstudianteControl;
import controlador.clases.HorarioControl;
import controlador.clases.PeriodoAcademicoControl;
import controlador.ed.ecepciones.PosicionException;
import controlador.ed.listas.ListaEnlazada;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Asignatura;
import modelo.Ciclo;
import modelo.Cursa;
import modelo.Docente;
import modelo.PeriodoAcademico;

/**
 *
 * @author mrbingus
 */
public class UtilVista {

    public static void cargarcomboBoxPeriodos(JComboBox cbx) throws EmptyException, PosicionException {
    PeriodoAcademicoControl cc = new PeriodoAcademicoControl();
    cbx.removeAllItems();
    ListaEnlazada<PeriodoAcademico> listaPeriodos = cc.getListaPeriodos();
    for (int i = 0; i < listaPeriodos.size(); i++) {
        PeriodoAcademico periodo = listaPeriodos.obtenerElementoEnPosicion(i);
        cbx.addItem(periodo);
    }
}   
    
    public static void cargarcomboBoxCiclos(JComboBox cbx) throws EmptyException, PosicionException {
    CicloControl cc = new CicloControl();
    cbx.removeAllItems();
    ListaEnlazada<Ciclo> listaCiclos = cc.getCiclos();
    for (int i = 0; i < listaCiclos.size(); i++) {
        Ciclo ciclo = listaCiclos.obtenerElementoEnPosicion(i);
        cbx.addItem(ciclo);
    }
}
    
    public static void cargarcomboBoxDocente(JComboBox cbx) throws PosicionException {
    DocenteControl dc = new DocenteControl();
    cbx.removeAllItems();
    ListaEnlazada<Docente> listaDocentes = dc.getListaDocentes();
    for (int i = 0; i < listaDocentes.size(); i++) {
        Docente docente = listaDocentes.obtenerElementoEnPosicion(i);
        cbx.addItem(docente);
    }
}

    
    public static void cargarcomboBoxEstudiante(JComboBox cbx) throws EmptyException, PosicionException {
        EstudianteControl ec = new EstudianteControl();
        cbx.removeAllItems();
        for (Integer i = 0; i < ec.getListaEstudiantes().getLength(); i++) {
            cbx.addItem(ec.getListaEstudiantes().getInfo(i));
        }
    }

   public static void cargarcomboBoxCursa(JComboBox cbx) throws PosicionException, EmptyException {
    CursaControl cursaControl = new CursaControl();
    cbx.removeAllItems();
    ListaEnlazada<Cursa> listaCursas = cursaControl.getListaCursas();

    for (int i = 0; i < listaCursas.size(); i++) {
        Cursa cursa = listaCursas.obtenerElementoEnPosicion(i);
        cbx.addItem(cursa);
    }
}

    public static void cargarcomboBoxAsignatura(JComboBox cbx) throws EmptyException, PosicionException {
    AsignaturaControl curc = new AsignaturaControl();
    cbx.removeAllItems();
    ListaEnlazada<Asignatura> listaAsignaturas = curc.getListaAsignaturas();
    for (int i = 0; i < listaAsignaturas.size(); i++) {
        Asignatura asignatura = listaAsignaturas.obtenerElementoEnPosicion(i);
        cbx.addItem(asignatura);
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
