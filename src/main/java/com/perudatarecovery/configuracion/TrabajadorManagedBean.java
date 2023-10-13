package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.Trabajador;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;

@Named(value = "trabajadorManagedBean")
@RequestScoped
public class TrabajadorManagedBean {

    private Trabajador selectedTrabajador;
    private List<Trabajador> listaTrabajador;
    private List<String> listaTipoContrato;
    private List<String> listaEstadoCivil;
    private List<String> listaPuestos;

    @PostConstruct
    public void init() {
        MostrarTipoContrato();
        MostrarEstadoCivil();
    }

    public TrabajadorManagedBean() {
        selectedTrabajador = new Trabajador();
    }

    public void inicializar() {
        listaTrabajador = obtenerRegistroTrabajador();
    }

    public Trabajador getSelectedTrabajador() {
        return selectedTrabajador;
    }

    public void setSelectedTrabajador(Trabajador selectedTrabajador) {
        this.selectedTrabajador = selectedTrabajador;
    }

    public List<Trabajador> getListaTrabajador() {
        return listaTrabajador;
    }

    public void setListaTrabajador(List<Trabajador> listaTrabajador) {
        this.listaTrabajador = listaTrabajador;
    }

    public List<String> getListaTipoContrato() {
        return listaTipoContrato;
    }

    public void setListaTipoContrato(List<String> listaTipoContrato) {
        this.listaTipoContrato = listaTipoContrato;
    }

    public List<String> getListaEstadoCivil() {
        return listaEstadoCivil;
    }

    public void setListaEstadoCivil(List<String> listaEstadoCivil) {
        this.listaEstadoCivil = listaEstadoCivil;
    }

    public List<String> getListaPuestos() {
        return listaPuestos;
    }

    public void setListaPuestos(List<String> listaPuestos) {
        this.listaPuestos = listaPuestos;
    }

    public List<Trabajador> obtenerRegistroTrabajador() {
        List<Trabajador> data = new ArrayList<>();

        try ( 
            Connection con = Conexion.obtenerConexion();  
            Statement sql = con.createStatement();  
            ResultSet rs = sql.executeQuery("SELECT * FROM tab_trabajador")) {

            while (rs.next()) {
                Trabajador trabajador = new Trabajador(
                        rs.getInt("id_trabajador"),
                        rs.getString("nombre"),
                        rs.getString("ap_p"),
                        rs.getString("ap_m"),
                        rs.getInt("dni_ce"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("genero"),
                        rs.getString("estado_civil"),
                        rs.getString("departamento"),
                        rs.getString("provincia"),
                        rs.getString("distrito"),
                        rs.getString("direccion"),
                        rs.getString("tipo_contrato"),
                        rs.getDate("fecha_ingreso"),
                        rs.getString("turno"),
                        rs.getString("area"),
                        rs.getString("puesto")
                );

                data.add(trabajador);
            }
            Collections.sort(data, Comparator.comparingInt(Trabajador::getId_trabajador));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public void agregarRegistroTrabajador() {
        try (
            Connection con = Conexion.obtenerConexion();  
            PreparedStatement pst = con.prepareStatement("INSERT INTO tab_trabajador (nombre, ap_p, ap_m, dni_ce, fecha_nacimiento, genero, estado_civil, fecha_ingreso, tipo_contrato, turno, area, puesto, distrito, provincia, departamento, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            pst.setString(1, selectedTrabajador.getNombre());
            pst.setString(2, selectedTrabajador.getAp_p());
            pst.setString(3, selectedTrabajador.getAp_m());
            pst.setInt(4, selectedTrabajador.getDni_ce());
            pst.setDate(5, new java.sql.Date(selectedTrabajador.getFecha_nacimiento().getTime()));
            pst.setString(6, selectedTrabajador.getGenero());
            pst.setString(7, selectedTrabajador.getEstado_civil());
            pst.setDate(8, new java.sql.Date(selectedTrabajador.getFecha_ingreso().getTime()));
            pst.setString(9, selectedTrabajador.getTipo_contrato());
            pst.setString(10, selectedTrabajador.getTurno());
            pst.setString(11, selectedTrabajador.getArea());
            pst.setString(12, selectedTrabajador.getPuesto());
            pst.setString(13, trabajador.getDistrito());
            pst.setString(14, trabajador.getProvincia());
            pst.setString(15, trabajador.getDepartamento());
            pst.setString(16, selectedTrabajador.getDireccion());

            pst.executeUpdate();

            listaTrabajador = obtenerRegistroTrabajador();

        } catch (Exception e) {
            System.out.println("Error al agregar el registro: " + e.toString());
        }
    }

    // MÉTODO PARA BOTON ELIMINAR
    public void eliminarRegistroTrabajador(int id_Trabajador) {
        try (
                 Connection conn = Conexion.obtenerConexion();  Statement sql = conn.createStatement()) {

            String query = "DELETE FROM tab_trabajador WHERE id_trabajador = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id_Trabajador);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void MostrarTipoContrato() {
        listaTipoContrato = new ArrayList<>();

        try ( Connection conn = Conexion.obtenerConexion();  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery("EXEC SP_MostrarTipoContrato")) {

            while (rs.next()) {
                String tipoContrato = rs.getString("tipo_contrato");
                listaTipoContrato.add(tipoContrato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void MostrarEstadoCivil() {
        listaEstadoCivil = new ArrayList<>();

        try ( Connection conn = Conexion.obtenerConexion();  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery("EXEC SP_MostrarEstadoCivil")) {

            while (rs.next()) {
                String estadoCivil = rs.getString("descripcion");
                listaEstadoCivil.add(estadoCivil);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarPuestos() {
        System.out.println("Actualizando lista de puestos...");
        if (selectedTrabajador != null && selectedTrabajador.getArea() != null) {
            listaPuestos = obtenerListaPuestosPorArea(selectedTrabajador.getArea());
        } else {
            listaPuestos = new ArrayList<>();
        }
    }

    public List<String> obtenerListaAreas() {
        List<String> listaAreas = new ArrayList<>();

        try ( Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("EXEC SP_ObtenerListaAreas");  ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                listaAreas.add(rs.getString("descripcion_area"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaAreas;
    }

    public List<String> obtenerListaPuestosPorArea(String nombreArea) {
        List<String> puestosPorArea = new ArrayList<>(); // Cambiamos el nombre de la variable local

        try ( Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("EXEC SP_ObtenerListaPuestos ?");) {
            pst.setString(1, nombreArea);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                puestosPorArea.add(rs.getString("descripcion_puesto"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return puestosPorArea;
    }

//    -----------------------------------------------------
    private Trabajador trabajador = new Trabajador();

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
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
