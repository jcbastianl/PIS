/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.DAO.implementaciones;

import controlador.DAO.Conexion;
import controlador.DAO.manejoArchivos.DAODocente;
import controlador.TDA.listas.DynamicList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.ClaseDictada;
import modelo.Cuenta;
import modelo.Docente;

/**
 *
 * @author mrbingus
 */
public class DocenteImplementacion implements DAODocente{
        private Conexion instanciaMysql = Conexion.getInstance();

    @Override
    public Boolean persist(Docente data) {
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareStatement("insert into docente(nombre, apellido, dni, titulo, telefono, preparacion, rol, cuenta)values('" + data.getNombre()+ "','" + data.getApellido()+ "','" + data.getDni()+ "','" + data.getTitulo()+ "','" + data.getTelefono()+ "','" + data.getPreparacion()+ "','" + data.getRol()+ "','" + data.getCuenta()+ "')");
            consulta.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("No se guardo la docente");
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (consulta != null) {
                getInstanciaMysql().cerrarStatement(consulta);
            }
            if (conexion != null) {
                getInstanciaMysql().desconectar(conexion);
            }
        }

    }

    @Override
    public Boolean merge(Docente data, Integer index) {
        Connection conexion = null;
        PreparedStatement consulta = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareCall("UPDATE docente SET nombre = '" + data.getNombre() + "', apellido = '" + data.getApellido() + "', dni = '" + data.getDni()+ "', titulo = '" + data.getTitulo()+ "', telefono = '" + data.getTelefono()+ "', preparacion = '" + data.getPreparacion() + "', rol = '" + data.getRol()+ "', cuenta = '" + data.getCuenta() + "' where id = '" + index + "';");
            consulta.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error al actualizar la clase");
            return false;
        } finally {
            if (consulta != null) {
                getInstanciaMysql().cerrarStatement(consulta);
            }
            if (conexion != null) {
                getInstanciaMysql().desconectar(conexion);
            }

        }
    }

    @Override
    public Boolean remove(Integer index) {
      PreparedStatement consulta = null;
        Connection conexion = null;

        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareStatement("DELETE FROM docente WHERE id = '" + index + "';");
            consulta.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally {
            if (consulta != null) {
                getInstanciaMysql().cerrarStatement(consulta);
            }
            if (conexion != null) {
                getInstanciaMysql().desconectar(conexion);
            }
        }
        return false;   
    }

    @Override
    public DynamicList<Docente> all() {
        DynamicList<Docente> lista = new DynamicList<>();
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareStatement("select * from docente");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                Docente a = new Docente();
                a.setNombre(rs.getString(2));
                a.setApellido(rs.getString(3));
                a.setDni(rs.getString(4));
                a.setTitulo(rs.getString(   5));
                a.setTelefono(rs.getString(6));
                a.setPreparacion(rs.getString(7));
                a.setRol(rs.getInt(8));   
                a.setCuenta(rs.getInt(9)); 
                a.setId(lista.getLenght() + 1);
                lista.add(a);
            }
            return lista;
            
        } catch (Exception e) {
            System.out.println("No se pudo listar a todas las docente");
            System.out.println(e.getMessage());
            return null;
        } finally {
            if (consulta != null) {
                getInstanciaMysql().cerrarStatement(consulta);
            }
            if (conexion != null) {
                getInstanciaMysql().desconectar(conexion);
            }
        }
    }

    @Override
    public Docente get(Integer id) {
        try {
            return all().getInfo(id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @return the instanciaMysql
     */
    public Conexion getInstanciaMysql() {
        return instanciaMysql;
    }

    /**
     * @param instanciaMysql the instanciaMysql to set
     */
    public void setInstanciaMysql(Conexion instanciaMysql) {
        this.instanciaMysql = instanciaMysql;
    }
    
}
