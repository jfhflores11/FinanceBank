package org.finance.bank.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.finance.bank.model.dao.DAOGeneral;

/**
 *
 * @author oscar
 */
public final class PrestamoUtil {

    public static double intEfectivo(BigDecimal sa, int i, BigDecimal t) {
        return DateUtil.round(sa.doubleValue() * (Math.pow(1D + t.doubleValue() / 100D, i / 360D) - 1D), 2);
    }
    private double periodo;

    public PrestamoUtil() {
        setReset();
    }
    private boolean recalculated;

    public void setReset() {
        /**Monto inicial (por defecto)*/
        this.XLEW_1_1_2 = 0D;
        /*Plazo XLEW_1_2_2 en meses (por defecto)*/
        this.XLEW_1_2_2 = 1D;
        /*Interés Anual, sabiendo que año tiene 12 meses y 360 días comerciales (por defecto)*/
        this.XLEW_1_3_2 = 0.0D;
        this.periodo = 30;
        recalculated = false;
    }

    public PrestamoUtil(double monto, double plazo, double tasa, double periodo) {
        /**Monto inicial (por defecto)*/
        this.XLEW_1_1_2 = monto;
        /*Plazo XLEW_1_2_2 en meses (por defecto)*/
        this.XLEW_1_2_2 = plazo;
        /*Interés Anual, sabiendo que año tiene 12 meses y 360 días comerciales (por defecto)*/
        this.XLEW_1_3_2 = tasa;
        this.periodo = periodo;
        recalculated = false;
    }
    private static final String[] fmtstrings = new String[]{",", " ", "S/.", "NUEVOS SOLES", "€", "/", "$"};
    public static final int[] fmtdate6 = new int[]{34, 33, 25, 2}; //--> S/. # ##0.##
    public static final int[] fmtdate7 = new int[]{25, 2, 33, 35}; //--> # ##0.## NUEVOS SOLES
    public static final int[] fmtdate8 = new int[]{25, 2, 33, 36}; //--> # ##0.## €
    public static final int[] fmtdate9 = new int[]{7, 37, 3, 37, 11}; //--> dd/MM/y,yyy
    public static final int[] fmtdate10 = new int[]{38, 25, 2}; //--> $# ##0.##
    /* Input */
    /**Monto XLEW_1_1_2 a prestar*/
    private double XLEW_1_1_2;

    public void setXLEW_1_1_2(double v) {
        this.XLEW_1_1_2 = v;
        recalculated = false;
    }

    public double getXLEW_1_1_2() {
        return XLEW_1_1_2;
    }
    /*Plazo XLEW_1_2_2 en meses*/
    private double XLEW_1_2_2;

    public void setXLEW_1_2_2(double v) {
        this.XLEW_1_2_2 = v;
        recalculated = false;
    }

    public double getXLEW_1_2_2() {
        return XLEW_1_2_2;
    }

    /*Interés Anual, sabiendo que año tiene 12 meses y 360 días comerciales*/
    private double XLEW_1_3_2;

    public void setXLEW_1_3_2(double v) {
        this.XLEW_1_3_2 = v;
        recalculated = false;
    }

    public double getXLEW_1_3_2() {
        return XLEW_1_3_2;
    }

    /* Output */
    /*tasa Interés mensual nominal, se calcula cuando cambia el tasa Interés anual*/
    private double XLEW_1_1_4;

    public double getXLEW_1_1_4() {
        if (!recalculated) {
            calc();
        }
        return XLEW_1_1_4;
    }

    /*Interés ganado por el banco*/
    private double XLEW_1_2_4;

    public double getXLEW_1_2_4() {
        if (!recalculated) {
            calc();
        }
        return XLEW_1_2_4;
    }

    /*Fecha Primer pago*/
    private double XLEW_1_2_6;

    public double getXLEW_1_2_6() {
        if (!recalculated) {
            calc();
        }
        return XLEW_1_2_6;
    }

    /*Cantidad de Meses a pagar*/
    private double XLEW_1_3_4;

    public double getXLEW_1_3_4() {
        if (!recalculated) {
            calc();
        }
        return XLEW_1_3_4;
    }
    private double XLEW_1_3_6;

    public double getXLEW_1_3_6() {
        if (!recalculated) {
            calc();
        }
        return XLEW_1_3_6;
    }

    /*Monto*/
    private double XLEW_1_5_5;

    public double getXLEW_1_5_5() {
        if (!recalculated) {
            calc();
        }
        return XLEW_1_5_5;
    }
    private Object[] eev = new Object[33];
    private double[] een = new double[9];
    public String regPrestamo[][] = null;

