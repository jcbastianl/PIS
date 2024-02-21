/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.DAO.implementaciones;

import controlador.DAO.Conexion;
import controlador.DAO.manejoArchivos.DAOPeriodoAcademico;
import controlador.TDA.listas.DynamicList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.PeriodoAcademico;

/**
 *
 * @author Usuario iTC
 */
public class PeriodoAcademicoImplementacion implements DAOPeriodoAcademico{
        private Conexion instanciaMysql = Conexion.getInstance();

        @Override
        public Boolean persist(PeriodoAcademico t) {
                PreparedStatement consulta = null;
                Connection conexion = null;
                try {
                        conexion = getInstanciaMysql().conectar();
                        consulta = conexion.prepareStatement("insert into periodoacademico(mesinicio, mesfin, yearinicio, yearfin, modalidad)values('" + t.getMesInicio()+ "','" + t.getMesFin()+ "','" + t.getYearInicio()+ "','" + t.getYearFin()+ "','" + t.getModalidad()+ "')");
                        consulta.executeUpdate();
                        return true;
                } catch (Exception e) {
                        System.out.println("No se guardo el periodo academico");
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
                        consulta = conexion.prepareStatement("DELETE FROM periodoacademico WHERE id = '" + id + "';");
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
        public Boolean merge(PeriodoAcademico t, Integer i) {
                Connection conexion = null;
                PreparedStatement consulta = null;
                try {
                        conexion = getInstanciaMysql().conectar();
                        consulta = conexion.prepareCall("UPDATE periodoacademico SET mesinicio = '" + t.getMesInicio()+ "', mesfin = '" + t.getMesFin()+ "', yearinicio = '" + t.getYearInicio() + "', yearfin = '" + t.getYearFin()+ "', modalidad = '" + t.getModalidad() + "' where id = '" + i + "';");
                        consulta.executeUpdate();
                        return true;
                 
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Error al actualizar el periodo academico");
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
        public PeriodoAcademico get(Integer id) {
                try {
                        return all().getInfo(id);
                } catch (Exception e) {
                        return null;
                }
        }

        @Override
        public DynamicList<PeriodoAcademico> all() {
                DynamicList<PeriodoAcademico> lista = new DynamicList<>();
                PreparedStatement consulta = null;
                Connection conexion = null;
                try {
                        conexion = getInstanciaMysql().conectar();
                        consulta = conexion.prepareStatement("select * from periodoacademico");
                        ResultSet rs = consulta.executeQuery();
                        while (rs.next()) {
                                PeriodoAcademico a = new PeriodoAcademico();
                                a.setMesInicio(rs.getString(2));
                                a.setMesFin(rs.getString(3));
                                a.setYearInicio(rs.getString(4));
                                a.setYearFin(rs.getString(5));
                                a.setModalidad(rs.getString(6));
                                a.setId(lista.getLenght() + 1);
                                lista.add(a);
                        }
                        return lista;
                } catch (Exception e) {
                        System.out.println("No se pudo listar a todos los periodos academicos");
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
