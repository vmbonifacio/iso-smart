
package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.PersonaI;
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

@Named(value = "personaIManagedBean")
@RequestScoped
public class PersonaPIncManagedBean {
    // Otros atributos y métodos de la clase
    private PersonaI selectedPersonaI;
    private List<PersonaI> listaPersonaI;

    public PersonaPIncManagedBean() {
        selectedPersonaI = new PersonaI();
    }

    public void inicializar() {
        listaPersonaI = obtenerRegistrosPersonaI();
    }

    public PersonaI getSelectedPersonaI() {
        return selectedPersonaI;
    }

    public void setSelectedPersonaI(PersonaI selectedPersonaI) {
        this.selectedPersonaI = selectedPersonaI;
    }

    public List<PersonaI> getListaPersonaI() {
        return listaPersonaI;
    }

    public void setListaPersonaI(List<PersonaI> listaPersonaI) {
        this.listaPersonaI = listaPersonaI;
    }
    
    public List<PersonaI> obtenerRegistrosPersonaI() {
        List<PersonaI> data = new ArrayList<>();

        try (
            Connection con = Conexion.obtenerConexion();  
            Statement sql = con.createStatement();  
            ResultSet rs = sql.executeQuery("select * from persona_incpp")) {

            while (rs.next()) {
                PersonaI personai = new PersonaI(
                        rs.getInt("id_personai"),
                        rs.getString("tipo_inci"),
                        rs.getString("nombres_c"),
                        rs.getString("apellido_p"),
                        rs.getString("apellido_m"),
                        rs.getInt("dni_ce"),
                        rs.getInt("edad"),
                        rs.getString("genero"),
                        rs.getString("area_trabajo"),
                        rs.getString("puesto_trabajo"),
                        rs.getString("antiguedad_empleo"),
                        rs.getString("turno"),
                        rs.getString("tipo_contrato"),
                        rs.getString("tiempo_experiencia"),
                        rs.getInt("h_trabajadas_antes_inci")
                );

                data.add(personai);
            }

            Collections.sort(data, Comparator.comparingInt(PersonaI::getId_personai));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
    
    public void agregarRegistroPI() {
        try (
                 Connection con = Conexion.obtenerConexion();  
                PreparedStatement pst = con.prepareStatement("INSERT INTO persona_incpp (tipo_inci, nombres_c, apellido_p, apellido_m, dni_ce, edad, genero, area_trabajo, puesto_trabajo, antiguedad_empleo, turno, tipo_contrato, tiempo_experiencia, h_trabajadas_antes_inci) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            pst.setString(1, selectedPersonaI.getTipo_inci());
            pst.setString(2, selectedPersonaI.getNombres_c());
            pst.setString(3, selectedPersonaI.getApellido_p());
            pst.setString(4, selectedPersonaI.getApellido_m());
            pst.setInt(5, selectedPersonaI.getDni_ce());
            pst.setInt(6, selectedPersonaI.getEdad());
            pst.setString(7, selectedPersonaI.getGenero());
            pst.setString(8, selectedPersonaI.getArea_trabajo());
            pst.setString(9, selectedPersonaI.getPuesto_trabajo());
            pst.setString(10, selectedPersonaI.getAntiguedad_empleo());
            pst.setString(11, selectedPersonaI.getTurno());
            pst.setString(12, selectedPersonaI.getTipo_contrato());
            pst.setString(13, selectedPersonaI.getTiempo_experiencia());
            pst.setInt(14, selectedPersonaI.getH_trabajadas_antes_inci());

            pst.executeUpdate();

            listaPersonaI = obtenerRegistrosPersonaI();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al agregar el registro: " + e.getMessage());
        }
    }
    
    private PersonaI personai = new PersonaI();

    public PersonaI getPersonaI() {
        return personai;
    }

    public void setPersonaI(PersonaI personai) {
        this.personai = personai;
    }
    
    public void eliminarRegistroPI(int id_personai) {
        try (
            Connection conn = Conexion.obtenerConexion();  
            Statement sql = conn.createStatement()) {

            String query = "DELETE FROM persona_incpp WHERE id_personai = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id_personai);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
