/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.EstadoMatricula;

/**
 *
 * @author mrbingus
 */
public class EstadoMatriculaControl extends DaoImplement<EstadoMatricula>{
    private EstadoMatricula estadoMatricula;
    private DynamicList<EstadoMatricula>listaEstados;

    public EstadoMatriculaControl() {
        super(EstadoMatricula.class);
    }

    public EstadoMatricula getEstadoMatricula() {
        if(estadoMatricula == null){
            estadoMatricula = new EstadoMatricula();
        }
        return estadoMatricula;
    }

    public void setEstadoMatricula(EstadoMatricula estadoMatricula) {
        this.estadoMatricula = estadoMatricula;
    }

    public DynamicList<EstadoMatricula> getListaEstados() {
        listaEstados = all();
        return listaEstados;
    }

    public void setListaEstados(DynamicList<EstadoMatricula> listaEstados) {
        this.listaEstados = listaEstados;
    }
    
        public Boolean persist() {
        estadoMatricula.setId(all().getLenght() + 1);
        return persist(estadoMatricula);
    }    
    
        /*public static void main(String[] args) {
        EstadoMatriculaControl c = new EstadoMatriculaControl();
            System.out.println(c.getListaEstados());
        c.getEstadoMatricula().setNombre("PENDIENTE");
        c.persist();
        c.setEstadoMatricula(null);
        c.getEstadoMatricula().setNombre("APROBADO");
        c.persist();
        c.setEstadoMatricula(null);
        c.getEstadoMatricula().setNombre("REPROBADO");
        c.persist();
        c.setEstadoMatricula(null);
                    System.out.println(c.getListaEstados());
    }*/
}
