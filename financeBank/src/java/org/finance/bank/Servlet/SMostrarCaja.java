package org.finance.bank.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.finance.bank.bean.TCaja;
import org.finance.bank.model.dao.DAOGeneral;

/**
 *
 * @author roger
 */
public class SMostrarCaja extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.getSession(true);
        DAOGeneral dao = new DAOGeneral();
        try {
            String codfilial = request.getParameter("codfili");
            String consulta = request.getParameter("inselect");
            if (consulta == null) {
                consulta = "no";
            } else if (consulta.equals("ok")) {
                consulta = "Y";
            }
            List result = dao.findAll("from TCaja ca where ca.TFilial='" + codfilial + "' order by codCaja");
            Iterator it = result.iterator();
            if (consulta.equals("Y")) {
                out.print("<select id='selcaja' name='caja'>");
                if (result.size() > 0) {
                    while (it.hasNext()) {
                        TCaja caja = (TCaja) it.next();
                        out.println("<option value='" + caja.getCodCaja() + "'>" + caja.getNombreCaja() + "</option>");
                    }
                } else {
                    out.println("<option value='0'>(NO HAY CAJA)</option>");
                }
                out.print("</select>");
                out.close();
                return;
            }
            out.println("<div id='menu'>");
            out.println("<ul>");
            out.println("<li>Caja:");
            out.println("</li>");
            int i = 0;
            boolean withPrifalse = false;
            String p="";
            if (result.size() > 0) {
                while (it.hasNext()) {
                    TCaja caja = (TCaja) it.next();
                    i = i + 1;
                    if ("PRIMARY".equals(caja.getTipo()) && "ACTIVO".equals(caja.getEstado())) {
                        withPrifalse = true;
                        p=caja.getCodCaja();
                    }
                    out.println("<li style='cursor: pointer' id='li" + i + "'>");
                    out.println("<a id='a" + i + "' onclick=\"selected('" + i + "'); verDetalleCatalogo('" + caja.getCodCaja() + "')\">" + caja.getCodCaja() + " " + caja.getNombreCaja());
                    out.println("</a></li>");
                }
            } else {
                out.println("<li id='li" + i + "'>Aún no hay Cajas creadas</li>");
            }
            if (withPrifalse) {
                out.print("<li id='Prim'><input id='pok' type='hidden' value='"+p+"'></li>");
            } else {
                out.print("<li id='Prim'><input id='pok' type='hidden' value='NO'></li>");
            }
            out.println("</ul>");
            out.println("</div>");
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
