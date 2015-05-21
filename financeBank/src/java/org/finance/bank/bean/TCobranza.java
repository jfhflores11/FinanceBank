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
 * TCobranza generated by hbm2java
 */
@Entity
@Table(name="t_cobranza"
    ,schema="public"
)
public class TCobranza  implements java.io.Serializable {


     private String idcobranza;
     private TDetallePrestamo TDetallePrestamo;
     private TOperacion TOperacion;
     private String fecha;
     private String idUser;
     private String ipUser;
     private String dateUser;
     private String estado;
     private BigDecimal importeTotal;
     private Integer codigoOperacion;
     private BigDecimal capitalPagado;
     private BigDecimal interesPagado;
     private BigDecimal moraPagada;
     private BigDecimal cargosPagados;
     private BigDecimal seguroPagado;
     private BigDecimal compPagado;
     private BigDecimal suspendPagado;
     private Integer numeroCuota;
     private String numeroTicket;
     private BigDecimal igv;

    public TCobranza() {
    }

	
    public TCobranza(String idcobranza, TDetallePrestamo TDetallePrestamo, TOperacion TOperacion, String fecha, String idUser, String ipUser, String dateUser, String estado) {
        this.idcobranza = idcobranza;
        this.TDetallePrestamo = TDetallePrestamo;
        this.TOperacion = TOperacion;
        this.fecha = fecha;
        this.idUser = idUser;
        this.ipUser = ipUser;
        this.dateUser = dateUser;
        this.estado = estado;
    }
    public TCobranza(String idcobranza, TDetallePrestamo TDetallePrestamo, TOperacion TOperacion, String fecha, String idUser, String ipUser, String dateUser, String estado, BigDecimal importeTotal, Integer codigoOperacion, BigDecimal capitalPagado, BigDecimal interesPagado, BigDecimal moraPagada, BigDecimal cargosPagados, BigDecimal seguroPagado, BigDecimal compPagado, BigDecimal suspendPagado, Integer numeroCuota, String numeroTicket, BigDecimal igv) {
       this.idcobranza = idcobranza;
       this.TDetallePrestamo = TDetallePrestamo;
       this.TOperacion = TOperacion;
       this.fecha = fecha;
       this.idUser = idUser;
       this.ipUser = ipUser;
       this.dateUser = dateUser;
       this.estado = estado;
       this.importeTotal = importeTotal;
       this.codigoOperacion = codigoOperacion;
       this.capitalPagado = capitalPagado;
       this.interesPagado = interesPagado;
       this.moraPagada = moraPagada;
       this.cargosPagados = cargosPagados;
       this.seguroPagado = seguroPagado;
       this.compPagado = compPagado;
       this.suspendPagado = suspendPagado;
       this.numeroCuota = numeroCuota;
       this.numeroTicket = numeroTicket;
       this.igv = igv;
    }
   
     @Id 
    
    @Column(name="idcobranza", unique=true, nullable=false, length=41)
    public String getIdcobranza() {
        return this.idcobranza;
    }
    
    public void setIdcobranza(String idcobranza) {
        this.idcobranza = idcobranza;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="iddetalleprestamo", nullable=false)
    public TDetallePrestamo getTDetallePrestamo() {
        return this.TDetallePrestamo;
    }
    
    public void setTDetallePrestamo(TDetallePrestamo TDetallePrestamo) {
        this.TDetallePrestamo = TDetallePrestamo;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idoperacion", nullable=false)
    public TOperacion getTOperacion() {
        return this.TOperacion;
    }
    
    public void setTOperacion(TOperacion TOperacion) {
        this.TOperacion = TOperacion;
    }
    
    @Column(name="fecha", nullable=false, length=50)
    public String getFecha() {
        return this.fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
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
    
    @Column(name="estado", nullable=false, length=50)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Column(name="importe_total", precision=20)
    public BigDecimal getImporteTotal() {
        return this.importeTotal;
    }
    
    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }
    
    @Column(name="codigo_operacion")
    public Integer getCodigoOperacion() {
        return this.codigoOperacion;
    }
    
    public void setCodigoOperacion(Integer codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }
    
    @Column(name="capital_pagado", precision=20)
    public BigDecimal getCapitalPagado() {
        return this.capitalPagado;
    }
    
    public void setCapitalPagado(BigDecimal capitalPagado) {
        this.capitalPagado = capitalPagado;
    }
    
    @Column(name="interes_pagado", precision=20)
    public BigDecimal getInteresPagado() {
        return this.interesPagado;
    }
    
    public void setInteresPagado(BigDecimal interesPagado) {
        this.interesPagado = interesPagado;
    }
    
    @Column(name="mora_pagada", precision=20)
    public BigDecimal getMoraPagada() {
        return this.moraPagada;
    }
    
    public void setMoraPagada(BigDecimal moraPagada) {
        this.moraPagada = moraPagada;
    }
    
    @Column(name="cargos_pagados", precision=20)
    public BigDecimal getCargosPagados() {
        return this.cargosPagados;
    }
    
    public void setCargosPagados(BigDecimal cargosPagados) {
        this.cargosPagados = cargosPagados;
    }
    
    @Column(name="seguro_pagado", precision=20)
    public BigDecimal getSeguroPagado() {
        return this.seguroPagado;
    }
    
    public void setSeguroPagado(BigDecimal seguroPagado) {
        this.seguroPagado = seguroPagado;
    }
    
    @Column(name="comp_pagado", precision=20)
    public BigDecimal getCompPagado() {
        return this.compPagado;
    }
    
    public void setCompPagado(BigDecimal compPagado) {
        this.compPagado = compPagado;
    }
    
    @Column(name="suspend_pagado", precision=20)
    public BigDecimal getSuspendPagado() {
        return this.suspendPagado;
    }
    
    public void setSuspendPagado(BigDecimal suspendPagado) {
        this.suspendPagado = suspendPagado;
    }
    
    @Column(name="numero_cuota")
    public Integer getNumeroCuota() {
        return this.numeroCuota;
    }
    
    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }
    
    @Column(name="numero_ticket", length=50)
    public String getNumeroTicket() {
        return this.numeroTicket;
    }
    
    public void setNumeroTicket(String numeroTicket) {
        this.numeroTicket = numeroTicket;
    }
    
    @Column(name="igv", precision=20)
    public BigDecimal getIgv() {
        return this.igv;
    }
    
    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }




}


