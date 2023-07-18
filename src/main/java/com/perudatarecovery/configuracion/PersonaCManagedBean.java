package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.PersonaC;

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

@Named(value = "personaCManagedBean")
@RequestScoped
public class PersonaCManagedBean {

    private PersonaC selectedPersonaC;
    private List<PersonaC> listaPersonaC;

    public PersonaCManagedBean() {
        selectedPersonaC = new PersonaC();
    }

    public void inicializar() {
        listaPersonaC = obtenerRegistrosPersonaC();
    }

    public PersonaC getSelectedPersonaC() {
        return selectedPersonaC;
    }

    public void setSelectedPersonaC(PersonaC selectedPersonaC) {
        this.selectedPersonaC = selectedPersonaC;
    }

    public List<PersonaC> getListaPersonaC() {
        return listaPersonaC;
    }

    public void setListaPersonaC(List<PersonaC> listaPersonaC) {
        this.listaPersonaC = listaPersonaC;
    }

    //    METODO OBTENER REGISTRO PERSONA
    public List<PersonaC> obtenerRegistrosPersonaC() {
        List<PersonaC> data = new ArrayList<>();

        try (
                 Connection con = Conexion.obtenerConexion(); 
                Statement sql = con.createStatement();  
                ResultSet rs = sql.executeQuery("select * from persona_acci_cp")) {
            while (rs.next()) {
                PersonaC personaC = new PersonaC(
                        rs.getInt("id_personaa_c"),
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

                data.add(personaC);
            }

            Collections.sort(data, Comparator.comparingInt(PersonaC::getId_personaa_c));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void agregarRegistroC() {
        try (
                 Connection con = Conexion.obtenerConexion();  
                 PreparedStatement pst = con.prepareStatement("INSERT INTO persona_acci_cp (tipo_acci, nombres_c, apellido_p, apellido_m, dni_ce, edad, genero, area_trabajo, puesto_trabajo, antiguedad_empleo, turno, tipo_contrato, tiempo_experiencia, h_trabajadas_antes_acci) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            pst.setString(1, selectedPersonaC.getTipo_acci());
            pst.setString(2, selectedPersonaC.getNombres_c());
            pst.setString(3, selectedPersonaC.getApellido_p());
            pst.setString(4, selectedPersonaC.getApellido_m());
            pst.setInt(5, selectedPersonaC.getDni_ce());
            pst.setInt(6, selectedPersonaC.getEdad());
            pst.setString(7, selectedPersonaC.getGenero());
            pst.setString(8, selectedPersonaC.getArea_trabajo());
            pst.setString(9, selectedPersonaC.getPuesto_trabajo());
            pst.setString(10, selectedPersonaC.getAntiguedad_empleo());
            pst.setString(11, selectedPersonaC.getTurno());
            pst.setString(12, selectedPersonaC.getTipo_contrato());
            pst.setString(13, selectedPersonaC.getTiempo_experiencia());
            pst.setInt(14, selectedPersonaC.getH_trabajadas_antes_acci());

            pst.executeUpdate();

            listaPersonaC = obtenerRegistrosPersonaC();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al agregar el registro: " + e.getMessage());
        }
    }
    
    
    private PersonaC personac = new PersonaC();

    public PersonaC getPersonaC() {
        return personac;
    }

    public void setPersonaC(PersonaC personac) {
        this.personac = personac;
    }

//METODO PARA BOTON ELIMINAR
    public void eliminarRegistroC(int id_personaa_c) {
            try (
                Connection conn = Conexion.obtenerConexion();
                PreparedStatement pst = conn.prepareStatement("DELETE FROM persona_acci_cp WHERE id_personaa_c = ?")
            ) {
                pst.setInt(1, id_personaa_c);
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


}