    private void calc() {
        int plazos = ((int) Math.round(XLEW_1_2_2)) + 1;
        regPrestamo = new String[plazos][5];
        double montoPrestamo = this.XLEW_1_1_2;
        double tasaAnual = XLEW_1_3_2;
        double tasaMensual = tasaAnual / (12.0D);
        double mesesPrestamo = this.XLEW_1_2_2;
        double fechaHoy = DateUtil.today();
        double pagoMensual = pmt(tasaMensual, mesesPrestamo, (montoPrestamo * (-1.0D)), (0.0D), (0.0D));
        /*Monto*/
        een[1] = montoPrestamo;
        /*Calculo de tasa de Interés mensual nominal*/
        een[0] = tasaMensual;
        /**Cantidad de meses a pagar*/
        een[2] = mesesPrestamo;
        /*Monto*/
        een[3] = montoPrestamo;

        regPrestamo[0][0] = "0";
        regPrestamo[0][1] = "REVISE LOS MONTOS CALCULADOS";
        regPrestamo[0][3] = CurrencyConverter.formatToMoneyUS(montoPrestamo, 2).replace(",", " ");
        regPrestamo[0][4] = DateUtil.String_valueOf(fechaHoy).replace(",", "");
        double capitalFaltante = montoPrestamo;
        double interes = 0;
        double amortizacion = 0;
        double eemonth = 0;
        for (int i = 1; i < plazos; i++) {
            interes = tasaMensual * capitalFaltante;
            amortizacion = DateUtil.round(pagoMensual, 2) - DateUtil.round(interes, 2);
            capitalFaltante = DateUtil.round(capitalFaltante, 2) - amortizacion;
            if (i == mesesPrestamo) {
                pagoMensual += capitalFaltante;
                amortizacion = DateUtil.round(pagoMensual, 2) - DateUtil.round(interes, 2);
                capitalFaltante = 0D;
            }
            eemonth = Math.round(i * periodo);
            double epoca = fechaHoy + eemonth;
            if (DateUtil.mod(epoca - 1, 7) == 0) {
                epoca -= 1;
            }
            regPrestamo[i][0] = CurrencyConverter.formatToMoneyUS(pagoMensual, 2).replace(",", "");
            regPrestamo[i][1] = CurrencyConverter.formatToMoneyUS(interes, 2).replace(",", "");
            regPrestamo[i][2] = CurrencyConverter.formatToMoneyUS(amortizacion, 2).replace(",", "");
            regPrestamo[i][3] = CurrencyConverter.formatToMoneyUS(capitalFaltante, 2).replace(",", "");
            regPrestamo[i][4] = DateUtil.String_valueOf(epoca).replace(",", "");
        }
        eev[22] = Double.parseDouble(regPrestamo[1][4]);
        eev[26] = Double.parseDouble(regPrestamo[plazos - 1][4]);
        een[8] = (pmt((een[0]), (een[2]), (((een[1]) * (-1.0D))), (0.0D), (0.0D)));
        calcContinue2();
    }

    private void calcContinue2() {
        /*Tasa de interés mensual nominal*/
        this.XLEW_1_1_4 = een[0];
        /* Interes*/
        this.XLEW_1_2_4 = een[8];
        /*Fecha Primer pago*/
        this.XLEW_1_2_6 = (Double) eev[22];
        /*Cantidad de meses a pagar o sea plazo actual*/
        this.XLEW_1_3_4 = een[2];
        /*Fecha último de pago*/
        this.XLEW_1_3_6 = (Double) eev[26];
        /*Monto*/
        this.XLEW_1_5_5 = een[3];
        recalculated = true;
    }
    private static final String String_NaN = "";
    private String panel_to_show;

    public void setPanel_to_show(String v) {
        panel_to_show = v;
    }

    public String getPanel_to_show() {
        return panel_to_show;
    }

