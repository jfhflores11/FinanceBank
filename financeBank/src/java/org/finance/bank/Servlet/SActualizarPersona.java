package org.finance.bank.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.finance.bank.bean.TCaja;
import org.finance.bank.bean.TCategoriaPersona;
import org.finance.bank.bean.TControlTipo;
import org.finance.bank.bean.TCuentaAcceso;
import org.finance.bank.bean.TPersona;
import org.finance.bank.bean.TPersonaCaja;
import org.finance.bank.bean.TTipoPersona;
import org.finance.bank.model.dao.DAOGeneral;
import org.finance.bank.util.ConvertUtil;
import org.finance.bank.util.MD5;

/**
 *
 * @author ronald
 */
public class SActualizarPersona extends HttpServlet {

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
        DAOGeneral dao = new DAOGeneral();
        try {
            String IDPERSONA = "";
            if (request.getParameter("idpersona") != null) {
                IDPERSONA = request.getParameter("idpersona").toString();
            } else {
                out.print("La persona no se puede actualizarse.");
                out.close();
                return;
            }
            String estado = "ACTIVO";
            String dni = "00000000";
            if (request.getParameter("dni") != null) {
                dni = request.getParameter("dni").toString();
            }
            String ruc = "1000000000";
            if (request.getParameter("ruc") != null) {
                ruc = request.getParameter("ruc").toString();
            }
            String nombres = "";
            if (request.getParameter("nombres") != null && !request.getParameter("nombres").equals("")) {
                nombres = request.getParameter("nombres").toString();
            }
            String apellidos = "";
            if (request.getParameter("apellidos") != null && !request.getParameter("apellidos").equals("")) {
                apellidos = request.getParameter("apellidos").toString();
            }
            String email = "";
            if (request.getParameter("email") != null && !request.getParameter("email").equals("")) {
                email = request.getParameter("email").toString();
            }
            String ubigeo = "050101";
            if (request.getParameter("ubigeo") != null && !request.getParameter("ubigeo").equals("")) {
                ubigeo = request.getParameter("ubigeo").toString();
            }
            String telefono = "";
            if (request.getParameter("telefono") != null && !request.getParameter("telefono").equals("")) {
                telefono = request.getParameter("telefono").toString();
            }
            String celular = "";
            if (request.getParameter("celular") != null && !request.getParameter("celular").equals("")) {
                celular = request.getParameter("celular").toString();
            }
            String direccion = "";
            if (request.getParameter("direccion") != null && !request.getParameter("direccion").equals("")) {
                direccion = request.getParameter("direccion").toString();
            }
            String login = "";
            if (request.getParameter("login") != null && !request.getParameter("login").equals("")) {
                login = request.getParameter("login").toString();
            } else {
                out.print("Este dato es requerido: login");
                out.close();
                return;
            }
            String contrasenia = "";
            if (request.getParameter("contrasenia") != null && !request.getParameter("contrasenia").equals("")) {
                contrasenia = request.getParameter("contrasenia").toString();
            } else {
                out.print("Este dato es requerido: contraseña");
                out.close();
                return;
            }
            String ipacceso = "*";
            if (request.getParameter("ipacceso") != null && !request.getParameter("ipacceso").equals("")) {
                ipacceso = request.getParameter("ipacceso").toString();
            }
            String grupo = "";
            if (request.getParameter("grupo") != null && !request.getParameter("grupo").equals("")) {
                grupo = request.getParameter("grupo").toString();
            } else {
                out.print("Este dato es requerido: grupo");
                out.close();
                return;
            }
            String caja = "0";
            if (request.getParameter("caja") != null && !request.getParameter("caja").equals("")) {
                caja = request.getParameter("caja").toString();
            } else {
                out.print("Este dato es requerido: caja");
                out.close();
                return;
            }
            String categoria = "";
            if (request.getParameter("categoria") != null && !request.getParameter("categoria").equals("")) {
                categoria = request.getParameter("categoria").toString();
            } else {
                out.println("No se suministró la categorìa");
                out.close();
                return;
            }
            TPersona persona = (TPersona) dao.load(TPersona.class, IDPERSONA);
            if (persona == null) {
                out.println("ERROR AL PROCESAR A LA PERSONA");
                dao.cerrarSession();
                out.close();
                return;
            }
            persona.setDocIdentidad(dni);
            persona.setNombre(nombres.toUpperCase());
            persona.setApellidos(apellidos.toUpperCase());
            persona.setEmail(email);
            persona.setUbigeo(ubigeo);
            persona.setTelefono(telefono);
            persona.setCelular(celular);
            persona.setDireccion(direccion.toUpperCase());
            persona.setEstado(estado);
            persona.setIdUser("ID");
            persona.setIpUser("IP");
            persona.setDateUser("DATE");
            TCategoriaPersona tcategoria = (TCategoriaPersona) dao.load(TCategoriaPersona.class, categoria);
            if (categoria == null) {
                out.println("Error en la categoría de Persona");
                dao.cerrarSession();
                out.close();
                return;
            }
            persona.setTCategoriaPersona(tcategoria);
            persona.setRuc(ruc);
            dao.update();
            if (!"0".equals(caja)) {
                TControlTipo control_grupo = (TControlTipo) dao.findAll("from TControlTipo where TPersona.idUserPk ='" + persona.getIdUserPk() + "'").get(0);
                TTipoPersona ttipopersona = (TTipoPersona) dao.load(TTipoPersona.class, grupo);
                control_grupo.setTTipoPersona(ttipopersona);
                control_grupo.setEstado(estado);
                control_grupo.setIdUser("ID " + request.getSession().getId());
                control_grupo.setIpUser("IP " + request.getRemoteAddr());
                control_grupo.setDateUser("DATE " + request.getSession().getLastAccessedTime());
                dao.update();
                TCuentaAcceso cuenta = (TCuentaAcceso) dao.findAll("from TCuentaAcceso where TPersona.idUserPk ='" + persona.getIdUserPk() + "'").get(0);
                cuenta.setLogin(ConvertUtil.prepareStringParameter(login.toLowerCase()));
                if (!cuenta.getContrasenia().equals(contrasenia)) {
                    cuenta.setContrasenia(MD5.encriptar(contrasenia));
                    cuenta.setKeyring(MD5.encriptar(MD5.encriptar(contrasenia).toLowerCase() + login.toLowerCase()));
                }
                cuenta.setEstado(estado);
                cuenta.setIdUser("ID");
                cuenta.setIpUser("IP");
                cuenta.setDateUser("DATE");
                cuenta.setIpAcceso(ipacceso);
                dao.update();
                TPersonaCaja pcaja = (TPersonaCaja) dao.findAll("from TPersonaCaja where TPersona.idUserPk ='" + persona.getIdUserPk() + "'").get(0);
                TCaja tcaja = (TCaja) dao.load(TCaja.class, caja);
                pcaja.setEstado(estado);
                pcaja.setTCaja(tcaja);
                pcaja.setIdUser("ID");
                pcaja.setIpUser("IP");
                pcaja.setDateUser("DATE");
                dao.update();
            }
            out.println("OK");
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
