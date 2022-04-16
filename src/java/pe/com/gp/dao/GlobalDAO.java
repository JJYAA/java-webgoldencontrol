package pe.com.gp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.gp.connection.ConectaHana;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.ConfiguracionDimension;
import pe.com.gp.entity.Global;
import pe.com.gp.util.Util;

public class GlobalDAO {

    private static final Logger LOGGER = LogManager.getLogger();
    
    public Global  getDatosGlobales(String empresa) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Global obj = new Global();      
        try { 
            cs = cn.prepareCall("{call web_datosglobales(?)}");
            cs.setString(1, empresa);
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                    obj.setTipoCambioDolar(rs.getDouble("TIPO_CAMBIO_DIA_DOL"));
                    obj.setFechaActualFormato1(rs.getString("FECHA_ACTUAL_F1"));
                    obj.setFechaActualFormato2(rs.getString("FECHA_ACTUAL_F2"));
                    obj.setFechaActualFormato3(rs.getString("FECHA_ACTUAL_F3"));
                    obj.setIGV(rs.getDouble("IGV"));
                    obj.setNombreEmailEnvAut(rs.getString("nombreEmailEnvAut"));
                    obj.setEmailEnvAut(rs.getString("mailCorreoFrom"));
                    obj.setClaveEmailEnvAut(rs.getString("mailClaveFrom"));                        
                    obj.setExiste(true);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return obj;
    }
    
    
    

    public ConfiguracionDimension configuracionDimensionDefecto(String codTienda, String codArea) throws Exception {
        ConfiguracionDimension obj = new ConfiguracionDimension();
        Connection cn = new ConectaHana().connection();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT "
                + "\"U_COD_TIENDA\","
                + "\"U_CC_UN\","
                + "\"U_CC_Tienda\","
                + "\"U_AREA\","
                + "\"U_ALMACEN\","
                + "\"U_ALM_REP_001\","
                + "\"U_ALM_REP_002\","
                + "\"U_ALM_SER_001\","
                + "\"U_TIPO_DOC_ENTREGA\","
                + "\"U_GLOSA\" "
                + "FROM \"@GP_CONF_DIMENSIONES\" "
                + "WHERE \"U_COD_TIENDA\"='" + codTienda + "' "
                + "AND \"U_AREA\"='" + codArea + "' "
                + "AND \"U_UN_DEFECTO\"='1'";
        if (cn != null) {
            try {
                stmt = cn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    obj.setCodigoTienda(rs.getString("U_COD_TIENDA"));
                    obj.setCentroCostoUniNeg(rs.getString("U_CC_UN"));
                    obj.setCodigoArea(rs.getString("U_AREA"));
                    obj.setCentroCostoTienda(rs.getString("U_CC_Tienda"));
                    obj.setCodigoAlmacenRep001(rs.getString("U_ALM_REP_001"));
                    obj.setCodigoAlmacenRep002(rs.getString("U_ALM_REP_002"));
                    obj.setCodigoAlmacenSer001(rs.getString("U_ALM_SER_001"));
                    obj.setTipoDocEntrega(rs.getString("U_TIPO_DOC_ENTREGA"));
                    obj.setGlosa(rs.getString("U_GLOSA"));
                    obj.setExiste(true);
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                Util.close(cn);
                Util.close(rs);
                Util.close(stmt);
            }
        }
        return obj;
    }

//    public Global GetDatosGlobales() throws Exception {
//        Global global = new Global();
//        Connection cn = new ConectaDb().connection();
//        CallableStatement cs = null;
//        ResultSet rs = null;
//        try {
//            cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.SP_GLOBALES(?)}");
//            cs.registerOutParameter(1, OracleTypes.CURSOR);
//            cs.execute();
//            rs = (ResultSet) cs.getObject(1);
//            while (rs.next()) {
//                global.setTCVta(rs.getDouble("N_I_VENTA"));
//                global.setTCCpra(rs.getDouble("N_I_COMPRA"));
//                global.setFechaSis(rs.getString("FECHA"));
//                global.setFechaSisMasUno(rs.getString("FECHA_MAS_UNO"));
//                global.setHora12(rs.getString("HORA_12"));
//                global.setHora24(rs.getString("HORA_24"));
//                global.setIGV(rs.getDouble("IGV"));
//                global.setISC(rs.getDouble("ISC"));
//                global.setNombreEmailEnvAut(rs.getString("NOM_ENV_AUT"));
//                global.setEmailEnvAut(rs.getString("EMAIL_ENV_AUT"));
//                global.setClaveEmailEnvAut(rs.getString("CLAVE_ENV_AUT"));
//            }
//        } catch (Exception ex) {
//        } finally {
//            try {
//                cn.close(); // libera cn
//            } catch (Exception e) {
//            }
//
//            try {
//                if (cs != null) {
//                    cs.close();
//                }
//            } catch (Exception e) {
//            }
//
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (Exception e) {
//            }
//        }
//        return global;
//    }
}
