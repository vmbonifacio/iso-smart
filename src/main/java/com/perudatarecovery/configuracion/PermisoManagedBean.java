package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.Permiso;
import com.perudatarecovery.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "permisoManagedBean")
@RequestScoped
public class PermisoManagedBean {

    private Permiso selectedPermiso; // Cambiar a tipo Permiso
    private String[] selectedPermisos;
    private Set<String> usuariosConPermisoDatosGenerales = new HashSet<>();

    @Inject
    private UsuarioManagedBean usuarioManagedBean;

    @PostConstruct
    public void init() {
        selectedPermiso = new Permiso();
    }

    public String[] getSelectedPermisos() {
        return selectedPermisos;
    }

    public void setSelectedPermisos(String[] selectedPermisos) {
        this.selectedPermisos = selectedPermisos;
    }

    public Permiso getSelectedPermiso() {
        return selectedPermiso;
    }

    public void setSelectedPermiso(Permiso selectedPermiso) {
        this.selectedPermiso = selectedPermiso;
    }

    public void setSelectedUsuario(Usuario usuario) {
        usuarioManagedBean.setSelectedUsuario(usuario);
    }

    public Set<String> getUsuariosConPermisoDatosGenerales() {
        return usuariosConPermisoDatosGenerales;
    }

    public void setUsuariosConPermisoDatosGenerales(Set<String> usuariosConPermisoDatosGenerales) {
        this.usuariosConPermisoDatosGenerales = usuariosConPermisoDatosGenerales;
    }

    public UsuarioManagedBean getUsuarioManagedBean() {
        return usuarioManagedBean;
    }

    public void setUsuarioManagedBean(UsuarioManagedBean usuarioManagedBean) {
        this.usuarioManagedBean = usuarioManagedBean;
    }

    public void agregarRegistroPermiso() {
        try ( Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("INSERT INTO control_permiso (usuario, datosg, estruc_orga, ges_traba, ges_areapuesto) VALUES (?, ?, ?, ?, ?)")) {

            pst.setString(1, selectedPermiso.getUsuario());
            pst.setString(2, isSelected("Datos Generales") ? "X" : "");
            pst.setString(3, isSelected("Estructura Organizacional") ? "X" : "");
            pst.setString(4, isSelected("Gestión de Trabajadores") ? "X" : "");
            pst.setString(5, isSelected("Gestión de Áreas y Puestos") ? "X" : "");

            pst.executeUpdate();

            if (isSelected("Datos Generales")) {
                usuariosConPermisoDatosGenerales.add(selectedPermiso.getUsuario());
            } else {
                usuariosConPermisoDatosGenerales.remove(selectedPermiso.getUsuario());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al agregar el registro: " + e.toString());
        }
    }

    public boolean isEnlaceDatosGeneralesDesactivado(String usuario) {
        return usuariosConPermisoDatosGenerales.contains(usuario);
    }

    private boolean isSelected(String permiso) {
        if (selectedPermisos != null) {
            for (String selected : selectedPermisos) {
                if (selected.equals(permiso)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setUsuarioYAgregarPermiso() {
        Usuario usuario = new Usuario();
        usuario.setNombre(selectedPermiso.getUsuario()); // Usar setNombre para establecer el nombre de usuario
        usuarioManagedBean.setSelectedUsuario(usuario);
        agregarRegistroPermiso();
    }

}
