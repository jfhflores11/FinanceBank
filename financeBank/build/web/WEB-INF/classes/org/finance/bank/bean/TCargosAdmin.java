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
 * TCargosAdmin generated by hbm2java
 */
@Entity
@Table(name="t_cargos_admin"
    ,schema="public"
)
public class TCargosAdmin  implements java.io.Serializable {


     private String idcargoadmin;
     private String denominacion;
     private String tipo;
     private Set TCargosPrestamos = new HashSet(0);

    public TCargosAdmin() {
    }

	
    public TCargosAdmin(String idcargoadmin, String denominacion) {
        this.idcargoadmin = idcargoadmin;
        this.denominacion = denominacion;
    }
    public TCargosAdmin(String idcargoadmin, String denominacion, String tipo, Set TCargosPrestamos) {
       this.idcargoadmin = idcargoadmin;
       this.denominacion = denominacion;
       this.tipo = tipo;
       this.TCargosPrestamos = TCargosPrestamos;
    }
   
     @Id 
    
    @Column(name="idcargoadmin", unique=true, nullable=false, length=41)
    public String getIdcargoadmin() {
        return this.idcargoadmin;
    }
    
    public void setIdcargoadmin(String idcargoadmin) {
        this.idcargoadmin = idcargoadmin;
    }
    
    @Column(name="denominacion", nullable=false, length=200)
    public String getDenominacion() {
        return this.denominacion;
    }
    
    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }
    
    @Column(name="tipo", length=50)
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TCargosAdmin")
    public Set getTCargosPrestamos() {
        return this.TCargosPrestamos;
    }
    
    public void setTCargosPrestamos(Set TCargosPrestamos) {
        this.TCargosPrestamos = TCargosPrestamos;
    }




}


