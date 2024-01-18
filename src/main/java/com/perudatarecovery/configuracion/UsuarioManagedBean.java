package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "usuarioManagedBean")
@RequestScoped
public class UsuarioManagedBean {

    private Usuario selectedUsuario;
    private List<Usuario> listaUsuarios;
    private String permiso;
    private Usuario usuarioConPermiso;
    private boolean enlaceDatosGeneralesHabilitado = true;

    @Inject
    private PermisoManagedBean permisoManagedBean;

    public UsuarioManagedBean() {
        selectedUsuario = new Usuario();
    }

    public void inicializar() {
        listaUsuarios = obtenerRegistrosUsuario();
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public Usuario getUsuarioConPermiso() {
        return usuarioConPermiso;
    }

    public void setUsuarioConPermiso(Usuario usuarioConPermiso) {
        this.usuarioConPermiso = usuarioConPermiso;
    }

    public PermisoManagedBean getPermisoManagedBean() {
        return permisoManagedBean;
    }

    public void setPermisoManagedBean(PermisoManagedBean permisoManagedBean) {
        this.permisoManagedBean = permisoManagedBean;
    }

    public boolean isEnlaceDatosGeneralesHabilitado() {
        return enlaceDatosGeneralesHabilitado;
    }

    public void setEnlaceDatosGeneralesHabilitado(boolean enlaceDatosGeneralesHabilitado) {
        this.enlaceDatosGeneralesHabilitado = enlaceDatosGeneralesHabilitado;
    }

    public List<Usuario> obtenerRegistrosUsuario() {
        List<Usuario> data = new ArrayList<>();

        try ( Connection con = Conexion.obtenerConexion();  Statement sql = con.createStatement();  ResultSet rs = sql.executeQuery("select * from registro_usuario")) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("correo"),
                        rs.getString("contraseña")
                );
                data.add(usuario);
            }

            Collections.sort(data, Comparator.comparingInt(Usuario::getId_usuario));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void agregarRegistroUsuario() {
        try ( Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("INSERT INTO registro_usuario(nombre,apellidos,correo,contraseña) VALUES (?, ?, ?, ?)")) {

            pst.setString(1, selectedUsuario.getNombre());
            pst.setString(2, selectedUsuario.getApellidos());
            pst.setString(3, selectedUsuario.getCorreo());
            pst.setString(4, selectedUsuario.getContraseña());

            pst.executeUpdate();

            listaUsuarios = obtenerRegistrosUsuario();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al agregar el registro: " + e.toString());
        }
    }

    public void tienePermiso(String permiso, String usuario) {
        // Verifica si el usuario actual es diferente al usuario seleccionado.
        boolean tienePermiso = !usuario.equals(selectedUsuario.getNombre());

        if (permiso.equals("Datos Generales") && tienePermiso) {
            // Si el permiso es "Datos Generales" y el usuario tiene permiso, habilita el enlace.
            enlaceDatosGeneralesHabilitado = true;
        } else {
            // Para otros permisos o cuando el usuario no tiene permiso, deshabilita el enlace.
            enlaceDatosGeneralesHabilitado = false;
        }
    }

    public boolean isSelected(Usuario usuario) {
        return selectedUsuario != null && selectedUsuario.equals(usuario);
    }

    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
