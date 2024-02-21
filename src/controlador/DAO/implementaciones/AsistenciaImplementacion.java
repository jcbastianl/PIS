/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.DAO.implementaciones;

import com.mysql.cj.protocol.Resultset;
import controlador.DAO.Conexion;
import controlador.DAO.manejoArchivos.DAOAsignatura;
import controlador.DAO.manejoArchivos.DAOAsistencia;
import controlador.TDA.listas.DynamicList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Asistencia;


/**
 *
 * @author Usuario iTC
 */
public class AsistenciaImplementacion implements DAOAsistencia {

        private Conexion instanciaMysql = Conexion.getInstance();

        @Override
        public Boolean persist(Asistencia t) {
                PreparedStatement consulta = null;
                Connection conexion = null;
                String estado = validar(t.getEstadoAsistencia());
                try {
                        conexion = getInstanciaMysql().conectar();
                        consulta = conexion.prepareStatement("insert into asistencia(estado, clasedictada_id_clasedictada, estudiante_id_estudiante)values('" + estado + "','" + t.getId_claseDictada() + "','" + t.getId_estudiante() + "')");
                        consulta.executeUpdate();
                        return true;
                } catch (Exception e) {
                        System.out.println("No se guardo la Asistencia");
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
                        consulta = conexion.prepareStatement("DELETE FROM asistencia WHERE id = '" + id + "';");
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
        public Boolean merge(Asistencia t, Integer i) {
                Connection conexion = null;
                PreparedStatement consulta = null;
                String estado = validar(t.getEstadoAsistencia());
                try {
                        conexion = getInstanciaMysql().conectar();
                        consulta = conexion.prepareCall("UPDATE asistencia SET estado = '" + estado + "', clasedictada_id_clasedictada = '" + t.getId_claseDictada() + "', estudiante_id_estudiante = '" + t.getId_estudiante() + "' where id = '" + i + "';");
                        consulta.executeUpdate();
                        return true;
                 
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Error al actualizar la asistencia");
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
        public Asistencia get(Integer id) {
                try {
                        return all().getInfo(id);
                } catch (Exception e) {
                        return null;
                }
        }

        @Override
        public DynamicList<Asistencia> all() {
                DynamicList<Asistencia> lista = new DynamicList<>();
                PreparedStatement consulta = null;
                Connection conexion = null;
                try {
                        conexion = getInstanciaMysql().conectar();
                        consulta = conexion.prepareStatement("select * from asistencia");
                        ResultSet rs = consulta.executeQuery();
                        while (rs.next()) {
                                Asistencia a = new Asistencia();
                                a.setEstadoAsistencia(validarTexto(rs.getString(2)));
                                a.setId_claseDictada(rs.getInt(3));
                                a.setId_estudiante(rs.getInt(4));
                                a.setId(lista.getLenght() + 1);
                                lista.add(a);
                        }
                        return lista;
                } catch (Exception e) {
                        System.out.println("No se pudo listar a todas las asistencias");
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

        public String validar(Boolean b) {
                if (b) {
                        return "PRESENTE";
                } else {
                        return "AUSENTE";
                }
        }
        
        public boolean validarTexto(String a){
                if (a.equalsIgnoreCase("PRESENTE")) {
                        return true;
                } else {
                        return false;
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
