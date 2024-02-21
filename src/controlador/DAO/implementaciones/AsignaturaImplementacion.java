/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.DAO.implementaciones;

import controlador.DAO.Conexion;
import controlador.DAO.manejoArchivos.DAOAsignatura;
import controlador.TDA.listas.DynamicList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Asignatura;

/**
 *
 * @author mrbingus
 */
public class AsignaturaImplementacion implements DAOAsignatura {

    private Conexion instanciaMysql = Conexion.getInstance();

    @Override
    public Boolean persist(Asignatura a) {
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareStatement("insert into asignatura(nombre, codigo)values('" + a.getNombre() + "','" + a.getCodigo() + "')");
            consulta.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("No se guardo la asignatura");
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
    public Boolean merge(Asignatura a, Integer index) {
        Connection conexion = null;
        PreparedStatement consulta = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareCall("UPDATE asignatura SET codigo = '" + a.getCodigo() + "', nombre = '" + a.getNombre() + "' where id = '" + index + "';");
            consulta.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error al actualizar la competencia");
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
            consulta = conexion.prepareStatement("DELETE FROM asignatura WHERE id = '" + index + "';");
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
    public DynamicList<Asignatura> all() {
        DynamicList<Asignatura> lista = new DynamicList<>();
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareStatement("select * from asignatura");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                Asignatura a = new Asignatura();
                a.setNombre(rs.getString(2));
                a.setCodigo(rs.getString(3));
                a.setId(lista.getLenght() + 1);
                lista.add(a);
            }
            return lista;
        } catch (Exception e) {
            System.out.println("No se pudo listar a todas las asignaturas");
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
    public Asignatura get(Integer id) {
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
