package org.finance.bank.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.finance.bank.bean.TCertificado;
import org.finance.bank.bean.TCobranza;
import org.finance.bank.bean.TControlTipo;
import org.finance.bank.bean.TCuentaAcceso;
import org.finance.bank.bean.TCuentaPersona;
import org.finance.bank.bean.TDepartamento;
import org.finance.bank.bean.TDetalleCuentaPersona;
import org.finance.bank.bean.TDetalleIntereses;
import org.finance.bank.bean.TOperacion;
import org.finance.bank.bean.TPersona;
import org.finance.bank.bean.TPersonaCaja;
import org.finance.bank.bean.TRegistroCompraVenta;
import org.finance.bank.bean.TRegistroDepositoRetiro;
import org.finance.bank.bean.TRegistroGiro;
import org.finance.bank.bean.TRegistroOtros;
import org.finance.bank.bean.TRegistroPrestamo;
import org.finance.bank.bean.TTransferenciaCaja;
import org.finance.bank.model.dao.DAOGeneral;

/**
 *
 * @author ronald
 */
public class UpdateData {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        DAOGeneral dao = new DAOGeneral();
//        List l = dao.findAll("from TCuentaPersona where estado = 'INACTIVO'");
//        if (l.size() > 0) {
//            Iterator i = l.iterator();
//            while (i.hasNext()) {
//                TCuentaPersona p = (TCuentaPersona) i.next();
//                Set sCP = p.getTDetalleCuentaPersonas();
//                Set sDI = p.getTDetalleIntereseses();
//                Set sDR = p.getTRegistroDepositoRetiros();
//                Set sRP = p.getTRegistroPrestamos();
//                Set sSG = p.getTSobregiros();
//                if (sCP.size() > 0) {
//                    Iterator iCP = sCP.iterator();
//                    while (iCP.hasNext()) {
//                        TDetalleCuentaPersona ct = (TDetalleCuentaPersona) iCP.next();
//                        dao.delete(ct);
//                    }
//                }
//                if (sDI.size() > 0) {
//                    Iterator iDI = sDI.iterator();
//                    while (iDI.hasNext()) {
//                        TDetalleIntereses di = (TDetalleIntereses) iDI.next();
//                        dao.delete(di);
//                    }
//                }
//                if (sDR.size() > 0) {
//                    Iterator iDR = sDR.iterator();
//                    while (iDR.hasNext()) {
//                        TRegistroDepositoRetiro dr = (TRegistroDepositoRetiro) iDR.next();
//                        Set s = dr.getTCertificados();
//                        if (s.size() > 0) {
//                            Iterator iS = s.iterator();
//                            while (iS.hasNext()) {
//                                TCertificado c = (TCertificado) iS.next();
//                                dao.delete(c);
//                            }
//                        }
//                        TOperacion op = dr.getTOperacion();
//                        dao.delete(dr);
//                        dao.delete(op);
//                    }
//                }
//                if (sRP.size() > 0) {
//                    Iterator iRP = sRP.iterator();
//                    while (iRP.hasNext()) {
//                        TRegistroPrestamo rp = (TRegistroPrestamo) iRP.next();
//                        dao.delete(rp);
//                    }
//                }
//                if (sSG.size() > 0) {
//                    Iterator iSG = sSG.iterator();
//                    while (iSG.hasNext()) {
//                        TRegistroGiro sg = (TRegistroGiro) iSG.next();
//                        dao.delete(sg);
//                    }
//                }
//                dao.delete(p);
//            }
//        }
        List l = dao.findAll("from TOperacion where estado='EXTORNADO'");
        if (l.size() > 0) {
            Iterator i = l.iterator();
            while (i.hasNext()) {
                TOperacion o = (TOperacion) i.next();
                System.out.println("o = " + o.getNumeroOperacion());
                Set sC = o.getTCobranzas();
                Set sRCV = o.getTRegistroCompraVentas();
                Set sRG = o.getTRegistroGiros();
                Set sDR = o.getTRegistroDepositoRetiros();
                Set sRO = o.getTRegistroOtroses();
                Set sP = o.getTRegistroPrestamos();
                Set sTC = o.getTTransferenciaCajas();
                if (sC.size() > 0) {
                    Iterator iC = sC.iterator();
                    while (iC.hasNext()) {
                        TCobranza c = (TCobranza) iC.next();
                        dao.delete(c);
                    }
                }
                if (sRCV.size() > 0) {
                    Iterator iRCV = sRCV.iterator();
                    while (iRCV.hasNext()) {
                        TRegistroCompraVenta rcv = (TRegistroCompraVenta) iRCV.next();
                        dao.delete(rcv);
                    }
                }
                if (sRG.size() > 0) {
                    Iterator iRG = sRG.iterator();
                    while (iRG.hasNext()) {
                        TRegistroGiro rg = (TRegistroGiro) iRG.next();
                        dao.delete(rg);
                    }
                }
                if (sDR.size() > 0) {
                    Iterator iDR = sDR.iterator();
                    while (iDR.hasNext()) {
                        TRegistroDepositoRetiro dr = (TRegistroDepositoRetiro) iDR.next();
                        dao.delete(dr);
                    }
                }
                if (sRO.size() > 0) {
                    Iterator iRO = sRO.iterator();
                    while (iRO.hasNext()) {
                        TRegistroOtros ro = (TRegistroOtros) iRO.next();
                        dao.delete(ro);
                    }
                }
                if (sP.size() > 0) {
                    Iterator iP = sP.iterator();
                    while (iP.hasNext()) {
                        TRegistroPrestamo p = (TRegistroPrestamo) iP.next();
                        dao.delete(p);
                    }
                }
                if (sTC.size() > 0) {
                    Iterator iTC = sTC.iterator();
                    while (iTC.hasNext()) {
                        TTransferenciaCaja tc = (TTransferenciaCaja) iTC.next();
                        dao.delete(tc);
                    }
                }
                dao.delete(o);
            }
        }
//        l = dao.findAll("from TOperacion where (TTipoOperacion.codigoTipoOperacion = 'TIPC5' or TTipoOperacion.codigoTipoOperacion = 'TIPC8') and fd <>'2012-12-05'");
//        if (l.size() > 0) {
//            Iterator i = l.iterator();
//            while (i.hasNext()) {
//                TOperacion o = (TOperacion) i.next();
//                System.out.println("o = " + o.getNumeroOperacion());
//                Set sC = o.getTCobranzas();
//                Set sRCV = o.getTRegistroCompraVentas();
//                Set sRG = o.getTRegistroGiros();
//                Set sDR = o.getTRegistroDepositoRetiros();
//                Set sRO = o.getTRegistroOtroses();
//                Set sP = o.getTRegistroPrestamos();
//                Set sTC = o.getTTransferenciaCajas();
//                if (sC.size() > 0) {
//                    Iterator iC = sC.iterator();
//                    while (iC.hasNext()) {
//                        TCobranza c = (TCobranza) iC.next();
//                        dao.delete(c);
//                    }
//                }
//                if (sRCV.size() > 0) {
//                    Iterator iRCV = sRCV.iterator();
//                    while (iRCV.hasNext()) {
//                        TRegistroCompraVenta rcv = (TRegistroCompraVenta) iRCV.next();
//                        dao.delete(rcv);
//                    }
//                }
//                if (sRG.size() > 0) {
//                    System.out.println("sTC = " + o.getNumeroOperacion());
////                    Iterator iRG = sRG.iterator();
////                    while (iRG.hasNext()) {
////                        TRegistroGiro rg = (TRegistroGiro) iRG.next();
////                        if (rg.getEstado().equals("ESPERA")) {
////                            continue;
////                        } else {
////                            dao.delete(rg);
////                        }
////                    }
//                    continue;
//                }
//                if (sDR.size() > 0) {
//                    //continue;
//                    Iterator iDR = sDR.iterator();
//                    while (iDR.hasNext()) {
//                        TRegistroDepositoRetiro dr = (TRegistroDepositoRetiro) iDR.next();
//                        dao.delete(dr);
//                    }
//                }
//                if (sRO.size() > 0) {
//                    Iterator iRO = sRO.iterator();
//                    while (iRO.hasNext()) {
//                        TRegistroOtros ro = (TRegistroOtros) iRO.next();
//                        dao.delete(ro);
//                    }
//                }
//                if (sP.size() > 0) {
//                    Iterator iP = sP.iterator();
//                    while (iP.hasNext()) {
//                        TRegistroPrestamo p = (TRegistroPrestamo) iP.next();
//                        dao.delete(p);
//                    }
//                }
//                if (sTC.size() > 0) {
//                    Iterator iTC = sTC.iterator();
//                    while (iTC.hasNext()) {
//                        TTransferenciaCaja tc = (TTransferenciaCaja) iTC.next();
//                        dao.delete(tc);
//                    }
//                }
//                dao.delete(o);
//            }
//        }
        //----------------------------------------------
        //-------------------------------------------
//        l = dao.findAll("from TPersona where estado like 'INA%'");
//        if (l.size() > 0) {
//            Iterator i = l.iterator();
//            while (i.hasNext()) {
//                TPersona p = (TPersona) i.next();
//                Set sCT = p.getTControlTipos();
//                Set sCA = p.getTCuentaAccesos();
//                Set sCP = p.getTCuentaPersonas();
//                Set sPC = p.getTPersonaCajas();
//                Set sRG = p.getTRegistroGiros();
//                if (sCT.size() > 0) {
//                    Iterator iCT = sCT.iterator();
//                    while (iCT.hasNext()) {
//                        TControlTipo ct = (TControlTipo) iCT.next();
//                        dao.delete(ct);
//                    }
//                }
//                if (sCA.size() > 0) {
//                    Iterator iCA = sCA.iterator();
//                    while (iCA.hasNext()) {
//                        TCuentaAcceso ca = (TCuentaAcceso) iCA.next();
//                        dao.delete(ca);
//                    }
//                }
//                if (sCP.size() > 0) {
//                    Iterator iCP = sCP.iterator();
//                    while (iCP.hasNext()) {
//                        TCuentaPersona cp = (TCuentaPersona) iCP.next();
//                        dao.delete(cp);
//                    }
//                }
//                if (sPC.size() > 0) {
//                    Iterator iPC = sPC.iterator();
//                    while (iPC.hasNext()) {
//                        TPersonaCaja pc = (TPersonaCaja) iPC.next();
//                        dao.delete(pc);
//                    }
//                }
//                if (sRG.size() > 0) {
//                    Iterator iRG = sRG.iterator();
//                    while (iRG.hasNext()) {
//                        TRegistroGiro rg = (TRegistroGiro) iRG.next();
//                        dao.delete(rg);
//                    }
//                }
//                dao.delete(p);
//            }
//        }
        System.out.println("DONE!");
        //////////
//        String fechaselected = "2012/09/30";
//        String hasta = "2012/09/03";
//        Date f1 = DateUtil.convertStringToDate(fechaselected);
//        Date f2 = DateUtil.convertStringToDate(hasta);
//        int i = f1.compareTo(f2);
//        String f = (i == -1) ? " (fecha >= '" + fechaselected + "' and fecha <= '" + hasta + "') " : ((i == 0) ? "fecha like '" + fechaselected+"%'" : " (fecha >= '" + hasta + "' and fecha <= '" + fechaselected + "') ");
//        System.out.println("f="+f);
        ////
//        System.out.println("" + UpdateData.class.getSimpleName());
//
//        Date f1 = DateUtil.convertStringToDate("2012/12/21");
//        Date f2 = DateUtil.convertStringToDate("2012/12/20");
//        int i = 0;
//        if (f1.equals(f2)) {
//            i = 1;
//        } else if (f1.after(f2)) {
//            i = 2;
//        } else {
//            i = 3;
//        }
//        System.out.println("i=" + 1);
//        System.out.println("ix = " + f1.compareTo(f2));
        //--------------
//        DAOGeneral d=new DAOGeneral();
//        String s="delete from TTransaccion where idcuenta='20110429145839194073' and userLogin='totita'";
//        d.executeUpdate(s);
        // TODO Auto-generated method stub --yyyyMMddhhmm
//        for (int a = 0; a < 5; a++) {
//            Date now = new Date();
//            String FECHAB = DateUtil.getDateTime("yyyy/MM/dd HH:mm:ss", now);
//            boolean f = (boolean) HibernateUtil.firstLogin(now, FECHAB);
//            System.out.println("f = " + f);
//        }
        //----------
//        DAOGeneral dao = new DAOGeneral();
//        String b = DateUtil.getDate(new Date()).replace("/", "");
//        for (int a = 0; a < 5; a++) {
//            System.out.println("a = " + HibernateUtil.firstLogin(b, "20110517163003803024"));
//        }


