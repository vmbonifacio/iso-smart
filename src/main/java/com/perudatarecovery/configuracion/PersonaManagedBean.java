package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.Persona;

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

@Named(value = "personaManagedBean")
@RequestScoped
public class PersonaManagedBean {

    // Otros atributos y métodos de la clase
    private Persona selectedPersona;
    private List<Persona> listaPersona;

    public PersonaManagedBean() {
        selectedPersona = new Persona();
    }

    public void inicializar() {
        listaPersona = obtenerRegistrosPersona();
    }

    public Persona getSelectedPersona() {
        return selectedPersona;
    }

    public void setSelectedPersona(Persona selectedPersona) {
        this.selectedPersona = selectedPersona;
    }

    public List<Persona> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(List<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }
    
    public void eliminarRegistro(int idPersonaa) {
        try (
            Connection conn = Conexion.obtenerConexion();  
            Statement sql = conn.createStatement()) {

            String query = "DELETE FROM persona_accipp WHERE id_personaa = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, idPersonaa);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Persona> obtenerRegistrosPersona() {
        List<Persona> data = new ArrayList<>();

        try (
            Connection con = Conexion.obtenerConexion();  
            Statement sql = con.createStatement();  
            ResultSet rs = sql.executeQuery("select * from persona_accipp")) {

            while (rs.next()) {
                Persona persona = new Persona(
                        rs.getInt("id_personaa"),
                        rs.getString("tipo_acci"),
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
                        rs.getInt("h_trabajadas_antes_acci")
                );

                data.add(persona);
            }

            Collections.sort(data, Comparator.comparingInt(Persona::getId_personaa));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    // Botón para registrar
    public void agregarRegistro() {
        try (
                 Connection con = Conexion.obtenerConexion();  
                PreparedStatement pst = con.prepareStatement("INSERT INTO persona_accipp (tipo_acci, nombres_c, apellido_p, apellido_m, dni_ce, edad, genero, area_trabajo, puesto_trabajo, antiguedad_empleo, turno, tipo_contrato, tiempo_experiencia, h_trabajadas_antes_acci) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            pst.setString(1, persona.getTipo_acci());
            pst.setString(2, selectedPersona.getNombres_c());
            pst.setString(3, selectedPersona.getApellido_p());
            pst.setString(4, selectedPersona.getApellido_m());
            pst.setInt(5, selectedPersona.getDni_ce());
            pst.setInt(6, selectedPersona.getEdad());
            pst.setString(7, selectedPersona.getGenero());
            pst.setString(8, selectedPersona.getArea_trabajo());
            pst.setString(9, selectedPersona.getPuesto_trabajo());
            pst.setString(10, selectedPersona.getAntiguedad_empleo());
            pst.setString(11, selectedPersona.getTurno());
            pst.setString(12, selectedPersona.getTipo_contrato());
            pst.setString(13, selectedPersona.getTiempo_experiencia());
            pst.setInt(14, selectedPersona.getH_trabajadas_antes_acci());

            pst.executeUpdate();

            listaPersona = obtenerRegistrosPersona();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al agregar el registro: " + e.getMessage());
        }
    }
    
     //Boton para realizar actualizanción
    public void prepararEdicion(Persona persona) {
        selectedPersona = persona;
    }

    private Persona persona = new Persona();

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    
}


