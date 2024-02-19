/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.ed.listas;

import controlador.ed.ecepciones.PosicionException;
import controlador.ed.ecepciones.VacioException;
import modelo.Docente;

/**
 *
 * @author walter
 */
public class ListaEnlazada<E> {

    private NodoLista<E> cabecera;
    private Integer size = 0;

    public NodoLista getCabecera() {
        return cabecera;
    }

    public void setCabecera(NodoLista cabecera) {
        this.cabecera = cabecera;
    }

    public boolean isEmpty() {
        return cabecera == null;

    }

    public void insertar(E info) {
        NodoLista<E> nuevo = new NodoLista<>(info, null);
        if (isEmpty()) {
            this.cabecera = nuevo;
            this.size++;
        } else {
            NodoLista<E> aux = cabecera;

            while (aux.getSig() != null) {
                aux = aux.getSig();
            }
            aux.setSig(nuevo);
            this.size++;
        }

    }

    public void insertarInicio(E info) {
        if (isEmpty()) {
            insertar(info);
        } else {
            NodoLista<E> nuevo = new NodoLista<>(info, null);
            nuevo.setSig(cabecera);
            cabecera = nuevo;
            size++;
        }
    }

    public void insertarPosicion(E info, Integer pos) throws PosicionException {

        if (isEmpty()) {
            insertar(info);
        } else if (pos >= 0 && pos < size() && pos == 0) {
            insertarInicio(info);
        } else if (pos >= 0 && pos < size()) {
            NodoLista<E> nuevo = new NodoLista<>(info, null);
            NodoLista<E> aux = cabecera;
            for (int i = 0; i < (pos - 1); i++) {
                aux = aux.getSig();
            }
            NodoLista<E> sig = aux.getSig();
            aux.setSig(nuevo);
            nuevo.setSig(sig);
            size++;
        } else {
            throw new PosicionException();
        }
    }

    public E get(Integer pos) throws VacioException, PosicionException {
        //7  -----  8 ----- 9  --- 0  --- null
        if (isEmpty()) {
            throw new VacioException();
        } else {
            E dato = null;
            if (pos >= 0 && pos < size()) {
                if (pos == 0) {
                    dato = cabecera.getInfo();
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSig();
                    }
                    dato = aux.getInfo();
                }
            } else {
                throw new PosicionException();
            }
            return dato;
        }
    }
    
    public void update(Integer pos, E dato) throws VacioException, PosicionException {
        if (isEmpty()) {
            throw new VacioException();
        } else {
            
            if (pos >= 0 && pos < size()) {
                if (pos == 0) {
                    //dato = cabecera.getInfo();
                    cabecera.setInfo(dato);
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSig();
                    }
                    aux.setInfo(dato);
                }
            } else {
                throw new PosicionException();
            }
            //return dato;
        }
    }
    
    public int getLength() {
    return size;
}

public E getInfo(int pos) throws PosicionException {
    if (pos >= 0 && pos < size()) {
        if (pos == 0) {
            return cabecera.getInfo();
        } else {
            NodoLista<E> aux = cabecera;
            for (int i = 0; i < pos; i++) {
                aux = aux.getSig();
            }
            return aux.getInfo();
        }
    } else {
        throw new PosicionException();
    }
}

    public E delete(Integer pos) throws VacioException, PosicionException {
        //7  -----  8 ----- 9  --- 0  --- null
        //delete 8
        //7  -----   9  --- 0  --- null
        if (isEmpty()) {
            throw new VacioException();
        } else {
            E dato = null;
            if (pos >= 0 && pos < size()) {
                if (pos == 0) {
                    dato = cabecera.getInfo();
                    cabecera = cabecera.getSig();
                    size--;
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < (pos-1); i++) {
                        aux = aux.getSig();
                    }

                        NodoLista<E> aux1 = aux.getSig();
                       dato = aux1.getInfo();
                    NodoLista<E> proximo = aux.getSig();
                        aux.setSig(proximo.getSig());
                    size--;
                }
            } else {
                throw new PosicionException();
            }
            return dato;
        }
    }

    public Integer size() {
        return size;
    }

    public void imprimir() throws VacioException {
        if (isEmpty()) {
            throw new VacioException();
        } else {
            NodoLista<E> aux = cabecera;
            System.out.println("----LISTA------");
            for (int i = 0; i < size(); i++) {
                System.out.print(aux.getInfo() + "  ");
                aux = aux.getSig();
            }
            System.out.println("");
            System.out.println("----LISTA FIN------");
        }

    }
    
    public void deleteAll() {
        this.cabecera = null;
    }

    public E obtenerElementoEnPosicion(int rowIndex) throws PosicionException {
    if (rowIndex >= 0 && rowIndex < size) {
        NodoLista<E> actual = cabecera;
        for (int i = 0; i < rowIndex; i++) {
            actual = actual.getSig();
        }
        return actual.getInfo();
    } else {
        throw new PosicionException("La posición especificada está fuera de los límites de la lista.");
    }
}

    
}