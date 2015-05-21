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
 * TTasa generated by hbm2java
 */
@Entity
@Table(name="t_tasa"
    ,schema="public"
)
public class TTasa  implements java.io.Serializable {


     private String idtasa;
     private TTipoCambio TTipoCambio;
     private BigDecimal FConversion;
     private String fecha;
     private String estado;
     private String idUser;
     private String ipUser;
     private String dateUser;
     private String tipoTasa;

    public TTasa() {
    }

	
    public TTasa(String idtasa, TTipoCambio TTipoCambio, BigDecimal FConversion, String fecha, String estado, String idUser, String ipUser, String dateUser) {
        this.idtasa = idtasa;
        this.TTipoCambio = TTipoCambio;
        this.FConversion = FConversion;
        this.fecha = fecha;
        this.estado = estado;
        this.idUser = idUser;
        this.ipUser = ipUser;
        this.dateUser = dateUser;
    }
    public TTasa(String idtasa, TTipoCambio TTipoCambio, BigDecimal FConversion, String fecha, String estado, String idUser, String ipUser, String dateUser, String tipoTasa) {
       this.idtasa = idtasa;
       this.TTipoCambio = TTipoCambio;
       this.FConversion = FConversion;
       this.fecha = fecha;
       this.estado = estado;
       this.idUser = idUser;
       this.ipUser = ipUser;
       this.dateUser = dateUser;
       this.tipoTasa = tipoTasa;
    }
   
     @Id 
    
    @Column(name="idtasa", unique=true, nullable=false, length=41)
    public String getIdtasa() {
        return this.idtasa;
    }
    
    public void setIdtasa(String idtasa) {
        this.idtasa = idtasa;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idtipocambio", nullable=false)
    public TTipoCambio getTTipoCambio() {
        return this.TTipoCambio;
    }
    
    public void setTTipoCambio(TTipoCambio TTipoCambio) {
        this.TTipoCambio = TTipoCambio;
    }
    
    @Column(name="f_conversion", nullable=false, precision=20, scale=3)
    public BigDecimal getFConversion() {
        return this.FConversion;
    }
    
    public void setFConversion(BigDecimal FConversion) {
        this.FConversion = FConversion;
    }
    
    @Column(name="fecha", nullable=false, length=50)
    public String getFecha() {
        return this.fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    @Column(name="estado", nullable=false, length=50)
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
    
    @Column(name="tipo_tasa", length=50)
    public String getTipoTasa() {
        return this.tipoTasa;
    }
    
    public void setTipoTasa(String tipoTasa) {
        this.tipoTasa = tipoTasa;
    }




}


