package com.perudatarecovery.configuracion;

import com.perudatarecovery.configuracion.Conexion;
import com.perudatarecovery.modelo.ComiteI;
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
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "comiteInvestigacionManagedBean")
@RequestScoped
public class ComiteInvestigacionManagedBean {

    private ComiteI selectedComiteI;
    private List<ComiteI> ListaComiteI;
    private List<String> listaPuestoComite;

    @PostConstruct
    public void init() {
        inicializar();
        MostrarPuestoComite();
    }

    public ComiteInvestigacionManagedBean() {
        selectedComiteI = new ComiteI();
    }

    public void inicializar() {
        ListaComiteI = obtenerRegistroComiteI();
    }

    public ComiteI getSelectedComiteI() {
        return selectedComiteI;
    }

    public void setSelectedComiteI(ComiteI selectedComiteI) {
        this.selectedComiteI = selectedComiteI;
    }

    public List<ComiteI> getListaComiteI() {
        return ListaComiteI;
    }

    public void setListaComiteI(List<ComiteI> ListaComiteI) {
        this.ListaComiteI = ListaComiteI;
    }

    public List<String> getListaPuestoComite() {
        return listaPuestoComite;
    }

    public void setListaPuestoComite(List<String> listaPuestoComite) {
        this.listaPuestoComite = listaPuestoComite;
    }

    public List<ComiteI> obtenerRegistroComiteI() {
        List<ComiteI> data = new ArrayList<>();

        try ( Connection con = Conexion.obtenerConexion();  Statement sql = con.createStatement();  ResultSet rs = sql.executeQuery("SELECT * FROM Tab_Comite_Investigacion")) {

            while (rs.next()) {
                ComiteI comitei = new ComiteI(
                        rs.getInt("id_comite_investigacion"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("puesto_comite"),
                        rs.getString("estado_persona")
                );

                data.add(comitei);
            }

            Collections.sort(data, Comparator.comparingInt(ComiteI::getId_comite_investigacion));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void agregarRegistroComite() {
        try ( Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("INSERT INTO Tab_Comite_Investigacion (nombre, apellidos, puesto_comite, estado_persona) VALUES (?, ?, ?, ?)")) {

            pst.setString(1, selectedComiteI.getNombre());
            pst.setString(2, selectedComiteI.getApellidos());
            pst.setString(3, selectedComiteI.getPuesto_comite());
            pst.setString(4, selectedComiteI.getEstado_persona());
            pst.executeUpdate();

            ListaComiteI = obtenerRegistroComiteI();

            System.out.println("Método agregarRegistroComite() ejecutado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al agregar el registro: " + e.toString());
        }
    }

    public void MostrarPuestoComite() {
        listaPuestoComite = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("EXEC SP_MostrarPuestoComiteI");

            while (rs.next()) {
                String puestoComite = rs.getString("Puesto_comite");
                listaPuestoComite.add(puestoComite);
            }
        } catch (SQLException e) {
            // Manejar excepciones de SQL aquí
            e.printStackTrace();
        } catch (Exception e) {
            // Manejar otras excepciones aquí
            e.printStackTrace();
        } finally {
            // Cerrar recursos en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
