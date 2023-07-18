package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.PersonaTI;
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

@Named(value = "personaTIncManagedBean")
@RequestScoped
public class PersonaTIncManagedBean {
    private PersonaTI selectedPersonaTI;
    private List<PersonaTI> listaPersonaTI;
    
     public PersonaTIncManagedBean() {
        selectedPersonaTI = new PersonaTI();
    }

    public void inicializar() {
        listaPersonaTI = obtenerRegistrosPersonaTI();

    }

    public PersonaTI getSelectedPersonaTI() {
        return selectedPersonaTI;
    }

    public void setSelectedPersonaTI(PersonaTI selectedPersonaTI) {
        this.selectedPersonaTI = selectedPersonaTI;
    }

    public List<PersonaTI> getListaPersonaTI() {
        return listaPersonaTI;
    }

    public void setListaPersonaTI(List<PersonaTI> listaPersonaTI) {
        this.listaPersonaTI = listaPersonaTI;
    }
    
    //    METODO OBTENER REGISTRO PERSONA
    
    public List<PersonaTI> obtenerRegistrosPersonaTI() {
        List<PersonaTI> data = new ArrayList<>();

        try (
            Connection con = Conexion.obtenerConexion();  
            Statement sql = con.createStatement();  
            ResultSet rs = sql.executeQuery("select * from persona_incitpp")) {

            while (rs.next()) {
                PersonaTI personati = new PersonaTI(
                        rs.getInt("id_personai_t"),
                        rs.getString("tipo_inci"),
                        rs.getString("nombres_c"),
                        rs.getString("apellido_p"),
                        rs.getString("apellido_m"),
                        rs.getInt("dni_ce"),
                        rs.getInt("edad"),
                        rs.getString("genero"),
                        rs.getString("empresa_p"),
                        rs.getString("area_trabajo"),
                        rs.getString("puesto_trabajo"),
                        rs.getString("antiguedad_empleo"),
                        rs.getString("turno"),
                        rs.getInt("h_trabajadas_antes_inci")
                );

                data.add(personati);
            }

            Collections.sort(data, Comparator.comparingInt(PersonaTI::getId_personai_t));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
    public void agregarRegistroTI() {
    try (Connection con = Conexion.obtenerConexion();
            PreparedStatement pst = con.prepareStatement("INSERT INTO persona_incitpp (tipo_inci, nombres_c, apellido_p, apellido_m, dni_ce, edad, genero, empresa_p, area_trabajo, puesto_trabajo, antiguedad_empleo, turno, h_trabajadas_antes_inci) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

        pst.setString(1, selectedPersonaTI.getTipo_inci());
        pst.setString(2, selectedPersonaTI.getNombres_c());
        pst.setString(3, selectedPersonaTI.getApellido_p());
        pst.setString(4, selectedPersonaTI.getApellido_m());
        pst.setInt(5, selectedPersonaTI.getDni_ce());
        pst.setInt(6, selectedPersonaTI.getEdad());
        pst.setString(7, selectedPersonaTI.getGenero());
        pst.setString(8, selectedPersonaTI.getEmpresa_p());
        pst.setString(9, selectedPersonaTI.getArea_trabajo());
        pst.setString(10, selectedPersonaTI.getPuesto_trabajo());
        pst.setString(11, selectedPersonaTI.getAntiguedad_empleo());
        pst.setString(12, selectedPersonaTI.getTurno());
        pst.setInt(13, selectedPersonaTI.getH_trabajadas_antes_inci());

        pst.executeUpdate();

        listaPersonaTI = obtenerRegistrosPersonaTI();

    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error al agregar el registro: " + e.getMessage());
    }
}
    
    //    METODO BOTON ELIMINAR REGISTRO
    
    public void eliminarRegistroTI(int id_personai_t) {
    try (Connection conn = Conexion.obtenerConexion();
            PreparedStatement pst = conn.prepareStatement("DELETE FROM persona_incitpp WHERE id_personai_t = ?")) {

        pst.setInt(1, id_personai_t);
        pst.executeUpdate();

        listaPersonaTI = obtenerRegistrosPersonaTI();

    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error al eliminar el registro: " + e.getMessage());
    }
}
}
