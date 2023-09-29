package com.perudatarecovery.configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ComiteManagedBean {

    private List<String> puestosComite;

    @PostConstruct
    public void init() {
        puestosComite = new ArrayList<>();
        loadPuestosComite();
    }

    public List<String> getPuestosComite() {
        return puestosComite;
    }

    private void loadPuestosComite() {
        try {
            Connection conn = Conexion.obtenerConexion();
            Statement stmt = conn.createStatement();
            String sql = "EXEC SP_MostrarPuestoComiteI";
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                puestosComite.add(resultSet.getString("puesto_comite"));
            }

            resultSet.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
