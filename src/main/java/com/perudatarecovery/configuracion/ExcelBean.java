package com.perudatarecovery.configuracion;

import com.perudatarecovery.configuracion.Conexion;
import com.perudatarecovery.modelo.CargaM;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.file.UploadedFile;

@ManagedBean
@ViewScoped
public class ExcelBean {

    private List<CargaM> registros;
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<CargaM> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CargaM> registros) {
        this.registros = registros;
    }

    @PostConstruct
    public void init() {
        obtenerRegistrosCargaM();
    }

    public void obtenerRegistrosCargaM() {
        registros = new ArrayList<>();

        try {
            Connection con = Conexion.obtenerConexion();

            // Preparar y ejecutar la consulta
            String sql = "EXEC ObtenerRegistrosCargaM";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Recorrer el resultado y crear objetos RegistroCargaM
            while (rs.next()) {
                CargaM registro = new CargaM();
                registro.setId_cargam(rs.getInt("id_cargam"));
                registro.setNombre(rs.getString("nombre"));
                registro.setAp_m(rs.getString("ap_m"));
                registro.setAp_p(rs.getString("ap_p"));
                registro.setTipoDoc(rs.getString("tipoDoc"));
                registro.setFechaN(rs.getDate("fechaN"));
                registro.setEstadoC(rs.getString("estadoC"));
                registro.setGenero(rs.getString("genero"));
                registro.setDepartamento(rs.getString("departamento"));
                registro.setProvincia(rs.getString("provincia"));
                registro.setDistrito(rs.getString("distrito"));
                registro.setDireccion(rs.getString("direccion"));
                registro.setTipoC(rs.getString("tipoC"));
                registro.setFechaI(rs.getDate("fechaI"));
                registro.setTurno(rs.getString("turno"));
                registro.setArea(rs.getString("area"));
                registro.setPuesto(rs.getString("puesto"));
                registro.setNivelE(rs.getString("nivelE"));
                registro.setTiempoE(rs.getString("tiempoE"));
                registro.setSalario(rs.getString("salario"));
                registro.setAseguradora(rs.getString("aseguradora"));
                registro.setSucursales(rs.getString("sucursales"));
                registro.setTipoS(rs.getString("tipoS"));

                registros.add(registro);
            }
            // Cerrar la conexión y liberar recursos
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            // Manejar cualquier error de la base de datos
            e.printStackTrace();
        }
    }

    public void importarDatos() {
        if (file != null) {
            try {
                // Guardar el archivo temporalmente en el servidor
                String rutaArchivoTemporal = guardarArchivoTemporal(file);

                // Ejecutar el procedimiento almacenado para importar datos desde el archivo
                Connection con = Conexion.obtenerConexion();
                String sql = "EXEC ImportarRegistrosDesdeExcel @rutaArchivo = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, rutaArchivoTemporal);
                stmt.executeUpdate();
                stmt.close();
                con.close();

                // Actualizar la lista de registros después de la importación
                obtenerRegistrosCargaM();

                // Mostrar mensaje de éxito
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Importación completada", null));
            } catch (Exception e) {
                // Manejar cualquier error durante la importación
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al importar los datos", null));
                e.printStackTrace();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione un archivo Excel", null));
        }
    }

    private String guardarArchivoTemporal(UploadedFile file) {
        String rutaArchivoTemporal = null;
        try {
            // Obtener la ruta temporal para guardar el archivo
            String rutaTemporal = System.getProperty("java.io.tmpdir");
            String nombreArchivoTemporal = "temp_" + System.currentTimeMillis() + ".xlsx";
            rutaArchivoTemporal = rutaTemporal + nombreArchivoTemporal;

            // Guardar el archivo en la ruta temporal
            try (InputStream inputStream = file.getInputStream(); FileOutputStream outputStream = new FileOutputStream(rutaArchivoTemporal)) {
                int bytesRead;
                byte[] buffer = new byte[8192];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            // Manejar cualquier error al guardar el archivo temporal
            e.printStackTrace();
        }
        return rutaArchivoTemporal;
    }

}
