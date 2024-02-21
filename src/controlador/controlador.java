/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.DAO.implementaciones.AsignaturaImplementacion;
import controlador.DAO.implementaciones.CicloImplementacion;
import controlador.DAO.implementaciones.CuentaImplementacion;
import controlador.DAO.implementaciones.HorarioImplementacion;
import controlador.TDA.listas.DynamicList;
import controlador.clases.CuentaControl;
import controlador.clases.CursaControl;
import controlador.clases.DocenteControl;
import controlador.clases.EstudianteControl;
import controlador.clases.JustificativoControl;
import controlador.utiles.Utiles;
import modelo.Asignatura;
import modelo.Ciclo;
import modelo.Cuenta;
import modelo.Docente;
import modelo.Persona;
import vista.docente.FrmDocenteAsignaciones;
import vista.docente.FrmDocentePrincipal;


/**
 *
 * @author mrbingus
 */
public class controlador {
    public static void main(String[] args) throws Exception{

        CuentaImplementacion aux = new CuentaImplementacion();
        System.out.println(aux.all().toString());
    }
}
