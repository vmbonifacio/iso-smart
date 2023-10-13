package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.EmpresaP;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "EmpresaPManagedBean")
@RequestScoped
public class EmpresaPManagedBean implements Serializable {

    private EmpresaP selectedEmpresaP;
    private EmpresaP empresaP = new EmpresaP();
    private List<EmpresaP> listaEmpresaP;
    private String departamentoSeleccionado;
    private String provinciaSeleccionada;
    private String distritoSeleccionado;

    public EmpresaPManagedBean() {
        selectedEmpresaP = new EmpresaP();
    }

    public void inicializar() {
        listaEmpresaP = obtenerRegistrosEmpresaP();
    }

    public EmpresaP getEmpresaP() {
        return empresaP;
    }

    public void setEmpresaP(EmpresaP empresaP) {
        this.empresaP = empresaP;
    }

    public EmpresaP getSelectedEmpresaP() {
        return selectedEmpresaP;
    }

    public void setSelectedEmpresaP(EmpresaP selectedEmpresaP) {
        this.selectedEmpresaP = selectedEmpresaP;
    }

    public List<EmpresaP> getListaEmpresaP() {
        return listaEmpresaP;
    }

    public void setListaEmpresaP(List<EmpresaP> listaEmpresaP) {
        this.listaEmpresaP = listaEmpresaP;
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

    public List<String> obtenerCampoEmpresaP(String nombreCampo) {
        List<String> data = new ArrayList<>();

        try (
                 Connection con = Conexion.obtenerConexion(); 
                 PreparedStatement stmt = con.prepareStatement("SELECT " + nombreCampo + " FROM Tab_Empresa_Central"); 
                 ResultSet rs = stmt.executeQuery()) {

         while (rs.next()) {
                  String valorCampo = rs.getString(1);  // Obtener el campo en la posición 1 (primer campo)
                  data.add(valorCampo);
         }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public List<EmpresaP> obtenerRegistrosEmpresaP() {
        List<EmpresaP> data = new ArrayList<>();

        try (
                 Connection con = Conexion.obtenerConexion();  Statement sql = con.createStatement();  ResultSet rs = sql.executeQuery("SELECT * FROM Tab_Empresa_Central")) {

            while (rs.next()) {
                EmpresaP empresap = new EmpresaP(
                        rs.getInt("Id_Empresa"),
                        rs.getInt("Ruc"),
                        rs.getString("Razon_S"),
                        rs.getString("Nombre_Com"),
                        rs.getString("Actividad"),
                        rs.getString("Departamento"),
                        rs.getString("Provincia"),
                        rs.getString("Distrito"),
                        rs.getString("Direccion"),
                        rs.getInt("Telefono"),
                        rs.getString("Correo"),
                        rs.getInt("Num_Licencias"),
                        rs.getDate("Fecha_Fundacion"),
                        rs.getDate("Fecha_Operacion"),
                        rs.getInt("Num_Sucur")
                );

                data.add(empresap);
            }
            Collections.sort(data, Comparator.comparingInt(EmpresaP::getId_empresa_p));
        } catch (Exception e) {
            System.out.println("Error al traer los datos del registro: " + e.toString());
        }
        return data;
    }

    public void agregarRegistroEmpresaP() {
        try (
                 Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("INSERT INTO Tab_Empresa_Central (Ruc, Razon_S, Nombre_Com, Actividad, Departamento, Provincia, Distrito, Direccion, Telefono, Correo, Num_Licencias, Fecha_Fundacion, Fecha_Operacion, Num_Sucur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            pst.setInt(1, selectedEmpresaP.getRuc());
            pst.setString(2, selectedEmpresaP.getRazon_s());
            pst.setString(3, selectedEmpresaP.getNombre_c());
            pst.setString(4, selectedEmpresaP.getActividad());
            pst.setString(5, empresaP.getDepartamento());
            pst.setString(6, empresaP.getProvincia());
            pst.setString(7, empresaP.getDistrito());
            pst.setString(8, selectedEmpresaP.getDireccion());
            pst.setInt(9, selectedEmpresaP.getTelefono());
            pst.setString(10, selectedEmpresaP.getCorreo());
            pst.setInt(11, selectedEmpresaP.getNum_licencia());
            pst.setDate(12, new java.sql.Date(selectedEmpresaP.getFecha_fundacion().getTime()));
            pst.setDate(13, new java.sql.Date(selectedEmpresaP.getFecha_operacion().getTime()));
            pst.setInt(14, selectedEmpresaP.getNumero_s());

            pst.executeUpdate();

            // Limpia los campos después de agregar el registro
            limpiarCampos();

            listaEmpresaP = obtenerRegistrosEmpresaP();

            // Agregar un mensaje de éxito
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro agregado exitosamente", "Registro agregado con éxito");
            FacesContext.getCurrentInstance().addMessage("mensajes", message);

        } catch (Exception e) {
            System.out.println("Error al agregar el registro: " + e.toString());
        }
    }

    // Método para limpiar los campos
    public void limpiarCampos() {
        // Establece los valores de los campos en blanco o en su valor predeterminado
        selectedEmpresaP.setRuc(null);
        selectedEmpresaP.setRazon_s("");
        selectedEmpresaP.setNombre_c("");
        selectedEmpresaP.setActividad("");
        empresaP.setDepartamento("");
        empresaP.setProvincia("");
        empresaP.setDistrito("");
        selectedEmpresaP.setDireccion("");
        selectedEmpresaP.setTelefono(null);
        selectedEmpresaP.setCorreo("");
        selectedEmpresaP.setNum_licencia(null);
        selectedEmpresaP.setFecha_fundacion(null);
        selectedEmpresaP.setFecha_operacion(null);
        selectedEmpresaP.setNumero_s(null);
    }

    public void eliminarRegistroEmp(int id_empresa_p) {
        try (
                 Connection conn = Conexion.obtenerConexion();  PreparedStatement pst = conn.prepareStatement("DELETE FROM Tab_Empresa_Central WHERE Id_Empresa = ?")) {
            pst.setInt(1, id_empresa_p);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al eliminar el registro: " + e.toString());
        }
    }
}
