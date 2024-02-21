/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.DAO.implementaciones;

import controlador.DAO.Conexion;
import controlador.DAO.manejoArchivos.DAOMatricula;
import controlador.TDA.listas.DynamicList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Cuenta;
import modelo.Matricula;

/**
 *
 * @author mrbingus
 */
public class MatriculaImplementacion implements DAOMatricula {

    private Conexion instanciaMysql = Conexion.getInstance();

    @Override
    public Boolean persist(Matricula data) {
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareStatement("insert into matricula(codigo, fecha_registro, estadomatricula, estudiante_id_estudiante, peracademic_id_peracademic , cursa_id_cursa)values('" + data.getCodigo() + "','" + data.getFechaRegistro()+ "','" + data.getEstadoMatricula()+ "','" + data.getEstudiante()+ "','" + data.getIdPeriodoAcademico() + "','" + data.getCursa() + "')");
            consulta.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("No se guardo la matricula");
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
    public Boolean merge(Matricula data, Integer index) {
        Connection conexion = null;
        PreparedStatement consulta = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareCall("UPDATE matricula SET codigo = '" + data.getCodigo() + "', fecha_registro = '" + data.getFechaRegistro() + "', estadomatricula = '" + data.getEstadoMatricula() + "', estudiante_id_estudiante = '" + data.getEstudiante() + "', peracademic_id_peracademic = '" + data.getIdPeriodoAcademico() + "', cursa_id_cursa = '" + data.getCursa() + "' where id = '" + index + "';");
            consulta.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error al actualizar la matricula");
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
            consulta = conexion.prepareStatement("DELETE FROM matricula WHERE id = '" + index + "';");
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
    public DynamicList<Matricula> all() {
        DynamicList<Matricula> lista = new DynamicList<>();
        PreparedStatement consulta = null;
        Connection conexion = null;
        try {
            conexion = getInstanciaMysql().conectar();
            consulta = conexion.prepareStatement("select * from matricula");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                Matricula a = new Matricula();
                a.setCodigo(rs.getInt(1));
                a.setFechaRegistro(rs.getDate(2));
                a.setEstadoMatricula(rs.getBoolean(3));
                a.setEstudiante(rs.getInt(4));
                a.setIdPeriodoAcademico(rs.getInt(5));
                a.setCursa(rs.getInt(6));
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
    public Matricula get(Integer id) {
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
