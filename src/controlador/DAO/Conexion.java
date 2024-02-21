/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.DAO;
import java.sql.*;

/**
 *
 * @author mrbingus
 */
public class Conexion {
    public static final String DRIVER ="com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost/pis_tablas";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static Conexion instancia;
    
    public Connection conectar() throws SQLException{
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
    
    public void desconectar(Connection conexion){
        try{
            conexion.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
   
    
    public void cerrarResultado(ResultSet resultado){
        try{
            resultado.close();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void cerrarStatement (PreparedStatement statement){
        try{
            statement.close();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static Conexion getInstance(){
        //se aplica el praton de diseño del single
        if(instancia == null)
            instancia = new Conexion();
        return instancia;
    }
}



