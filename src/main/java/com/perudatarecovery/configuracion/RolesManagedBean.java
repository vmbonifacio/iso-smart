package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.Roles;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "rolesManagedBean")
@RequestScoped
public class RolesManagedBean implements Serializable {

    private Roles selectedRoles;
    private List<Roles> listaPermisos;
    private List<String> listaRoles;
    private List<String> listaUsuarios;
    private boolean mostrarAdminDialog = false;
  
    @PostConstruct
    public void init() {
        listaRoles = obtenerRolesDesdeBD();
        listaUsuarios = obtenerUsuariosDesdeBD();
    }

    public boolean isMostrarAdminDialog() {
        return mostrarAdminDialog;
    }

    public void setMostrarAdminDialog(boolean mostrarAdminDialog) {
        this.mostrarAdminDialog = mostrarAdminDialog;
    }

//  ACTIVACION DE CHECKBOXS PARA CONFIGURAR PERMISOS
    public RolesManagedBean() {
        selectedRoles = new Roles(); // Inicializa selectedRoles
    }

    public Roles getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(Roles selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    public List<Roles> getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(List<Roles> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public List<String> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<String> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List<String> getListaUsuarios() {
        return listaUsuarios;
    }


    public List<Roles> obtenerRegistroRoles() {
        List<Roles> data = new ArrayList<>();

        try (
                 Connection con = Conexion.obtenerConexion();  Statement sql = con.createStatement();  ResultSet rs = sql.executeQuery("SELECT * FROM asig_permisos")) {

            while (rs.next()) {
                Roles roles = new Roles(
                        rs.getInt("id_asig"),
                        rs.getString("usuario"),
                        rs.getString("rol")
                );

                data.add(roles);
            }

            Collections.sort(data, Comparator.comparingInt(Roles::getId_asig));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void agregarRegistroRoles() {
        try (
                 Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("INSERT INTO asig_permisos (usuario, rol) VALUES (?, ?)")) {

            pst.setString(1, selectedRoles.getUsuario());
            pst.setString(2, selectedRoles.getRol());

            pst.executeUpdate();

            listaPermisos = obtenerRegistroRoles();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al agregar el registro: " + e.toString());
        }
    }

    private List<String> obtenerRolesDesdeBD() {
        List<String> roles = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.obtenerConexion();
            String sql = "EXEC SP_ObtenerListaRoles";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String rol = rs.getString("roles");
                roles.add(rol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return roles;
    }

    public List<String> obtenerUsuariosDesdeBD() {
        listaUsuarios = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.obtenerConexion();
            String sql = "EXEC SP_ObtenerListaUsuarios";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");

                // Concatenar nombre y apellidos
                String nombreCompleto = nombre + " " + apellidos;

                listaUsuarios.add(nombreCompleto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    //METODO PARA BOTON ELIMINAR
    public void eliminarRegistroRoles(int id_asig) {
        try (
                 Connection conn = Conexion.obtenerConexion();  Statement sql = conn.createStatement()) {

            String query = "DELETE FROM asig_permisos WHERE id_asig= ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id_asig);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}

