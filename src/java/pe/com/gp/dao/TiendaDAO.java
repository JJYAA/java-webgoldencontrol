package pe.com.gp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.com.gp.connection.ConectaDb;
import oracle.jdbc.OracleTypes;
import pe.com.gp.entity.Tienda;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.DtoTienda;
import pe.com.gp.entity.ListaGenerica;
import pe.com.gp.util.Util;

public class TiendaDAO {
    private static final Logger LOGGER = LogManager.getLogger();
    
    public List<ListaGenerica> listaTiendas(String empresa) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        ListaGenerica obj = null;
        List<ListaGenerica> list = new ArrayList();
         String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call web_lista_tiendas(?)}");
            cs.setString(1, empresa);            
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                obj = new ListaGenerica();
                obj.setIndice(rs.getString("indice"));
                obj.setDescripcion(rs.getString("descripcion"));
                list.add(obj);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return list;
    }    
    
    
    public JSONArray tiendasEmpresaJSON(String tituloPersona) {
        LOGGER.info("<==== Inicio Metodo: titulosPersonaJSON (JSONObject) ====>");
        JSONArray jArray = new JSONArray();
        JSONObject jsonObj;
        if (tituloPersona.equals("TPJ")) {
            jsonObj = new JSONObject();
            jsonObj.put("indice", "6");
            jsonObj.put("descripcion", "Registro Unico de Contribuyentes");
            jArray.put(jsonObj);
        } else {
            jsonObj = new JSONObject();
            jsonObj.put("indice", "0");
            jsonObj.put("descripcion", "Otros Tipos De Documentos");
            jArray.put(jsonObj);
            jsonObj = new JSONObject();
            jsonObj.put("indice", "1");
            jsonObj.put("descripcion", "Documento Nacional de Identidad (DNI)");
            jArray.put(jsonObj);
            jsonObj = new JSONObject();
            jsonObj.put("indice", "4");
            jsonObj.put("descripcion", "Carnet de Extranjeria");
            jArray.put(jsonObj);
        }
        return jArray;
    }

    public List<DtoTienda> listaTiendasEmpresa(String rucEmpresa,String empresa) throws Exception {
        DtoTienda tienda = null;
        Connection cn = new ConectaSQL().connection();
        Statement stmt = null;
        ResultSet rs = null;
        List<DtoTienda> list = new ArrayList<>();
        String sql = "select c_c_actividad,c_t_actividad from actividad where c_c_empresa='" + empresa + "' and c_fl_estado='1'";

        if (cn != null) {
            try {
                stmt = cn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    tienda = new DtoTienda();
                    tienda.setCodigo(rs.getString("c_c_actividad"));
                    tienda.setNombre(rs.getString("c_t_actividad"));
                    list.add(tienda);
                }

            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    cn.close(); // libera cn
                } catch (Exception e) {
                }
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
                }
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Exception e) {
                }

            }
        }
        return list;
    }
    
    
    
    public List<DtoTienda> listaTiendasXCodGen(String codGen) throws Exception {
        Connection cn = new ConectaDb().connection();
        ResultSet rs = null;
        Statement stmt = null;
        List<DtoTienda> list = new ArrayList<>();
        String sql = "SELECT "
                + "ceg_cod_emp, "
                + "ceg_nom_emp "
                + "FROM cab_emp_gen "
                + "WHERE ceg_cod_Gen = '" + codGen + "'";
        if (cn != null) {
            try {
                stmt = cn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    DtoTienda tienda = new DtoTienda();
                    tienda.setCodigo(rs.getString("ceg_cod_emp"));
                    tienda.setNombre(rs.getString("ceg_nom_emp"));
                    list.add(tienda);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                Util.close(cn);
                Util.close(rs);
                Util.close(stmt);
            }
        }
        return list;
    }

    
    public Tienda Busca_Codigo_Empresa(String nombreEmp) throws Exception {
        Tienda t = null;
        Connection cn = new ConectaDb().connection();
        Statement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT ceg_cod_cli,ceg_nom_emp "
                + " FROM CAB_EMP_GEN "
                + " WHERE "
                + " TRIM(ceg_nom_emp)='" + nombreEmp.trim() + "'";

        if (cn != null) {
            try {
                stmt = cn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    t = new Tienda();
                    t.setCodigo(rs.getString("ceg_cod_cli"));
                    t.setNombre(rs.getString("ceg_nom_emp"));
                }

            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    cn.close(); // libera cn
                } catch (Exception e) {
                }
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
                }
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Exception e) {
                }

            }
        }
        return t;
    }

    /*public List Item_Empresas() throws Exception {
        ArrayList<ListaGenerica> lista = new ArrayList<>();
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = cn.prepareCall("{call PANAAUTOS.PKG_SERVICIOS_WEB.GetEmpresas(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                ListaGenerica LST = new ListaGenerica();
                LST.setDescripcion(rs.getString("descripcion"));
                LST.setIndice(rs.getString("indice"));
                lista.add(LST);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
        } finally {
            try {
                cn.close(); // libera cn
            } catch (Exception e) {
            }

            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (Exception e) {
            }

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
        }
        return lista;
    }*/
    public String getNombreTienda(String codigo) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        String nombre = "";
        if (cn != null) {
            try {
                cs = cn.prepareCall("{?=call panaautos.pkg_generales.FN_NOMBRE_EMP(?)}");
                cs.registerOutParameter(1, OracleTypes.VARCHAR);
                cs.setString(2, codigo);
                cs.execute();
                nombre = cs.getString(1);
            } catch (Exception e) {
                throw new Exception("GP.ERROR: " + e);
            } finally {
                Util.close(cn);
                Util.close(cs);
                Util.close(rs);
            }
        }
        return nombre;
    }

    public String getAliasTienda(String codigo) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        String alias = "";
        if (cn != null) {
            try {
                cs = cn.prepareCall("{?=call panaautos.pkg_generales.FN_NOMBRE_ALIAS(?)}");
                cs.registerOutParameter(1, OracleTypes.VARCHAR);
                cs.setString(2, codigo);
                cs.execute();
                alias = cs.getString(1);
            } catch (Exception e) {
                throw e;
            } finally {
                Util.close(cn);
                Util.close(cs);
                Util.close(rs);
            }
        }
        return alias;
    }

    public String esTiendaVehiculos(String codigoTienda) throws Exception {
        String fn = "{?=call panaautos.pkg_vehiculos.fn_es_tienda_vehiculos(?)}";
        Integer paramOUT = OracleTypes.VARCHAR;
        Object[] paramIN = {codigoTienda};
        Object obj = Util.fn_ejecuta(fn, paramOUT, paramIN);
        String result = Util.nullCad(obj);
        return result;
    }

    public String esTiendaMotos(String codigoTienda) throws Exception {
        String fn = "{?=call panaautos.pkg_util.fn_es_tienda_motos(?)}";
        Object[] paramIN = {codigoTienda};
        Object obj = Util.fn_ejecuta(fn, OracleTypes.VARCHAR, paramIN);
        String result = Util.nullCad(obj);
        return result;
    }

    public String codigoJefeVtas(String codigoTienda, String tipo) throws Exception {
        String fn = "{?=call panaautos.pkg_generales.fn_codigo_jefeventas(?,?)}";
        Integer paramOUT = OracleTypes.VARCHAR;
        Object[] paramIN = {codigoTienda, tipo};
        Object obj = Util.fn_ejecuta(fn, paramOUT, paramIN);
        String result = Util.nullCad(obj);
        return result;
    }

    public String nombreBrokerSeguros(String codigoTienda) throws Exception {
        ResultSet rs = null;
        Connection cn = new ConectaDb().connection();
        Statement stmt = null;
        String result = "";
        String sql = "SELECT DISTINCT "
                + "trim(b.c_t_razon_social) broker "
                + "FROM cab_emp_gen a "
                + "INNER JOIN proveedor b "
                + "ON TRIM(a.ceg_cod_broker) = TRIM(b.c_c_codigo) "
                + "WHERE ceg_cod_emp = '" + codigoTienda + "'";
        if (cn != null) {
            try {
                stmt = cn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    result = rs.getString("broker");
                }
            } catch (Exception e) {
                throw e;
            } finally {
                Util.close(cn);
                Util.close(rs);
                Util.close(stmt);
            }
        }
        return result;
    }

        
        
    public Tienda  obtenerDatosTienda(String empresa) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Tienda tienda = new Tienda();    
        try { 
            cs = cn.prepareCall("{call web_empresa(?)}");
            cs.setString(1, empresa);
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                tienda.setCodigo(rs.getString("C_C_EMPRESA"));
                tienda.setNombre(rs.getString("EMPRESA"));
                tienda.setDireccion(rs.getString("DIRECCION"));                 
                tienda.setExiste(true);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return tienda;
    }        
   
    public Tienda  obtenerDatosTiendaActividad(String empresa,String Actividad) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Tienda tienda = new Tienda();    
        try { 
            cs = cn.prepareCall("{call relaciones.dbo.web_empresa(?)}");
            cs.setString(1, empresa);
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                tienda.setCodigo(rs.getString("C_C_EMPRESA"));
                tienda.setNombre(rs.getString("EMPRESA"));
                tienda.setDireccion(rs.getString("DIRECCION"));  
                tienda.setActividad(rs.getString("c_c_actividad"));
                tienda.setActividadTienda(rs.getString("c_t_actividad"));
                tienda.setExiste(true);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return tienda;
    }        
       
