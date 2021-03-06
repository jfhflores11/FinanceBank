package org.finance.bank.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.finance.bank.bean.TCaja;
import org.finance.bank.bean.TOperacion;
import org.finance.bank.bean.TRegistroCompraVenta;
import org.finance.bank.bean.TRegistroDepositoRetiro;
import org.finance.bank.bean.TRegistroGiro;
import org.finance.bank.bean.TTransferenciaCaja;
import org.finance.bank.model.dao.DAOGeneral;
import org.finance.bank.util.DateUtil;

/**
 *
 * @author admin
 */
public class SMostrarOp extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.getSession(true);
        DAOGeneral dao = new DAOGeneral();
        try {
            String idOperacion = request.getParameter("id");
            if (idOperacion == null) {
                dao.cerrarSession();
                out.close();
                return;
            }
            TOperacion o = (TOperacion) dao.load(TOperacion.class, idOperacion);
            if (o == null) {
                out.print("<fieldset><legend style='font-size:9px'><b>DATOS DE LA OPERACION</b></legend>"
                        + "<table border='0' cellpadding='5' cellspacing='5' id='h1' class='tabla' width='100%'>"
                        + "<tr><td valign='top'>NO RESULTS.</td></tr></table></fieldset>");
            } else {
                int chain = (int) Integer.parseInt(o.getTTipoOperacion().getCodigoTipoOperacion().substring(4));
                switch (chain) {
                    case 1:
                        TRegistroCompraVenta compra = (TRegistroCompraVenta) o.getTRegistroCompraVentas().iterator().next();
                        if (compra.getEstado().equals("EXTORNADO")) {
                            out.println("<fieldset><legend style='font-size:9px'><b>DATOS DE LA OPERACION</b></legend>"
                                    + "<table border='0' cellpadding='5' cellspacing='5' id='h1' class='tabla' width='100%'>"
                                    + "<tr><td valign='top'>Esta operación a sido extornado el " + o.getFechaExtornacion()
                                    + "</td></tr></table></fieldset>");
                            dao.cerrarSession();
                            out.close();
                            return;
                        }
                        out.println("<fieldset><legend style='font-size:9px'><b>OPERACION: COMPRA DE MONEDA EXTRANJERA</b></legend>"
                                + "<table border='0' cellpadding='5' cellspacing='5' id='h1' class='tabla' width='100%'>"
                                + "<tr><td colspan='2'>CAJA: " + o.getTPersonaCaja().getTCaja().getCodCaja() + " : " + o.getTPersonaCaja().getTPersona().getNombre() + " " + o.getTPersonaCaja().getTPersona().getApellidos()
                                + "</td></tr><tr><td>FECHA: " + o.getFecha() + "</td><td>" + o.getDescripcion() + "</td></tr>"
                                + "<tr><td>OPERACION: " + o.getTTipoOperacion().getNombre() + "</td><td style='color:" + o.getTMoneda().getColor() + "'>MONEDA: " + o.getTMoneda().getNombre() + "</td></tr>"
                                + "<tr><td>M. ENTREGADO: " + o.getTMoneda().getSimbolo() + " " + compra.getMontoEntregado() + "</td><td>M. RECIBIDO: " + compra.getMontoRecibido() + "</td></tr>"
                                + "<tr><td>TIPO CAMBIO: " + compra.getTipoCambio() + "</td><td>DESC. DETERIORO: " + compra.getDescuentoDeterioro() + "</td></tr>"
                                + "<tr><td>" + (o.getFecha().substring(0, 10).equals(DateUtil.getDate(new Date())) ? "<p style='color: red;font-weight:bold;'> PUEDE EXTORNAR</p>" : "NO ES POSIBLE EXTORNAR")
                                + "</td><td><input type='button' value='REIMPRIMIR' onclick=\"reimp('" + o.getIdOperacion() + "');\" /></td></tr>"
                                + "</table></fieldset>");
                        break;
                    case 2:
                        TRegistroCompraVenta venta = (TRegistroCompraVenta) o.getTRegistroCompraVentas().iterator().next();
                        if (venta.getEstado().equals("EXTORNADO")) {
                            out.println("<fieldset><legend style='font-size:9px'><b>DATOS DE LA OPERACION</b></legend>"
                                    + "<table border='0' cellpadding='5' cellspacing='5' id='h1' class='tabla' width='100%'>"
                                    + "<tr><td valign='top'>Esta operación a sido extornado el " + o.getFechaExtornacion()
                                    + "</td></tr></table></fieldset>");
                            dao.cerrarSession();
                            out.close();
                            return;
                        }
                        out.println("<fieldset><legend style='font-size:9px'><b>OPERACION: VENTA DE MONEDA EXTRANJERA</b></legend>"
                                + "<table border='0' cellpadding='5' cellspacing='5' id='h1' class='tabla' width='100%'>"
                                + "<tr><td colspan='2'>CAJA: " + o.getTPersonaCaja().getTCaja().getCodCaja() + " : " + o.getTPersonaCaja().getTPersona().getNombre() + " " + o.getTPersonaCaja().getTPersona().getApellidos()
                                + "</td></tr><tr><td>FECHA: " + o.getFecha() + "</td><td>" + o.getDescripcion() + "</td></tr>"
                                + "<tr><td>OPERACION: " + o.getTTipoOperacion().getNombre() + "</td><td style='color:" + o.getTMoneda().getColor() + "'>MONEDA: " + o.getTMoneda().getNombre() + "</td></tr>"
                                + "<tr><td>M. ENTREGADO: " + o.getTMoneda().getSimbolo() + " " + venta.getMontoEntregado() + "</td><td>M. RECIBIDO: " + venta.getMontoRecibido() + "</td></tr>"
                                + "<tr><td>TIPO CAMBIO: " + venta.getTipoCambio() + "</td><td>DESC. DETERIORO: " + venta.getDescuentoDeterioro() + "</td></tr>"
                                + "<tr><td>" + (o.getFecha().substring(0, 10).equals(DateUtil.getDate(new Date())) ? "<p style='color: red;font-weight:bold;'> PUEDE EXTORNAR</p>" : "NO ES POSIBLE EXTORNAR")
                                + "</td><td><input type='button' value='REIMPRIMIR' onclick=\"reimp('" + o.getIdOperacion() + "');\" /></td></tr>"
                                + "</table></fieldset>");
                        break;
                    case 3:
                        TRegistroDepositoRetiro deposito = (TRegistroDepositoRetiro) o.getTRegistroDepositoRetiros().iterator().next();
                        if (deposito.getEstado().equals("EXTORNADO")) {
                            out.println("<fieldset><legend style='font-size:9px'><b>DATOS DE LA OPERACION</b></legend>"
                                    + "<table border='0' cellpadding='5' cellspacing='5' id='h1' class='tabla' width='100%'>"
                                    + "<tr><td valign='top'>Esta operación a sido extornado el " + o.getFechaExtornacion()
                                    + "</td></tr></table></fieldset>");
                            dao.cerrarSession();
                            out.close();
                            return;
                        }
                        out.println("<fieldset><legend style='font-size:9px'><b>OPERACION: DEPÓSITO A CUENTA DE " + deposito.getTCuentaPersona().getTPersona().getNombre() + " " + deposito.getTCuentaPersona().getTPersona().getApellidos() + "</b></legend>"
                                + "<table border='0' cellpadding='5' cellspacing='5' id='h1' class='tabla' width='100%'>"
                                + "<tr><td colspan='2'>CAJA: " + o.getTPersonaCaja().getTCaja().getCodCaja() + " : " + o.getTPersonaCaja().getTPersona().getNombre() + " " + o.getTPersonaCaja().getTPersona().getApellidos()
                                + "</td></tr><tr><td>FECHA: " + o.getFecha() + "</td><td>" + o.getDescripcion() + "</td></tr>"
                                + "<tr><td>OPERACION: " + o.getTTipoOperacion().getNombre() + "</td><td style='color:" + o.getTMoneda().getColor() + "'>MONEDA: " + o.getTMoneda().getNombre() + "</td></tr>"
                                + "<tr><td>MONTO DEPOSITO: " + o.getTMoneda().getSimbolo() + " " + deposito.getImporte() + "</td><td>NUM. CUENTA: " + deposito.getTCuentaPersona().getNumCta() + "</td></tr>"
                                + "<tr><td>TIPO DE CUENTA: " + deposito.getTCuentaPersona().getTTipoCuenta().getDescripcion() + "</td><td>REF.: " + deposito.getNombreRepresentante() + " " + deposito.getApellidosRepresentante() + "</td></tr>"
                                + "<tr><td>" + (o.getFecha().substring(0, 10).equals(DateUtil.getDate(new Date())) ? "<p style='color: red;font-weight:bold;'> PUEDE EXTORNAR</p>" : "NO ES POSIBLE EXTORNAR")
                                + "</td><td><input type='button' value='REIMPRIMIR' onclick=\"reimp('" + o.getIdOperacion() + "');\" /></td></tr>"
                                + "</table></fieldset>");
                        break;
                    case 4:
                        TRegistroDepositoRetiro retiro = (TRegistroDepositoRetiro) o.getTRegistroDepositoRetiros().iterator().next();
                        if (retiro.getEstado().equals("EXTORNADO")) {
                            out.println("<fieldset><legend style='font-size:9px'><b>DATOS DE LA OPERACION</b></legend>"
                                    + "<table border='0' cellpadding='5' cellspacing='5' id='h1' class='tabla' width='100%'>"
                                    + "<tr><td valign='top'>Esta operación a sido extornado el " + o.getFechaExtornacion()
                                    + "</td></tr></table></fieldset>");
                            dao.cerrarSession();
                            out.close();
                            return;
                        }
                        out.println("<fieldset><legend style='font-size:9px'><b>OPERACION: RETIRO DE LA CUENTA DE " + retiro.getTCuentaPersona().getTPersona().getNombre() + " " + retiro.getTCuentaPersona().getTPersona().getApellidos() + "</b></legend>"
                                + "<table border='0' cellpadding='5' cellspacing='5' id='h1' class='tabla' width='100%'>"
                                + "<tr><td colspan='2'>CAJA: " + o.getTPersonaCaja().getTCaja().getCodCaja() + " : " + o.getTPersonaCaja().getTPersona().getNombre() + " " + o.getTPersonaCaja().getTPersona().getApellidos()
                                + "</td></tr><tr><td>FECHA: " + o.getFecha() + "</td><td>" + o.getDescripcion() + "</td></tr>"
                                + "<tr><td>OPERACION: " + o.getTTipoOperacion().getNombre() + "</td><td style='color:" + o.getTMoneda().getColor() + "'>MONEDA: " + o.getTMoneda().getNombre() + "</td></tr>"
                                + "<tr><td>MONTO RETIRO: " + o.getTMoneda().getSimbolo() + " " + retiro.getImporte() + "</td><td>NUM. CUENTA: " + retiro.getTCuentaPersona().getNumCta() + "</td></tr>"
                                + "<tr><td>TIPO DE CUENTA: " + retiro.getTCuentaPersona().getTTipoCuenta().getDescripcion() + "</td><td>REF.: " + retiro.getNombreRepresentante() + " " + retiro.getApellidosRepresentante() + "</td></tr>"
                                + "<tr><td>" + (o.getFecha().substring(0, 10).equals(DateUtil.getDate(new Date())) ? "<p style='color: red;font-weight:bold;'> PUEDE EXTORNAR</p>" : "NO ES POSIBLE EXTORNAR")
                                + "</td><td><input type='button' value='REIMPRIMIR' onclick=\"reimp('" + o.getIdOperacion() + "');\" /></td></tr>"
                                + "</table></fieldset>");
                        break;
                    case 5:
                        TRegistroGiro giro = (TRegistroGiro) o.getTRegistroGiros().iterator().next();
                        if (giro.getEstado().equals("EXTORNADO")) {
                            out.println("<fieldset><legend style='font-size:9px'><b>DATOS DE LA OPERACION</b></legend>"
                                    + "<table border='0' cellpadding='5' cellspacing='5' id='h1' class='tabla' width='100%'>"
                                    + "<tr><td valign='top'>Esta operación a sido extornado el " + o.getFechaExtornacion()
                                    + "</td></tr></table></fieldset>");
                            dao.cerrarSession();
                            out.close();
                            return;
                        }
                        out.println("<fieldset><legend style='font-size:9px'><b>OPERACION: GIRO DE " + giro.getGirador().replace("|", " ") + "</b></legend>"
                                + "<table border='0' cellpadding='5' cellspacing='5' id='h1' class='tabla' width='100%'>"
                                + "<tr><td colspan='2'>CAJA: " + o.getTPersonaCaja().getTCaja().getCodCaja() + " : " + o.getTPersonaCaja().getTPersona().getNombre() + " " + o.getTPersonaCaja().getTPersona().getApellidos()
                                + "</td></tr><tr><td>FECHA: " + o.getFecha() + "</td><td>" + o.getDescripcion() + "</td></tr>"
                                + "<tr><td>OPERACION: " + o.getTTipoOperacion().getNombre() + "</td><td style='color:" + o.getTMoneda().getColor() + "'>MONEDA: " + o.getTMoneda().getNombre() + "</td></tr>"
                                + "<tr><td>MONTO GIRADO: " + o.getTMoneda().getSimbolo() + " " + giro.getImporte() + "</td><td>M. COMISIÓN: " + giro.getComision() + "</td></tr>"
                                + "<tr><td>TIPO DE PAGO: " + giro.getFpagoImporte() + "</td><td>Lug. COBRO: " + giro.getTFilial().getNombre() + "</td></tr>"
                                + "<tr><td>PARA: " + giro.getRecibidor().replace("|", " ") + "</td><td>ESTADO ACTUAL: " + giro.getEstado() + "</td></tr>"
                                + "<tr><td>" + (o.getFecha().substring(0, 10).equals(DateUtil.getDate(new Date())) ? "<p style='color: red;font-weight:bold;'> PUEDE EXTORNAR</p>" : "NO ES POSIBLE EXTORNAR")
                                + "</td><td><input type='button' value='REIMPRIMIR' onclick=\"reimp('" + o.getIdOperacion() + "');\" /></td></tr>"
                                + "</table></fieldset>");
                        break;
                    case 6:
                        out.print("AUN NO IMPLEMENTADO");
                        break;
                    case 7:
                        out.print("AUN NO IMPLEMENTADO");
                        break;
                    case 8:
                        TRegistroGiro giro_cobro = null;
                        try {
                            giro_cobro = (TRegistroGiro) dao.findAll("from TRegistroGiro where idoperacioncobro='" + o.getIdOperacion() + "'").get(0);
                        } catch (Exception ex) {
                            out.print("ESTA OPERACION NO SE PUEDE LEER!!! " + ex.getMessage());
                            dao.cerrarSession();
                            out.close();
                            return;
                        }
                        out.println("<fieldset><legend style='font-size:9px'><b>OPERACION: GIRO DE " + giro_cobro.getGirador().replace("|", " ") + "</b></legend>"
                                + "<table border='0' cellpadding='5' cellspacing='5' id='h1' class='tabla' width='100%'>"
                                + "<tr><td colspan='2'>CAJA: " + o.getTPersonaCaja().getTCaja().getCodCaja() + " : " + o.getTPersonaCaja().getTPersona().getNombre() + " " + o.getTPersonaCaja().getTPersona().getApellidos()
                                + "</td></tr><tr><td>FECHA: " + o.getFecha() + "</td><td>" + o.getDescripcion() + "</td></tr>"
                                + "<tr><td>OPERACION: " + o.getTTipoOperacion().getNombre() + "</td><td style='color:" + o.getTMoneda().getColor() + "'>MONEDA: " + o.getTMoneda().getNombre() + "</td></tr>"
                                + "<tr><td>MONTO COBRADO: " + o.getTMoneda().getSimbolo() + " " + giro_cobro.getImporte() + "</td><td>M. COMISIÓN: " + giro_cobro.getComision() + "</td></tr>"
                                + "<tr><td>TIPO DE PAGO: " + giro_cobro.getFpagoImporte() + "</td><td>Lug. COBRO: " + giro_cobro.getTFilial().getNombre() + "</td></tr>"
                                + "<tr><td>PARA: " + giro_cobro.getRecibidor().replace("|", " ") + "</td><td>ESTADO ACTUAL: " + giro_cobro.getEstado() + "</td></tr>"
                                + "<tr><td>" + (o.getFecha().substring(0, 10).equals(DateUtil.getDate(new Date())) ? "<p style='color: red;font-weight:bold;'> PUEDE EXTORNAR</p>" : "NO ES POSIBLE EXTORNAR")
                                + "</td><td><input type='button' value='REIMPRIMIR' onclick=\"reimp('" + o.getIdOperacion() + "');\" /></td></tr>"
                                + "</table></fieldset>");
                        break;
                    case 9:
                        TTransferenciaCaja ttc = (TTransferenciaCaja) o.getTTransferenciaCajas().iterator().next();
                        if (ttc.getEstado().equals("EXTORNADO")) {
                            out.println("<fieldset><legend style='font-size:9px'><b>DATOS DE LA OPERACION</b></legend>"
                                    + "<table border='0' cellpadding='5' cellspacing='5' id='h1' class='tabla' width='100%'>"
                                    + "<tr><td valign='top'>Esta operación a sido extornado el " + o.getFechaExtornacion()
                                    + "</td></tr></table></fieldset>");
                            dao.cerrarSession();
                            out.close();
                            return;
                        }
                        out.println("<fieldset><legend style='font-size:9px'><b>OPERACION: TRANSFERENCIA DE " + ttc.getTCaja().getCodCaja() + " A " + ttc.getCodCajaDestino() + "</b></legend>"
                                + "<table border='0' cellpadding='5' cellspacing='5' id='h1' class='tabla' width='100%'>"
                                + "<tr><td colspan='2'>CAJA: " + o.getTPersonaCaja().getTCaja().getCodCaja() + " : " + o.getTPersonaCaja().getTPersona().getNombre() + " " + o.getTPersonaCaja().getTPersona().getApellidos()
                                + "</td></tr><tr><td>FECHA: " + o.getFecha() + "</td><td>" + o.getDescripcion() + "</td></tr>"
                                + "<tr><td>OPERACION: " + o.getTTipoOperacion().getNombre() + " INTERNA</td><td style='color:" + o.getTMoneda().getColor() + "'>MONEDA: " + o.getTMoneda().getNombre() + "</td></tr>"
                                + "<tr><td>MONTO TRANSF.: " + o.getTMoneda().getSimbolo() + " " + ttc.getMonto() + "</td><td>DESTINO: " + ((TCaja) dao.load(TCaja.class, ttc.getCodCajaDestino())).getNombreCaja() + "</td></tr>"
                                + "<tr><td>PARA: " + ttc.getTCaja().getCodCaja() + "</td><td>ESTADO ACTUAL: " + ttc.getEstado() + "</td></tr>"
                                + "<tr><td>" + (o.getFecha().substring(0, 10).equals(DateUtil.getDate(new Date())) ? "<p style='color: red;font-weight:bold;'> PUEDE EXTORNAR</p>" : "NO ES POSIBLE EXTORNAR")
                                + "</td><td><input type='button' value='REIMPRIMIR' onclick=\"reimp('" + o.getIdOperacion() + "');\" /></td></tr>"
                                + "</table></fieldset>");
                        break;
                    case 10:
                        out.print("AUN NO IMPLEMENTADO");
                        break;
                    case 11:
                        out.print("AUN NO IMPLEMENTADO");
                        break;
                    case 12:
                        out.print("AUN NO IMPLEMENTADO");
                        break;
                    case 13:
                        out.print("AUN NO IMPLEMENTADO");
                        break;
                    case 14:
                        out.print("AUN NO IMPLEMENTADO");
                        break;
                    default:
                        out.print("<fieldset><legend style='font-size:9px'><b>DATOS DE LA OPERACION</b></legend>"
                                + "<table border='0' cellpadding='5' cellspacing='5' id='h1' class='tabla' width='100%'>"
                                + "<tr><td valign='top'>NO hay nada que mostrar.</td></tr></table></fieldset>");
                }
            }
        } finally {
            dao.cerrarSession();
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
