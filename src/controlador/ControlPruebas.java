/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.TDA.listas.Exception.EmptyException;
import controlador.clases.EstudianteControl;
import vista.estudiante.FrmEstudiantePrincipal;

/**
 *
 * @author Usuario iTC
 */
public class ControlPruebas {
        public static void main(String[] args) throws EmptyException {
                //new FrmDocentePrincipal(new DocenteControl().getListaDocentes().getInfo(2)).setVisible(true);
                new FrmEstudiantePrincipal(new EstudianteControl().getListaEstudiantes().getInfo(1)).setVisible(true);
                //new FrmEstudianteAsistencias(new EstudianteControl().getListaEstudiantes().getInfo(1).getId(), "Base Datos").setVisible(true);
        }
}