    public static final String eedatefmt(int[] fmt, double x) {
        if (!DateUtil.isFinite(x)) {
            return String_NaN;
        }
        double tmp = 0;
        String res = "";
        int len = fmt.length;
        for (int ii = 0; ii < len; ii++) {
            if (fmt[ii] > 31) {
                res += fmtstrings[fmt[ii] - 32];
            } else {
                switch (fmt[ii]) {
                    case 2:
                        res += DateUtil.String_valueOf(DateUtil.eemonth(x));
                        break;
                    case 3:
                        tmp = DateUtil.eemonth(x);
                        if (tmp < 10) {
                            res += "0";
                        }
                        res += DateUtil.String_valueOf(tmp);
                        break;
                    case 4:
                        res += DateUtil.fmtmonthnamesshort[(int) DateUtil.eemonth(x) - 1];
                        break;
                    case 5:
                        res += DateUtil.fmtmonthnameslong[(int) DateUtil.eemonth(x) - 1];
                        break;

                    case 6:
                        res += DateUtil.String_valueOf(DateUtil.eeday(x));
                        break;
                    case 7:
                        tmp = DateUtil.eeday(x);
                        if (tmp < 10) {
                            res += "0";
                        }
                        res += DateUtil.String_valueOf(tmp);
                        break;
                    case 8:
                        res += DateUtil.fmtdaynamesshort[(int) DateUtil.weekday(x, 1) - 1];
                        break;
                    case 9:
                        res += DateUtil.fmtdaynameslong[(int) DateUtil.weekday(x, 1) - 1];
                        break;
                    case 10:
                        tmp = DateUtil.year(x) % 100;
                        if (tmp < 10) {
                            res += "0";
                        }
                        res += DateUtil.String_valueOf(tmp);
                        break;
                    case 11:
                        res += DateUtil.String_valueOf(DateUtil.year(x));
                        break;
                    case 12:
                        res += DateUtil.String_valueOf(DateUtil.hour(x));
                        break;
                    case 13:
                        tmp = DateUtil.hour(x);
                        if (tmp < 10) {
                            res += "0";
                        }
                        res += DateUtil.String_valueOf(tmp);
                        break;
                    case 14:
                        tmp = DateUtil.hour(x) % 12;

                        if (tmp == 0) {
                            res += "12";
                        } else {
                            res += DateUtil.String_valueOf(tmp % 12);
                        }
                        break;
                    case 15:
                        tmp = DateUtil.hour(x) % 12;
                        if (tmp == 0) {
                            res += "12";
                        } else {
                            if (tmp < 10) {
                                res += "0";
                            }
                            res += DateUtil.String_valueOf(tmp);
                        }
                        break;
                    case 16:
                        res += DateUtil.String_valueOf(DateUtil.minute(x));
                        break;
                    case 17:
                        tmp = DateUtil.minute(x);
                        if (tmp < 10) {
                            res += "0";
                        }
                        res += DateUtil.String_valueOf(tmp);
                        break;
                    case 18:
                        res += DateUtil.String_valueOf(DateUtil.second(x));
                        break;
                    case 19:
                        tmp = DateUtil.second(x);
                        if (tmp < 10) {
                            res += "0";
                        }
                        res += DateUtil.String_valueOf(tmp);
                        break;
                    case 21:
                    case 22:
                        if (DateUtil.hour(x) < 12) {
                            res += "AM";
                        } else {
                            res += "PM";
                        }
                        break;
                    case 23:
                        res += DateUtil.eedisplayFloat(x);
                        break;
                    case 24:
                        tmp = fmt[++ii];
                        res += DateUtil.eedisplayFloatND(x, (int) tmp);
                        break;
                    case 25:
                        tmp = fmt[++ii];
                        res += DateUtil.eedisplayFloatNDTh(x, (int) tmp);
                        break;
                    case 26:
                        res += DateUtil.eedisplayPercent(x);
                        break;
                    case 27:
                        tmp = fmt[++ii];
                        res += DateUtil.eedisplayPercentND(x, (int) tmp);
                        break;
                    case 28:
                        tmp = fmt[++ii];
                        res += DateUtil.eedisplayPercentNDTh(x, (int) tmp);
                        break;
                    case 29:
                        tmp = fmt[++ii];
                        res += DateUtil.eedisplayScientific(x, (int) tmp);
                        break;
                    case 30:
                        int padding = fmt[++ii];
                        tmp = DateUtil.hour(x) + Math.floor(x) * 24;
                        String tmpstr = DateUtil.String_valueOf(tmp);
                        if (tmpstr.length() < padding) {
                            res += ("00000000000000000000").substring(0, padding - tmpstr.length());
                        }
                        res += tmpstr;
                        break;
                }
            }
        }
        return res;

    }

    public static final String eedatefmtv(int[] fmt, Object x) {
        if (x instanceof Double) {
            return eedatefmt(fmt, (((Double) x).doubleValue()));
        } else {
            return DateUtil.v2s(x);
        }
    }

    private static final double pmt(double rate, double nper, double pv, double fv, double type) {
        if (rate == 0) {
            return -pv / nper;
        } else {
            double pvif = Math.pow(1 + rate, nper);
            double fvifa = (pvif - 1) / rate;
            double type1 = (type != 0) ? 1 : 0;
            return ((-pv * pvif - fv) / ((1 + rate * type1) * fvifa));
        }
    }

    public static final double pmt2(double rate, int nper, double pv, int d) {
        if (rate == 0D) {
            return -pv / nper;
        } else {
            rate = Math.pow((1 + rate), d) - 1;
            double pvif = Math.pow(1 + rate, nper);
            double fvifa = (pvif - 1) / rate;
            return ((-pv * pvif) / fvifa);
        }
    }

