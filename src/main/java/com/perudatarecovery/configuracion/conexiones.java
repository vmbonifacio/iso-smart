package com.perudatarecovery.configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class conexiones {
    
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlserver://192.168.1.77:1433;databaseName=SEKUR_IPERC";
        String usuario = "sa";
        String contraseña = "sa123";
        Connection conexion = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection(url,usuario, contraseña);
            System.out.println("Conexión exitosa a SQL Server");
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargar la clase del controlador: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al conectar a SQL Server: " + e.getMessage());
        }
  
    }
    
    /*public static void main(String[] args) throws SQLException {
        Connection conexion = conectar();
        
        if (conexion != null) {
            try {
                String consulta = "SELECT * FROM Inputs";
                PreparedStatement ps = conexion.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    char Ruc = rs.getChar("Ruc");
                    int Domicilio = rs.getInt("Domicilio");
                    String Direccion = rs.getString("Direccion");
                    int Actividad = rs.getInt("Actividad");
                    int  NumTrabajadores = rs.getInt(" NumTrabajadores");
                    int  NumTrabajadoresSCTR = rs.getInt("NumTrabajadoresSCTR");
                    int  NumTrabajadoresnoSCTR = rs.getInt(" NumTrabajadoresnoSCTR");
                    String Nafiliadora = rs.getString("Nafiliadora");
                    System.out.println("Ruc: " + ruc + ",Domicilio: " + domicilio + ", Direccion: " + direccion +", Actividad: " + actividad + ", NumTrabajadores: " + numTrabajadores +",NumTrabajadoresSCTR" + numTrabajadoresSCTR +",NumTrabajadoresnoSCTR" + numTrabajadoresnoSCTR + ",Nafiliadora" + afiliadora); 
                }
            } catch (SQLException e) {
                System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            } finally {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    private static Connection conectar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
}


