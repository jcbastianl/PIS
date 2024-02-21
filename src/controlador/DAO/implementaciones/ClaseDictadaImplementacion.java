/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.DAO.implementaciones;

import controlador.DAO.Conexion;
import controlador.DAO.manejoArchivos.DAOClaseDictada;
import controlador.TDA.listas.DynamicList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Asignatura;
import modelo.ClaseDictada;

/**
 *
 * @author mrbingus
 */
public class ClaseDictadaImplementacion implements DAOClaseDictada{
    
        private Conexion instanciaMysql = Conexion.getInstance();

    @Override
    public Boolean persist(ClaseDictada data) {
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareStatement("insert into clasedictada(tema, fecha, cursa_id_cursa, horario_id_horario)values('" + data.getTema() + "','" + data.getFecha()+ "','" + data.getId_cursa()+ "','" + data.getId_horario() + "')");
            consulta.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("No se guardo la clasedictada");
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
    public Boolean merge(ClaseDictada data, Integer index) {
        Connection conexion = null;
        PreparedStatement consulta = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareCall("UPDATE clasedictada SET tema = '" + data.getTema() + "', fecha = '" + data.getFecha() + "', cursa_id_cursa = '" + data.getId_cursa() + "', horario_id_horario = '" + data.getId_horario() + "' where id = '" + index + "';");
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
            consulta = conexion.prepareStatement("DELETE FROM clasedictada WHERE id = '" + index + "';");
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
    public DynamicList<ClaseDictada> all() {
        DynamicList<ClaseDictada> lista = new DynamicList<>();
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareStatement("select * from asignatura");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                ClaseDictada a = new ClaseDictada();
                a.setTema(rs.getString(1));
                a.setFecha(rs.getDate(2));
                a.setId_cursa(rs.getInt(3));
                a.setId_horario(rs.getInt(4));
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
    public ClaseDictada get(Integer id) {
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
