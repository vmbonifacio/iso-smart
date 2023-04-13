package configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class conexiones {
    
    public static Connection conectar() throws SQLException{   
        String url = "jdbc:sqlserver://192.168.1.77:1433;databaseName=SEKUR_IPERC";
        String usuario = "sa";
        String contraseña = "sa123";
        Connection conexion = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión exitosa a SQL Server");
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargar la clase del controlador: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al conectar a SQL Server: " + e.getMessage());
        }

        return conexion;    
    }
    
    public static void main(String[] args) throws SQLException {
        Connection conexion = conectar();
        
        if (conexion != null) {
            try {
                String consulta = "SELECT * FROM Tab_Severidad";
                PreparedStatement ps = conexion.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("ID_SEVERIDAD");
                    String consecuencia = rs.getString("CONSECUENCIA");
                    int indice = rs.getInt("INDICE_SEVERIDAD");
                    System.out.println("ID_SEVERIDAD: " + id + ", CONSECUENCIA: " + consecuencia + ", INDICE_SEVERIDAD: " + indice);
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
}


