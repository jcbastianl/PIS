/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.DAO.implementaciones;

import controlador.DAO.Conexion;
import controlador.DAO.manejoArchivos.DAOJustificativo;
import controlador.TDA.listas.DynamicList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Justificativo;

/**
 *
 * @author Usuario iTC
 */
public class JustificativoImplementacion implements DAOJustificativo{
        private Conexion instanciaMysql = Conexion.getInstance();

        @Override
        public Boolean persist(Justificativo t) {
                PreparedStatement consulta = null;
                Connection conexion = null;
                try {
                        conexion = getInstanciaMysql().conectar();
                        consulta = conexion.prepareStatement("insert into estudiante(motivo, descripcion, asistencia_id_asistencia)values('" + t.getMotivo()+ "','" + t.getDescripcion()+ "','" + t.getId_asistencia() + "')");
                        consulta.executeUpdate();
                        return true;
                } catch (Exception e) {
                        System.out.println("No se guardo el justificativo");
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
                        consulta = conexion.prepareStatement("DELETE FROM justificativo WHERE id = '" + id + "';");
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
        public Boolean merge(Justificativo t, Integer i) {
                Connection conexion = null;
                PreparedStatement consulta = null;
                try {
                        conexion = getInstanciaMysql().conectar();
                        consulta = conexion.prepareCall("UPDATE justificativo SET motivo = '" + t.getMotivo()+ "', descripcion = '" + t.getDescripcion()+ "', asistencia_id_asistencia = '" + t.getId_asistencia() + "' where id = '" + i + "';");
                        consulta.executeUpdate();
                        return true;
                 
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Error al actualizar el justificativo");
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
        public Justificativo get(Integer id) {
                try {
                        return all().getInfo(id);
                } catch (Exception e) {
                        return null;
                }
        }

        @Override
        public DynamicList<Justificativo> all() {
                DynamicList<Justificativo> lista = new DynamicList<>();
                PreparedStatement consulta = null;
                Connection conexion = null;
                try {
                        conexion = getInstanciaMysql().conectar();
                        consulta = conexion.prepareStatement("select * from justificativo");
                        ResultSet rs = consulta.executeQuery();
                        while (rs.next()) {
                                Justificativo a = new Justificativo();
                                a.setMotivo(rs.getString(2));
                                a.setDescripcion(rs.getString(3));
                                a.setId_asistencia(rs.getInt(4));
                                a.setId(lista.getLenght() + 1);
                                lista.add(a);
                        }
                        return lista;
                } catch (Exception e) {
                        System.out.println("No se pudo listar a todos los justificativos");
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
