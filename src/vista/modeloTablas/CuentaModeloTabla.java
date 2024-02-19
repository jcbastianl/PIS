/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.ed.ecepciones.PosicionException;
import controlador.ed.listas.ListaEnlazada;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import modelo.Cuenta;

/**
 *
 * @author jsbal
 */
public class CuentaModeloTabla extends AbstractTableModel {

    private ListaEnlazada<Cuenta> cuentas;

    @Override
    public int getRowCount() {
        return (cuentas != null) ? cuentas.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 3; // Ajusta este valor según la cantidad de columnas que quieras mostrar
    }

    @Override
public Object getValueAt(int rowIndex, int columnIndex) {
    try {
        Cuenta cuenta = cuentas.obtenerElementoEnPosicion(rowIndex);
        switch (columnIndex) {
            case 0:
                return (cuenta != null) ? cuenta.getCorreo() : " ";
            case 1:
                return (cuenta != null) ? cuenta.getContraseña() : " ";
            case 2:
                return (cuenta != null) ? cuenta.getEstado() : " ";
            default:
                return null;
        }
    } catch (IndexOutOfBoundsException ex) {
        return null;
    }   catch (PosicionException ex) {
            Logger.getLogger(CuentaModeloTabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Correo";
            case 1:
                return "Contraseña";
            case 2:
                return "Estado";
            default:
                return null;
        }
    }
    
    /**
     * @return the cuentas
     */
    public ListaEnlazada<Cuenta> getCuentas() {
        return cuentas;
    }

    /**
     * @param cuentas the cuentas to set
     */
    public void setCuentas(ListaEnlazada<Cuenta> cuentas) {
        this.cuentas = cuentas;
        fireTableDataChanged(); // Notifica a los listeners que los datos de la tabla han cambiado
    }
}

