/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.ed.colas;

import controlador.ed.ecepciones.PosicionException;
import controlador.ed.ecepciones.TopeException;
import controlador.ed.ecepciones.VacioException;


/**
 *
 * @author walter
 */
public class Cola<E> {
    private ColaI<E> cola;

    public Cola(Integer tope) {
        cola = new ColaI(tope);
    }
    
    public void queue(E obj) throws TopeException {
        cola.queue(obj);
    }

    public E dequeue() throws VacioException, PosicionException {
        return cola.dequeue();
    }
    
    public Integer getTope() {
        return cola.getTope();
    }
    
    public void print() throws VacioException {
        cola.imprimir();
    }
    
}
