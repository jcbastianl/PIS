/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.Docente;

/**
 *
 * @author mrbingus
 */
public class DocenteControl extends DaoImplement<Docente>{
    
    private DynamicList<Docente>listaDocentes;
    private Docente docente;
    
    
    public DocenteControl() {
        super(Docente.class);
    }

    /**
     * @return the listaDocentes
     */
    public DynamicList<Docente> getListaDocentes() {
        listaDocentes = all();
        return listaDocentes;
    }

    /**
     * @param listaDocentes the listaDocentes to set
     */
    public void setListaDocentes(DynamicList<Docente> listaDocentes) {
        this.listaDocentes = listaDocentes;
    }

    /**
     * @return the Docente
     */
    public Docente getDocente() {
        if(docente == null){
            docente = new Docente();
        }
        return docente;
    }

    /**
     * @param Docente the Docente to set
     */
    public void setDocente(Docente Docente) {
        this.docente = Docente;
    }
    
    public Boolean persist() {
        docente.setId(all().getLenght() + 1);
        return persist(docente);
    }    
    
    
}
