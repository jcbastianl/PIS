/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.utiles;

import controlador.TDA.listas.Exception.EmptyException;
import controlador.clases.CursaControl;
import controlador.clases.DocenteControl;
import controlador.clases.EstadoMatriculaControl;
import controlador.clases.EstudianteControl;
import javax.swing.JComboBox;


/**
 *
 * @author mrbingus
 */
public class UtilVista {
    
    public static void cargarcomboBoxDocente(JComboBox cbx) throws EmptyException{
        DocenteControl dc = new DocenteControl();
        cbx.removeAllItems();
        for (Integer i = 0; i < dc.getListaDocentes().getLenght(); i++) {
            cbx.addItem(dc.getListaDocentes().getInfo(i));
        }
    }
    
    public static void cargarcomboBoxEstudiante(JComboBox cbx) throws EmptyException{
        EstudianteControl ec = new EstudianteControl();
        cbx.removeAllItems();
        for (Integer i = 0; i < ec.getListaEstudiantes().getLenght(); i++) {
            cbx.addItem(ec.getListaEstudiantes().getInfo(i));
        }
    }
    
    public static void cargarcomboBoxCursa(JComboBox cbx) throws EmptyException{
        CursaControl curc = new CursaControl();
        cbx.removeAllItems();
        for (Integer i = 0; i < curc.getListaCursas().getLenght(); i++) {
            cbx.addItem(curc.getListaCursas().getInfo(i));
        }
    }

    public static void cargarcomboBoxEstadosMatricula(JComboBox cbx) throws EmptyException{
        EstadoMatriculaControl curc = new EstadoMatriculaControl();
        cbx.removeAllItems();
        for (Integer i = 0; i < curc.getListaEstados().getLenght(); i++) {
            cbx.addItem(curc.getListaEstados().getInfo(i));
        }
    }
}
