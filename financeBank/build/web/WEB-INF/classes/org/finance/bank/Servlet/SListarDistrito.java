package org.finance.bank.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.finance.bank.bean.TDistrito;
import org.finance.bank.bean.TOperacion;
import org.finance.bank.bean.TRegistroCompraVenta;
import org.finance.bank.bean.TRegistroDepositoRetiro;
import org.finance.bank.bean.TRegistroGiro;
import org.finance.bank.bean.TTransferenciaCaja;
import org.finance.bank.model.dao.DAOGeneral;
import org.finance.bank.util.DateUtil;

/**
 *
 * @author roger
 */
public class SListarDistrito extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.getSession(true);
        DAOGeneral dao = new DAOGeneral();
        String iddep = request.getParameter("iddep");
        if (iddep == null) {
            //gg other proceeds
            String type = request.getParameter("type");
            if (type == null) {
                dao.cerrarSession();
                out.close();
                return;
            }
            String data = request.getParameter("data");
            if (type == null) {
                dao.cerrarSession();
                out.close();
                return;
            }
            String[] dataF = data.split(";");
            if (type.equals("fast")) {
                String tipoC = dataF[0];
                String datoF = dataF[1].replace(":", "&");
                String datoE = dataF[2];
                if (tipoC.equals("MONTOX1")) {
                    List listDatos = dao.findAll("from TOperacion where numeroOperacion like '%" + datoF.toUpperCase() + "' and fecha like '" + DateUtil.getDate(new Date()) + "%'");
                    if (datoE.equals("n")) {
                        if (listDatos.isEmpty()) {
                            out.print("NO HAY RESULTADOS");
                        } else {
                            if (listDatos.size() > 40) {
                                out.print("<font color='red'>HAY DEMASIADOS RESULTADOS, ajuste más su búsqueda...</font>");
                                for (; listDatos.size() > 40;) {
                                    listDatos.remove(listDatos.size() - 1);
                                }
//                                dao.cerrarSession();
//                                out.close();
//                                return;
                            }
                            Iterator iD = listDatos.iterator();
                            while (iD.hasNext()) {
                                TOperacion opD = (TOperacion) iD.next();
                                out.print("Num. Oper.:" + opD.getNumeroOperacion().substring(15) + " Fecha: " + opD.getFecha() + " " + opD.getTTipoOperacion().getNombre()
                                        + " (" + opD.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + opD.getIdOperacion() + "');\" /> <br/>");
                            }
                        }
                    } else {
                        out.print("Su busqueda tuvo " + listDatos.size() + " Resultados.");
                    }
                } else if (tipoC.equals("MONTOX2")) {
                    List listDatos = dao.findAll("from TRegistroDepositoRetiro where nombreRepresentante like '%" + datoF.toUpperCase() + "%' and fecha like '" + DateUtil.getDate(new Date()) + "%'");
                    if (listDatos.isEmpty()) {
                        listDatos = dao.findAll("from TRegistroDepositoRetiro where apellidosRepresentante like '%" + datoF.toUpperCase() + "%' and fecha like '" + DateUtil.getDate(new Date()) + "%'");
                    }
                    if (datoE.equals("n")) {
                        if (listDatos.isEmpty()) {
                            out.print("NO HAY RESULTADOS");
                        } else {
                            if (listDatos.size() > 40) {
                                out.print("<font color='red'>HAY DEMASIADOS RESULTADOS, ajuste más su búsqueda...</font>");
                                for (; listDatos.size() > 40;) {
                                    listDatos.remove(listDatos.size() - 1);
                                }
//                                dao.cerrarSession();
//                                out.close();
//                                return;
                            }
                            Iterator iD = listDatos.iterator();
                            while (iD.hasNext()) {
                                TRegistroDepositoRetiro reD = (TRegistroDepositoRetiro) iD.next();
                                out.print("Num. Oper.:" + reD.getTOperacion().getNumeroOperacion().substring(15) + " fecha: " + reD.getTOperacion().getFecha() + " " + reD.getTOperacion().getTTipoOperacion().getNombre()
                                        + " (" + reD.getTOperacion().getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reD.getTOperacion().getIdOperacion() + "');\" /> <br/>");
                            }
                        }
                    } else {
                        out.print("Su busqueda tuvo " + listDatos.size() + " Resultados.");
                    }
                } else if (tipoC.equals("MONTOX3")) {
                    List listDatos = dao.findAll("from TRegistroDepositoRetiro where numCta like '%" + datoF + "%' and fecha like '" + DateUtil.getDate(new Date()) + "%'");
                    if (datoE.equals("n")) {
                        if (listDatos.isEmpty()) {
                            out.print("NO HAY RESULTADOS");
                        } else {
                            if (listDatos.size() > 40) {
                                out.print("<font color='red'>HAY DEMASIADOS RESULTADOS, ajuste más su búsqueda...</font>");
                                for (; listDatos.size() > 40;) {
                                    listDatos.remove(listDatos.size() - 1);
                                }
//                                dao.cerrarSession();
//                                out.close();
//                                return;
                            }
                            Iterator iD = listDatos.iterator();
                            while (iD.hasNext()) {
                                TRegistroDepositoRetiro reD = (TRegistroDepositoRetiro) iD.next();
                                out.print("Num. Oper.:" + reD.getTOperacion().getNumeroOperacion().substring(15) + " fecha: " + reD.getTOperacion().getFecha() + " " + reD.getTOperacion().getTTipoOperacion().getNombre()
                                        + " (" + reD.getTOperacion().getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reD.getTOperacion().getIdOperacion() + "');\" /> <br/>");
                            }
                        }
                    } else {
                        out.print("Su busqueda tuvo " + listDatos.size() + " Resultados.");
                    }
                } else if (tipoC.equals("MONTOX4")) {
                    Logger.getLogger(SListarDistrito.class.getName()).log(Level.INFO, "datoF = " + datoF.length());
                    if (datoF.length() < 2) {
                        if (datoF.length() == 0) {
                            datoF = "00";
                        } else {
                            datoF = "0" + datoF;
                        }
                    } else {
                        datoF = datoF.substring(0, 2);
                    }
                    List listDatos = dao.findAll("from TOperacion where fecha like '" + DateUtil.getDate(new Date()) + " " + datoF + "%'");
                    if (datoE.equals("n")) {
                        if (listDatos.isEmpty()) {
                            out.print("NO HAY RESULTADOS");
                        } else {
                            if (listDatos.size() > 40) {
                                out.print("<font color='red'>HAY DEMASIADOS RESULTADOS, ajuste más su búsqueda...</font>");
                                for (; listDatos.size() > 40;) {
                                    listDatos.remove(listDatos.size() - 1);
                                }
//                                dao.cerrarSession();
//                                out.close();
//                                return;
                            }
                            Iterator iD = listDatos.iterator();
                            while (iD.hasNext()) {
                                TOperacion reO = (TOperacion) iD.next();
                                out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                        + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                            }
                        }
                    } else {
                        out.print("Su busqueda tuvo " + listDatos.size() + " Resultados.");
                    }
                } else if (tipoC.equals("MONTOX5")) {
                    List listDatos = dao.findAll("from TOperacion where TTipoOperacion.codigoTipoOperacion='" + datoF.toUpperCase() + "' and fecha like '" + DateUtil.getDate(new Date()) + "%'");
                    if (datoE.equals("n")) {
                        if (listDatos.isEmpty()) {
                            out.print("NO HAY RESULTADOS");
                        } else {
                            if (listDatos.size() > 40) {
                                out.print("<font color='red'>HAY DEMASIADOS RESULTADOS, ajuste más su búsqueda...</font>");
                                for (; listDatos.size() > 40;) {
                                    listDatos.remove(listDatos.size() - 1);
                                }
//                                dao.cerrarSession();
//                                out.close();
//                                return;
                            }
                            Iterator iD = listDatos.iterator();
                            while (iD.hasNext()) {
                                TOperacion reO = (TOperacion) iD.next();
                                out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                        + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                            }
                        }
                    } else {
                        out.print("Su busqueda tuvo " + listDatos.size() + " Resultados.");
                    }
                } else if (tipoC.equals("MONTOX6")) {
                    datoF = datoF.replace(",", "");
                    List listDatos = dao.findAll("from TOperacion where fecha like '" + DateUtil.getDate(new Date()) + "%'");
                    if (datoE.equals("n")) {
                        if (listDatos.isEmpty()) {
                            System.out.println("on=" + listDatos.size());
                            out.print("NO HAY RESULTADOS");
                        } else {
                            Iterator iD = listDatos.iterator();
                            int t = 0;
                            while (iD.hasNext()) {
                                TOperacion reO = (TOperacion) iD.next();
                                int chain = (int) Integer.parseInt(reO.getTTipoOperacion().getCodigoTipoOperacion().substring(4));
                                switch (chain) {
                                    case 1:
                                        TRegistroCompraVenta compra = (TRegistroCompraVenta) reO.getTRegistroCompraVentas().iterator().next();
                                        if (compra.getMontoRecibido().equals(new BigDecimal(datoF)) || compra.getMontoEntregado().equals(new BigDecimal(datoF))) {
                                            out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                                    + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                                            t++;
                                        }
                                        break;
                                    case 2:
                                        TRegistroCompraVenta venta = (TRegistroCompraVenta) reO.getTRegistroCompraVentas().iterator().next();
                                        if (venta.getMontoRecibido().equals(new BigDecimal(datoF)) || venta.getMontoEntregado().equals(new BigDecimal(datoF))) {
                                            out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                                    + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                                            t++;
                                        }
                                        break;
                                    case 3:
                                        TRegistroDepositoRetiro deposito = (TRegistroDepositoRetiro) reO.getTRegistroDepositoRetiros().iterator().next();
                                        if (deposito.getImporte().equals(new BigDecimal(datoF))) {
                                            out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                                    + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                                            t++;
                                        }
                                        break;
                                    case 4:
                                        TRegistroDepositoRetiro retiro = (TRegistroDepositoRetiro) reO.getTRegistroDepositoRetiros().iterator().next();
                                        if (retiro.getImporte().equals(new BigDecimal(datoF))) {
                                            out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                                    + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                                            t++;
                                        }
                                        break;
                                    case 5:
                                        TRegistroGiro giro = (TRegistroGiro) reO.getTRegistroGiros().iterator().next();
                                        if (giro.getImporte().equals(new BigDecimal(datoF))) {
                                            out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                                    + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                                            t++;
                                        }
                                        break;
                                    case 6:
                                        break;
                                    case 7:
                                        break;
                                    case 8:
                                        TRegistroGiro giro_cobro = null;
                                        List lL = dao.findAll("from TRegistroGiro where idoperacioncobro='" + reO.getIdOperacion() + "'");
                                        if (lL.isEmpty()) {
                                            out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                                    + " (" + reO.getTMoneda().getSimbolo() + ") OP. COBROGIRO SIN LEER <br/>");
                                            t++;
                                        } else {
                                            try {
                                                giro_cobro = (TRegistroGiro) lL.get(0);
                                            } catch (Exception ex) {
                                                Logger.getLogger(SListarDistrito.class.getName()).log(Level.WARNING, ex.getMessage());
                                                out.print("FALLO AL CARGAR EL DATO!!! " + ex.getMessage());
                                                dao.cerrarSession();
                                                out.close();
                                                return;
                                            }
                                            if (giro_cobro.getImporte().equals(new BigDecimal(datoF))) {
                                                out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                                        + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                                                t++;
                                            }
                                        }
                                        break;
                                    case 9:
                                        TTransferenciaCaja ttc = (TTransferenciaCaja) reO.getTTransferenciaCajas().iterator().next();
                                        if (ttc.getMonto().equals(new BigDecimal(datoF))) {
                                            out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                                    + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                                            t++;
                                        }
                                        break;
                                    case 10:
                                        break;
                                    case 11:
                                        break;
                                    case 12:
                                        break;
                                    case 13:
                                        break;
                                    case 14:
                                        break;
                                }
                                if (t > 40) {
                                    out.print("<font color='red'>HAY DEMASIADOS RESULTADOS, ajuste más su búsqueda...</font>");
                                    dao.cerrarSession();
                                    out.close();
                                    return;
                                }
                            }
                            if (t == 0) {
                                out.print("NO HAY RESULTADOS");
                            }
                        }
                    } else {
                        out.print("Su busqueda tuvo " + listDatos.size() + " Resultados.");
                    }
                } else {
                    out.print("Sin Resultados...");
                }
            } else {
                String tipoC = dataF[0];
                String datoF = dataF[1].replace(":", "&");
                String f1 = dataF[2];
                String f2 = dataF[3];
                Date d1 = DateUtil.convertStringToDate("dd/MM/yyyy", f1);
                Date d2 = DateUtil.convertStringToDate("dd/MM/yyyy", f2);
                String datoE = dataF[4];
                if (d1.equals(d2)) {
                    if (tipoC.equals("MONTOX1")) {
                        List listDatos = dao.findAll("from TOperacion where numeroOperacion like '%" + datoF.toUpperCase() + "' and fecha like '" + DateUtil.getDate(d1) + "%'");
                        if (datoE.equals("n")) {
                            if (listDatos.isEmpty()) {
                                out.print("NO HAY RESULTADOS");
                            } else {
                                if (listDatos.size() > 40) {
                                    out.print("<font color='red'>HAY DEMASIADOS RESULTADOS, ajuste más su búsqueda...</font>");
                                    for (; listDatos.size() > 40;) {
                                        listDatos.remove(listDatos.size() - 1);
                                    }
                                }
                                Iterator iD = listDatos.iterator();
                                while (iD.hasNext()) {
                                    TOperacion opD = (TOperacion) iD.next();
                                    out.print("Num. Oper.:" + opD.getNumeroOperacion().substring(15) + " Fecha: " + opD.getFecha() + " " + opD.getTTipoOperacion().getNombre()
                                            + " (" + opD.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + opD.getIdOperacion() + "');\" /> <br/>");
                                }
                            }
                        } else {
                            out.print("Su busqueda tuvo " + listDatos.size() + " Resultados.");
                        }
                    } else if (tipoC.equals("MONTOX2")) {
                        List listDatos = dao.findAll("from TRegistroDepositoRetiro where nombreRepresentante like '%" + datoF.toUpperCase() + "%' and fecha like '" + DateUtil.getDate(d1) + "%'");
                        if (listDatos.isEmpty()) {
                            listDatos = dao.findAll("from TRegistroDepositoRetiro where apellidosRepresentante like '%" + datoF.toUpperCase() + "%' and fecha like '" + DateUtil.getDate(d1) + "%'");
                        }
                        if (datoE.equals("n")) {
                            if (listDatos.isEmpty()) {
                                out.print("NO HAY RESULTADOS");
                            } else {
                                if (listDatos.size() > 40) {
                                    out.print("<font color='red'>HAY DEMASIADOS RESULTADOS, ajuste más su búsqueda...</font>");
                                    for (; listDatos.size() > 40;) {
                                        listDatos.remove(listDatos.size() - 1);
                                    }
                                }
                                Iterator iD = listDatos.iterator();
                                while (iD.hasNext()) {
                                    TRegistroDepositoRetiro reD = (TRegistroDepositoRetiro) iD.next();
                                    out.print("Num. Oper.:" + reD.getTOperacion().getNumeroOperacion().substring(15) + " fecha: " + reD.getTOperacion().getFecha() + " " + reD.getTOperacion().getTTipoOperacion().getNombre()
                                            + " (" + reD.getTOperacion().getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reD.getTOperacion().getIdOperacion() + "');\" /> <br/>");
                                }
                            }
                        } else {
                            out.print("Su busqueda tuvo " + listDatos.size() + " Resultados.");
                        }
                    } else if (tipoC.equals("MONTOX3")) {
                        List listDatos = dao.findAll("from TRegistroDepositoRetiro where numCta like '%" + datoF + "%' and fecha like '" + DateUtil.getDate(d1) + "%'");
                        if (datoE.equals("n")) {
                            if (listDatos.isEmpty()) {
                                out.print("NO HAY RESULTADOS");
                            } else {
                                if (listDatos.size() > 40) {
                                    out.print("<font color='red'>HAY DEMASIADOS RESULTADOS, ajuste más su búsqueda...</font>");
                                    for (; listDatos.size() > 40;) {
                                        listDatos.remove(listDatos.size() - 1);
                                    }
                                }
                                Iterator iD = listDatos.iterator();
                                while (iD.hasNext()) {
                                    TRegistroDepositoRetiro reD = (TRegistroDepositoRetiro) iD.next();
                                    out.print("Num. Oper.:" + reD.getTOperacion().getNumeroOperacion().substring(15) + " fecha: " + reD.getTOperacion().getFecha() + " " + reD.getTOperacion().getTTipoOperacion().getNombre()
                                            + " (" + reD.getTOperacion().getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reD.getTOperacion().getIdOperacion() + "');\" /> <br/>");
                                }
                            }
                        } else {
                            out.print("Su busqueda tuvo " + listDatos.size() + " Resultados.");
                        }
                    } else if (tipoC.equals("MONTOX4")) {
                        Logger.getLogger(SListarDistrito.class.getName()).log(Level.INFO, "datoF = " + datoF.length());
                        if (datoF.length() < 2) {
                            if (datoF.length() == 0) {
                                datoF = "00";
                            } else {
                                datoF = "0" + datoF;
                            }
                        } else {
                            datoF = datoF.substring(0, 2);
                        }
                        List listDatos = dao.findAll("from TOperacion where fecha like '" + DateUtil.getDate(d1) + " " + datoF + "%'");
                        if (datoE.equals("n")) {
                            if (listDatos.isEmpty()) {
                                out.print("NO HAY RESULTADOS");
                            } else {
                                if (listDatos.size() > 40) {
                                    out.print("<font color='red'>HAY DEMASIADOS RESULTADOS, ajuste más su búsqueda...</font>");
                                    for (; listDatos.size() > 40;) {
                                        listDatos.remove(listDatos.size() - 1);
                                    }
                                }
                                Iterator iD = listDatos.iterator();
                                while (iD.hasNext()) {
                                    TOperacion reO = (TOperacion) iD.next();
                                    out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                            + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                                }
                            }
                        } else {
                            out.print("Su busqueda tuvo " + listDatos.size() + " Resultados.");
                        }
                    } else if (tipoC.equals("MONTOX5")) {
                        List listDatos = dao.findAll("from TOperacion where TTipoOperacion.codigoTipoOperacion='" + datoF.toUpperCase() + "' and fecha like '" + DateUtil.getDate(d1) + "%'");
                        if (datoE.equals("n")) {
                            if (listDatos.isEmpty()) {
                                out.print("NO HAY RESULTADOS");
                            } else {
                                if (listDatos.size() > 40) {
                                    out.print("<font color='red'>HAY DEMASIADOS RESULTADOS, ajuste más su búsqueda...</font>");
                                    for (; listDatos.size() > 40;) {
                                        listDatos.remove(listDatos.size() - 1);
                                    }
                                }
                                Iterator iD = listDatos.iterator();
                                while (iD.hasNext()) {
                                    TOperacion reO = (TOperacion) iD.next();
                                    out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                            + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                                }
                            }
                        } else {
                            out.print("Su busqueda tuvo " + listDatos.size() + " Resultados.");
                        }
                    } else if (tipoC.equals("MONTOX6")) {
                        datoF = datoF.replace(",", "");
                        List listDatos = dao.findAll("from TOperacion where fecha like '" + DateUtil.getDate(d1) + "%'");
                        if (datoE.equals("n")) {
                            if (listDatos.isEmpty()) {
                                out.print("NO HAY RESULTADOS");
                            } else {
                                Iterator iD = listDatos.iterator();
                                int t = 0;
                                while (iD.hasNext()) {
                                    TOperacion reO = (TOperacion) iD.next();
                                    int chain = (int) Integer.parseInt(reO.getTTipoOperacion().getCodigoTipoOperacion().substring(4));
                                    switch (chain) {
                                        case 1:
                                            TRegistroCompraVenta compra = (TRegistroCompraVenta) reO.getTRegistroCompraVentas().iterator().next();
                                            if (compra.getMontoRecibido().equals(new BigDecimal(datoF)) || compra.getMontoEntregado().equals(new BigDecimal(datoF))) {
                                                out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                                        + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                                                t++;
                                            }
                                            break;
                                        case 2:
                                            TRegistroCompraVenta venta = (TRegistroCompraVenta) reO.getTRegistroCompraVentas().iterator().next();
                                            if (venta.getMontoRecibido().equals(new BigDecimal(datoF)) || venta.getMontoEntregado().equals(new BigDecimal(datoF))) {
                                                out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                                        + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                                                t++;
                                            }
                                            break;
                                        case 3:
                                            TRegistroDepositoRetiro deposito = (TRegistroDepositoRetiro) reO.getTRegistroDepositoRetiros().iterator().next();
                                            if (deposito.getImporte().equals(new BigDecimal(datoF))) {
                                                out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                                        + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                                                t++;
                                            }
                                            break;
                                        case 4:
                                            TRegistroDepositoRetiro retiro = (TRegistroDepositoRetiro) reO.getTRegistroDepositoRetiros().iterator().next();
                                            if (retiro.getImporte().equals(new BigDecimal(datoF))) {
                                                out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                                        + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                                                t++;
                                            }
                                            break;
                                        case 5:
                                            TRegistroGiro giro = (TRegistroGiro) reO.getTRegistroGiros().iterator().next();
                                            if (giro.getImporte().equals(new BigDecimal(datoF))) {
                                                out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                                        + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                                                t++;
                                            }
                                            break;
                                        case 6:
                                            break;
                                        case 7:
                                            break;
                                        case 8:
                                            TRegistroGiro giro_cobro = null;
                                            List lL = dao.findAll("from TRegistroGiro where idoperacioncobro='" + reO.getIdOperacion() + "'");
                                            if (lL.isEmpty()) {
                                                out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                                        + " (" + reO.getTMoneda().getSimbolo() + ") O. COBROGIRO SIN LEER <br/>");
                                                t++;
                                            } else {
                                                try {
                                                    giro_cobro = (TRegistroGiro) lL.get(0);
                                                } catch (Exception ex) {
                                                    Logger.getLogger(SListarDistrito.class.getName()).log(Level.INFO, ex.getMessage());
                                                    out.print("FALLO AL CARGAR EL DATO!!! " + ex.getMessage());
                                                    dao.cerrarSession();
                                                    out.close();
                                                    return;
                                                }
                                                if (giro_cobro.getImporte().equals(new BigDecimal(datoF))) {
                                                    out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                                            + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                                                    t++;
                                                }
                                            }
                                            break;
                                        case 9:
                                            TTransferenciaCaja ttc = (TTransferenciaCaja) reO.getTTransferenciaCajas().iterator().next();
                                            if (ttc.getMonto().equals(new BigDecimal(datoF))) {
                                                out.print("Num. Oper.:" + reO.getNumeroOperacion().substring(15) + " fecha: " + reO.getFecha() + " " + reO.getTTipoOperacion().getNombre()
                                                        + " (" + reO.getTMoneda().getSimbolo() + ") <input type='button' value='MOSTRAR' onclick=\"mostrarOp('" + reO.getIdOperacion() + "');\" /> <br/>");
                                                t++;
                                            }
                                            break;
                                        case 10:
                                            break;
                                        case 11:
                                            break;
                                        case 12:
                                            break;
                                        case 13:
                                            break;
                                        case 14:
                                            break;
                                    }
                                    if (t > 40) {
                                        out.print("<font color='red'>HAY DEMASIADOS RESULTADOS, ajuste más su búsqueda...</font>");
                                        dao.cerrarSession();
                                        out.close();
                                        return;
                                    }
                                }
                                if (t == 0) {
                                    out.print("NO HAY RESULTADOS");
                                }
                            }
                        } else {
                            out.print("Su busqueda tuvo " + listDatos.size() + " Resultados.");
                        }
                    } else {
                        out.print("Sin Resultados...");
                    }
                } else if (d1.before(d2)) {
                    out.print("AUN NO SE IMPLEMENTO");
                } else {
                    out.print("AUN NO SE IMPLEMENTO");
                }
            }
            dao.cerrarSession();
            out.close();
            return;
        }
        try {
            List result = dao.findAll("from TDistrito dist where dist.TProvincia='" + iddep + "'");
            Iterator it = result.iterator();
            if (result.size() > 0) {
                out.println("<select id='seldistrito' name='seldistrito'>");
                out.println("<option value='0'>(Seleccione Distrito)</option>");
                while (it.hasNext()) {
                    TDistrito distrito = (TDistrito) it.next();
                    out.println("<option value='" + distrito.getIddistrito() + "'>" + distrito.getDescripcion() + "</option>");
                }
                out.println("</select>");
            } else {
                out.println("<select id='seldistrito' name='seldistrito'>");
                out.println("<option value='0'>No hay Distritos</option>");
                out.println("</select>");
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
