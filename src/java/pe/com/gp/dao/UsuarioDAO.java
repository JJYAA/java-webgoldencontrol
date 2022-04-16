package pe.com.gp.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import pe.com.gp.connection.ConectaDb;
import oracle.jdbc.OracleTypes;

public class UsuarioDAO {

    private String nombre = null;
    private String nomprinter = null;
    private String estado = null;
    private String primerNombre = null;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int BuscaImpresora() throws Exception {

        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        int Existe = 0;
        try {
            cs = conexion.prepareCall("{call PANAAUTOS.PKG_GENERALES.NomPrinter(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);// 
            while (rs.next()) {
                Existe = 1;
                this.setNomprinter(rs.getString("nomPrinter"));
            }
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        } finally {
            try {
                conexion.close(); // libera cn
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
        return Existe;
    }

    public String getNomprinter() {
        return nomprinter;
    }

    public void setNomprinter(String nomprinter) {
        this.nomprinter = nomprinter;
    }

    public int BuscaUsr(String codUsr) throws Exception {
        ConectaDb myconnection = new ConectaDb();
        Connection conexion = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        this.setEstado("1");
        int Existe = -1;
        try {
            conexion = myconnection.connection();
            cs = conexion.prepareCall("{call PANAAUTOS.PKG_GENERALES.GetDatosUsuario(?,?)}");
            cs.setString(1, codUsr);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(2);//   
            while (rs.next()) {
                Existe = 20;
                this.setNombre(rs.getString("nombre"));
                this.setEstado(rs.getString("c_st_situacion"));
                this.setPrimerNombre(rs.getString("primerNombre"));
            }
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        } finally {
            try {
                conexion.close(); // libera cn
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
        return Existe;
    }

    public int BuscaAcceso(String Tienda, String codUsr, String opcion) throws Exception {
        int Existe = 0;
        ConectaDb cn = new ConectaDb();
        Connection conexion = null;
        if (BuscaUsr(codUsr) == -1) {
            Existe = -1;
        } else if (this.getEstado().equals("2")) {
            Existe = 1;
        } else {
            conexion = cn.connection();
            CallableStatement cs = null;
            ResultSet rs = null;
            long ord_tra = 0;
            try {
                cs = conexion.prepareCall("{?=call PANAAUTOS.PKG_GENERALES.fn_acceso_ipad2(?,?,?)}");
                cs.registerOutParameter(1, OracleTypes.VARCHAR);
                cs.setString(2, Tienda);
                cs.setString(3, codUsr);
                cs.setString(4, opcion);
                cs.execute();
                Existe = cs.getInt(1);
                //cs.getInt(1);
                //rs = (ResultSet) cs.getObject(1);// 
                //if (cs.getInt(1)==null)
                //	Existe=0;
                ///else
                //	Existe = cs.getInt(1);
                //Obtener los valores

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    conexion.close(); // libera cn
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
        return Existe;
    }

    public int BuscaAcceso(String Tienda, String codUsr, String Passs, String opcion) throws Exception {
        int Existe = 0;
        if (BuscaUsr(codUsr) == -1) {
            Existe = -1;
        } else if (this.getEstado().equals("2")) {
            Existe = 1;
        } else {
            Connection conexion = new ConectaDb().connection();
            CallableStatement cs = null;
            ResultSet rs = null;
            long ord_tra = 0;
            try {
                cs = conexion.prepareCall("{?=call PANAAUTOS.PKG_GENERALES.fn_acceso_ipad(?,?,?,?)}");
                cs.registerOutParameter(1, OracleTypes.VARCHAR);
                cs.setString(2, Tienda);
                cs.setString(3, codUsr);
                cs.setString(4, Passs);
                cs.setString(5, opcion);
                cs.execute();
                Existe = cs.getInt(1);
                //Obtener los valores

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    conexion.close(); // libera cn
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

        return Existe;
    }

    public int BuscaUsr(String codi, String pass, String sist) throws Exception {

        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        int Existe = 0;
        try {
            cs = conexion.prepareCall("{call PANAAUTOS.PKG_GENERALES.GetAccesoUsuario(?,?,?,?)}");
            cs.setString(1, codi);
            cs.setString(2, pass);
            cs.setString(3, sist);
            cs.registerOutParameter(4, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(4);//  
            while (rs.next()) {
                Existe = 1;
                this.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        } finally {
            try {
                conexion.close(); // libera cn
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
        return Existe;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

}
