package pe.com.gp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.gp.connection.ConectaHana;
import pe.com.gp.entity.Cliente;
import pe.com.gp.util.Util;

public class PedidoDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    public Cliente getDatosClienteOfertaVenta(String DocEntry) throws Exception {
        Connection cn = new ConectaHana().connection();
        ResultSet rs = null;
        Cliente cliente = new Cliente();
        Statement stmt = null;
        try {
            String sql = "SELECT DET.\"DocEntry\",DET.\"DocNum\" ,DET.\"DocDate\",DET.\"CardCode\" c_c_codigo,DET.\"CardName\" c_t_razon_social,DET.\"Address\" c_t_direccion,DET.\"DocCur\",CLI.\"U_SYP_BPTP\",CLI.\"LicTradNum\",DET.\"U_SYP_CANALVP\" canalVenta,DET.\"PayToCode\" indiceDireccion FROM \"OQUT\" DET ,\"OCRD\" CLI "
                    + " WHERE  DET.\"DocEntry\"='" + DocEntry + "' AND  DET.\"CardCode\" = CLI.\"CardCode\" ";
            stmt = cn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                cliente.setCodigo(rs.getString("c_c_codigo"));
                cliente.setAuxiliar("C");
                cliente.setRazon_social(Util.quitarApostrofos(rs.getString("c_t_razon_social")));
                cliente.setDocumentoSAP(rs.getString("LicTradNum"));
                cliente.setTipo_persona(rs.getString("U_SYP_BPTP"));
                cliente.setMoneda(rs.getString("DocCur"));
                cliente.setCanalVenta(rs.getString("canalventa"));
                cliente.setIndiceDireccion(rs.getString("indiceDireccion"));
                cliente.setOfertaVenta(rs.getString("DocNum"));
                cliente.setMoneda(rs.getString("DocCur"));
                cliente.setTelefono1("");
                cliente.setPaterno("");
                cliente.setMaterno("");
                cliente.setPrimer_nombre("");
                cliente.setSegundo_nombre("");
                cliente.setEmail_01("");
                cliente.setTelefono2("");
                cliente.setUbigeo("");
                cliente.setCelular1("");
                cliente.setExiste(true);
                break;
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(stmt);
        }

        return cliente;
    }
}