/*    public Tienda obtenerDatosTienda(String codigoTienda) throws Exception {
    Connection cn = new ConectaSQL().connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Tienda tienda = new Tienda();
        try {
            String sql = "SELECT "
                    + "'05' C_C_EMPRESA,"
                    + " 'AAAAAAAAAAAAAAAA' EMPRESA,'TIENDAAAAAAAAAA' DIRECCION "  ;                  
                    
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                tienda.setCodigo(rs.getString("C_C_EMPRESA"));
                tienda.setNombre(rs.getString("EMPRESA"));
                tienda.setDireccion(rs.getString("DIRECCION"));               
                tienda.setExiste(true);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(ps);
        }
        return tienda;
    }*/

//     public Tienda obtenerDatosTienda(String codEmp) throws Exception {
//        Connection cn = new ConectaDb().connection();
//        Statement stmt = null;
//        ResultSet rs = null;
//        Tienda obj = new Tienda();
//
//        String sql = "SELECT "
//                + "ceg_cod_emp,"
//                + "ceg_nom_emp,"
//                + "ceg_cod_tdp,"
//                + "ceg_cod_gen,"
//                + "ceg_ser_fad,"
//                + "ceg_ser_bad "
//                + "FROM panaautos.cab_emp_gen "
//                + "WHERE ceg_cod_emp = '" + codEmp + "'";
//
//        if (cn != null) {
//            try {
//                stmt = cn.createStatement();
//                rs = stmt.executeQuery(sql);
//                while (rs.next()) {
//                    obj.setCodigo(rs.getString("ceg_cod_emp"));
//                    obj.setNombre(rs.getString("ceg_nom_emp"));
//                    obj.setCodTDP(rs.getString("ceg_cod_tdp"));
//                    obj.setCodGen(rs.getString("ceg_cod_gen"));
//                    obj.setContaSerieBAD(rs.getLong("ceg_ser_bad"));
//                    obj.setContaSerieFAD(rs.getLong("ceg_ser_fad"));
//                    obj.setExiste(true);
//                }
//
//            } catch (Exception e) {
//                LOGGER.error("GP.ERROR: {}", e);
//            } finally {
//                try {
//                    cn.close(); // libera cn
//                } catch (Exception e) {
//                }
//                try {
//                    if (rs != null) {
//                        rs.close();
//                    }
//                } catch (Exception e) {
//                }
//                try {
//                    if (stmt != null) {
//                        stmt.close();
//                    }
//                } catch (Exception e) {
//                }
//
//            }
//        }
//        return obj;
//    }
    
    public String esTienda(String codigo) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        String result = "N";
        if (cn != null) {
            try {
                cs = cn.prepareCall("{?=call panaautos.pkg_generales.fn_es_tienda(?)}");
                cs.registerOutParameter(1, OracleTypes.VARCHAR);
                cs.setString(2, codigo);
                cs.execute();
                result = cs.getString(1);
            } catch (Exception e) {
                throw e;
            } finally {
                Util.close(cn);
                Util.close(cs);
                Util.close(rs);
            }
        }
        return result;
    }

    public List<ListaGenerica> listaTiendasEmpresa2(String rucEmpresa,String empresa) throws Exception {
        ArrayList<ListaGenerica> listaTiendas = new ArrayList<>();
        Connection cn = new ConectaSQL().connection();
        Statement stmt = null;
        ResultSet rs = null;
        List<DtoTienda> list = new ArrayList<>();
        String sql = "select c_c_actividad,c_t_actividad from actividad where c_c_empresa='" + empresa + "' and c_fl_estado='1'";

        if (cn != null) {
            try {
                stmt = cn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                     ListaGenerica obj = new ListaGenerica();
                     obj.setIndice(rs.getString("c_c_actividad"));
                     obj.setDescripcion(rs.getString("c_t_actividad"));
                    listaTiendas.add(obj);
                }

            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    cn.close(); // libera cn
                } catch (Exception e) {
                }
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
                }
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Exception e) {
                }

            }
        }
        return listaTiendas;
    }

}
