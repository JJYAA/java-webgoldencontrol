package pe.com.gp.dao;

import pe.com.gp.connection.ConectaDb;
import pe.com.gp.entity.Parte;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.gp.connection.ConectaHana;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.BeanTotales;

import pe.com.gp.util.Util;

public class ParteDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    public String anulaDetalleProformaTEMPO(long secuencia, String codigo, String tienda, String tipo, String item) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        String result = null;
        try {
            cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.ANULA_DET_PROF(?,?,?,?,?)}");
            cs.setLong(1, secuencia);
            cs.setString(2, codigo);
            cs.setString(3, tienda);
            cs.setString(4, tipo);
            cs.setString(5, item);
            cs.executeUpdate();
        } catch (Exception e) {
            result = e.getMessage();
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
        }
        return result;
    }

    public Parte BuscaParteTempo(long secuencia, String tienda, String codigo, String moneda, String tipo) throws Exception {
        Parte parte = null;
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {

            cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.LISTA_DET_PROF(?,?,?,?,?)}");
            cs.setLong(1, secuencia);
            cs.setString(2, tienda);
            cs.setString(3, codigo);
            cs.setString(4, tipo);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(5);
            while (rs.next()) {
                parte = new Parte();
                parte.setCodigo(rs.getString("COD_PARTE"));
                parte.setDescripcion(rs.getString("DESCRIPCION1"));
                parte.setCAL_cantidad(rs.getInt("CANTIDAD"));
                parte.setCAL_descuento(rs.getDouble("DESCUENTO"));
                parte.setIgv(rs.getDouble("IMPUESTO"));
                parte.setVVPSol(rs.getDouble("VVP_SOL_UNI"));
                parte.setVVPDol(rs.getDouble("VVP_DOL_UNI"));
                parte.setVVDSol(rs.getDouble("VVD_SOL_UNI"));
                parte.setVVDDol(rs.getDouble("VVD_DOL_UNI"));
                if ("S".equals(moneda)) {
                    parte.setCAL_TotBruto_Sol(rs.getDouble("COSTO_UNI_SOL"));
                    parte.setCAL_TotDscto_Sol(rs.getDouble("COSTO_UNI_DOL"));
                    parte.setCAL_TotVtaSol(rs.getDouble("COSTO_TOT_SOL"));
                    parte.setCAL_TotIGV_Sol(rs.getDouble("COSTO_TOT_DOL"));
                    parte.setCAL_TotGralVtaSol(rs.getDouble("IGV_UNI_SOL"));

                } else {
                    parte.setCAL_TotBruto_Dol(rs.getDouble("COSTO_UNI_SOL"));
                    parte.setCAL_TotDscto_Dol(rs.getDouble("COSTO_UNI_DOL"));
                    parte.setCAL_TotVtaDol(rs.getDouble("COSTO_TOT_SOL"));
                    parte.setCAL_TotIGV_Dol(rs.getDouble("COSTO_TOT_DOL"));
                    parte.setCAL_TotGralVtaDol(rs.getDouble("IGV_UNI_SOL"));
                }
                parte.setUbicacion(rs.getString("OTRO1"));
                parte.setAccion("U");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
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

        return parte;
    }

    public List<Parte> ListaDetalleProformaTEMPO(long secuencia, String tienda, String codigo, String moneda, String tipo) throws Exception {
        List<Parte> lista = new ArrayList<>();
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.LISTA_DET_PROF(?,?,?,?,?)}");
            cs.setLong(1, secuencia);
            cs.setString(2, tienda);
            cs.setString(3, codigo);
            cs.setString(4, tipo);
            cs.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(5);
            while (rs.next()) {
                Parte parte = new Parte();
                parte.setCodigo(rs.getString("COD_PARTE"));
                parte.setDescripcion(rs.getString("DESCRIPCION1"));
                parte.setCAL_cantidad(rs.getInt("CANTIDAD"));
                parte.setCAL_descuento(rs.getDouble("DESCUENTO"));
                parte.setIgv(rs.getDouble("IMPUESTO"));
                parte.setVVPSol(rs.getDouble("VVP_SOL_UNI"));
                parte.setVVPDol(rs.getDouble("VVP_DOL_UNI"));
                parte.setVVDSol(rs.getDouble("VVD_SOL_UNI"));
                parte.setVVDDol(rs.getDouble("VVD_DOL_UNI"));
                if ("S".equals(moneda)) {
                    parte.setCAL_TotBruto_Sol(rs.getDouble("COSTO_UNI_SOL"));
                    parte.setCAL_TotDscto_Sol(rs.getDouble("COSTO_UNI_DOL"));
                    parte.setCAL_TotVtaSol(rs.getDouble("COSTO_TOT_SOL"));
                    parte.setCAL_TotIGV_Sol(rs.getDouble("COSTO_TOT_DOL"));
                    parte.setCAL_TotGralVtaSol(rs.getDouble("IGV_UNI_SOL"));

                } else {
                    parte.setCAL_TotBruto_Dol(rs.getDouble("COSTO_UNI_SOL"));
                    parte.setCAL_TotDscto_Dol(rs.getDouble("COSTO_UNI_DOL"));
                    parte.setCAL_TotVtaDol(rs.getDouble("COSTO_TOT_SOL"));
                    parte.setCAL_TotIGV_Dol(rs.getDouble("COSTO_TOT_DOL"));
                    parte.setCAL_TotGralVtaDol(rs.getDouble("IGV_UNI_SOL"));
                }
                parte.setUbicacion(rs.getString("OTRO1"));
                parte.setItem(rs.getString("NUM_ITEM"));
                lista.add(parte);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
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
    }

    // inserta repuestos de la proforma consultada, en el tempo de la secuencia en sesion
    public String InsertaProformaRepuestoTEMPO(long secuencia, long proforma, String tienda, double TCambio, String moneda, double TCambioPro) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        String result = null;
        if (cn != null) {
            try {
                cs = cn.prepareCall("{CALL panaautos.PKG_WEB_TALLER_MOVIL.INS_PROF_REPUESTO(?,?,?,?,?,?)}");
                cs.setLong(1, secuencia);
                cs.setString(2, tienda);
                cs.setLong(3, proforma);
                cs.setDouble(4, TCambio);
                cs.setString(5, moneda);
                cs.setDouble(6, TCambioPro);
                cs.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
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
            }
        }
        return result;
    }

    public String InsertaDetalleProformaTEMPO(long secuencia, String tienda, Parte parte, String moneda) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        String result = null;
        if (cn != null) {
            try {
                cs = cn.prepareCall("{CALL panaautos.PKG_WEB_TALLER_MOVIL.INS_UPD_PROF(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setLong(1, secuencia);
                cs.setString(2, tienda);
                cs.setString(3, parte.getCodigo());
                cs.setString(4, parte.getDescripcion());
                cs.setInt(5, parte.getCAL_cantidad());
                cs.setDouble(6, parte.getCAL_descuento());
                cs.setDouble(7, parte.getIgv());
                cs.setDouble(8, parte.getVVPSol());
                cs.setDouble(9, parte.getVVPDol());
                cs.setDouble(10, parte.getVVDSol());
                cs.setDouble(11, parte.getVVDDol());
                cs.setString(12, parte.getAccion());
                if ("S".equals(moneda)) {
                    cs.setDouble(13, parte.getCAL_TotBruto_Sol());          //Liquido
                    cs.setDouble(14, parte.getCAL_TotDscto_Sol());          // Dscto 
                    cs.setDouble(15, parte.getCAL_TotVtaSol());             // Neto
                    cs.setDouble(16, parte.getCAL_TotIGV_Sol());            // Igv
                    cs.setDouble(17, parte.getCAL_TotGralVtaSol());         // Total
                } else {
                    cs.setDouble(13, parte.getCAL_TotBruto_Dol());
                    cs.setDouble(14, parte.getCAL_TotDscto_Dol());
                    cs.setDouble(15, parte.getCAL_TotVtaDol());
                    cs.setDouble(16, parte.getCAL_TotIGV_Dol());
                    cs.setDouble(17, parte.getCAL_TotGralVtaDol());
                }
                cs.setString(18, "UBICACION");
                cs.setString(19, parte.getTipo());
                cs.setString(20, "");
                cs.setString(21, "");
                cs.setString(22, "");
                cs.setInt(23, parte.getSecuencia());
                cs.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
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
            }
        }
        return result;
    }

    /* METODOS DE GENERACION DE PEDIDO DE REPUESTOS*/
    public Parte BuscaPartePedRepTempo(long secuencia, String tienda, String codigo, String moneda) throws Exception {
        Parte parte = null;
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.lista_det_ped_rep(?,?,?,?)}");
            cs.setLong(1, secuencia);
            cs.setString(2, tienda);
            cs.setString(3, codigo);
            cs.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(4);
            while (rs.next()) {
                parte = new Parte();
                parte.setCodigo(rs.getString("CODIGO"));
                parte.setDescripcion(rs.getString("DESCRIPCION"));
                parte.setCAL_cantidad(rs.getInt("CANTIDAD"));
                parte.setCAL_descuento(rs.getDouble("DSCTO"));
                parte.setIgv(rs.getDouble("IMPUESTO"));
                parte.setVVPSol(rs.getDouble("PRECIO_SOL"));
                parte.setVVPDol(rs.getDouble("PRECIO_DOL"));
                parte.setVVDSol(rs.getDouble("COSTO_SOL"));
                parte.setVVDDol(rs.getDouble("COSTO_DOL"));
                parte.setCAL_TotBruto_Sol(rs.getDouble("BRUTO_SOL"));
                parte.setCAL_TotDscto_Sol(rs.getDouble("TOT_DSCTO_SOL"));
                parte.setCAL_TotVtaSol(rs.getDouble("TOT_VTA_SOL"));
                parte.setCAL_TotIGV_Sol(rs.getDouble("TOT_IGV_SOL"));
                parte.setCAL_TotGralVtaSol(rs.getDouble("TOT_GEN_SOL"));
                parte.setCAL_TotBruto_Dol(rs.getDouble("BRUTO_DOL"));
                parte.setCAL_TotDscto_Dol(rs.getDouble("TOT_DSCTO_DOL"));
                parte.setCAL_TotVtaDol(rs.getDouble("TOT_VTA_DOL"));
                parte.setCAL_TotIGV_Dol(rs.getDouble("TOT_IGV_DOL"));
                parte.setCAL_TotGralVtaDol(rs.getDouble("TOT_GEN_DOL"));
                parte.setUbicacion(rs.getString("UBICACION"));
                parte.setItem(rs.getString("NUM_ITEM"));
                parte.setAccion("U");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
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

        return parte;
    }

    public BeanTotales LeeTotalesPedRep(long secuencia) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        BeanTotales bt = null;
        try {
            cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.lista_totales_ped_rep(?,?)");
            cs.setLong(1, secuencia);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(2);
            while (rs.next()) {
                bt = new BeanTotales();

                bt.setTotalCostoSol(rs.getDouble("TotalCostoSol"));
                bt.setTotDctoSol(rs.getDouble("TotDctoSol"));
                bt.setTotIgvSol(rs.getDouble("TotIgvSol"));
                bt.setBrutoSol(bt.getTotalCostoSol() + bt.getTotDctoSol());
                bt.setTotNetoSol(bt.getBrutoSol() - bt.getTotDctoSol());
                bt.setTotGenSol(bt.getTotNetoSol() + bt.getTotIgvSol());

                bt.setTotalCostoDol(rs.getDouble("TotalCostoDol"));
                bt.setTotDctoDol(rs.getDouble("TotDctoDol"));
                bt.setTotIgvDol(rs.getDouble("TotIgvDol"));
                bt.setBrutoDol(bt.getTotalCostoDol() + bt.getTotDctoDol());
                bt.setTotNetoDol(bt.getBrutoDol() - bt.getTotDctoDol());
                bt.setTotGenDol(bt.getTotNetoDol() + bt.getTotIgvDol());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        return bt;
    }

    public List<Parte> ListaDetallePedidoTEMPO(long secuencia, String tienda, String codigo, String moneda) throws Exception {
        List<Parte> lista = new ArrayList<>();
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.lista_det_ped_rep(?,?,?,?)}");
            cs.setLong(1, secuencia);
            cs.setString(2, tienda);
            cs.setString(3, codigo);
            cs.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(4);
            while (rs.next()) {
                Parte parte = new Parte();
                parte.setCodigo(rs.getString("CODIGO"));
                parte.setDescripcion(rs.getString("DESCRIPCION"));
                parte.setCAL_cantidad(rs.getInt("CANTIDAD"));
                parte.setCAL_descuento(rs.getDouble("DSCTO"));
                parte.setIgv(rs.getDouble("IMPUESTO"));
                parte.setVVPSol(rs.getDouble("PRECIO_SOL"));
                parte.setVVPDol(rs.getDouble("PRECIO_DOL"));
                parte.setVVDSol(rs.getDouble("COSTO_SOL"));
                parte.setVVDDol(rs.getDouble("COSTO_DOL"));
                parte.setCAL_TotBruto_Sol(rs.getDouble("BRUTO_SOL"));
                parte.setCAL_TotDscto_Sol(rs.getDouble("TOT_DSCTO_SOL"));
                parte.setCAL_TotVtaSol(rs.getDouble("COSTO_ACUM_SOL")); // TOT_VTA_SOL
                parte.setCAL_TotIGV_Sol(rs.getDouble("TOT_IGV_SOL"));
                parte.setCAL_TotGralVtaSol(rs.getDouble("TOT_GEN_SOL"));
                parte.setCAL_TotBruto_Dol(rs.getDouble("BRUTO_DOL"));
                parte.setCAL_TotDscto_Dol(rs.getDouble("TOT_DSCTO_DOL"));
                parte.setCAL_TotVtaDol(rs.getDouble("COSTO_ACUM_DOL")); // TOT_VTA_DOL
                parte.setCAL_TotIGV_Dol(rs.getDouble("TOT_IGV_DOL"));
                parte.setCAL_TotGralVtaDol(rs.getDouble("TOT_GEN_DOL"));
                parte.setUbicacion(rs.getString("UBICACION"));
                parte.setItem(rs.getString("NUM_ITEM"));
                lista.add(parte);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
    }

    public String InsertaDetallePedidoTEMPO(long secuencia, String tienda, Parte parte, String moneda, double TCambio) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        String result = null;
        if (cn != null) {
            try {
                cs = cn.prepareCall("{CALL panaautos.PKG_WEB_TALLER_MOVIL.INS_UPD_PED_REP(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setLong(1, secuencia);
                cs.setString(2, "PE");
                cs.setString(3, tienda);
                cs.setString(4, parte.getCodigo());
                cs.setString(5, parte.getDescripcion());
                cs.setInt(6, parte.getCAL_cantidad());
                cs.setDouble(7, parte.getCAL_descuento());
                cs.setDouble(8, parte.getIgv());
                cs.setDouble(9, parte.getVVPSol());
                cs.setDouble(10, parte.getVVPDol());
                cs.setDouble(11, parte.getVVDSol());
                cs.setDouble(12, parte.getVVDDol());
                cs.setDouble(13, parte.getCAL_TotVtaSol());
                cs.setDouble(14, parte.getCAL_TotVtaDol());
                cs.setDouble(15, parte.getCAL_TotGralVtaSol());
                cs.setDouble(16, parte.getCAL_TotGralVtaDol());
                cs.setDouble(17, TCambio);
                cs.setDouble(18, parte.getCAL_TotDscto_Sol());
                cs.setDouble(19, parte.getCAL_TotDscto_Dol());
                cs.setDouble(20, parte.getCAL_TotIGV_Sol());
                cs.setDouble(21, parte.getCAL_TotIGV_Dol());
                cs.setString(22, parte.getObservado());
                cs.setString(23, parte.getUbicacion());
                cs.setDouble(24, parte.getCAL_TotBruto_Sol());
                cs.setDouble(25, parte.getCAL_TotBruto_Dol());
                cs.setString(26, parte.getTipoDescuento());
                cs.setString(27, parte.getAccion());
                cs.setString(28, parte.getItem());
                cs.executeUpdate();
            } catch (Exception e) {
                result = e.getMessage();
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
            }

        }
        return result;
    }

    public String anulaDetallePedRepTEMPO(long secuencia, String codigo, String tienda, String item) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        String result = null;
        try {
            cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.ANULA_DET_PED_REP(?,?,?,?)}");
            cs.setLong(1, secuencia);
            cs.setString(2, codigo);
            cs.setString(3, tienda);
            cs.setString(4, item);
            cs.executeUpdate();
        } catch (Exception e) {
            result = e.getMessage();
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
        }
        return result;
    }

    public int VerificaInventario(String codigo_emp, String codigo_rep, String codigo_alm) throws Exception {
        int inventario = 0;
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = cn.prepareCall("{?=call PANAAUTOS.PKG_WEB_TALLER_MOVIL.VERIFICA_INVENTARIO(?,?,?)}");
            cs.registerOutParameter(1, OracleTypes.INTEGER);
            cs.setString(2, codigo_emp);
            cs.setString(3, codigo_rep);
            cs.setString(4, codigo_alm);
            cs.execute();
            inventario = cs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        return inventario;
    }

    public Parte ConsultaParteVtas(String codigo, String tienda) throws Exception {
        Parte repuesto = null;
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.BUSCAPARTE_VTAS(?,?,?)}");
            cs.setString(1, codigo);
            cs.setString(2, tienda);
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(3);
            while (rs.next()) {
                repuesto = new Parte();
                repuesto.setCostoProm(rs.getDouble("SRG_ULT_COS"));
                repuesto.setUltCosto(rs.getDouble("SRG_UCOS_PRO"));
                repuesto.setUltFecSal(rs.getString("srg_ult_sal"));
                repuesto.setUltFecIng(rs.getString("srg_ult_ing"));
                repuesto.setStkTdp(rs.getInt("SRG_STK_TDP"));
                repuesto.setComMes(rs.getInt("ComMes"));
                repuesto.setVtaMes(rs.getInt("VtaMes"));
                repuesto.setVenta1M(rs.getInt("Vta1Mes"));
                repuesto.setVenta2M(rs.getInt("Vta2Mes"));
                repuesto.setVenta3M(rs.getInt("Vta3Mes"));
                repuesto.setVenta4M(rs.getInt("Vta4Mes"));
                repuesto.setVenta5M(rs.getInt("Vta5Mes"));
                repuesto.setVenta6M(rs.getInt("Vta6Mes"));
            }
            rs.close();
            cs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

        return repuesto;
    }

    // OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO
    // OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO
    // OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO
    // @author msilva   
    // *********************
    // Comentario de Alex: Por favor no hacer este tipo de metodos, debe hacerse por separado, no todo en uno solo. Gracias.
    // NO invocar a 3 stored en un solo metodo.
    // Gracias.
    // **********************
    // OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO
    // OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO
    // OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO, OJO
    public Parte BuscaParte(int tipoConsulta, String codigo, String tienda) throws Exception {
        Parte repuesto = null;

        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.sp_existe_repuesto(?,?,?)}");
            cs.setInt(1, tipoConsulta);
            cs.setString(2, codigo);
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(3);
            while (rs.next()) {
                repuesto = new Parte();
                repuesto.setCodigo(rs.getString("LPR_COD_PRO"));
                repuesto.setDescripcion(rs.getString("LPR_DES_PRO"));
                repuesto.setVVPSol(rs.getDouble("LPR_VVP_SOL"));
                repuesto.setVVDSol(rs.getDouble("LPR_VVD_SOL"));

                if ("0".equals(rs.getString("lpr_VVP_Dol"))) {
                    repuesto.setVVPDol(rs.getDouble("VVP_DOLARES"));
                } else {
                    repuesto.setVVPDol(rs.getDouble("lpr_VVP_Dol"));
                }
                if ("0".equals(rs.getString("lpr_VVD_Dol"))) {
                    repuesto.setVVDDol(rs.getDouble("VVD_DOLARES"));
                } else {
                    repuesto.setVVDDol(rs.getDouble("lpr_VVD_Dol"));
                }

                repuesto.setIgv(rs.getDouble("LPR_IGV_PRO"));
                repuesto.setCodProcedencia(rs.getString("LPR_PRO_PRO"));
                repuesto.setDsctoMaximo(rs.getDouble("LPR_DES_MAX"));
                repuesto.setDsctoProducto(rs.getDouble("LPR_DES_MO"));
                repuesto.setDsctoTaller(rs.getDouble("LPR_DES_OT"));
                repuesto.setAnulado(rs.getString("LPR_ANU_SN"));
                repuesto.setAccion("I");
                repuesto.setEsObsequio(rs.getString("LPR_OBS_SN"));
                /*if (rs.getString("LPR_OBS_SN").equals("S")) {
                    repuesto.setBolObsequio(true);
                } else {
                    repuesto.setBolObsequio(false);
                }*/
            }
            rs.close();
            cs.close();

            if (repuesto != null) {
                // Datos Stock
                cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.SP_BUSCA_STOCK(?,?,?)}");
                cs.setString(1, tienda);
                cs.setString(2, codigo);
                cs.registerOutParameter(3, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(3);
                while (rs.next()) {
                    repuesto.setStkAlm(rs.getInt("STK_ALM"));
                    repuesto.setUbicacion(rs.getString("UBICACION"));
                    repuesto.setUltFecIng(rs.getString("SRG_ULT_ING"));
                    repuesto.setUltCosto(rs.getDouble("SRG_UCOS_PRO"));
                    repuesto.setPedPendiente(rs.getString("OrdComPen"));
                    repuesto.setSepPendiente(rs.getString("SepPen"));
                }
                rs.close();
                cs.close();
                // Verifica Stock
                int STK_DIS;
                cs = cn.prepareCall("{?=call PANAAUTOS.FN_VERIFICA_STOCK(?,?,?,?)}");
                cs.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                cs.setString(2, tienda);
                cs.setString(3, codigo);
                cs.setInt(4, 0);
                cs.setInt(5, 0);
                cs.execute();
                STK_DIS = cs.getInt(1);
                if (STK_DIS < 0) {
                    STK_DIS = 0;
                }
                repuesto.setStkDis(STK_DIS);
                rs.close();
                cs.close();
                // Busca Descuentos
                cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.SP_BUSCA_DSCTO_RPTO(?,?)}");
                cs.setString(1, codigo);
                cs.registerOutParameter(2, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(2);
                while (rs.next()) {
                    repuesto.setTipoDscto(rs.getString("FDC_TIP_DES"));
                    repuesto.setDsctoMO(rs.getInt("DSCTO_MO"));
                    repuesto.setDsctoGP(rs.getInt("DSCTO_GP"));
                    repuesto.setDsctoTDP(rs.getInt("DSCTO_TDP"));
                }
                rs.close();
                cs.close();
                // Es accesorio importado
                repuesto.setAccImp("N");
                cs = cn.prepareCall("{call PANAAUTOS.pkg_repuestos.sp_Get_Datos_Accesorios(?,?)}");
                cs.setString(1, codigo);
                cs.registerOutParameter(2, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(2);
                while (rs.next()) {
                    repuesto.setAccImp("S");
                }
                rs.close();
                cs.close();

            }
        } catch (Exception e) {

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

        return repuesto;

    }

    // Busca un repuesto en LIS_PRE_REP
    public Parte buscaParteLPR(String empresa, String codParte) throws Exception {
        LOGGER.info("<==== Inicio Metodo: buscaParteLPR ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Parte obj = new Parte();
        try {
            cs = cn.prepareCall("{call web_existe_repuesto(?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, codParte);
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                obj.setCodigo(rs.getString("c_c_codigo_prod"));
                obj.setDescripcion(rs.getString("c_t_codigo_prod"));
                obj.setVVPSol(rs.getDouble("vvp_soles"));
                obj.setVVPDol(rs.getDouble("vvp_dolar"));
                obj.setMasPrecios(rs.getString("c_c_mas_precio"));
                obj.setAnulado(rs.getString("c_fl_estado"));
                obj.setCajas(rs.getString("cajas"));
                obj.setVvp(rs.getDouble("vvp"));
                obj.setTotal(rs.getDouble("disponible"));
                obj.setDisponible(rs.getDouble("disponible") );
                obj.setExiste(true);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(cs);
        }
        LOGGER.info("<==== Fin Metodo: buscaParteLPR ====>");
        return obj;
    }

//    public Parte buscaParteStock(String codEmp, String codParte) throws Exception {
//        LOGGER.info("<==== Inicio Metodo: buscaParteStock ====>");
//        Parte obj = new Parte();
//        String sp = "panaautos.pkg_web_taller_movil.sp_busca_stock(?,?,?)";
//        Object[] paramIN = {codEmp, codParte};
//        ResultSet rs = Util.sp_consulta(sp, paramIN);
//        while (rs.next()) {
//            obj.setStkAlm(rs.getInt("stk_alm"));
//            obj.setUbicacion(rs.getString("ubicacion"));
//            obj.setUltFecIng(rs.getString("srg_ult_ing"));
//            obj.setUltCosto(rs.getDouble("srg_ucos_pro"));
//            obj.setPedPendiente(rs.getString("OrdComPen"));
//            obj.setSepPendiente(rs.getString("SepPen"));
//            obj.setExiste(true);
//        }
//        LOGGER.info("<==== Fin Metodo: buscaParteStock ====>");
//        return obj;
//    }
    public int verificaParteStock(String codEmp, String codParte) throws Exception {
        LOGGER.info("<==== Inicio Metodo: buscaParteStock ====>");
        String fn = "{?=call panaautos.fn_verifica_stock(?,?,?,?)}";
        Integer paramOUT = OracleTypes.INTEGER;
        Object[] paramIN = {codEmp, codParte, 0, 0};
        Object obj = Util.fn_ejecuta(fn, paramOUT, paramIN);
        int result = Util.nullNum(obj) < 0 ? 0 : Util.nullNum(obj);
        LOGGER.info("<==== Fin Metodo: buscaParteStock ====>");
        return result;
    }

    // Descuento por Canal o Cliente
    public Double buscaParteDsctosCC(String codTienda, String codCanal, String codParte, String codCliente, String moneda) throws Exception {
        LOGGER.info("<==== Inicio Metodo: buscaParteDsctosCC ====>");
        Connection cn = new ConectaDb().connection();
        double porcentaje = 0;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = cn.prepareCall("{?=call panaautos.pkg_repuestos.fn_porcentaje_descuento_sap(?,?,?,?,?)}");
            cs.registerOutParameter(1, OracleTypes.DOUBLE);
            cs.setString(2, codTienda);
            cs.setString(3, codCanal);
            cs.setString(4, codParte);
            cs.setString(5, codCliente);
            cs.setString(6, moneda);
            cs.execute();
            porcentaje = cs.getDouble(1);
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(cs);
        }
        LOGGER.info("<==== Fin Metodo: buscaParteDsctosCC ====>");
        return porcentaje;
    }

    // Descuento por Canal o Cliente
    public Double buscaParteDsctosCC2(String codTienda, String codCanal, String codParte, Double Precio, String codCliente, String moneda) throws Exception {
        LOGGER.info("<==== Inicio Metodo: buscaParteDsctosCC2 ====>");
        Connection cn = new ConectaDb().connection();
        double porcentaje = 0;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = cn.prepareCall("{?=call panaautos.pkg_repuestos.fn_porcentaje_dscto2(?,?,?,?,?,?)}");
            cs.registerOutParameter(1, OracleTypes.DOUBLE);
            cs.setString(2, codTienda);
            cs.setString(3, codCanal);
            cs.setString(4, codParte);
            cs.setDouble(5, Precio);
            cs.setString(6, codCliente);
            cs.setString(7, moneda);
            cs.execute();
            porcentaje = cs.getDouble(1);
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(cs);
        }
        LOGGER.info("<==== Fin Metodo: buscaParteDsctosCC2 ====>");
        return porcentaje;
    }

    public Parte buscaParteDsctos(String codParte) throws Exception {
//        LOGGER.info("<==== Inicio Metodo: buscaParteDsctos ====>");
//        Parte obj = new Parte();
//        String sp = "panaautos.pkg_web_taller_movil.sp_busca_dscto_rpto(?,?)";
//        Object[] paramIN = {codParte};
//        ResultSet rs = Util.sp_consulta(sp, paramIN);
//        while (rs.next()) {
//            obj.setTipoDscto(rs.getString("fdc_tip_des"));
//            obj.setDsctoMO(rs.getInt("dscto_mo"));
//            obj.setDsctoGP(rs.getInt("dscto_gp"));
//            obj.setDsctoTDP(rs.getInt("dscto_tdp"));
//            obj.setExiste(true);
//        }
//        LOGGER.info("<==== Fin Metodo: buscaParteDsctos ====>");
//        return obj;
        LOGGER.info("<==== Inicio Metodo: buscaParteDsctos ====>");
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Parte obj = new Parte();
        try {
            cs = cn.prepareCall("{call panaautos.pkg_web_taller_movil.sp_busca_dscto_rpto(?,?)}");
            cs.setString(1, codParte);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(2);
            while (rs.next()) {
                obj.setTipoDscto(rs.getString("fdc_tip_des"));
                obj.setDsctoMO(rs.getInt("dscto_mo"));
                obj.setDsctoGP(rs.getInt("dscto_gp"));
                obj.setDsctoTDP(rs.getInt("dscto_tdp"));
                obj.setExiste(true);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(cs);
        }
        LOGGER.info("<==== Fin Metodo: buscaParteDsctos ====>");
        return obj;
    }

    public String esAccesorioImportado(String codParte) throws Exception {
//        LOGGER.info("<==== Inicio Metodo: esAccesorioImportado ====>");
//        String result = "N";
//        String sp = "panaautos.pkg_repuestos.sp_get_datos_accesorios(?,?)";
//        Object[] paramIN = {codParte};
//        ResultSet rs = Util.sp_consulta(sp, paramIN);
//        while (rs.next()) {
//            result = "S";
//        }
//        LOGGER.info("<==== Fin Metodo: esAccesorioImportado ====>");
//        return result;
        LOGGER.info("<==== Inicio Metodo: esAccesorioImportado ====>");
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        String result = "N";
        try {
            cs = cn.prepareCall("{call panaautos.pkg_repuestos.sp_get_datos_accesorios(?,?)}");
            cs.setString(1, codParte);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(2);
            while (rs.next()) {
                result = "S";
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(cs);
        }
        LOGGER.info("<==== Fin Metodo: esAccesorioImportado ====>");
        return result;
    }

    public String insertaParteEnTempo(
            int intChk,
            long numSeq,
            String tipDoc,
            String codEmp,
            String codPro,
            String desPro,
            int cantidad,
            double dscto,
            double igv,
            double precioSol,
            double precioDol,
            double costoSol,
            double costoDol,
            double totVtaSol,
            double totVtaDol,
            double totGralVtaSol,
            double totGralVtaDol,
            double totGralCpraSol,
            double totGralCpraDol,
            double tipoCambio,
            double totDctoSol,
            double totDctoDol,
            double totIgvSol,
            double totIgvDol,
            String observacion,
            String ubicacion,
            int numItem
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: insertaParteEnTempo ====>");
        String sp = "{call panaautos.sp_verifica_inserta_stock_new(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        Object[] paramIN = {
            intChk, numSeq, tipDoc, codEmp, codPro, desPro, cantidad, dscto, igv, precioSol, precioDol,
            costoSol, costoDol, totVtaSol, totVtaDol, totGralVtaSol, totGralVtaDol,
            totGralCpraSol, totGralCpraDol, tipoCambio, totDctoSol, totDctoDol,
            totIgvSol, totIgvDol, observacion, ubicacion, numItem
        };
        String result = Util.nullCad(Util.sp_ejecuta(sp, null, paramIN));
        LOGGER.info("<==== Fin Metodo: insertaParteEnTempo ====>");
        return result;
    }

    public String procesaCalculos(String empresa,long seqTmp2) throws Exception {
        LOGGER.info("<==== Inicio Metodo: procesaCalculos ====>");
        String sp = "{call web_procesa_calculos(?,?)}";
        Object[] paramIN = {empresa, seqTmp2}; // el tercer parametro no es usado dentro del sp
        String result = Util.nullCad(Util.sp_ejecuta(sp, null, paramIN));
        LOGGER.info("<==== Fin Metodo: procesaCalculos ====>");
        return result;
    }

    public double validaDsctoAccesorio(String codParte, String codCliente, int cantidad, String codUsr, String codEmp) throws Exception {
//        LOGGER.info("<==== Inicio Metodo: esAccesorioImportado ====>");
//        double result = 0;
//        String sp = "panaautos.pkg_repuestos.sp_valida_dsctos_accesorio(?,?,?,?,?,?)";
//        Object[] paramIN = {codParte, codCliente, cantidad, codUsr, codEmp};
//        ResultSet rs = Util.sp_consulta(sp, paramIN);
//        while (rs.next()) {
//            result = rs.getDouble("ln_dscto");
//        }
//        LOGGER.info("<==== Fin Metodo: esAccesorioImportado ====>");
//        return result;
        LOGGER.info("<==== Inicio Metodo: validaDsctoAccesorio ====>");
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        double result = 0;
        try {
            cs = cn.prepareCall("{call panaautos.pkg_repuestos.sp_valida_dsctos_accesorio(?,?,?,?,?,?)}");
            cs.setString(1, codParte);
            cs.setString(2, codCliente);
            cs.setInt(3, cantidad);
            cs.setString(4, codUsr);
            cs.setString(5, codEmp);
            cs.registerOutParameter(6, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(6);
            while (rs.next()) {
                result = rs.getDouble("ln_dscto");
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(cs);
        }
        LOGGER.info("<==== Fin Metodo: validaDsctoAccesorio ====>");
        return result;
    }

    public Parte buscaParteEnTempo(String empresa,String codParte, long seqTmp2) throws Exception {
        LOGGER.info("<==== Inicio Metodo: buscaParteEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Parte obj = new Parte();
        try {
            cs = cn.prepareCall("{call web_existe_tempo_repuestos(?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, codParte);
            cs.setLong(3, seqTmp2);          
            cs.execute();
           rs = cs.getResultSet();
            while (rs.next()) {
                obj.setCodigo(rs.getString("c_c_codigo_prod"));
                obj.setDescripcion(rs.getString("descripcion1"));
                obj.setIgv(rs.getDouble("impuesto"));
                obj.setDescuento(rs.getDouble("descuento"));
                obj.setVVPSol(rs.getDouble("vvp_sol_uni"));
                obj.setVVPDol(rs.getDouble("vvp_dol_uni"));
                obj.setVVDSol(rs.getDouble("vvd_sol_uni"));
                obj.setVVDDol(rs.getDouble("vvd_dol_uni"));
                obj.setCantidad(rs.getInt("cantidad"));
                obj.setItem(rs.getString("num_item"));
                obj.setExiste(true);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(cs);
        }
        LOGGER.info("<==== Fin Metodo: buscaParteEnTempo ====>");
        return obj;
    }
  
    public Parte  ArticulosDisponibleSAP(String codParte, String codigoAlmacen) throws Exception {
        Connection cn = new ConectaHana().connection();
        Statement stmt = null;
        ResultSet rs = null;
        Parte obj = null;
        try {
            String SQLSelect = "SELECT  \"OnHand\" total, \"OnHand\" - \"IsCommited\" disponible,\"ItemCode\" codigo  FROM \"OITW\"  WHERE \"WhsCode\"='" + codigoAlmacen + "' and \"ItemCode\" = '" + codParte + "'";
            stmt = cn.createStatement();
            rs = stmt.executeQuery(SQLSelect);
            while (rs.next()) {
                obj = new Parte();
                obj.setCodigo(rs.getString("codigo"));
                obj.setStkTotal(rs.getInt("total"));
                obj.setStkDis(rs.getInt("disponible"));
              
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(stmt);
        }
        return obj;
    }  
    
public List<Parte> listaPartesTempoSAP(long seqTmp2) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaDb().connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Parte> list = new ArrayList();
        try {
            String sql = "SELECT cod_parte,"
                    + "descripcion1,"
                    + "cantidad,"
                    + "descuento,"
                    + "impuesto,"
                    + "vvp_sol_uni,"
                    + "tot_neto_sol,"
                    + "tot_gen_sol,"
                    + "vvp_dol_uni,"
                    + "tot_neto_dol,"
                    + "tot_gen_dol,"
                    + "ubicacion,num_item,indicadorimp,num_fil cantidad_ov, stk_aux_difa,ORD_TRABAJO_AUX,NVL(ELECTR_SN,'WEB') proviene,NVL(REP_POR_ANU,'N') anulado,nvl(cod_emp_cot,'00') codAnullado "
                    + "FROM tempo "
                    + "WHERE seq_temporal = " + seqTmp2 + " "
                    + "AND cod_parte <> ' '  "
                    + "ORDER BY num_item";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Parte obj = new Parte();
                obj.setCodigo(rs.getString("cod_parte"));
                obj.setDescripcion(rs.getString("descripcion1"));
                obj.setCantidad(rs.getInt("cantidad"));
                obj.setDescuento(rs.getDouble("descuento"));
                obj.setIgv(rs.getDouble("impuesto"));
                obj.setVVPSol(rs.getDouble("vvp_sol_uni"));
                obj.setTotVtaSol(rs.getDouble("tot_neto_sol"));
                obj.setTotGralVtaSol(rs.getDouble("tot_gen_sol"));
                obj.setVVPDol(rs.getDouble("vvp_dol_uni"));
                obj.setTotVtaDol(rs.getDouble("tot_neto_dol"));
                obj.setTotGralVtaDol(rs.getDouble("tot_gen_dol"));
                obj.setUbicacion(rs.getString("ubicacion"));
                obj.setItem(rs.getString("num_item"));
                obj.setIndicadorIGV(rs.getString("indicadorimp"));
                obj.setCantidad_ov(rs.getInt("cantidad_ov"));
                obj.setCantidadPendiente(rs.getInt("stk_aux_difa"));
                obj.setCantidadTotal(rs.getInt("ORD_TRABAJO_AUX"));
                obj.setProviene(rs.getString("proviene"));
                obj.setAnulado(rs.getString("anulado"));
                obj.setCodigoAnulado(rs.getString("codAnullado"));
                list.add(obj);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(ps);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return list;
    }    


    public List<Parte> muestraListaProductos(String empresa,String producto) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Parte obj = null;
        List<Parte> list = new ArrayList();
        try { 
            cs = cn.prepareCall("{call web_muestraListaProductos(?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, producto);          
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                obj = new Parte();
                obj.setDescripcion(rs.getString("c_t_descripcion"));
                obj.setVVPSol(rs.getDouble("vvp_soles"));
                obj.setItem(rs.getString("c_c_item"));
                obj.setExiste(true);
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


    public List<Parte> listaPartesTempo(String empresa,long seqTmp2) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Parte obj = null;
        List<Parte> list = new ArrayList();
        try { 
            cs = cn.prepareCall("{call web_lista_temporal(?,?)}");
            cs.setString(1, empresa);
            cs.setLong(2, seqTmp2);          
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                obj = new Parte();
                obj.setCodigo(rs.getString("c_c_codigo_prod"));
                obj.setDescripcion(rs.getString("descripcion1"));
                obj.setIgv(rs.getDouble("impuesto"));
                obj.setDescuento(rs.getDouble("descuento"));
                obj.setDescuentoCantidad(rs.getDouble("descuentoCantidad"));
                obj.setVVPSol(rs.getDouble("vvp_sol_uni"));
                obj.setVVPDol(rs.getDouble("vvp_dol_uni"));
                obj.setVVDSol(rs.getDouble("vvd_sol_uni"));
                obj.setVVDDol(rs.getDouble("vvd_dol_uni"));
                obj.setCantidad(rs.getInt("cantidad"));
                obj.setItem(rs.getString("num_item"));
                obj.setTotalPublicoSol(rs.getDouble("totpublicoSol"));
                obj.setExiste(true);
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

    public Parte obtieneTotalesTempo(String empresa,long seqTmp2) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Parte obj = null;
        try { 
            cs = cn.prepareCall("{call web_muestra_calculos(?,?)}");
            cs.setString(1, empresa);
            cs.setLong(2, seqTmp2);          
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                obj = new Parte();
                obj.setTotBrutoSol(rs.getDouble("brutoSol"));
                obj.setTotBrutoDol(rs.getDouble("brutoDol"));
                obj.setTotDsctoSol( Util.redondear(rs.getDouble("descuSol"),2));
                obj.setTotDsctoDol(rs.getDouble("descuDol"));
                obj.setTotVtaSol(rs.getDouble("netoSol"));
                obj.setTotVtaDol(rs.getDouble("netoDol"));
                obj.setTotIgvSol(rs.getDouble("igvSol"));
                obj.setTotIgvDol(rs.getDouble("igvDol"));
                obj.setTotGralVtaSol(rs.getDouble("totalSol"));
                obj.setTotGralVtaDol(rs.getDouble("totalDol"));
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

    public int maximoItemTempo(long seqTmp2) throws Exception {
//        LOGGER.info("<==== Inicio Metodo: maximoItemTempo ====>");
//        String sql = "SELECT nvl(MAX(num_item), 0) + 1 AS Maximo FROM tempo WHERE seq_temporal = " + seqTmp2 + "";
//        ResultSet rs = Util.sql_consulta(sql, null);
//        int result = 1;
//        while (rs.next()) {
//            result = rs.getInt("Maximo");
//        }
//        LOGGER.info("<==== Fin Metodo: maximoItemTempo ====>");
//        return result;
        LOGGER.info("<==== Inicio Metodo: maximoItemTempo ====>");
        Connection cn = new ConectaDb().connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 1;
        try {
            String sql = "SELECT nvl(MAX(num_item), 0) + 1 AS Maximo FROM tempo WHERE seq_temporal = " + seqTmp2 + "";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("Maximo");
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(ps);
        }
        LOGGER.info("<==== Fin Metodo: maximoItemTempo ====>");
        return result;
    }

    public String InsertaParteTempoNOTA(String codParte, long seqTmp2,String nota) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        String sql = "insert into panaautos.tempo_notas_sap (seq_temporal,cod_repuesto,nota) VALUES(" + seqTmp2 + ",'" + codParte + "','" + nota + "')";
        String result = Util.nullCad(Util.sql_ejecuta(sql, null));
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return result;
    }    
    
    public String updateTempoDescuento(String empresa,String codParte,Double descuento, long seqTmp2) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        String sql = "UPDATE temporal_web SET  descuento=" + descuento + " where c_c_empresa='" + empresa + "' and c_c_codigo_prod = '" + codParte + "' AND secuencia = " + seqTmp2 + "";
        String result = Util.nullCad(Util.sql_ejecuta(sql, null));
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return result;
    } 
    
    public String updateParteDelTempo(String codParte, long seqTmp2,String estado) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        String sql = "UPDATE tempo SET REP_POR_ANU='S',COD_EMP_COT='" + estado + "' WHERE cod_parte = '" + codParte + "' AND seq_temporal = " + seqTmp2 + "";
        String result = Util.nullCad(Util.sql_ejecuta(sql, null));
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return result;
    }    
    
    
    public String eliminarParteDelTempo(String empresa,String codParte, long seqTmp2) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        String sql = "DELETE FROM temporal_web WHERE c_c_empresa='" + empresa + "' and c_c_codigo_prod = '" + codParte + "' AND secuencia = " + seqTmp2 + "";
        String result = Util.nullCad(Util.sql_ejecuta(sql, null));
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return result;
    }

    public String actualizaCalculoIGV(long secuencia, String tienda, String codParte, double impuesto, long item) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        String result = null;
        try {
            cs = cn.prepareCall("{call PANAAUTOS.SP_ACT_ITEM_SAP(?,?,?,?,?)}");
            cs.setLong(1, secuencia);
            cs.setString(2, tienda);
            cs.setString(3, codParte);
            cs.setDouble(4, impuesto);
            cs.setLong(5, item);
            cs.executeUpdate();
        } catch (Exception e) {
            result = e.getMessage();
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
        }
        return result;
    }

    
    
    public List<Parte> ListaStockTiendas(String CodTienda, String CodParte, String Tipo, long Secuencia) throws Exception {
        List<Parte> lista = new ArrayList<>();
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.ListaStockTiendas(?,?,?,?)}");
            cs.setString(1, CodParte);
            cs.setString(2, Tipo);
            cs.setLong(3, Secuencia);
            cs.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(4);
            while (rs.next()) {
                Parte p = new Parte();
                p.setCodigoEmpresa(rs.getString("Empresa"));
                p.setCodigo(rs.getString("codigo"));
                p.setStkDis(rs.getInt("Stock"));
                p.setCAL_cantidad(rs.getInt("cantdp"));
                lista.add(p);
            }
        } catch (Exception e) {
            throw e;
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
    }



    public String insertaMotivosAnulacion(
            int pedido,
            String producto,
            String motivoAnulacion
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: insertaParteEnTempo ====>");
        String sp = "{call gp_inserta_motivoAnulacion(?,?,?)}";
        Object[] paramIN = { pedido,producto, motivoAnulacion};
        String result = Util.nullCad(Util.sp_ejecuta_SAP(sp, null, paramIN));
        LOGGER.info("<==== Fin Metodo: insertaParteEnTempo ====>");
        return result;
    }
    
    public void InsertaMotivos(int pedido,long secuencia) throws  Exception{
        Connection cn = new ConectaDb().connection();
        Statement stmt = cn.createStatement();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            
            rs = stmt.executeQuery("SELECT cod_repuesto,nota FROM panaautos.tempo_notas_sap WHERE seq_temporal =" + secuencia);
            while (rs.next()) {
                insertaMotivosAnulacion(pedido,rs.getString("cod_repuesto"),rs.getString("nota") );
            }
        } catch (Exception e) {
            throw e;
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

        }        
        
        
    }
    

    public List<Parte> muestraListaCotizaciones(String empresa,String cliente,String Fecha) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Parte> list = new ArrayList();
        try { 
            cs = cn.prepareCall("{call web_sp_cotizaciones(?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, cliente);  
            cs.setString(3, Fecha);              
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                Parte obj  = new Parte();
                obj.setPropuesta(Util.nullCad(rs.getLong("n_n_operacion")));
                obj.setFechaProp(rs.getString("fecha"));
                obj.setMoneda(rs.getString("c_fl_moneda"));
                obj.setCAL_TotBruto_Sol(rs.getDouble("n_i_venta"));
                obj.setCAL_TotDscto_Sol(rs.getDouble("n_i_descuento"));
                obj.setCAL_TotVtaSol(rs.getDouble("n_i_neto"));
                obj.setCAL_TotIGV_Sol(rs.getDouble("n_i_igv"));
                obj.setCAL_TotGralVtaSol(rs.getDouble("n_i_total"));
                obj.setExiste(true);
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
    
    
    
}
