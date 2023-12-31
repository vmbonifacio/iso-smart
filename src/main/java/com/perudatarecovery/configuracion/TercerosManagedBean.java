package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.EmpresaP;
import com.perudatarecovery.modelo.Tercero;
import java.io.Serializable;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

@Named(value = "tercerosManagedBean")
@RequestScoped
public class TercerosManagedBean implements Serializable {

    private Tercero selectedTercero;
    private List<Tercero> listaTerceros;
    private String departamentoSeleccionado;
    private String provinciaSeleccionada;
    private String distritoSeleccionado;
    private EmpresaP selectedEmpresaP;

    @ManagedProperty(value = "#{trabajadorManagedBean}")
    private TrabajadorManagedBean trabajadorManagedBean;

    private boolean mostrarCampos;

    public TercerosManagedBean() {
        selectedTercero = new Tercero();
        listaTerceros = new ArrayList();
        mostrarCampos = false;

        selectedEmpresaP = obtenerRegistroUnicoEmpresaP();

        if (selectedEmpresaP == null) {
            selectedEmpresaP = new EmpresaP();
        }
    }

    public void inicializar() {
        listaTerceros = obtenerRegistrosTerceros();

        selectedEmpresaP = obtenerRegistroUnicoEmpresaP();

        if (selectedEmpresaP == null) {
            selectedEmpresaP = new EmpresaP();
        }

        // Llama al método contarTotalRegistros() del TrabajadorManagedBean para obtener el total de registros.
        trabajadorManagedBean.contarTotalRegistros();
        // Ahora, puedes usar totalRegistrosTrabajadores según tus necesidades.
    }

    public TrabajadorManagedBean getTrabajadorManagedBean() {
        return trabajadorManagedBean;
    }

    public void setTrabajadorManagedBean(TrabajadorManagedBean trabajadorManagedBean) {
        this.trabajadorManagedBean = trabajadorManagedBean;
    }

    public int getTotalRegistros() {
        if (trabajadorManagedBean != null) {
            return trabajadorManagedBean.getTotalRegistros();
        }
        return 0; // O algún valor predeterminado si no se ha inicializado
    }

    public EmpresaP getSelectedEmpresaP() {
        return selectedEmpresaP;
    }

    public void setSelectedEmpresaP(EmpresaP selectedEmpresaP) {
        this.selectedEmpresaP = selectedEmpresaP;
    }

    public List<Tercero> getListaTerceros() {
        return listaTerceros;
    }

    public void setListaTerceros(List<Tercero> listaTerceros) {
        this.listaTerceros = listaTerceros;
    }

    public Tercero getSelectedTercero() {
        return selectedTercero;
    }

    public void setSelectedTercero(Tercero selectedTercero) {
        this.selectedTercero = selectedTercero;
    }

    public boolean isMostrarCampos() {
        return mostrarCampos;
    }

    public void setMostrarCampos(boolean mostrarCampos) {
        this.mostrarCampos = mostrarCampos;
    }

