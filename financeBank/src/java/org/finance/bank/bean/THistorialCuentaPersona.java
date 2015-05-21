package org.finance.bank.bean;
// Generated 07/01/2014 06:22:58 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * THistorialCuentaPersona generated by hbm2java
 */
@Entity
@Table(name="t_historial_cuenta_persona"
    ,schema="public"
)
public class THistorialCuentaPersona  implements java.io.Serializable {


     private String idhistorialcuenta;
     private TCuentaPersona TCuentaPersona;
     private String fecha;
     private BigDecimal monto;
     private BigDecimal montoSinInteres;
     private Date fd;

    public THistorialCuentaPersona() {
    }

	
    public THistorialCuentaPersona(String idhistorialcuenta, TCuentaPersona TCuentaPersona) {
        this.idhistorialcuenta = idhistorialcuenta;
        this.TCuentaPersona = TCuentaPersona;
    }
    public THistorialCuentaPersona(String idhistorialcuenta, TCuentaPersona TCuentaPersona, String fecha, BigDecimal monto, BigDecimal montoSinInteres, Date fd) {
       this.idhistorialcuenta = idhistorialcuenta;
       this.TCuentaPersona = TCuentaPersona;
       this.fecha = fecha;
       this.monto = monto;
       this.montoSinInteres = montoSinInteres;
       this.fd = fd;
    }
   
     @Id 
    
    @Column(name="idhistorialcuenta", unique=true, nullable=false, length=41)
    public String getIdhistorialcuenta() {
        return this.idhistorialcuenta;
    }
    
    public void setIdhistorialcuenta(String idhistorialcuenta) {
        this.idhistorialcuenta = idhistorialcuenta;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idcuentapersona", nullable=false)
    public TCuentaPersona getTCuentaPersona() {
        return this.TCuentaPersona;
    }
    
    public void setTCuentaPersona(TCuentaPersona TCuentaPersona) {
        this.TCuentaPersona = TCuentaPersona;
    }
    
    @Column(name="fecha", length=50)
    public String getFecha() {
        return this.fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    @Column(name="monto", precision=10, scale=4)
    public BigDecimal getMonto() {
        return this.monto;
    }
    
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    
    @Column(name="monto_sin_interes", precision=10, scale=4)
    public BigDecimal getMontoSinInteres() {
        return this.montoSinInteres;
    }
    
    public void setMontoSinInteres(BigDecimal montoSinInteres) {
        this.montoSinInteres = montoSinInteres;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fd", length=29)
    public Date getFd() {
        return this.fd;
    }
    
    public void setFd(Date fd) {
        this.fd = fd;
    }




}

