package pe.com.gp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import oracle.jdbc.OracleTypes;
import pe.com.gp.connection.ConectaDb;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.util.Util;

public class FechaDAO {

    /**
     * Opera sobre los dias de la fecha actual
     *
     * @param tipo 1(yyyy-mm-dd), 2(dd/mm/yyyy), 3(yyyymmdd)
     * @param dias numero de dias a sumar o restar, para el actual enviar 0
     * (cero)
     * @return
     * @throws Exception
     */
    public String operarDiasEnFechaActual(String tipo, int dias) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        String result = "";
        if (cn != null) {
            try {
                cs = cn.prepareCall("{?=call PANAAUTOS.PKG_WEB_CLIENTES.fn_opera_dias_fecha(?,?)}");
                cs.registerOutParameter(1, OracleTypes.VARCHAR);
                cs.setString(2, tipo);
                cs.setInt(3, dias);
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

    /**
     * Opera sobre los meses de la fecha actual
     *
     * @param tipo 1(yyyy-mm-dd), 2(dd/mm/yyyy), 3(yyyymmdd)
     * @param meses numero de meses a sumar o restar, para el actual enviar 0
     * (cero)
     * @return
     * @throws Exception
     */
    public String operarMesesEnFechaActual(String tipo, int meses) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        String result = "";
        if (cn != null) {
            try {
                cs = cn.prepareCall("{?=call PANAAUTOS.PKG_WEB_CLIENTES.fn_opera_meses_fecha(?,?)}");
                cs.registerOutParameter(1, OracleTypes.VARCHAR);
                cs.setString(2, tipo);
                cs.setInt(3, meses);
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

    /**
     * Obtiene el primer dia de un mes, sobre la fecha actual
     *
     * @param tipo 1(yyyy-mm-dd), 2(dd/mm/yyyy), 3(yyyymmdd)
     * @param meses numero de meses a sumar o restar, para el actual enviar 0
     * (cero)
     * @return
     * @throws Exception
     */
    public String obtenerPrimerDiaDeUnMes(String tipo, int meses) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        String result = "";
        if (cn != null) {
            try {
                cs = cn.prepareCall("{?=call PANAAUTOS.PKG_WEB_CLIENTES.fn_primer_dia_mes(?,?)}");
                cs.registerOutParameter(1, OracleTypes.VARCHAR);
                cs.setString(2, tipo);
                cs.setInt(3, meses);
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

    /**
     * Obtiene la fecha actual (sysdate) de acuerdo al formato especificado
     *
     * @param tipo 1(yyyy-mm-dd), 2(dd/mm/yyyy), 4(yyyymmdd)
     * @return fecha actual
     * @throws Exception
     */
    public String obtenerFechaActual() throws Exception {
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        String fecha = "";
        try {
            cs = cn.prepareCall("{?=call uf_fecha_sistema()}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.execute();
            fecha = cs.getString(1);
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(cs);
            Util.close(rs);
        }
        return fecha;
    }

    /**
     * Recibe una fecha y la tienda y devuelve asi: Ejemplo: San Miguel, 09 de
     * marzo del 2016, si no recibe la tienda igual funciona devolviendo solo la
     * fecha
     *
     * @param codigoTienda PA,GR,GT...
     * @param fecha dd/mm/yyyy
     * @return
     * @throws Exception
     */
    public String fechaLiteral(String codigoTienda, String fecha) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        String result = "";
        if (cn != null) {
            try {
                cs = cn.prepareCall("{?=call PANAAUTOS.PKG_UTIL.fn_fecha_literal(?,?)}");
                cs.registerOutParameter(1, OracleTypes.VARCHAR);
                cs.setString(2, codigoTienda);
                cs.setString(3, fecha);
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
}