        //--------------

//        List a=(List)dao.findAll("from TCuentaAcceso");
//        Iterator b=a.iterator();
//        while(b.hasNext()){
//            TCuentaAcceso c=(TCuentaAcceso)b.next();
//            if(c.getKeyring()==null){
//                c.setKeyring(MD5.encriptar(c.getContrasenia().toLowerCase()+c.getLogin().toLowerCase()));
//                dao.update();
//            }
//        }
        //------
//        boolean ne=false;
//        String tested="";
//        while(!ne){
//            tested=DateUtil.convertDateId();
//            ne=HibernateUtil.dbTestId(tested, "idcaja", "nombre clase", "1");
//        }
//        System.out.println("tes="+tested);
//-----
//        ArrayList a = new ArrayList();
//        a.add("INICIO");
//        for (int b = 0; b < 100; b++) {
//            a.add(DateUtil.convertDateId());
//        }
//        int fund = 0;
//        for (int c = 0; c < a.size(); c++) {
//            boolean r = HibernateUtil.dbTestId(a.get(c).toString(), "YO", String.valueOf(c),"g");
//            if (r) {
//                System.out.println("Insertado=" + a.get(c));
//            } else {
//                fund++;
//                System.out.println("BLOQUED " + c + "-" + a.get(c));
//            }
//        }
//        System.out.println("Bloqueados = " + fund);
//        HibernateUtil.truncarBloqueo();
        //       -----
//        ArrayList a = new ArrayList();
//        a.add("INICIO");
//        for (int b = 0; b < 1000; b++) {
//            SimpleDateFormat df = new SimpleDateFormat("ss");
////            a.add(df.format(new Date()) + (int) (Math.ceil(Math.random() * 100000)) + "");
//            a.add((df.format(new Date()) + Double.toString(System.nanoTime() * 100000L / Math.random()).replace(".", "").replace("-", "0")).substring(11));
//        }
//        for (int c = 0; c < a.size(); c++) {
//            System.out.println("a" + c + " = " + a.get(c));
//        }

//        List bo = dao.findAll("from TOperacion where fecha not like '2012/04/21%' and TTipoOperacion.codigoTipoOperacion='TIPC12' order by fecha");
//        Iterator it = bo.iterator();
//        while (it.hasNext()) {
//            TOperacion o = (TOperacion) it.next();
//            Set xT = o.getTTransferenciaCajas();
//            if (!xT.isEmpty()) {
//                Iterator i = xT.iterator();
//                while (i.hasNext()) {
//                    TTransferenciaCaja x=(TTransferenciaCaja)i.next();
//                            dao.delete(x);
//                }
//            }
//            dao.delete(o);
//        }
//---------
//        Date f1 = DateUtil.convertStringToDate("yyyy/MM/dd", "2012/04/24");
//        Date f2 = DateUtil.convertStringToDate("yyyy/MM/dd", "2012/05/08");
//        Calendar c = Calendar.getInstance();
//        c.setTime(f1);
//        Long when = 0L;
//        while (f2.after(f1)) {
//            List mons = dao.findAll("from TMoneda where estado='ACTIVO'");
//            if (!mons.isEmpty()) {
//                Iterator iMons = mons.iterator();
//                while (iMons.hasNext()) {
//                    TMoneda m = (TMoneda) iMons.next();
//                    TBalancexmoneda bi = (TBalancexmoneda) dao.load(TBalancexmoneda.class, DateUtil.getDateAnt(f1).replaceAll("/", "") + "0" + m.getCodParaNumCuenta() + "0000000000");
//                    TBalancexmoneda bf = (TBalancexmoneda) dao.load(TBalancexmoneda.class, DateUtil.getDate(f1).replaceAll("/", "") + "0" + m.getCodParaNumCuenta() + "0000000000");
//                    if (bi == null && bf != null) {
//                        System.out.println("bi f1=" + f1);
//                        //public TBalancexmoneda(String idbalance, TMoneda TMoneda, BigDecimal activoCajaybanco,
////BigDecimal activoCuentaxcobrar, BigDecimal pasivo, BigDecimal encaje, BigDecimal PRespaldo,
////String estado, String idUser, String ipUser, String dateUser)
//                        TBalancexmoneda balance = new TBalancexmoneda(DateUtil.getDateAnt(f1).replaceAll("/", "") + "0" + m.getCodParaNumCuenta() + "0000000000", m, BigDecimal.ZERO,
//                                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
//                                "TRANSIT", "ADMIN", "LOCAL", DateUtil.getDateTime("yyyy/MM/dd HH:mm:ss", f1));
//                        balance.setFd(new Date());
//                        dao.persist(balance);
//                        List lFil = dao.findAll("from TFilial where estado = 'ACTIVO'");
//                        if (lFil.isEmpty()) {
//                            System.out.println("lFil = " + "Error de Lectura de BD...");
//                        }
//                        Iterator iFil = lFil.iterator();
//                        while (iFil.hasNext()) {
//                            TFilial iFilial = (TFilial) iFil.next();
//                            when += 1L;
//                            String ex = String.valueOf(when);
//                            TPatrimonio newPatrimonio = new TPatrimonio(DateUtil.getDate(f1).replaceAll("/", "") + "000000000000".substring(ex.length()) + when, balance, BigDecimal.ZERO,
//                                    BigDecimal.ZERO, "ACTIVO" + iFilial.getCodFilial(), "ADMIN", "LOCAL", DateUtil.getDateTime("yyyy/MM/dd HH:mm:ss", f1));
//                            newPatrimonio.setTipoVariacion("INICIAL");
//                            dao.persist(newPatrimonio);
//                        }//fin filiales
//                    }
//                    if (bi == null && bf == null) {
//                        //public TBalancexmoneda(String idbalance, TMoneda TMoneda, BigDecimal activoCajaybanco,
////BigDecimal activoCuentaxcobrar, BigDecimal pasivo, BigDecimal encaje, BigDecimal PRespaldo,
////String estado, String idUser, String ipUser, String dateUser)
//                        TBalancexmoneda balance = new TBalancexmoneda(DateUtil.getDate(f1).replaceAll("/", "") + "0" + m.getCodParaNumCuenta() + "0000000000", m, BigDecimal.ZERO,
//                                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
//                                "TRANSIT", "ADMIN", "LOCAL", DateUtil.getDateTime("yyyy/MM/dd HH:mm:ss", f1));
//                        balance.setFd(new Date());
//                        dao.persist(balance);
//                        List lFil = dao.findAll("from TFilial where estado = 'ACTIVO'");
//                        if (lFil.isEmpty()) {
//                            System.out.println("Error de Lectura de BD...");
//                        }
//                        Iterator iFil = lFil.iterator();
//                        while (iFil.hasNext()) {
//                            when += 1L;
//                            String ex = String.valueOf(when);
//                            TFilial iFilial = (TFilial) iFil.next();
//                            TPatrimonio newPatrimonio = new TPatrimonio(DateUtil.getDate(f1).replaceAll("/", "") + "000000000000".substring(ex.length()) + when, balance, BigDecimal.ZERO,
//                                    BigDecimal.ZERO, "ACTIVO" + iFilial.getCodFilial(), "ADMIN", "LOCAL", DateUtil.getDateTime("yyyy/MM/dd HH:mm:ss", f1));
//                            newPatrimonio.setTipoVariacion("INICIAL");
//                            dao.persist(newPatrimonio);
//                        }//fin filiales
//                    } else {
//                        bf.setActivoCajaybanco(BigDecimal.ZERO);
//                        bf.setActivoCuentaxcobrar(BigDecimal.ZERO);
//                        bf.setPasivo(BigDecimal.ZERO);
//                        bf.setEncaje(BigDecimal.ZERO);
//                        bf.setPRespaldo(BigDecimal.ZERO);
//                        dao.update();
//                        Set ps = bf.getTPatrimonios();
//                        Iterator i = ps.iterator();
//                        while (i.hasNext()) {
//                            TPatrimonio p = (TPatrimonio) i.next();
//                            p.setPatrimonio(BigDecimal.ZERO);
//                            p.setPatrimonioActual(BigDecimal.ZERO);
//                            dao.update();
//                        }
//                    }
//                    System.out.println("bf f1=" + f1);
//                }
//            }
//            c.add(Calendar.DATE, +1);
//            f1 = c.getTime();
//            c.setTime(f1);
//        }
    }
}
