package org.finance.bank.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.finance.bank.bean.TMoneda;
import org.finance.bank.model.dao.DAOGeneral;
import org.finance.bank.util.DateUtil;
import org.finance.bank.util.formatMoneda;

/**
 *
 * @author ubuntu
 */
public class SCambioMoneda extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        DAOGeneral dao = new DAOGeneral();
        try {
            String verfecha = DateUtil.getDateTime("dd/MM/yyyy HH:mm:ss", new Date());
            verfecha = verfecha.substring(0, 10);
            String verhora =verfecha.substring(11);
            String recibido = request.getParameter("recibido");
            double recibido1 = Double.parseDouble(recibido);
            String entregado = request.getParameter("entregado");
            double entregado1 = Double.parseDouble(entregado);
            String tipo = request.getParameter("tipo");
            String codigoMoneda = request.getParameter("cod_moneda");
            String tasa = request.getParameter("tasa");
            String ddeterioro = "";
            if (request.getParameter("ddeterioro") != null && !request.getParameter("ddeterioro").equals("")) {
                ddeterioro = request.getParameter("ddeterioro").toString();
            }
            TMoneda m = (TMoneda) dao.load(TMoneda.class, codigoMoneda);
            Map ticket = new HashMap();
            ticket.put("FECHA", verfecha);
            ticket.put("HORA", verhora);
            ticket.put("TASA", formatTasa(tasa));
            ticket.put("RECIBIDO", formatMoneda.formatMoneda(recibido1));
            ticket.put("ENTREGADO", formatMoneda.formatMoneda(entregado1));
            ticket.put("TIPO", tipo);
            ticket.put("MONEDA", m.getNombre());
            ticket.put("SIMBOLO", m.getSimbolo());
            ticket.put("D_DETERIORO", ddeterioro);
            session.setAttribute("ticket", ticket);
            response.sendRedirect("vercambiomoneda.htm");
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

    private static String formatTasa(String t) {
        String tasa = t;
        tasa = tasa.replace(".", "-");
        String[] array = tasa.split("-");
        if (array[1].length() < 3) {
            t = t + "&nbsp;&nbsp;";
        }
        return t;
    }
}
