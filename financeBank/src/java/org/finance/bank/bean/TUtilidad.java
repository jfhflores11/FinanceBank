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
 * TUtilidad generated by hbm2java
 */
@Entity
@Table(name="t_utilidad"
    ,schema="public"
)
public class TUtilidad  implements java.io.Serializable {


     private String idutilidad;
     private TBalancexmoneda TBalancexmoneda;
     private BigDecimal utilidad;
     private String fecha;
     private String idUser;
     private String ipUser;
     private String dateUser;

    public TUtilidad() {
    }

	
    public TUtilidad(String idutilidad, TBalancexmoneda TBalancexmoneda, BigDecimal utilidad, String fecha, String idUser) {
        this.idutilidad = idutilidad;
        this.TBalancexmoneda = TBalancexmoneda;
        this.utilidad = utilidad;
        this.fecha = fecha;
        this.idUser = idUser;
    }
    public TUtilidad(String idutilidad, TBalancexmoneda TBalancexmoneda, BigDecimal utilidad, String fecha, String idUser, String ipUser, String dateUser) {
       this.idutilidad = idutilidad;
       this.TBalancexmoneda = TBalancexmoneda;
       this.utilidad = utilidad;
       this.fecha = fecha;
       this.idUser = idUser;
       this.ipUser = ipUser;
       this.dateUser = dateUser;
    }
   
     @Id 
    
    @Column(name="idutilidad", unique=true, nullable=false, length=41)
    public String getIdutilidad() {
        return this.idutilidad;
    }
    
    public void setIdutilidad(String idutilidad) {
        this.idutilidad = idutilidad;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idbalance", nullable=false)
    public TBalancexmoneda getTBalancexmoneda() {
        return this.TBalancexmoneda;
    }
    
    public void setTBalancexmoneda(TBalancexmoneda TBalancexmoneda) {
        this.TBalancexmoneda = TBalancexmoneda;
    }
    
    @Column(name="utilidad", nullable=false, precision=20, scale=4)
    public BigDecimal getUtilidad() {
        return this.utilidad;
    }
    
    public void setUtilidad(BigDecimal utilidad) {
        this.utilidad = utilidad;
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
    
    @Column(name="ip_user", length=50)
    public String getIpUser() {
        return this.ipUser;
    }
    
    public void setIpUser(String ipUser) {
        this.ipUser = ipUser;
    }
    
    @Column(name="date_user", length=50)
    public String getDateUser() {
        return this.dateUser;
    }
    
    public void setDateUser(String dateUser) {
        this.dateUser = dateUser;
    }




}


