package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.PersonaCI;
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

@Named(value = "personaCIManagedBean")
@RequestScoped
public class PersonaCIncManagedBean {
    private PersonaCI selectedPersonaCI;
    private List<PersonaCI> listaPersonaCI;

    public PersonaCIncManagedBean() {
        selectedPersonaCI = new PersonaCI();
    }

    public void inicializar() {
        listaPersonaCI = obtenerRegistrosPersonaCI();
    }

    public PersonaCI getSelectedPersonaCI() {
        return selectedPersonaCI;
    }

    public void setSelectedPersonaCI(PersonaCI selectedPersonaCI) {
        this.selectedPersonaCI = selectedPersonaCI;
    }

    public List<PersonaCI> getListaPersonaCI() {
        return listaPersonaCI;
    }

    public void setListaPersonaCI(List<PersonaCI> listaPersonaCI) {
        this.listaPersonaCI = listaPersonaCI;
    }
    
    //    METODO OBTENER REGISTRO PERSONA
    public List<PersonaCI> obtenerRegistrosPersonaCI() {
        List<PersonaCI> data = new ArrayList<>();

        try (
              Connection con = Conexion.obtenerConexion();  
              Statement sql = con.createStatement();  
              ResultSet rs = sql.executeQuery("select * from persona_incic")) {
            while (rs.next()) {
                PersonaCI personaci = new PersonaCI(
                        rs.getInt("id_personac"),
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

                data.add(personaci);
            }

            Collections.sort(data, Comparator.comparingInt(PersonaCI::getId_personac));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
    
    public void agregarRegistroC() {
        try (
                 Connection con = Conexion.obtenerConexion();  
                 PreparedStatement pst = con.prepareStatement("INSERT INTO persona_incic(tipo_inci, nombres_c, apellido_p, apellido_m, dni_ce, edad, genero, area_trabajo, puesto_trabajo, antiguedad_empleo, turno, tipo_contrato, tiempo_experiencia, h_trabajadas_antes_inci) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            pst.setString(1, selectedPersonaCI.getTipo_inci());
            pst.setString(2, selectedPersonaCI.getNombres_c());
            pst.setString(3, selectedPersonaCI.getApellido_p());
            pst.setString(4, selectedPersonaCI.getApellido_m());
            pst.setInt(5, selectedPersonaCI.getDni_ce());
            pst.setInt(6, selectedPersonaCI.getEdad());
            pst.setString(7, selectedPersonaCI.getGenero());
            pst.setString(8, selectedPersonaCI.getArea_trabajo());
            pst.setString(9, selectedPersonaCI.getPuesto_trabajo());
            pst.setString(10, selectedPersonaCI.getAntiguedad_empleo());
            pst.setString(11, selectedPersonaCI.getTurno());
            pst.setString(12, selectedPersonaCI.getTipo_contrato());
            pst.setString(13, selectedPersonaCI.getTiempo_experiencia());
            pst.setInt(14, selectedPersonaCI.getH_trabajadas_antes_inci());

            pst.executeUpdate();

            listaPersonaCI = obtenerRegistrosPersonaCI();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al agregar el registro: " + e.getMessage());
        }
    }
    
    private PersonaCI personaci = new PersonaCI();

    public PersonaCI getPersonaCI() {
        return personaci;
    }

    public void setPersonaCI(PersonaCI personaci) {
        this.personaci = personaci;
    }

//METODO PARA BOTON ELIMINAR
    public void eliminarRegistroC(int id_personac) {
            try (
                Connection conn = Conexion.obtenerConexion();
                PreparedStatement pst = conn.prepareStatement("DELETE FROM persona_incic WHERE id_personac = ?")
            ) {
                pst.setInt(1, id_personac);
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
