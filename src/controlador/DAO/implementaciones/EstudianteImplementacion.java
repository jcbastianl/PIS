/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.DAO.implementaciones;

import controlador.DAO.Conexion;
import controlador.DAO.manejoArchivos.DAOEstudiante;
import controlador.TDA.listas.DynamicList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Estudiante;


/**
 *
 * @author Usuario iTC
 */
public class EstudianteImplementacion implements DAOEstudiante{
        private Conexion instanciaMysql = Conexion.getInstance();

        @Override
        public Boolean persist(Estudiante t) {
                PreparedStatement consulta = null;
                Connection conexion = null;
                try {
                        conexion = getInstanciaMysql().conectar();
                        consulta = conexion.prepareStatement("insert into estudiante(colegioprocedencia, provinciaorigen, nombre, apellido, dni, telefono, rol, cuenta_id)values('" + t.getColegioProcedencia()+ "','" + t.getProvinciaOrigen()+ "','" + t.getNombre()+ "','" + t.getApellido() + "','" + t.getDni() + "','" + t.getTelefono() + "','" + t.getRol()+ "','" + t.getCuenta()+ "')");
                        consulta.executeUpdate();
                        return true;
                } catch (Exception e) {
                        System.out.println("No se guardo el estudiante");
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
                        consulta = conexion.prepareStatement("DELETE FROM estudiante WHERE id = '" + id + "';");
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
        public Boolean merge(Estudiante t, Integer i) {
                Connection conexion = null;
                PreparedStatement consulta = null;
                try {
                        conexion = getInstanciaMysql().conectar();
                        consulta = conexion.prepareCall("UPDATE estudiante SET colegioprocedencia = '" + t.getColegioProcedencia() + "', provinciaorigen = '" + t.getProvinciaOrigen() + "', nombre = '" + t.getNombre() + "', apellido = '" + t.getApellido() + "', dni = '" + t.getDni() + "', telefono = '" + t.getProvinciaOrigen() + "', rol = '" + t.getRol() + ", cuenta_id = " + t.getCuenta() +"' where id = '" + i + "';");
                        consulta.executeUpdate();
                        return true;
                 
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Error al actualizar el estudiante");
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
        public Estudiante get(Integer id) {
                try {
                        return all().getInfo(id);
                } catch (Exception e) {
                        return null;
                }
        }

        @Override
        public DynamicList<Estudiante> all() {
                DynamicList<Estudiante> lista = new DynamicList<>();
                PreparedStatement consulta = null;
                Connection conexion = null;
                try {
                        conexion = getInstanciaMysql().conectar();
                        consulta = conexion.prepareStatement("select * from estudiante");
                        ResultSet rs = consulta.executeQuery();
                        while (rs.next()) {
                                Estudiante a = new Estudiante();
                                a.setColegioProcedencia(rs.getString(2));
                                a.setProvinciaOrigen(rs.getString(3));
                                a.setNombre(rs.getString(4));
                                a.setApellido(rs.getString(5));
                                a.setDni(rs.getString(6));
                                a.setTelefono(rs.getString(7));
                                a.setRol(rs.getInt(8));
                                a.setCuenta(rs.getInt(9));
                                a.setId(lista.getLenght() + 1);
                                lista.add(a);
                        }
                        return lista;
                } catch (Exception e) {
                        System.out.println("No se pudo listar a todos los estudiantes");
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
