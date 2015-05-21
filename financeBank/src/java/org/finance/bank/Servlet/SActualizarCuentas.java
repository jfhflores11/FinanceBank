package org.finance.bank.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.finance.bank.model.CheckCapital;

/**
 *
 * @author roger
 */
/**OPCION PARA PAGAR CON ANTICIPACIÓN LOS INTERESES Y PERMITIR LOS DESEMBOLSOS/RETIROS A LOS USUARIOS EN SUS CUENTAS.*/
public class SActualizarCuentas extends HttpServlet {

    /**
     * OPCION PARA PAGAR CON ANTICIPACIÓN LOS INTERESES Y PERMITIR LOS DESEMBOLSOS/RETIROS A LOS USUARIOS EN SUS CUENTAS.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        String idcuenta = request.getParameter("idcuenta");
        String m = request.getParameter("m");
        if (idcuenta == null) {
            out.println("FAIL");
            out.close();
            return;
        }
        if (m == null) {
            out.println("FAIL");
            out.close();
            return;
        }
        try {
            Double mx = Double.parseDouble(m.replace(",", ""));
            Logger.getLogger(SActualizarCuentas.class.getName()).log(Level.INFO, "" + mx);
            if (mx != 0) {
                boolean tryied = CheckCapital.capitalizarForced(session, new Date(), idcuenta, mx);
                if (tryied) {
                    out.print("OK");
                    out.close();
                    return;
                }
            }
            out.print("FAIL");
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
