package pe.com.gp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import pe.com.gp.connection.ConectaDb;
import oracle.jdbc.OracleTypes;
import pe.com.gp.connection.ConectaHana;
import pe.com.gp.util.Util;

public class TipoCambioDAO {

    //private String NombreTienda = "";
//    public double Retorna_IGV() throws Exception {
//        double ln_igv = 0;
//        Connection conexion = new ConectaDb().connection();
//        CallableStatement cs = null;
//        ResultSet rs = null;
//        long ord_tra = 0;
//        try {
//            cs = conexion.prepareCall("{?=call PANAAUTOS.FN_IGV()}");
//            cs.registerOutParameter(1, OracleTypes.NUMBER);
//            cs.execute();
//            ord_tra = cs.getLong(1);
//            //Obtener los valores
//
//        } catch (SQLException e) {
//        } finally {
//            try {
//                conexion.close(); // libera cn
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
//        return ln_igv;
//    }
//    public double Tipo_cambio(String ls_Fecha) throws Exception {
//        double ln_tc = 0;
//        Connection conexion = new ConectaDb().connection();
//        CallableStatement cs = null;
//        ResultSet rs = null;
//        try {
//            cs = conexion.prepareCall("{?=call PANAAUTOS.fn_tip_Cam(?)}");
//            cs.registerOutParameter(1, OracleTypes.NUMBER);
//            cs.setString(2, ls_Fecha);
//            cs.execute();
//            ln_tc = cs.getDouble(1);
//        } catch (Exception e) {
//            System.out.println("" + e);
//        } finally {
//            try {
//                conexion.close(); // libera cn
//            } catch (Exception e) {
//            }
//            try {
//                if (cs != null) {
//                    cs.close();
//                }
//            } catch (Exception e) {
//            }
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (Exception e) {
//            }
//        }
//        return ln_tc;
//    }
    public double obtieneTipoCambio(String fecha, int valor) throws Exception {
        String fn = "{?=call panaautos.fn_tipo_cambio_SISGP(?,?)}";
        Object[] paramIN = {fecha, valor};
        double result = Util.nullDou(Util.fn_ejecuta(fn, OracleTypes.DOUBLE, paramIN));
        return result;
    }

//    public String nombreTienda(String codigoEmpresa) throws Exception {
//        Connection conexion = new ConectaDb().connection();
//        CallableStatement cs = null;
//        ResultSet rs = null;
//        String nombreTienda = "";
//        try {
//            cs = conexion.prepareCall("{?=call  PANAAUTOS.PKG_GENERALES.FN_NOMBRE_EMP(?)}");
//            cs.registerOutParameter(1, OracleTypes.VARCHAR);
//            cs.setString(2, codigoEmpresa);
//            cs.execute();
//            nombreTienda = cs.getString(1);
//        } catch (Exception e) {
//            System.out.println("" + e);
//        } finally {
//            try {
//                conexion.close(); // libera cn
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
//
//        return nombreTienda;
//    }
//    public void NombreTienda(String ls_empresa) throws Exception {
//
//        Connection conexion = new ConectaDb().connection();
//        CallableStatement cs = null;
//        ResultSet rs = null;
//        try {
//            cs = conexion.prepareCall("{?=call  PANAAUTOS.PKG_GENERALES.FN_NOMBRE_EMP(?)}");
//            cs.registerOutParameter(1, OracleTypes.VARCHAR);
//            cs.setString(2, ls_empresa);
//            cs.execute();
//            //this.setNombreTienda(cs.getString(1));
//
//        } catch (SQLException ex) {
//            // TODO Auto-generated catch block
//            ex.printStackTrace();
//        } finally {
//            try {
//                conexion.close(); // libera cn
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
//    }

    /* public String getNombreTienda() {
     return NombreTienda;
     }

     public void setNombreTienda(String nombreTienda) {
     NombreTienda = nombreTienda;
     }*/
//    public static double getTipCamGPxFecha(String fecha) throws Exception {
//        String fn = "{?=call panaautos.pkg_web_consultas.fn_tipocambio_gp(?)}";
//        Integer paramOUT = OracleTypes.DOUBLE;
//        Object[] paramIN = {fecha};
//        Object obj = Util.fn_ejecuta(fn, paramOUT, paramIN);
//        double result = Util.nullDou(obj);
//        return result;
//    }
    public double getTipoCambio(String fecha) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        double tipoCambio = 0D;

        if (cn != null) {
            try {
                cs = cn.prepareCall("{?=call panaautos.PKG_WEB_CONSULTAS.FN_TIPOCAMBIO_GP(?)}");
                cs.registerOutParameter(1, OracleTypes.NUMBER);
                cs.setString(2, fecha);
                cs.execute();
                tipoCambio = cs.getDouble(1);
            } catch (Exception e) {
                throw new Exception("GP.ERROR: " + e);
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
        }
        return tipoCambio;
    }

    // Obtener el Tipo de Cambio de SAP
    public double obtenerTipoCambioSAP(String moneda, String fecha) throws Exception {
        Connection cn = new ConectaHana().connection();
        Statement stmt = null;
        ResultSet rs = null;
        double result = 0;
        try {
            String sql = "SELECT "
                    + "T0.\"Rate\" "
                    + "FROM \"ORTT\" T0 "
                    + "WHERE TO_VARCHAR(T0.\"RateDate\",'yyyymmdd') = '" + fecha + "' "
                    + "AND T0.\"Currency\"='" + moneda + "'";
            stmt = cn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                result = rs.getDouble("Rate");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(stmt);
        }
        return result;
    }
}
