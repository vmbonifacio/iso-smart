package com.perudatarecovery.configuracion;

import java.sql.*;

public class PruebaConexion {

//    MÉTODO MAIN PROBAR CONECTIVIDAD
//    public static void main(String[] args) {
//        try {
//             Obtener la conexión a SQL Server
//            Connection connection = Conexion.obtenerConexion();
//            if (connection != null) {
//                System.out.println("Conexión exitosa a SQL Server");
//
//                 Realizar consulta de conectividad
//                Statement statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery("SELECT 1");
//
//                 Verificar resultado de la consulta
//                if (resultSet.next()) {
//                    int resultado = resultSet.getInt(1);
//                    System.out.println("Consulta exitosa. Resultado: " + resultado);
//                }
//
//                 Cerrar recursos
//                resultSet.close();
//                statement.close();
//                connection.close();
//                System.out.println("Conexión cerrada exitosamente");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error al conectar a SQL Server: " + e.getMessage());
//        }
//    }
//     MÉTODO MAIN CONTAR REGISTROS DE LA BASE DE DATOS
//     public static void main(String[] args) {
//           TercerosManagedBean managedBean = new TercerosManagedBean();
//           List<TercerosManagedBean> registros = managedBean.obtenerRegistrosTerceros();
//            System.out.println("Tamaño de la lista de registros: " + registros.size());
//     }
    public static void main(String[] args) {
        try {
            // Obtener la conexión
            Connection conn = Conexion.obtenerConexion();

            if (conn != null) {
                System.out.println("Conexión exitosa");

                // Crear la consulta preparada
                String query = "UPDATE empresa_tp SET razon_social = ?, ruc = ?, departamento = ?, provincia = ?, distrito = ?, direccion = ?, a_economica = ?, n_trabajadores = ?, n_trabajadores_sctr = ?, n_trabajadores_nosctr = ?, n_aseguradora = ? WHERE id_empresa_tp = ?";
                PreparedStatement pst = conn.prepareStatement(query);

                // Establecer los parámetros de la consulta
                pst.setString(1, "Nueva Razon Social");
                pst.setInt(2, 5555);
                pst.setString(3, "Nuevo Departamento");
                pst.setString(4, "Nueva Provincia");
                pst.setString(5, "Nuevo Distrito");
                pst.setString(6, "Nueva Dirección");
                pst.setString(7, "Nueva Actividad Económica");
                pst.setInt(8, 100);
                pst.setInt(9, 50);
                pst.setInt(10, 50);
                pst.setString(11, "Nueva Aseguradora");
                pst.setInt(12, 5); // ID del tercero a actualizar

                // Ejecutar la consulta
                int filasActualizadas = pst.executeUpdate();
                System.out.println("Filas actualizadas: " + filasActualizadas);

                // Cerrar la consulta preparada
                pst.close();

                // Cerrar la conexión
                conn.close();
            } else {
                System.out.println("Error al establecer la conexión");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
