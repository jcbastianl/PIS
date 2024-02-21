/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.DAO.implementaciones;

import controlador.DAO.Conexion;
import controlador.DAO.manejoArchivos.DAOCursa;
import controlador.TDA.listas.DynamicList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Cursa;

/**
 *
 * @author Usuario iTC
 */
public class CursaImplementacion implements DAOCursa{
        private Conexion instanciaMysql = Conexion.getInstance();

        @Override
        public Boolean persist(Cursa t) {
                PreparedStatement consulta = null;
                Connection conexion = null;
                try {
                        conexion = getInstanciaMysql().conectar();
                        consulta = conexion.prepareStatement("insert into asistencia(fechainicio, fechafin, docenteid, cicloid, asignaturaid)values('" + t.getFechaInicio() + "','" + t.getFechaFin() + "','" + t.getDocente() + "','" + t.getCiclo() + "," + t.getAsignatura() + "')");
                        consulta.executeUpdate();
                        return true;
                } catch (Exception e) {
                        System.out.println("No se guardo el Cursa");
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
        public Boolean remove(Integer id) {
                PreparedStatement consulta = null;
                Connection conexion = null;
                try {
                        conexion = getInstanciaMysql().conectar();
                        consulta = conexion.prepareStatement("DELETE FROM cursa WHERE id = '" + id + "';");
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
        public Boolean merge(Cursa t, Integer i) {
                Connection conexion = null;
                PreparedStatement consulta = null;
                try {
                        conexion = getInstanciaMysql().conectar();
                        consulta = conexion.prepareCall("UPDATE cursa SET fechainicio = '" + t.getFechaInicio() + "', fechafin = '" + t.getFechaFin()+ "', docenteid = '" + t.getDocente()+ "', cicloid = '" + t.getCiclo() + "', asignatura = '" + t.getAsignatura() +"' where id = '" + i + "';");
                        consulta.executeUpdate();
                        return true;
                 
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Error al actualizar el cursa");
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
        public Cursa get(Integer id) {
                try {
                        return all().getInfo(id);
                } catch (Exception e) {
                        return null;
                }
        }

        @Override
        public DynamicList<Cursa> all() {
                DynamicList<Cursa> lista = new DynamicList<>();
                PreparedStatement consulta = null;
                Connection conexion = null;
                try {
                        conexion = getInstanciaMysql().conectar();
                        consulta = conexion.prepareStatement("select * from cursa");
                        ResultSet rs = consulta.executeQuery();
                        while (rs.next()) {
                                Cursa a = new Cursa();
                                a.setFechaInicio(rs.getDate(2));
                                a.setFechaFin(rs.getDate(3));
                                a.setDocente(rs.getInt(4));
                                a.setCiclo(rs.getInt(5));
                                a.setAsignatura(rs.getInt(6));
                                a.setId(lista.getLenght() + 1);
                                lista.add(a);
                        }
                        return lista;
                } catch (Exception e) {
                        System.out.println("No se pudo listar a todas las cursas");
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
