package org.finance.bank.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.finance.bank.bean.TCaja;
import org.finance.bank.bean.TDetalleCaja;
import org.finance.bank.bean.TLogFinance;
import org.finance.bank.bean.TMoneda;
import org.finance.bank.bean.TOperacion;
import org.finance.bank.bean.TPersonaCaja;
import org.finance.bank.bean.TTipoOperacion;
import org.finance.bank.bean.TTransferenciaCaja;
import org.finance.bank.model.dao.DAOGeneral;
import org.finance.bank.model.iniDetalleCaja;
import org.finance.bank.util.DateUtil;
import org.finance.bank.util.numeroOperacion;

/**
 *
 * @author ronald
 */
public class SCerrarCaja extends HttpServlet {

    /**
     * No se puede cerrar caja con monto negativo en algunas de las monedas.
     * Código que cierra es 932.-
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
        HttpSession session = request.getSession(true);
        DAOGeneral dao = new DAOGeneral();
        String myIDP = session.getAttribute("USER_ID_PERSONA_CAJA").toString();
        String myFi = session.getAttribute("USER_CODFILIAL").toString();
        String myCaja = session.getAttribute("USER_CODCAJA").toString();
        String myId = session.getAttribute("USER_ID").toString();
        String myIp = session.getAttribute("USER_IP").toString();
        try {
            List result = dao.findAll("from TMoneda where estado ='ACTIVO'");
            if (result.isEmpty()) {
                out.print("ERROR: no se pudo leer la bases de datos!!");
                out.close();
                return;
            }
            Iterator it = result.iterator();
            TCaja cajaPrimary = (TCaja) dao.load(TCaja.class, myFi + "CA001");
            while (it.hasNext()) {
                TMoneda xMoney = (TMoneda) it.next();
                //TDetalleCaja detallecajaD = iniDetalleCaja.detalleActivaCaja(cajaPrimary.getCodCaja(),
                //        xMoney.getCodMoneda(), DateUtil.getDate(new Date()));
                TLogFinance detallecajaD = (TLogFinance) dao.load(TLogFinance.class, "LOG" + cajaPrimary.getCodCaja() + xMoney.getCodMoneda());
                String idForInitNewestTable = DateUtil.convertDateId(myIDP, SCerrarCaja.class.getSimpleName());
                String idForInitNewestTable2 = DateUtil.convertDateId(myIDP, SCerrarCaja.class.getSimpleName());
                //TDetalleCaja detallecajaO = iniDetalleCaja.detalleActivaCaja(myCaja, xMoney.getCodMoneda(), DateUtil.getDate(new Date()));
                TLogFinance detallecajaO = (TLogFinance) dao.load(TLogFinance.class, "LOG" + myCaja + xMoney.getCodMoneda());
                TPersonaCaja pcaja = (TPersonaCaja) dao.load(TPersonaCaja.class, myIDP);
                TOperacion operacion = new TOperacion();
                operacion.setIdOperacion(idForInitNewestTable);
                operacion.setDescripcion(DateUtil.getDate(new Date()) + " TRANSFERENCIA INTERNA");
                operacion.setFecha(DateUtil.getNOW_S());
                TTipoOperacion tipoOper = (TTipoOperacion) dao.load(TTipoOperacion.class, "TIPC9");
                operacion.setTTipoOperacion(tipoOper);
                operacion.setEstado("ACTIVO");
                operacion.setNumeroOperacion(numeroOperacion.getNumber(myFi, myCaja));
                operacion.setTMoneda(xMoney);
                operacion.setIdUser(myId + " 932");
                operacion.setIpUser(myIp);
                operacion.setDateUser(DateUtil.getNOW_S());
                operacion.setTPersonaCaja(pcaja);
                operacion.setSaldofinal(BigDecimal.ZERO);
                operacion.setSaldoFinalSinInteres(BigDecimal.ZERO);
                operacion.setFd(new Date());
                dao.persist(operacion);
                TOperacion recepcion = new TOperacion();
                recepcion.setIdOperacion(idForInitNewestTable2);
                recepcion.setDescripcion(DateUtil.getDate(new Date()) + " TRANSFERENCIA INTERNA");
                recepcion.setFecha(DateUtil.getNOW_S());
                TTipoOperacion tipoOper2 = (TTipoOperacion) dao.load(TTipoOperacion.class, "TIPC12");
                recepcion.setTTipoOperacion(tipoOper2);
                recepcion.setEstado("ACTIVO");
                recepcion.setNumeroOperacion(numeroOperacion.getNumber(myFi, cajaPrimary.getCodCaja()));
                recepcion.setTMoneda(xMoney);
                recepcion.setIdUser(myId + " 932");
                recepcion.setIpUser(myIp);
                recepcion.setDateUser(DateUtil.getNOW_S());
                recepcion.setTPersonaCaja(pcaja);
                recepcion.setSaldofinal(BigDecimal.ZERO);
                recepcion.setSaldoFinalSinInteres(BigDecimal.ZERO);
                recepcion.setFd(new Date());
                dao.persist(recepcion);
                TTransferenciaCaja nTransf = new TTransferenciaCaja();
                nTransf.setIdtransferenciacaja(idForInitNewestTable);
                nTransf.setFecha(DateUtil.getNOW_S());
                nTransf.setMonto(detallecajaO.getMontoFinal());
                nTransf.setDescripcion("* CERRADO DE CAJA " + myCaja);
                nTransf.setCodCajaDestino(cajaPrimary.getCodCaja());
                nTransf.setTCaja((TCaja) dao.load(TCaja.class, "" + myCaja));
                nTransf.setTipo("932");
                nTransf.setIdUser(myId + " 932");
                nTransf.setIpUser(myIp);
                nTransf.setDateUser(DateUtil.getNOW_S());
                nTransf.setEstado("ACTIVO");
                nTransf.setTOperacion(operacion);
                nTransf.setIdope(recepcion.getIdOperacion());
                dao.persist(nTransf);
                BigDecimal totalSaldo = detallecajaO.getMontoFinal();
                //TDetalleCaja xxd = (TDetalleCaja) dao.load(TDetalleCaja.class, detallecajaO.getIddetallecaja());
                detallecajaO.setMontoEntregado(detallecajaO.getMontoEntregado().add(totalSaldo));
                detallecajaO.setMontoFinal(BigDecimal.ZERO);
                //xxd.setDateUser(DateUtil.getNOW_S());
                dao.update();
                BigDecimal ntotal = new BigDecimal(detallecajaD.getMontoFinal().doubleValue() + totalSaldo.doubleValue());
                //TDetalleCaja xd = (TDetalleCaja) dao.load(TDetalleCaja.class, detallecajaD.getIddetallecaja());
                detallecajaD.setMontoFinal(ntotal);
                detallecajaD.setMontoRecibido(detallecajaD.getMontoRecibido().add(totalSaldo));
                //xd.setDateUser(DateUtil.getNOW_S());
                dao.update();
//                adminCapital adminK = new adminCapital(detallecajaO.getTMoneda().getCodMoneda(), myId, request.getRemoteAddr());
//                TPatrimonio pat = adminK.ponerAsiento();
//                TLogFinance newLog = new TLogFinance();
//                newLog.setIdlogfinance(idForInitNewestTable);
//                newLog.setActivoCajaybanco(pat.getTBalancexmoneda().getActivoCajaybanco());
//                newLog.setActivoCuentaxcobrar(pat.getTBalancexmoneda().getActivoCuentaxcobrar());
//                newLog.setCajero(xxd.getMontoFinal().toString());
//                newLog.setEncaje(pat.getTBalancexmoneda().getEncaje());
//                newLog.setFecha(DateUtil.getNOW_S());
//                newLog.setFilial(detallecajaO.getTCaja().getTFilial().getCodFilial());
//                newLog.setMonto(nTransf.getMonto());
//                newLog.setObservacion(ntotal.toString());
//                newLog.setPRespaldo(pat.getTBalancexmoneda().getPRespaldo());
//                newLog.setPasivo(pat.getTBalancexmoneda().getPasivo());
//                newLog.setPatrimonio(pat.getPatrimonioActual());
//                newLog.setTipoOperacion(idForInitNewestTable);
//                newLog.setReferencia(pat.getPatrimonio().toString());
//                dao.persist(newLog);
            }
        } finally {
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
