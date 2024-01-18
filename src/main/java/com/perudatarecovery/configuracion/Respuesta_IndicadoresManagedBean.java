package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.RespuestasI;
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


@Named(value = "Respuesta_IndicadoresManagedBean") 
@RequestScoped
public class Respuesta_IndicadoresManagedBean {
    
    private RespuestasI selectedRespuestasI;
    private List<RespuestasI> listaRespuestasI;
    
    public Respuesta_IndicadoresManagedBean() {
        selectedRespuestasI = new RespuestasI();
        inicializar();
    }

    public void inicializar(){
        listaRespuestasI =  obtenerRegistrosRespuestaI();
    }

    public RespuestasI getSelectedRespuestasI() {
        return selectedRespuestasI;
    }

    public void setSelectedRespuestasI(RespuestasI selectedRespuestasI) {
        this.selectedRespuestasI = selectedRespuestasI;
    }

    public List<RespuestasI> getListaRespuestasI() {
        return listaRespuestasI;
    }

    public void setListaRespuestasI(List<RespuestasI> listaRespuestasI) {
        this.listaRespuestasI = listaRespuestasI;
    }
    
    public List<String> obtenerCampoRespuestaI(String nombreCampo) {
        List<String> data = new ArrayList<>();

        try ( Connection con = Conexion.obtenerConexion();  
                PreparedStatement stmt = con.prepareStatement("SELECT " + nombreCampo + " FROM Tab_Respuesta_Indicador");  
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String valorCampo = rs.getString(1);  // Obtener el campo en la posición 1 (primer campo)
                data.add(valorCampo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
    
    public List<RespuestasI> obtenerRegistrosRespuestaI() {
        List<RespuestasI> data = new ArrayList<>();

        try ( Connection con = Conexion.obtenerConexion();  
                Statement sql = con.createStatement();  
                ResultSet rs = sql.executeQuery("SELECT * FROM Tab_Respuesta_Indicador")) {

            while (rs.next()) {
                RespuestasI respuestasI = new RespuestasI(
                        rs.getInt("id_respuesta_indicador"),
                        rs.getString("fuente"),
                        rs.getString("si"),
                        rs.getString("no"),
                        rs.getString("observaciones")
                    );

                data.add(respuestasI);
            }

            Collections.sort(data, Comparator.comparingInt(RespuestasI::getId_respuesta_indicador));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
    
    public void agregarRegistroRespuestaI() {
        try ( Connection con = Conexion.obtenerConexion();  
                PreparedStatement pst = con.prepareStatement("INSERT INTO Tab_Respuesta_Indicador (fuente, si, no, observaciones) VALUES (?, ?, ?, ?)")) {

            pst.setString(1, selectedRespuestasI.getFuente());
            pst.setString(2, selectedRespuestasI.getSi());
            pst.setString(3, selectedRespuestasI.getNo());
            pst.setString(4, selectedRespuestasI.getObservaciones());
            


            pst.executeUpdate();

            listaRespuestasI = obtenerRegistrosRespuestaI();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al agregar el registro: " + e.toString());
        }
    }
    
    
}

