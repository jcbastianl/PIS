/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.DAO.implementaciones;

import controlador.DAO.Conexion;
import controlador.DAO.manejoArchivos.DAOCiclo;
import controlador.TDA.listas.DynamicList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import modelo.Asignatura;
import modelo.Ciclo;

/**
 *
 * @author mrbingus
 */
public class CicloImplementacion implements DAOCiclo{
    
    private Conexion instanciaMysql = Conexion.getInstance();

    @Override
    public Boolean persist(Ciclo c) {
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareStatement("insert into ciclo(ciclo, paralelo)values('" + c.getCiclo() + "','" + c.getParalelo() + "')");
            consulta.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("No se guardo la competencia");
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
    public Boolean merge(Ciclo data, Integer index) {
        Connection conexion = null;
        PreparedStatement consulta = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareCall("UPDATE ciclo SET ciclo = '" + data.getCiclo() + "', paralelo = '" + data.getParalelo() + "' where id = '" + index + "';");
            consulta.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error al actualizar el ciclo");
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
            consulta = conexion.prepareStatement("DELETE FROM ciclo WHERE id = '" + index + "';");
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
    public DynamicList<Ciclo> all() {
        DynamicList<Ciclo> lista = new DynamicList<>();
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareStatement("select * from ciclo");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                Ciclo a = new Ciclo();
                a.setCiclo(rs.getInt(2));
                a.setParalelo(rs.getString(3));
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
    public Ciclo get(Integer id) {
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