    public static final String nextNumberPrestamo(String codFilial) {
        String nuevonumero = "";
        DAOGeneral dao = new DAOGeneral();
        String cadena = DateUtil.getDateTime("yyyyMMdd", new Date());
        int numero = 0;
        String fechaformat = "CRED" + codFilial + cadena;
        Logger.getLogger(PrestamoUtil.class.getName()).log(Level.INFO, "fechaformat = " + fechaformat);
        String hql = "select max(op.descripcion) from TOperacion op where op.descripcion like '" + fechaformat + "%'";
        List result2 = dao.findAll(hql);
        String numeroOp = null;
        try {
            numeroOp = (String) result2.get(0);
        } catch (IndexOutOfBoundsException ex) {
        }
        if (numeroOp == null) {
            nuevonumero = "0001";
        } else {
            numeroOp = numeroOp.replaceAll(fechaformat, "");
            numero = Integer.parseInt(numeroOp);
            numero = numero + 1;
            nuevonumero = Integer.toString(numero);
        }
        if (nuevonumero.length() < 4) {
            nuevonumero = "0000".substring(nuevonumero.length()) + nuevonumero;
        }
        nuevonumero = fechaformat + nuevonumero;
        dao.cerrarSession();
        return nuevonumero;
    }

    public static final String nextNumberContrato(String codFilial) {
        String nuevonumero = "";
        DAOGeneral dao = new DAOGeneral();
        int numero = 0;
        String fechaformat = DateUtil.getDateTime("yyyyMMdd", new Date());
        String cadena = codFilial + fechaformat;
        String hql = "select max(tc.numContrato) from TContrato tc where tc.numContrato like '" + cadena + "%' ";
        List result2 = dao.findAll(hql);
        String numeroOp = null;
        try {
            numeroOp = (String) result2.get(0);
        } catch (IndexOutOfBoundsException ex) {
        }
        if (numeroOp == null) {
            nuevonumero = "0001";
        } else {
            numeroOp = numeroOp.replaceAll(cadena, "");
            numero = Integer.parseInt(numeroOp);
            numero = numero + 1;
            nuevonumero = Integer.toString(numero);
        }
        if (nuevonumero.length() < 4) {
            nuevonumero = "0000".substring(nuevonumero.length()) + nuevonumero;
        }
        nuevonumero = cadena + nuevonumero;
        dao.cerrarSession();
        return nuevonumero;
    }

    public static final String nextNumberCertificado(String codFilial) {
        String nuevonumero = "";
        DAOGeneral dao = new DAOGeneral();
        int numero = 0;
        String fechaformat = DateUtil.getDateTime("yyyyMMdd", new Date());
        String cadena = codFilial + fechaformat;
        String hql = "select max(tc.numCertificado) from TCertificado tc where tc.numCertificado like '" + cadena + "%' ";
        List result2 = dao.findAll(hql);
        String numeroOp = null;
        try {
            numeroOp = (String) result2.get(0);
        } catch (IndexOutOfBoundsException ex) {
            Logger.getLogger(PrestamoUtil.class.getName()).log(Level.INFO, "ex = " + ex.getMessage());
        }
        if (numeroOp == null) {
            nuevonumero = "0001";
        } else {
            numeroOp = numeroOp.replaceAll(cadena, "");
            numero = Integer.parseInt(numeroOp);
            numero = numero + 1;
            nuevonumero = Integer.toString(numero);
        }
        if (nuevonumero.length() < 4) {
            nuevonumero = "0000".substring(nuevonumero.length()) + nuevonumero;
        }
        nuevonumero = cadena + nuevonumero;
        dao.cerrarSession();
        return nuevonumero;
    }

    public static double calculoMora(double dc, double fin, double monto, double tasa) {
        return monto * (Math.pow(Math.pow(1D + tasa / 100D, 1D / 360D), dc) - 1D);
    }

    public static double calculoInteresCompensatorio(double k, double m, double t) {
        if (k < 0D || t < 0D) {
            return 0D;
        }
        return k * m * (t / 360D);
    }

    public static double calTEA(double interes) {
        return Math.pow(1D + interes / 12D, 12D) - 1.0D;
    }

    public static double calTEAINVmes(double interes) {
        return 12D * (Math.pow(interes + 1D, 1.0D / 12D) - 1.0D);
    }

    public static double calTEAINVdia(double interes) {
        return 360D * (Math.pow(interes + 1D, 1.0D / 360D) - 1.0D);
    }

    public static double calCom(double k, double i) {
        return k * i / 100D;
    }

    public static double interesNominal(double P, double i, double n) {
        return P * Math.pow((i / 100.0D + 1.0D), n);
    }

    public static double interesMensual(double P, double i, double n) {
        return interesNominal(P, i / 12.0D, n);
    }

    public static Double interesDiario(double P, double i, double n) {
        return interesNominal(P, i / 360.0D, n);
    }
}
