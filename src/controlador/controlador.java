/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.clases.CursaControl;
import controlador.clases.EstudianteControl;


/**
 *
 * @author mrbingus
 */
public class controlador {
    public static void main(String[] args) {
        CursaControl cursacControl = new CursaControl();
        try {
                         cursacControl.setCursa(cursacControl.getListaCursas().getInfo(0));
                        cursacControl.getCursa().getMatriculas().remove(0);
                        cursacControl.merge(cursacControl.getCursa(), 0);
                        cursacControl.setCursa(null);           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
    }
}