    public void eliminarRegistro(int idEmpresa) {
        try (
                 Connection conn = Conexion.obtenerConexion();  Statement sql = conn.createStatement()) {

            String query = "DELETE FROM empresa_tp WHERE id_empresa_tp = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, idEmpresa);
            pst.executeUpdate();

            // Actualizar la lista de registros después de eliminar uno
            obtenerRegistrosTerceros();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public EmpresaP obtenerRegistroUnicoEmpresaP() {
        EmpresaP empresa = null;

        try ( Connection con = Conexion.obtenerConexion();  Statement sql = con.createStatement();  ResultSet rs = sql.executeQuery("SELECT Razon_S, Ruc, Actividad, Departamento, Provincia, Distrito, Direccion FROM Tab_Empresa_Central WHERE ID_Empresa = 10;")) {

            if (rs.next()) {
                empresa = new EmpresaP();
                empresa.setRazon_s(rs.getString("Razon_S"));
                empresa.setRuc(rs.getInt("Ruc"));
                empresa.setActividad(rs.getString("Actividad"));
                empresa.setDepartamento(rs.getString("Departamento"));
                empresa.setProvincia(rs.getString("Provincia"));
                empresa.setDistrito(rs.getString("Distrito"));
                empresa.setDireccion(rs.getString("Direccion"));
            }
        } catch (Exception e) {
            System.out.println("Error al traer el registro único: " + e.toString());
        }
        return empresa;
    }

    public List<Tercero> obtenerRegistrosTerceros() {
        listaTerceros.clear();

        try (
                 Connection con = Conexion.obtenerConexion();  Statement stmt = con.createStatement();  ResultSet rs = stmt.executeQuery("SELECT * FROM empresa_tp")) {

            while (rs.next()) {
                int id = rs.getInt("id_empresa_tp");
                String razonSocial = rs.getString("razon_social");
                int ruc = rs.getInt("ruc");
                String departamento = rs.getString("departamento");
                String provincia = rs.getString("provincia");
                String distrito = rs.getString("distrito");
                String direccion = rs.getString("direccion");
                String aEconomica = rs.getString("a_economica");
                int nTrabajadores = rs.getInt("n_trabajadores");
                int nTrabajadoresSctr = rs.getInt("n_trabajadores_sctr");
                int nTrabajadoresNoSctr = rs.getInt("n_trabajadores_nosctr");
                String nAseguradora = rs.getString("n_aseguradora");

                Tercero tercero = new Tercero(id, razonSocial, ruc, departamento, provincia, distrito, direccion, aEconomica, nTrabajadores, nTrabajadoresSctr, nTrabajadoresNoSctr, nAseguradora);
                listaTerceros.add(tercero);
            }

            Collections.sort(listaTerceros, Comparator.comparingInt(Tercero::getId_empresa_tp));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener los registros: " + e.toString());
        }

        return listaTerceros;
    }

    public void agregarRegistro() {
        try (
                 Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement(
                "INSERT INTO empresa_tp (razon_social, ruc, departamento,"
                + "provincia, distrito, direccion, a_economica, n_trabajadores,"
                + "n_trabajadores_sctr, n_trabajadores_nosctr, n_aseguradora)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            pst.setString(1, tercero.getRazon_social());
            pst.setInt(2, tercero.getRuc());
            pst.setString(3, tercero.getDepartamento());
            pst.setString(4, tercero.getProvincia());
            pst.setString(5, tercero.getDistrito());
            pst.setString(6, tercero.getDireccion());
            pst.setString(7, tercero.getA_economica());
            pst.setInt(8, tercero.getN_trabajadores());
            pst.setInt(9, tercero.getN_trabajadores_sctr());
            pst.setInt(10, tercero.getN_trabajadores_nosctr());
            pst.setString(11, tercero.getN_aseguradora());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                // Actualizar la lista de registros después de eliminar uno
                listaTerceros = obtenerRegistrosTerceros();
            }

            // Limpia los campos después de agregar el registro
            limpiarCampos();
        } catch (SQLException e) {
            System.out.println("Error al agregar el registro: " + e.toString());
        }
    }

    public void actualizarRegistro() {
        try ( Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement(
                "UPDATE empresa_tp SET razon_social = ?, ruc = ?, departamento = ?, provincia = ?, "
                + "distrito = ?, direccion = ?, a_economica = ?, n_trabajadores = ?, "
                + "n_trabajadores_sctr = ?, n_trabajadores_nosctr = ?, n_aseguradora = ? "
                + "WHERE id_empresa_tp = ?")) {

            pst.setString(1, tercero.getRazon_social());
            pst.setInt(2, tercero.getRuc());
            pst.setString(3, tercero.getDepartamento());
            pst.setString(4, tercero.getProvincia());
            pst.setString(5, tercero.getDistrito());
            pst.setString(6, tercero.getDireccion());
            pst.setString(7, tercero.getA_economica());
            pst.setInt(8, tercero.getN_trabajadores());
            pst.setInt(9, tercero.getN_trabajadores_sctr());
            pst.setInt(10, tercero.getN_trabajadores_nosctr());
            pst.setString(11, tercero.getN_aseguradora());
            pst.setInt(12, tercero.getId_empresa_tp()); // Asegúrate de incluir la clave primaria para identificar el registro a actualizar

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                // Actualizar la lista de registros después de actualizar uno
                listaTerceros = obtenerRegistrosTerceros();
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el registro: " + e.toString());
        }
    }

    // Método para limpiar los campos
    public void limpiarCampos() {
        // Establece los valores de los campos en blanco o en su valor predeterminado
        selectedTercero.setRazon_social("");
        selectedTercero.setRuc(null);
        selectedTercero.setDepartamento("");
        selectedTercero.setProvincia("");
        selectedTercero.setDistrito("");
        selectedTercero.setDireccion("");
        selectedTercero.setA_economica("");
        selectedTercero.setN_trabajadores(null);
        selectedTercero.setN_trabajadores_sctr(null);
        selectedTercero.setN_trabajadores_nosctr(null);
        selectedTercero.setN_aseguradora("");
    }

    public void prepararEdicion(Tercero tercero) {
        selectedTercero = tercero;
    }

    private Tercero tercero = new Tercero();

    public Tercero getTercero() {
        return tercero;
    }

    public void setTercero(Tercero tercero) {
        this.tercero = tercero;
    }

    public String getDepartamentoSeleccionado() {
        return departamentoSeleccionado;
    }

    public void setDepartamentoSeleccionado(String departamentoSeleccionado) {
        this.departamentoSeleccionado = departamentoSeleccionado;
    }

    public String getProvinciaSeleccionada() {
        return provinciaSeleccionada;
    }

    public void setProvinciaSeleccionada(String provinciaSeleccionada) {
        this.provinciaSeleccionada = provinciaSeleccionada;
    }

    public String getDistritoSeleccionado() {
        return distritoSeleccionado;
    }

    public void setDistritoSeleccionado(String distritoSeleccionado) {
        this.distritoSeleccionado = distritoSeleccionado;
    }
}
