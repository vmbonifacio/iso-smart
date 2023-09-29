package com.perudatarecovery.configuracion;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "validacionManagedBean")
@SessionScoped
public class ValidacionManagedBean implements Serializable {

    private String correo;
    private String contraseña;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String validarInicioSesion() {
        // Implementa la lógica de validación de inicio de sesión aquí.
        // Accede a los valores de correo y contraseña utilizando los getters correspondientes.

        // Ejemplo de validación ficticia
        if (validarUsuario(correo, contraseña)) {
            // Almacena información del usuario en la sesión si es necesario.
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", correo);
            // Redirige al usuario a la página "dashboard.xhtml" después de la validación exitosa.
            return "dashboard.xhtml?faces-redirect=true";
        } else {
            // Autenticación fallida, muestra un mensaje de error
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de inicio de sesión", "Correo o contraseña incorrectos"));
            return null; // Permanece en la misma página de inicio de sesión
        }
    }

    // Método para validar el usuario en tu base de datos
    private boolean validarUsuario(String correo, String contraseña) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Obtiene la conexión a la base de datos desde la clase Conexion.
            connection = Conexion.obtenerConexion();

            // Supongamos que tienes una tabla llamada 'registro_usuario' con columnas 'correo' y 'contraseña'.
            String sql = "SELECT * FROM registro_usuario WHERE correo = ? AND contraseña = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, contraseña);

            resultSet = preparedStatement.executeQuery();

            // Si se encuentra un registro que coincide con el correo y la contraseña proporcionados,
            // significa que la validación es exitosa.
            return resultSet.next();
        } catch (SQLException e) {
            // Maneja cualquier excepción de la base de datos aquí.
            e.printStackTrace();
            return false;
        } finally {
            // Cierra los recursos (ResultSet, PreparedStatement y Connection).
            // Asegúrate de manejar adecuadamente las excepciones aquí.
            cerrarRecursos(resultSet, preparedStatement, connection);
        }
    }

    // Método para cerrar recursos
    private void cerrarRecursos(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // Maneja las excepciones adecuadamente
            e.printStackTrace();
        }
    }
}
