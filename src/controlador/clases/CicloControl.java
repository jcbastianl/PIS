/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.DAO.implementaciones.AsistenciaImplementacion;
import controlador.DAO.implementaciones.CicloImplementacion;
import controlador.TDA.listas.DynamicList;
import modelo.Asistencia;
import modelo.Ciclo;
import modelo.Cursa;

/**
 *
 * @author mrbingus
 */
public class CicloControl extends DaoImplement<Ciclo> {
    
    private CicloImplementacion aux = new CicloImplementacion();

    private DynamicList<Ciclo> ciclos;
    private Ciclo ciclo;

    public CicloControl() {
        super(Ciclo.class);
    }

    /**
     * @return the ciclos
     */
    public DynamicList<Ciclo> getCiclos() {
        ciclos = aux.all();
        return ciclos;
    }

    /**
     * @param ciclos the ciclos to set
     */
    public void setCiclos(DynamicList<Ciclo> ciclos) {
        this.ciclos = ciclos;
    }

    /**
     * @return the ciclo
     */
    public Ciclo getCiclo() {
        if (ciclo == null) {
            ciclo = new Ciclo();
        }
        return ciclo;
    }

    /**
     * @param ciclo the ciclo to set
     */
    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public Boolean persist() {
        ciclo.setId(all().getLenght() + 1);
        return aux.persist(ciclo);
    }
    
    public Boolean merge(Ciclo a, Integer index) {
        return aux.merge(a, index + 1);
    }

    public Boolean remove(Integer s) {
        return aux.remove(s + 1);
    }
    
}
