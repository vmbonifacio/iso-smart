package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.PersonaT;
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


@Named(value = "personaTManagedBean")
@RequestScoped

public class PersonaTManagedBean {
    
    private PersonaT selectedPersonaT;
    private List<PersonaT> listaPersonaT;
    
     public PersonaTManagedBean() {
        selectedPersonaT = new PersonaT();
    }

    public void inicializar() {
        listaPersonaT = obtenerRegistrosPersonaT();

    }

    public PersonaT getSelectedPersonaT() {
        return selectedPersonaT;
    }

    public void setSelectedPersonaT(PersonaT selectedPersonaT) {
        this.selectedPersonaT = selectedPersonaT;
    }

    public List<PersonaT> getListaPersonaT() {
        return listaPersonaT;
    }

    public void setListaPersonaT(List<PersonaT> listaPersonaT) {
        this.listaPersonaT = listaPersonaT;
    }
    
//    METODO BOTON ELIMINAR REGISTRO
    
    public void eliminarRegistroT(int id_personaa_t) {
    try (Connection conn = Conexion.obtenerConexion();
            PreparedStatement pst = conn.prepareStatement("DELETE FROM persona_accitpp WHERE id_personaa_t = ?")) {

        pst.setInt(1, id_personaa_t);
        pst.executeUpdate();

        listaPersonaT = obtenerRegistrosPersonaT();

    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error al eliminar el registro: " + e.getMessage());
    }
}

    
//    METODO OBTENER REGISTRO PERSONA
    
    public List<PersonaT> obtenerRegistrosPersonaT() {
        List<PersonaT> data = new ArrayList<>();

        try (
            Connection con = Conexion.obtenerConexion();  
            Statement sql = con.createStatement();  
            ResultSet rs = sql.executeQuery("select * from persona_accitpp")) {

            while (rs.next()) {
                PersonaT personat = new PersonaT(
                        rs.getInt("id_personaa_t"),
                        rs.getString("tipo_acci"),
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
                        rs.getInt("h_trabajadas_antes_acci")
                );

                data.add(personat);
            }

            Collections.sort(data, Comparator.comparingInt(PersonaT::getId_personaa_t));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
    
    public void agregarRegistroT() {
    try (Connection con = Conexion.obtenerConexion();
            PreparedStatement pst = con.prepareStatement("INSERT INTO persona_accitpp (tipo_acci, nombres_c, apellido_p, apellido_m, dni_ce, edad, genero, empresa_p, area_trabajo, puesto_trabajo, antiguedad_empleo, turno, h_trabajadas_antes_acci) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

        pst.setString(1, selectedPersonaT.getTipo_acci());
        pst.setString(2, selectedPersonaT.getNombres_c());
        pst.setString(3, selectedPersonaT.getApellido_p());
        pst.setString(4, selectedPersonaT.getApellido_m());
        pst.setInt(5, selectedPersonaT.getDni_ce());
        pst.setInt(6, selectedPersonaT.getEdad());
        pst.setString(7, selectedPersonaT.getGenero());
        pst.setString(8, selectedPersonaT.getEmpresa_p());
        pst.setString(9, selectedPersonaT.getArea_trabajo());
        pst.setString(10, selectedPersonaT.getPuesto_trabajo());
        pst.setString(11, selectedPersonaT.getAntiguedad_empleo());
        pst.setString(12, selectedPersonaT.getTurno());
        pst.setInt(13, selectedPersonaT.getH_trabajadas_antes_acci());

        pst.executeUpdate();

        listaPersonaT = obtenerRegistrosPersonaT();

    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error al agregar el registro: " + e.getMessage());
    }
}


}
