package pe.com.gp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.gp.connection.ConectaDb;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.BeanPreProvision;
import pe.com.gp.util.Util;

public class SecuenciaDAO {
    private static final Logger LOGGER = LogManager.getLogger();
   public void deleteMotivoAnulacion(long secuencia) throws Exception {
        Connection cn = new ConectaDb().connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            PreparedStatement st = cn.prepareStatement("delete FROM panaautos.tempo_notas_sap WHERE seq_temporal=?");
            st.setLong(1,secuencia);
            st.executeUpdate(); 
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(ps);
        }
    }    
    
    public Long getSecuenciaTemporal(String empresa) throws Exception {
        Long ls_secuencia = null;
        Connection conexion = new ConectaSQL().connection();
        CallableStatement cs = null;
        if (conexion != null) {
            try {
                cs = conexion.prepareCall("{call web_secuencia_temporal(?,?)}");
                cs.setString(1, empresa);
                cs.registerOutParameter(2, java.sql.Types.INTEGER);
                cs.execute();
                ls_secuencia = (long) cs.getInt(2);
            } catch (Exception e) {
                throw e;
            } finally {
                try {
                    conexion.close(); // libera conexion
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
        return ls_secuencia;
    }

    public String eliminaSecuencia(String empresa,long secuencia) {
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        String result = null;
        if (cn != null) {
            try {
                cs = cn.prepareCall("{call sp_borra_temporal(?,?)}");
                cs.setString(1, empresa);
                cs.setLong(2, secuencia);
                cs.executeUpdate();
            } catch (Exception e) {
                result = "" + e;
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

 



//    public Long Sequencia_Multiple(String codEmpresa, String TipDoc1, String TipDoc2) throws Exception {
//        Long ls_secuencia = null;
//        Connection conexion = new ConectaDb().connection();
//        CallableStatement cs = null;
//        ResultSet rs = null;
//        try {
//            cs = conexion.prepareCall("{?=call PANAAUTOS.FN_SECUENCIA_DINAMICA(?,?,?)}");
//            cs.registerOutParameter(1, OracleTypes.INTEGER);
//            cs.setString(2, codEmpresa.trim());
//            cs.setString(3, TipDoc1.trim());
//            cs.setString(4, TipDoc2.trim());
//            cs.execute();
//            ls_secuencia = (long) cs.getInt(1);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
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
//        return ls_secuencia;
//    }
    //panaautos.fn_seqtemporal
//    public Long Sequencia_Temporal() throws Exception {
//        Long ls_secuencia = null;
//        Connection conexion = new ConectaDb().connection();
//        CallableStatement cs = null;
//        ResultSet rs = null;
//
//        try {
//            cs = conexion.prepareCall("{?=call PANAAUTOS.FN_SEQTEMPORAL()}");
//            cs.registerOutParameter(1, OracleTypes.INTEGER);
//            cs.execute();
//            ls_secuencia = (long) cs.getInt(1);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        } finally {
//            try {
//                conexion.close(); // libera conexion
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
//        return ls_secuencia;
//    }
//
//    public void borraTemporal(long secuencia) {
//        Connection conexion = new ConectaDb().connection();
//        CallableStatement cs = null;
//
//        if (conexion != null) {
//            try {
//                cs = conexion.prepareCall("{call PANAAUTOS.sp_borra_temporal(?)}");
//                cs.setLong(1, secuencia);
//                cs.execute();
//
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            } finally {
//                try {
//                    conexion.close(); // libera conexion
//                } catch (Exception e) {
//                }
//
//                try {
//                    if (cs != null) {
//                        cs.close();
//                    }
//                } catch (Exception e) {
//                }
//            }
//        }
//    }
    public int contarRegistrosTempo(String empresa, long secuencia) throws Exception {
        Connection cn = new ConectaSQL().connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            String sql = "select count(*) ctos from temporal_web where c_c_empresa='" + empresa + "' and secuencia=" + secuencia; 
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("ctos");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(ps);
        }
        return result;
    }
    
    
    public Long getSecuenciaTemporal2(String empresa) throws Exception {
        Connection cn = new ConectaSQL().connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long result = new Long(0);
        try {
            String sql = "select next value for secuencia_control";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getLong(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(ps);
        }
        return result;
    }
    
     public Long getSecuencia() throws Exception {
        Connection cn = new ConectaSQL().connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long result = new Long(0);
        try {
            String sql = "select next value for relaciones.dbo.secuencia_temporal";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getLong(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(ps);
        }
        return result;
    }     
 
     
    public long SecuenciaEmpresa(String empresa,String tipo)throws Exception {
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        long secuencia = 0;
        if (cn != null) {
            try {                
                CallableStatement cs = cn.prepareCall("{call relaciones.dbo.sp_secuencia_empresa(?,?)}");
                cs.setString(1, empresa);  
                cs.setString(2, tipo); 
                cs.execute();                
            } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        }
        return secuencia;
    }   
    
     public long getSecuenciaTemporal(String empresa,String tipo)throws Exception {
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        long secuencia = 0;
        if (cn != null) {
            try {                
                CallableStatement cs = cn.prepareCall("{call relaciones.dbo.get_secuencia_empresa(?,?)}");
                cs.setString(1, empresa);  
                cs.setString(2, tipo); 
                cs.execute();
                rs = cs.getResultSet();
                while (rs.next()) {                  
                    secuencia = rs.getLong("secuencia");
                }
            } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        }
        return secuencia;
    }
     
     public long getEliminaSecuenciaTemporal(String empresa,String tipo)throws Exception {
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        long secuencia = 0;
        if (cn != null) {
            try {                
                CallableStatement cs = cn.prepareCall("{call relaciones.dbo.elimina_secuencia_empresa(?,?)}");
                cs.setString(1, empresa);  
                cs.setString(2, tipo); 
                cs.execute();
                rs = cs.getResultSet();
                while (rs.next()) {                  
                    secuencia = rs.getLong("secuencia");
                }
            } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        }
        return secuencia;
    }      
    
    
}
