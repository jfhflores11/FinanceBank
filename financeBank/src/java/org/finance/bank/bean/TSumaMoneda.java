package org.finance.bank.bean;
// Generated 07/01/2014 06:22:58 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TSumaMoneda generated by hbm2java
 */
@Entity
@Table(name="t_suma_moneda"
    ,schema="public"
)
public class TSumaMoneda  implements java.io.Serializable {


     private String idsumamoneda;
     private String estado;
     private String idoperacion;
     private Set TDetalleSumas = new HashSet(0);

    public TSumaMoneda() {
    }

	
    public TSumaMoneda(String idsumamoneda, String idoperacion) {
        this.idsumamoneda = idsumamoneda;
        this.idoperacion = idoperacion;
    }
    public TSumaMoneda(String idsumamoneda, String estado, String idoperacion, Set TDetalleSumas) {
       this.idsumamoneda = idsumamoneda;
       this.estado = estado;
       this.idoperacion = idoperacion;
       this.TDetalleSumas = TDetalleSumas;
    }
   
     @Id 
    
    @Column(name="idsumamoneda", unique=true, nullable=false, length=50)
    public String getIdsumamoneda() {
        return this.idsumamoneda;
    }
    
    public void setIdsumamoneda(String idsumamoneda) {
        this.idsumamoneda = idsumamoneda;
    }
    
    @Column(name="estado", length=50)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Column(name="idoperacion", nullable=false, length=50)
    public String getIdoperacion() {
        return this.idoperacion;
    }
    
    public void setIdoperacion(String idoperacion) {
        this.idoperacion = idoperacion;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TSumaMoneda")
    public Set getTDetalleSumas() {
        return this.TDetalleSumas;
    }
    
    public void setTDetalleSumas(Set TDetalleSumas) {
        this.TDetalleSumas = TDetalleSumas;
    }




}

