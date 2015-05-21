package org.finance.bank.bean;
// Generated 07/01/2014 06:22:58 PM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TDetalleSuma generated by hbm2java
 */
@Entity
@Table(name="t_detalle_suma"
    ,schema="public"
)
public class TDetalleSuma  implements java.io.Serializable {


     private String iddetallesuma;
     private TDenominacionMoneda TDenominacionMoneda;
     private TSumaMoneda TSumaMoneda;
     private Integer cantidad;
     private String estado;

    public TDetalleSuma() {
    }

	
    public TDetalleSuma(String iddetallesuma, TDenominacionMoneda TDenominacionMoneda, TSumaMoneda TSumaMoneda) {
        this.iddetallesuma = iddetallesuma;
        this.TDenominacionMoneda = TDenominacionMoneda;
        this.TSumaMoneda = TSumaMoneda;
    }
    public TDetalleSuma(String iddetallesuma, TDenominacionMoneda TDenominacionMoneda, TSumaMoneda TSumaMoneda, Integer cantidad, String estado) {
       this.iddetallesuma = iddetallesuma;
       this.TDenominacionMoneda = TDenominacionMoneda;
       this.TSumaMoneda = TSumaMoneda;
       this.cantidad = cantidad;
       this.estado = estado;
    }
   
     @Id 
    
    @Column(name="iddetallesuma", unique=true, nullable=false, length=50)
    public String getIddetallesuma() {
        return this.iddetallesuma;
    }
    
    public void setIddetallesuma(String iddetallesuma) {
        this.iddetallesuma = iddetallesuma;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="iddenominacionmoneda", nullable=false)
    public TDenominacionMoneda getTDenominacionMoneda() {
        return this.TDenominacionMoneda;
    }
    
    public void setTDenominacionMoneda(TDenominacionMoneda TDenominacionMoneda) {
        this.TDenominacionMoneda = TDenominacionMoneda;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idsumamoneda", nullable=false)
    public TSumaMoneda getTSumaMoneda() {
        return this.TSumaMoneda;
    }
    
    public void setTSumaMoneda(TSumaMoneda TSumaMoneda) {
        this.TSumaMoneda = TSumaMoneda;
    }
    
    @Column(name="cantidad", precision=6, scale=0)
    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    @Column(name="estado", length=50)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }




}


