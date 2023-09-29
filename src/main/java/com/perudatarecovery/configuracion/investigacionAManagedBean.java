package com.perudatarecovery.configuracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class investigacionAManagedBean {

    public investigacionAManagedBean() {

    }

    public void inicializar() {

    }

    public List<String> obtenerListaCapacitacion() {
        List<String> listaAreas = new ArrayList<>();

        try ( Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("EXEC SP_ObtenerLista_Capacitacion");  ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                listaAreas.add(rs.getString("DESCRIP_CAPA"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaAreas;
    }

    public List<String> obtenerListaProcedimiento() {
        List<String> listaAreas = new ArrayList<>();

        try ( Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("EXEC SP_ObtenerLista_Procedimientos");  ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                listaAreas.add(rs.getString("DESCRIP_EXISPRO"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaAreas;
    }

    public List<String> obtenerListaExposicion() {
        List<String> listaAreas = new ArrayList<>();

        try ( Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("EXEC SP_ObtenerLista_Exposicion");  ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                listaAreas.add(rs.getString("EXPOSICION_RIESGO"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaAreas;
    }
}
