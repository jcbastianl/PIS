/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.DAO.implementaciones;

import controlador.DAO.Conexion;
import controlador.DAO.manejoArchivos.DAOHorario;
import controlador.TDA.listas.DynamicList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Horario;

/**
 *
 * @author mrbingus
 */
public class HorarioImplementacion implements DAOHorario {

    private Conexion instanciaMysql = Conexion.getInstance();

    @Override
    public Boolean persist(Horario data) {
        PreparedStatement consulta = null;
        Connection conexion = null;

        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareStatement("insert into horario(horainicio, horafin, dia)values('" + data.getHoraInicio() + "','" + data.getHoraFin() + "','" + data.getDia() + "')");
            consulta.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("No se guardo la Horario");
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
    public Boolean merge(Horario data, Integer index) {
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareStatement("DELETE FROM horario WHERE id = '" + index + "';");
            consulta.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
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
    public Boolean remove(Integer index) {
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareStatement("DELETE FROM horario WHERE id = '" + index + "';");
            consulta.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
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
    public DynamicList<Horario> all() {
        DynamicList<Horario> lista = new DynamicList<>();
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareStatement("select * from horario");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                Horario a = new Horario();
                a.setHoraInicio(rs.getString(1));
                a.setHoraFin(rs.getString(2));
                a.setDia(rs.getString(3));
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
        }    }

    @Override
    public Horario get(Integer id) {
        try {
            return all().getInfo(id);
        } catch (Exception e) {
            return null;
        }    }

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
