package com.perudatarecovery.configuracion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class LineaBaseManagedBean {

    private List<String> listaComponentes = new ArrayList<>();
    private List<String> listaComponentesPolitica = new ArrayList<>();
    private List<String> listaComponentesPlaneamiento = new ArrayList<>();
    private List<String> listaComponentesImplementacion = new ArrayList<>();
    private List<String> listaComponentesEvaluacion = new ArrayList<>();
    private List<String> listaComponentesVerificacion = new ArrayList<>();
    private List<String> listaComponentesControlInformacion = new ArrayList<>();
    private List<String> listaComponentesRevision = new ArrayList<>();

    private List<String> listaIndicadores = new ArrayList<>();
    private List<String> listaIndicadoresPolitica = new ArrayList<>();
    private List<String> listaIndicadoresDireccion = new ArrayList<>();
    private List<String> listaIndicadoresLiderazgo = new ArrayList<>();
    private List<String> listaIndicadoresOrganizacion = new ArrayList<>();
    private List<String> listaIndicadoresCompetencia = new ArrayList<>();
    private List<String> listaIndicadoresDiagnostico = new ArrayList<>();
    private List<String> listaIndicadoresPlaneamientoEvaluacion = new ArrayList<>();
    private List<String> listaIndicadoresObjetivos = new ArrayList<>();
    private List<String> listaIndicadoresPrograma = new ArrayList<>();
    private List<String> listaIndicadoresEstructura = new ArrayList<>();
    private List<String> listaIndicadoresCapacitacion = new ArrayList<>();
    private List<String> listaIndicadoresMedidas = new ArrayList<>();
    private List<String> listaIndicadoresPreparacion = new ArrayList<>();
    private List<String> listaIndicadoresContratistas = new ArrayList<>();
    private List<String> listaIndicadoresConsulta = new ArrayList<>();
    private List<String> listaIndicadoresRequisitos = new ArrayList<>();
    private List<String> listaIndicadoresSupervision = new ArrayList<>();
    private List<String> listaIndicadoresSalud = new ArrayList<>();
    private List<String> listaIndicadoresAccidentes = new ArrayList<>();
    private List<String> listaIndicadoresInvestigacion = new ArrayList<>();
    private List<String> listaIndicadoresControl = new ArrayList<>();
    private List<String> listaIndicadoresGestion = new ArrayList<>();
    private List<String> listaIndicadoresAuditoria = new ArrayList<>();
    private List<String> listaIndicadoresDocumentos = new ArrayList<>();
    private List<String> listaIndicadoresControlDocumentacion = new ArrayList<>();
    private List<String> listaIndicadoresGestionRiesgo = new ArrayList<>();
    private List<String> listaIndicadoresGestionMejora = new ArrayList<>();

    public LineaBaseManagedBean() {
        obtenerListaComponentes();
        obtenerListaComponentesPolitica();
        obtenerListaComponentesPlaneamiento();
        obtenerListaComponentesImplementacion();
        obtenerListaComponentesEvaluacion();
        obtenerListaComponentesVerificacion();
        obtenerListaComponentesControlInformacion();
        obtenerListaComponentesRevision();
        
        
        
        obtenerListaIndicadores();
        obtenerListaIndicadoresPolitica();
        obtenerListaIndicadoresDireccion();
        obtenerListaIndicadoresLiderazgo();
        obtenerListaIndicadoresOrganizacion();
        obtenerListaIndicadoresCompetencia();
        obtenerListaIndicadoresDiagnostico();
        obtenerListaIndicadoresPlaneamientoEvaluacion();
        obtenerListaIndicadoresObjetivos();
        obtenerListaIndicadoresPrograma();
        obtenerListaIndicadoresEstructura();
        obtenerListaIndicadoresCapacitacion();
        obtenerListaIndicadoresMedidas();
        obtenerListaIndicadoresPreparacion();
        obtenerListaIndicadoresContratistas();
        obtenerListaIndicadoresConsulta();
        obtenerListaIndicadoresRequisitos();
        obtenerListaIndicadoresSupervision();
        obtenerListaIndicadoresSalud();
        obtenerListaIndicadoresAccidentes();
        obtenerListaIndicadoresInvestigacion();
        obtenerListaIndicadoresControl();
        obtenerListaIndicadoresGestion();
        obtenerListaIndicadoresAuditoria();
        obtenerListaIndicadoresDocumentos();
        obtenerListaIndicadoresControlDocumentacion();
        obtenerListaIndicadoresGestionRiesgo();
        obtenerListaIndicadoresGestionMejora();
    }

    private void obtenerListaComponentes() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaComponente(?)}");
            cs.setInt(1, obtenerIdLineamiento()); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreComponente = rs.getString("Nombre_Componente");
                listaComponentes.add(nombreComponente);

                // Agregar registro de depuración
                System.out.println("Nombre_Componente: " + nombreComponente);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadores() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, obtenerIdLineamiento()); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadores.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    //***************************COMPROMISOS************************************//
    
    private void obtenerListaComponentesPolitica() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaComponente(?)}");
            cs.setInt(1, 2); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreComponente = rs.getString("Nombre_Componente");
                listaComponentesPolitica.add(nombreComponente);

                // Agregar registro de depuración
                System.out.println("Nombre_Componente: " + nombreComponente);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresPolitica() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 2); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresPolitica.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresDireccion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 3); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresDireccion.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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

    private void obtenerListaIndicadoresLiderazgo() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 4); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresLiderazgo.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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

    private void obtenerListaIndicadoresOrganizacion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 5); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresOrganizacion.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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

    private void obtenerListaIndicadoresCompetencia() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 6); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresCompetencia.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    //****************************POLITICA DE SEGURIDAD *************************************//
    
    private void obtenerListaComponentesPlaneamiento() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaComponente(?)}");
            cs.setInt(1, 3); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreComponente = rs.getString("Nombre_Componente");
                listaComponentesPlaneamiento.add(nombreComponente);

                // Agregar registro de depuración
                System.out.println("Nombre_Componente: " + nombreComponente);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresDiagnostico() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 7); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresDiagnostico.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresPlaneamientoEvaluacion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 8); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresPlaneamientoEvaluacion.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresObjetivos() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 9); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresObjetivos.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresPrograma() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 10); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresPrograma.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    
    //***************************PLANEAMINETO Y APLICACION*******************************//
    
    private void obtenerListaComponentesImplementacion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaComponente(?)}");
            cs.setInt(1, 4); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreComponente = rs.getString("Nombre_Componente");
                listaComponentesImplementacion.add(nombreComponente);

                // Agregar registro de depuración
                System.out.println("Nombre_Componente: " + nombreComponente);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresEstructura() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 11); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresEstructura.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresCapacitacion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 12); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresCapacitacion.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresMedidas() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 13); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresMedidas.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresPreparacion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 14); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresPreparacion.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresContratistas() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 15); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresContratistas.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresConsulta() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 16); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresConsulta.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    //***************************IMPLEMENTACION  Y OPERACION *******************************//
    
    private void obtenerListaComponentesEvaluacion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaComponente(?)}");
            cs.setInt(1, 5); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreComponente = rs.getString("Nombre_Componente");
                listaComponentesEvaluacion.add(nombreComponente);

                // Agregar registro de depuración
                System.out.println("Nombre_Componente: " + nombreComponente);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresRequisitos() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 17); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresRequisitos.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
     //*************************** EVALUACION NORMATIVA *******************************//
    
    private void obtenerListaComponentesVerificacion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaComponente(?)}");
            cs.setInt(1, 6); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreComponente = rs.getString("Nombre_Componente");
                listaComponentesVerificacion.add(nombreComponente);

                // Agregar registro de depuración
                System.out.println("Nombre_Componente: " + nombreComponente);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresSupervision() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 18); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresSupervision.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresSalud() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 19); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresSalud.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresAccidentes() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 20); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresAccidentes.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresInvestigacion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 21); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresInvestigacion.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresControl() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 22); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresControl.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresGestion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 23); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresGestion.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresAuditoria() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 24); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresAuditoria.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
     //*************************** CONTROL DE LA INFROMACION Y DOCUMENTOS*******************************//
    
    private void obtenerListaComponentesControlInformacion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaComponente(?)}");
            cs.setInt(1, 7); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreComponente = rs.getString("Nombre_Componente");
                listaComponentesControlInformacion.add(nombreComponente);

                // Agregar registro de depuración
                System.out.println("Nombre_Componente: " + nombreComponente);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresDocumentos() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 25); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresDocumentos.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresControlDocumentacion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 26); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresControlDocumentacion.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresGestionRiesgo() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 27); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresGestionRiesgo.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    //*************************** REVISIONPOR LA DIRECCCION *******************************//
    
    private void obtenerListaComponentesRevision() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaComponente(?)}");
            cs.setInt(1, 8); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreComponente = rs.getString("Nombre_Componente");
                listaComponentesRevision.add(nombreComponente);

                // Agregar registro de depuración
                System.out.println("Nombre_Componente: " + nombreComponente);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    private void obtenerListaIndicadoresGestionMejora() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerListaIndicador(?)}");
            cs.setInt(1, 28); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Nombre_Indicador");
                listaIndicadoresGestionMejora.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
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
    
    
    
    
    
    
    // Método para obtener el id_Lineamiento
    private int obtenerIdLineamiento() {
        // Lógica para obtener el id_Lineamiento, por ejemplo, usando otro procedimiento almacenado
        return 1; // Debes adaptar esta lógica según tus necesidades
    }

    public List<String> getListaComponentes() {
        return listaComponentes;
    }
    
    public void setListaComponentes(List<String> listaComponentes) {
        this.listaComponentes = listaComponentes;
    }

    public List<String> getListaComponentesPolitica() {
        return listaComponentesPolitica;
    }

    public void setListaComponentesPolitica(List<String> listaComponentesPolitica) {
        this.listaComponentesPolitica = listaComponentesPolitica;
    }

    public List<String> getListaComponentesPlaneamiento() {
        return listaComponentesPlaneamiento;
    }

    public void setListaComponentesPlaneamiento(List<String> listaComponentesPlaneamiento) {
        this.listaComponentesPlaneamiento = listaComponentesPlaneamiento;
    }

    public List<String> getListaComponentesEvaluacion() {
        return listaComponentesEvaluacion;
    }

    public void setListaComponentesEvaluacion(List<String> listaComponentesEvaluacion) {
        this.listaComponentesEvaluacion = listaComponentesEvaluacion;
    }

    public List<String> getListaComponentesVerificacion() {
        return listaComponentesVerificacion;
    }

    public void setListaComponentesVerificacion(List<String> listaComponentesVerificacion) {
        this.listaComponentesVerificacion = listaComponentesVerificacion;
    }

    public List<String> getListaComponentesControlInformacion() {
        return listaComponentesControlInformacion;
    }

    public void setListaComponentesControlInformacion(List<String> listaComponentesControlInformacion) {
        this.listaComponentesControlInformacion = listaComponentesControlInformacion;
    }

    public List<String> getListaComponentesRevision() {
        return listaComponentesRevision;
    }

    public void setListaComponentesRevision(List<String> listaComponentesRevision) {
        this.listaComponentesRevision = listaComponentesRevision;
    }

    //********************************COMPONENTES**************************************************************//

    
    
    
    
    public List<String> getListaIndicadores() {
        return listaIndicadores;
    }

    public void setListaIndicadores(List<String> listaIndicadores) {
        this.listaIndicadores = listaIndicadores;
    }
    
    //**********************COMPROMISOS E INVOLUCRAMIENTO **********************************//
    
    public List<String> getListaIndicadoresPolitica() {
        return listaIndicadoresPolitica;
    }

    public void setListaIndicadoresPolitica(List<String> listaIndicadoresPolitica) {
        this.listaIndicadoresPolitica = listaIndicadoresPolitica;
    }

    public List<String> getListaIndicadoresDireccion() {
        return listaIndicadoresDireccion;
    }

    public void setListaIndicadoresDireccion(List<String> listaIndicadoresDireccion) {
        this.listaIndicadoresDireccion = listaIndicadoresDireccion;
    }

    public List<String> getListaIndicadoresLiderazgo() {
        return listaIndicadoresLiderazgo;
    }

    public void setListaIndicadoresLiderazgo(List<String> listaIndicadoresLiderazgo) {
        this.listaIndicadoresLiderazgo = listaIndicadoresLiderazgo;
    }

    public List<String> getListaIndicadoresOrganizacion() {
        return listaIndicadoresOrganizacion;
    }

    public void setListaIndicadoresOrganizacion(List<String> listaIndicadoresOrganizacion) {
        this.listaIndicadoresOrganizacion = listaIndicadoresOrganizacion;
    }

    public List<String> getListaIndicadoresCompetencia() {
        return listaIndicadoresCompetencia;
    }

    public void setListaIndicadoresCompetencia(List<String> listaIndicadoresCompetencia) {
        this.listaIndicadoresCompetencia = listaIndicadoresCompetencia;
    }

    //********************* POLITICA DE SEGURIDAD***********************************//
    
    public List<String> getListaIndicadoresDiagnostico() {
        return listaIndicadoresDiagnostico;
    }

    public void setListaIndicadoresPlaneamientoDiagnostico(List<String> listaIndicadoresDiagnostico) {
        this.listaIndicadoresDiagnostico = listaIndicadoresDiagnostico;
    }

    public List<String> getListaIndicadoresPlaneamientoEvaluacion() {
        return listaIndicadoresPlaneamientoEvaluacion;
    }

    public void setListaIndicadoresPlaneamientoEvaluacion(List<String> listaIndicadoresPlaneamientoEvaluacion) {
        this.listaIndicadoresPlaneamientoEvaluacion = listaIndicadoresPlaneamientoEvaluacion;
    }
    
    public List<String> getListaIndicadoresObjetivos() {
        return listaIndicadoresObjetivos;
    }

    public void setListaIndicadoresObjetivos(List<String> listaIndicadoresObjetivos) {
        this.listaIndicadoresObjetivos = listaIndicadoresObjetivos;
    }
    
     public List<String> getListaIndicadoresPrograma() {
        return listaIndicadoresPrograma;
    }

    public void setListaIndicadoresPrograma(List<String> listaIndicadoresPrograma) {
        this.listaIndicadoresPrograma = listaIndicadoresPrograma;
    }

   
    //******************************PLANEAMIENTO Y APLICACION**********************************************//

    public List<String> getListaComponentesImplementacion() {
        return listaComponentesImplementacion;
    }

    public void setListaComponentesImplementacion(List<String> listaComponentesImplementacion) {
        this.listaComponentesImplementacion = listaComponentesImplementacion;
    }

    public List<String> getListaIndicadoresEstructura() {
        return listaIndicadoresEstructura;
    }

    public void setListaIndicadoresEstructura(List<String> listaIndicadoresEstructura) {
        this.listaIndicadoresEstructura = listaIndicadoresEstructura;
    }
    
    public List<String> getListaIndicadoresCapacitacion() {
        return listaIndicadoresCapacitacion;
    }

    public void setListaIndicadoresCapacitacion(List<String> listaIndicadoresCapacitacion) {
        this.listaIndicadoresCapacitacion = listaIndicadoresCapacitacion;
    }
    
    public List<String> getListaIndicadoresMedidas() {
        return listaIndicadoresMedidas;
    }

    public void setListaIndicadoresMedidas(List<String> listaIndicadoresMedidas) {
        this.listaIndicadoresMedidas = listaIndicadoresMedidas;
    }
    
    public List<String> getListaIndicadoresPreparacion() {
        return listaIndicadoresPreparacion;
    }

    public void setListaIndicadoresPreparacion(List<String> listaIndicadoresPreparacion) {
        this.listaIndicadoresPreparacion = listaIndicadoresPreparacion;
    }

    public List<String> getListaIndicadoresContratistas() {
        return listaIndicadoresContratistas;
    }

    public void setListaIndicadoresContratistas(List<String> listaIndicadoresContratistas) {
        this.listaIndicadoresContratistas = listaIndicadoresContratistas;
    }
    
    public List<String> getListaIndicadoresConsulta() {
        return listaIndicadoresConsulta;
    }

    public void setListaIndicadoresConsulta(List<String> listaIndicadoresConsulta) {
        this.listaIndicadoresConsulta = listaIndicadoresConsulta;
    }
    
     //******************************IMPLEMENTACION Y OPERACION *********************************************//

    public List<String> getListaIndicadoresRequisitos() {
        return listaIndicadoresRequisitos;
    }

    public void setListaIndicadoresRequisitos(List<String> listaIndicadoresRequisitos) {
        this.listaIndicadoresRequisitos = listaIndicadoresRequisitos;
    }

    //******************************    EVALUACION NORMATIVA *********************************************//

    
    public List<String> getListaIndicadoresSupervision() {
        return listaIndicadoresSupervision;
    }

    public void setListaIndicadoresSupervision(List<String> listaIndicadoresSupervision) {
        this.listaIndicadoresSupervision = listaIndicadoresSupervision;
    }

    public List<String> getListaIndicadoresSalud() {
        return listaIndicadoresSalud;
    }

    public void setListaIndicadoresSalud(List<String> listaIndicadoresSalud) {
        this.listaIndicadoresSalud = listaIndicadoresSalud;
    }
    
    public List<String> getListaIndicadoresAccidentes() {
        return listaIndicadoresAccidentes;
    }

    public void setListaIndicadoresAccidentes(List<String> listaIndicadoresAccidentes) {
        this.listaIndicadoresAccidentes = listaIndicadoresAccidentes;
    }
    
    public List<String> getListaIndicadoresInvestigacion() {
        return listaIndicadoresInvestigacion;
    }

    public void setListaIndicadoresInvestigacion(List<String> listaIndicadoresInvestigacion) {
        this.listaIndicadoresInvestigacion = listaIndicadoresInvestigacion;
    }
    
    public List<String> getListaIndicadoresControl() {
        return listaIndicadoresControl;
    }

    public void setListaIndicadoresControl(List<String> listaIndicadoresControl) {
        this.listaIndicadoresControl = listaIndicadoresControl;
    }

    public List<String> getListaIndicadoresGestion() {
        return listaIndicadoresGestion;
    }

    public void setListaIndicadoresGestion(List<String> listaIndicadoresGestion) {
        this.listaIndicadoresGestion = listaIndicadoresGestion;
    }
    
    public List<String> getListaIndicadoresAuditoria() {
        return listaIndicadoresAuditoria;
    }

    public void setListaIndicadoresAuditoria(List<String> listaIndicadoresAuditoria) {
        this.listaIndicadoresAuditoria = listaIndicadoresAuditoria;
    }
    
    public List<String> getListaIndicadoresDocumentos() {
        return listaIndicadoresDocumentos;
    }

    public void setListaIndicadoresDocumentos(List<String> listaIndicadoresDocumentos) {
        this.listaIndicadoresDocumentos = listaIndicadoresDocumentos;
    }
    
     //******************************   VERIFICACION*********************************************//

    public List<String> getListaIndicadoresControlDocumentacion() {
        return listaIndicadoresControlDocumentacion;
    }

    public void setListaIndicadoresControlDocumentacion(List<String> listaIndicadoresControlDocumentacion) {
        this.listaIndicadoresControlDocumentacion = listaIndicadoresControlDocumentacion;
    }

    public List<String> getListaIndicadoresGestionRiesgo() {
        return listaIndicadoresGestionRiesgo;
    }

    public void setListaIndicadoresGestionRiesgo(List<String> listaIndicadoresGestionRiesgo) {
        this.listaIndicadoresGestionRiesgo = listaIndicadoresGestionRiesgo;
    }
    
    //******************************  CONTROL DE LA INFORMACION Y DOCUMENTOS *********************************************//

    public List<String> getListaIndicadoresGestionMejora() {
        return listaIndicadoresGestionMejora;
    }

    public void setListaIndicadoresGestionMejora(List<String> listaIndicadoresGestionMejora) {
        this.listaIndicadoresGestionMejora = listaIndicadoresGestionMejora;
    }

}