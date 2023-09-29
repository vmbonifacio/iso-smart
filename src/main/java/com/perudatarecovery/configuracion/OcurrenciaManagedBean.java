package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.Ocurrencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;

@Named(value = "ocurrenciaManagedBean")
@RequestScoped
public class OcurrenciaManagedBean {

    private Ocurrencia selectedOcurrencia;
    private List<Ocurrencia> listaOcurrencia;
    private String tipoOcurrenciaSeleccionada;
    private String accidenteNombre; // New attribute to store the name for the accident

    public OcurrenciaManagedBean() {
        selectedOcurrencia = new Ocurrencia();
        listaOcurrencia = new ArrayList();
    }

    @PostConstruct
    public void init() {
        listaOcurrencia = obtenerRegistroOcurrencia();
    }

    public String getTipoOcurrenciaSeleccionada() {
        return tipoOcurrenciaSeleccionada;
    }

    public void setTipoOcurrenciaSeleccionada(String tipoOcurrenciaSeleccionada) {
        this.tipoOcurrenciaSeleccionada = tipoOcurrenciaSeleccionada;
    }

    public Ocurrencia getSelectedOcurrencia() {
        return selectedOcurrencia;
    }

    public void setSelectedOcurrencia(Ocurrencia selectedOcurrencia) {
        this.selectedOcurrencia = selectedOcurrencia;
    }

    public List<Ocurrencia> getListaOcurrencia() {
        return listaOcurrencia;
    }

    public void setListaOcurrencia(List<Ocurrencia> listaOcurrencia) {
        this.listaOcurrencia = listaOcurrencia;
    }

    public void onCellEdit(CellEditEvent event) {
        DataTable dataTable = (DataTable) event.getSource();
        Ocurrencia ocurrencia = (Ocurrencia) dataTable.getRowData();

        if (ocurrencia != null) {
            try {
                OcurrenciaManagedBean.actualizarInformacion(ocurrencia);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Cell updated successfully");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred while updating cell");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                e.printStackTrace();
            }
        }
    }

    public static void actualizarInformacion(Ocurrencia ocurrencia) {
        try (
                 Connection con = Conexion.obtenerConexion();  PreparedStatement pstmt = con.prepareStatement("UPDATE ocurrencia SET nombre = ?, tipoOcurrencia = ?, fechaOcurrencia = ?, numPersonas = ? WHERE idOcurrencia = ?")) {

            pstmt.setString(1, ocurrencia.getNombre());
            pstmt.setString(2, ocurrencia.getTipoOcurrencia());
            pstmt.setDate(3, new java.sql.Date(ocurrencia.getFechaOcurrencia().getTime()));
            pstmt.setInt(4, ocurrencia.getNumPersonasAfectadas());
            pstmt.setInt(5, ocurrencia.getCodigo());

            pstmt.executeUpdate();

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización exitosa", "La información se ha actualizado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (SQLException e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un error al actualizar la información: " + e.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<Ocurrencia> obtenerRegistroOcurrencia() {
        listaOcurrencia.clear();

        try (
                 Connection con = Conexion.obtenerConexion();  Statement stmt = con.createStatement();  ResultSet rs = stmt.executeQuery("SELECT * FROM ocurrencia")) {

            while (rs.next()) {
                int codigo = rs.getInt("idOcurrencia");
                String nombreOcurrencia = rs.getString("nombre");
                String tipoOcurrencia = rs.getString("tipoOcurrencia");
                Date fechaOcurrencia = rs.getDate("fechaOcurrencia");
                int numPersonas = rs.getInt("numPersonas");

                Ocurrencia ocurrencia = new Ocurrencia(codigo, nombreOcurrencia, tipoOcurrencia, fechaOcurrencia, numPersonas);
                listaOcurrencia.add(ocurrencia);
            }

            Collections.sort(listaOcurrencia, Comparator.comparingInt(Ocurrencia::getCodigo));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener los registros: " + e.toString());
        }

        return listaOcurrencia;
    }

    public void registrarTipoOcurrenciaSeleccionada() {
        if (selectedOcurrencia != null) {
            selectedOcurrencia.setTipoOcurrencia(tipoOcurrenciaSeleccionada);
        }
    }

    public void agregarRegistroTipoOcurrencia() {
        try ( Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("INSERT INTO ocurrencia (nombre, tipoOcurrencia) VALUES (?,?)")) {

            if (selectedOcurrencia.getTipoOcurrencia().equals("Accidente")) {
                pst.setString(1, accidenteNombre); // Use the accidenteNombre attribute
            } else {
                pst.setString(1, selectedOcurrencia.getNombre()); // Use the selectedOcurrencia.nombre for incidents
            }

            pst.setString(2, selectedOcurrencia.getTipoOcurrencia());

            pst.executeUpdate();

            listaOcurrencia = obtenerRegistroOcurrencia();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al agregar el registro: " + e.toString());
        }
    }

    public void guardarInformacionAdicional() {
        try (
                 Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("UPDATE ocurrencia SET numPersonas = ? WHERE idOcurrencia = ?")) {

            pst.setInt(1, ocurrencia.getNumPersonasAfectadas());
            pst.setInt(2, ocurrencia.getCodigo());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al guardar información adicional: " + e.toString());
        }
    }
    

    public void eliminarRegistro() {
        if (selectedOcurrencia != null) {
            try ( Connection conn = Conexion.obtenerConexion();  Statement sql = conn.createStatement()) {
                String query = "DELETE FROM ocurrencia WHERE idOcurrencia = ?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, selectedOcurrencia.getCodigo());
                pst.executeUpdate();

                // Actualizar la lista de registros después de eliminar uno
                listaOcurrencia = obtenerRegistroOcurrencia();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void prepararEdicion(Ocurrencia ocurrencia) {
        selectedOcurrencia = ocurrencia;
    }

    private Ocurrencia ocurrencia = new Ocurrencia();

    public Ocurrencia getOcurrencia() {
        return ocurrencia;
    }

    public void setOcurrencia(Ocurrencia ocurrencia) {
        this.ocurrencia = ocurrencia;
    }

    public String getAccidenteNombre() {
        return accidenteNombre;
    }

    public void setAccidenteNombre(String accidenteNombre) {
        this.accidenteNombre = accidenteNombre;
    }
}
