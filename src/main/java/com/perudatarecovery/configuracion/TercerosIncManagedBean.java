package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.TerceroInc;
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

@Named(value = "tercerosIncManagedBean")
@RequestScoped
public class TercerosIncManagedBean {

    // Otros atributos y métodos de la clase
    private TerceroInc selectedTerceroInc;
    private List<TerceroInc> listaTercerosInc;

    public TercerosIncManagedBean() {
        selectedTerceroInc = new TerceroInc();
    }

    public void inicializar() {
        listaTercerosInc = obtenerRegistrosTercerosInc();
    }

    public TerceroInc getSelectedTerceroInc() {
        return selectedTerceroInc;
    }

    public void setSelectedTerceroInc(TerceroInc selectedTerceroInc) {
        this.selectedTerceroInc = selectedTerceroInc;
    }

    public List<TerceroInc> getListaTercerosInc() {
        return listaTercerosInc;
    }

    public void setListaTercerosInc(List<TerceroInc> listaTercerosInc) {
        this.listaTercerosInc = listaTercerosInc;
    }
    
    //    METODO BOTON ELIMINAR
    public void eliminarRegistroTerceroInc(int id_empresa_ti) {
        try (
                 Connection conn = Conexion.obtenerConexion();  PreparedStatement pst = conn.prepareStatement("DELETE FROM empresa_ti WHERE id_empresa_ti = ?")) {
            pst.setInt(1, id_empresa_ti);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TerceroInc> obtenerRegistrosTercerosInc() {
        List<TerceroInc> data = new ArrayList<>();

        try ( Connection con = Conexion.obtenerConexion();  Statement sql = con.createStatement();  ResultSet rs = sql.executeQuery("select * from empresa_ti")) {

            while (rs.next()) {
                TerceroInc terceroinc = new TerceroInc(
                        rs.getInt("id_empresa_ti"),
                        rs.getString("razon_social"),
                        rs.getInt("ruc"),
                        rs.getString("departamento"),
                        rs.getString("provincia"),
                        rs.getString("distrito"),
                        rs.getString("direccion"),
                        rs.getString("a_economica"),
                        rs.getInt("n_trabajadores"),
                        rs.getInt("n_trabajadores_sctr"),
                        rs.getInt("n_trabajadores_nosctr"),
                        rs.getString("n_aseguradora")
                );

                data.add(terceroinc);
            }

            Collections.sort(data, Comparator.comparingInt(TerceroInc::getId_empresa_ti));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    //Boton para registrar 
    public void agregarRegistroTerceroInc() {
        try (
                 Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("INSERT INTO empresa_ti (razon_social, ruc, departamento, provincia, distrito, direccion, a_economica, n_trabajadores, n_trabajadores_sctr, n_trabajadores_nosctr, n_aseguradora) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            pst.setString(1, terceroinc.getRazon_social());
            pst.setInt(2, terceroinc.getRuc());
            pst.setString(3, terceroinc.getDepartamento());
            pst.setString(4, terceroinc.getProvincia());
            pst.setString(5, terceroinc.getDistrito());
            pst.setString(6, terceroinc.getDireccion());
            pst.setString(7, terceroinc.getA_economica());
            pst.setInt(8, terceroinc.getN_trabajadores());
            pst.setInt(9, terceroinc.getN_trabajadores_sctr());
            pst.setInt(10, terceroinc.getN_trabajadores_nosctr());
            pst.setString(11, terceroinc.getN_aseguradora());

            pst.executeUpdate();

            listaTercerosInc = obtenerRegistrosTercerosInc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TerceroInc terceroinc = new TerceroInc();

    public TerceroInc getTerceroInc() {
        return terceroinc;
    }

    public void setTerceroInc(TerceroInc terceroinc) {
        this.terceroinc = terceroinc;
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
