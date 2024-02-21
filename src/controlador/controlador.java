/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.clases.CuentaControl;
import controlador.clases.CursaControl;
import controlador.clases.DocenteControl;
import controlador.clases.EstudianteControl;
import controlador.clases.JustificativoControl;
import controlador.utiles.Utiles;
import modelo.Cuenta;
import modelo.Docente;
import vista.docente.FrmDocenteAsignaciones;
import vista.docente.FrmDocentePrincipal;


/**
 *
 * @author mrbingus
 */
public class controlador {
    public static void main(String[] args) throws Exception{

//        Persona p = new Persona();
//        p.setNombre("ADMIN");
//        p.setApellido("ADMIN");
//        p.setRol(2);
//        CuentaControl cc = new CuentaControl();
//        Cuenta cuenta = new Cuenta();
//        cuenta.setContraseña("admin");
//        cuenta.setCorreo("admin");
//        cuenta.setPersona(p);
//        cc.setCuenta(cuenta);
//        cc.persist();

        //new FrmDocenteAsignaciones(new CursaControl().all().getInfo(1)).setVisible(true);
        
        new FrmDocentePrincipal(new DocenteControl().getListaDocentes().getInfo(0)).setVisible(true);
//        JustificativoControl n = new JustificativoControl();
//        n.getJustificativo().setDescripcion("Falte porque jugaba el madrid");
//        n.getJustificativo().setMotivo("madrid vs barce");
//        n.getJustificativo().setId(1);
//        n.getJustificativo().setId_asistencia(1);
//        n.persist();
        
    }
}
