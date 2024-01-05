/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.utiles;

import controlador.TDA.listas.Exception.EmptyException;
import controlador.clases.DocenteControl;
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
    
}
