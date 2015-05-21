package org.finance.bank.bean;
// Generated 07/01/2014 06:22:58 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TSobregiro generated by hbm2java
 */
@Entity
@Table(name="t_sobregiro"
    ,schema="public"
)
public class TSobregiro  implements java.io.Serializable {


     private String idsobregiro;
     private TCuentaPersona TCuentaPersona;
     private BigDecimal montoActual;
     private BigDecimal interessg;
     private String fechaPago;
     private String estado;
     private String idUser;
     private String ipUser;
     private String dateUser;
     private BigDecimal montoSinInteres;
     private String fechaActualizacion;
     private String fechaCap;
     private String idRegistro;

    public TSobregiro() {
    }

	
    public TSobregiro(String idsobregiro, TCuentaPersona TCuentaPersona, String idUser, String ipUser, String dateUser) {
        this.idsobregiro = idsobregiro;
        this.TCuentaPersona = TCuentaPersona;
        this.idUser = idUser;
        this.ipUser = ipUser;
        this.dateUser = dateUser;
    }
    public TSobregiro(String idsobregiro, TCuentaPersona TCuentaPersona, BigDecimal montoActual, BigDecimal interessg, String fechaPago, String estado, String idUser, String ipUser, String dateUser, BigDecimal montoSinInteres, String fechaActualizacion, String fechaCap, String idRegistro) {
       this.idsobregiro = idsobregiro;
       this.TCuentaPersona = TCuentaPersona;
       this.montoActual = montoActual;
       this.interessg = interessg;
       this.fechaPago = fechaPago;
       this.estado = estado;
       this.idUser = idUser;
       this.ipUser = ipUser;
       this.dateUser = dateUser;
       this.montoSinInteres = montoSinInteres;
       this.fechaActualizacion = fechaActualizacion;
       this.fechaCap = fechaCap;
       this.idRegistro = idRegistro;
    }
   
     @Id 
    
    @Column(name="idsobregiro", unique=true, nullable=false, length=41)
    public String getIdsobregiro() {
        return this.idsobregiro;
    }
    
    public void setIdsobregiro(String idsobregiro) {
        this.idsobregiro = idsobregiro;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idcuentapersona", nullable=false)
    public TCuentaPersona getTCuentaPersona() {
        return this.TCuentaPersona;
    }
    
    public void setTCuentaPersona(TCuentaPersona TCuentaPersona) {
        this.TCuentaPersona = TCuentaPersona;
    }
    
    @Column(name="monto_actual", precision=20)
    public BigDecimal getMontoActual() {
        return this.montoActual;
    }
    
    public void setMontoActual(BigDecimal montoActual) {
        this.montoActual = montoActual;
    }
    
    @Column(name="interessg", precision=20)
    public BigDecimal getInteressg() {
        return this.interessg;
    }
    
    public void setInteressg(BigDecimal interessg) {
        this.interessg = interessg;
    }
    
    @Column(name="fecha_pago", length=50)
    public String getFechaPago() {
        return this.fechaPago;
    }
    
    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }
    
    @Column(name="estado", length=10)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Column(name="id_user", nullable=false, length=50)
    public String getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    
    @Column(name="ip_user", nullable=false, length=50)
    public String getIpUser() {
        return this.ipUser;
    }
    
    public void setIpUser(String ipUser) {
        this.ipUser = ipUser;
    }
    
    @Column(name="date_user", nullable=false, length=50)
    public String getDateUser() {
        return this.dateUser;
    }
    
    public void setDateUser(String dateUser) {
        this.dateUser = dateUser;
    }
    
    @Column(name="monto_sin_interes", precision=20)
    public BigDecimal getMontoSinInteres() {
        return this.montoSinInteres;
    }
    
    public void setMontoSinInteres(BigDecimal montoSinInteres) {
        this.montoSinInteres = montoSinInteres;
    }
    
    @Column(name="fecha_actualizacion", length=50)
    public String getFechaActualizacion() {
        return this.fechaActualizacion;
    }
    
    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    @Column(name="fecha_cap", length=50)
    public String getFechaCap() {
        return this.fechaCap;
    }
    
    public void setFechaCap(String fechaCap) {
        this.fechaCap = fechaCap;
    }
    
    @Column(name="id_registro", length=41)
    public String getIdRegistro() {
        return this.idRegistro;
    }
    
    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }




}

