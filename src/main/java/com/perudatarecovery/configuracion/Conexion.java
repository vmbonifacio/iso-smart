package com.perudatarecovery.configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:sqlserver://192.168.1.77:1433;databaseName=SEKUR_IPERC";
    private static final String USUARIO = "sa";
    private static final String CONTRASENA = "sa123";

    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargar la clase del controlador: " + e.getMessage());
        }

        return null;
    }
}
