package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.EmpresaP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "EmpresaPManagedBean")
@RequestScoped
public class EmpresaPManagedBean {

    private EmpresaP selectedEmpresaP;
    private List<EmpresaP> listaEmpresaP;

    public EmpresaPManagedBean() {
        selectedEmpresaP = new EmpresaP();
    }

    public void inicializar() {
        listaEmpresaP = obtenerRegistrosEmpresaP();
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

    public List<EmpresaP> obtenerRegistrosEmpresaP() {
        List<EmpresaP> data = new ArrayList<>();

        try (Connection con = Conexion.obtenerConexion();
                Statement sql = con.createStatement();
                ResultSet rs = sql.executeQuery("SELECT * FROM empresa_principal")) {

            while (rs.next()) {
                EmpresaP empresap = new EmpresaP(
                        rs.getInt("id_empresa_p"),
                        rs.getInt("ruc"),
                        rs.getString("razon_s"),
                        rs.getString("nombre_c"),
                        rs.getString("actividad"),
                        rs.getString("departamento"),
                        rs.getString("provincia"),
                        rs.getString("distrito"),
                        rs.getString("direccion"),
                        rs.getInt("telefono"),
                        rs.getString("correo"),
                        rs.getInt("num_licencia"),
                        rs.getDate("fecha_fundacion"),
                        rs.getDate("fecha_operacion"),
                        rs.getInt("numero_s")
                );

                data.add(empresap);
            }

        Collections.sort(data, Comparator.comparingInt(EmpresaP::getId_empresa_p));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void agregarRegistroEmpresaP() {
        try (Connection con = Conexion.obtenerConexion();
                PreparedStatement pst = con.prepareStatement("INSERT INTO empresa_principal (ruc, razon_s, nombre_c, actividad, departamento, provincia, distrito, direccion, telefono, correo, num_licencia, fecha_fundacion, fecha_operacion, numero_s) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            pst.setInt(1, selectedEmpresaP.getRuc());
            pst.setString(2, selectedEmpresaP.getRazon_s());
            pst.setString(3, selectedEmpresaP.getNombre_c());
            pst.setString(4, selectedEmpresaP.getActividad());
            pst.setString(5, selectedEmpresaP.getDepartamento());
            pst.setString(6, selectedEmpresaP.getProvincia());
            pst.setString(7, selectedEmpresaP.getDistrito());
            pst.setString(8, selectedEmpresaP.getDireccion());
            pst.setInt(9, selectedEmpresaP.getTelefono());
            pst.setString(10, selectedEmpresaP.getCorreo());
            pst.setInt(11, selectedEmpresaP.getNum_licencia());
            pst.setDate(12, new java.sql.Date(selectedEmpresaP.getFecha_fundacion().getTime()));
            pst.setDate(13, new java.sql.Date(selectedEmpresaP.getFecha_operacion().getTime()));
            pst.setInt(14, selectedEmpresaP.getNumero_s());
            
            System.out.println("Fecha Fundación: " + selectedEmpresaP.getFecha_fundacion());
        System.out.println("Fecha Operación: " + selectedEmpresaP.getFecha_operacion());


            pst.executeUpdate();

            listaEmpresaP = obtenerRegistrosEmpresaP();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al agregar el registro: " + e.toString());
        }
    }
   
    private EmpresaP empresap = new EmpresaP();

    public EmpresaP getEmpresaP() {
        return empresap;
    }  
    
     public void setEmpresaP(EmpresaP empresap) {
        this.empresap = empresap;
    }
    
 private String departamentoSeleccionado;
    private String provinciaSeleccionada;
    private String distritoSeleccionado;

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
    

