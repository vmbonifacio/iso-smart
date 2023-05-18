package modeloDAO;
        
import com.perudatarecovery.configuracion.conexiones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Persona;

public class PersonaDAO {
    
        public static void guardarPersona(Persona persona) {
            Connection conexion = null;
            PreparedStatement pstmt = null;

            /*try {
                // Obtén la conexión a la base de datos
                conexion = conexiones.conectar();

                // Define la consulta SQL para la inserción de datos
                String sql = "INSERT INTO Inputs (Nombre, Apellido, Edad, Correo) VALUES (?, ?, ?, ?)";

                // Prepara la sentencia SQL con los valores de los atributos de la persona
                pstmt = conexion.prepareStatement(sql);
                pstmt.setString(1, persona.getNombre());
                pstmt.setString(2, persona.getApellido());
                pstmt.setInt(3, persona.getEdad());
                pstmt.setString(4, persona.getCorreo());

                // Ejecuta la consulta SQL
                pstmt.executeUpdate();

                // Cierra los recursos
                pstmt.close();
                conexion.close();

            } catch (SQLException ex) {
                System.out.println("Error al guardar la persona en la base de datos: " + ex.getMessage());
            }
        }

        public ArrayList<Persona> listarPersonas() {
            ArrayList<Persona> listaPersonas = new ArrayList<>();
            Connection conexion = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                // Obtén la conexión a la base de datos
                conexion = conexiones.conectar();

                // Define la consulta SQL para la obtención de datos
                String sql = "SELECT * FROM Inputs";

                // Prepara la sentencia SQL
                pstmt = conexion.prepareStatement(sql);

                // Ejecuta la consulta SQL y obtiene los resultados
                rs = pstmt.executeQuery();

                // Recorre los resultados y los agrega a la lista de personas
                while (rs.next()) {
                    Persona persona = new Persona(
                            rs.getInt("ID"),
                            rs.getString("Nombre"),
                            rs.getString("Apellido"),
                            rs.getInt("Edad"),
                            rs.getString("Correo")
                    );
                    listaPersonas.add(persona);
                }

                // Cierra los recursos
                rs.close();
                pstmt.close();
                conexion.close();

            } catch (SQLException ex) {
                System.out.println("Error al obtener las personas de la base de datos: " + ex.getMessage());
            }

            return listaPersonas;        }*/
        }
}
