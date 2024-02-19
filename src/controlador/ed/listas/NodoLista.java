/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.ed.listas;

/**
 *
 * @author walter
 */
public class NodoLista<E> {
    private E info;
    private NodoLista sig;

    public NodoLista() {
        info = null;
        sig = null;
    }

    public NodoLista(E info, NodoLista sig) {
        this.info = info;
        this.sig = sig;
    }
    
    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public NodoLista getSig() {
        return sig;
    }

    public void setSig(NodoLista sig) {
        this.sig = sig;
    }
    
}
