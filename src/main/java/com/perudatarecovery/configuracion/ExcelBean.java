package com.perudatarecovery.configuracion;

import com.perudatarecovery.configuracion.Conexion;
import com.perudatarecovery.modelo.CargaM;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FilesUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.primefaces.util.EscapeUtils;

@ManagedBean
@ViewScoped
public class ExcelBean {

    private List<CargaM> registros;

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
                registro.setAeguradora(rs.getString("aeguradora"));
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

    public List<CargaM> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CargaM> registros) {
        this.registros = registros;
    }

//    PARA LA IMPORTACIÓN
    public static void importDataFromExcel(String filePath) {
        try {
            // Establecer la conexión con la base de datos
            Connection conn = Conexion.obtenerConexion();

            // Llamar al procedimiento almacenado
            String sql = "EXEC ImportarRegistrosDesdeExcel ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, filePath);
            stmt.execute();

            // Cerrar la conexión y liberar recursos
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            // Manejar cualquier error de la base de datos
            e.printStackTrace();
        }
    }

}
