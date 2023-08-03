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
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

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

    public boolean hasSelectedOcurrencias() {
        return this.listaOcurrencia != null && !this.listaOcurrencia.isEmpty();
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedOcurrencias()) {
            int size = this.listaOcurrencia.size();
            return size > 1 ? size + " ocurrencias seleccionadas" : "1 ocurrencia seleccionada";
        }

        return "Borrar";
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

    public void eliminarRegistro(int codigo) {
        try (
                 Connection conn = Conexion.obtenerConexion();  Statement sql = conn.createStatement()) {

            String query = "DELETE FROM ocurrencia WHERE idOcurrencia = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, codigo);
            pst.executeUpdate();

            // Actualizar la lista de registros después de eliminar uno
            obtenerRegistroOcurrencia();

        } catch (Exception e) {
            e.printStackTrace();
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
