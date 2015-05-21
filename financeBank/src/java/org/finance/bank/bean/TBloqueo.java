package org.finance.bank.bean;
// Generated 07/01/2014 06:22:58 PM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * TBloqueo generated by hbm2java
 */
@Entity
@Table(name="t_bloqueo"
    ,schema="public"
    , uniqueConstraints = @UniqueConstraint(columnNames="bloquear_id") 
)
public class TBloqueo  implements java.io.Serializable {


     private String idvitory;
     private String bloquearId;
     private String fecha;
     private String idpersonacaja;
     private String nombretabla;
     private String prioridad;

    public TBloqueo() {
    }

	
    public TBloqueo(String idvitory, String bloquearId) {
        this.idvitory = idvitory;
        this.bloquearId = bloquearId;
    }
    public TBloqueo(String idvitory, String bloquearId, String fecha, String idpersonacaja, String nombretabla, String prioridad) {
       this.idvitory = idvitory;
       this.bloquearId = bloquearId;
       this.fecha = fecha;
       this.idpersonacaja = idpersonacaja;
       this.nombretabla = nombretabla;
       this.prioridad = prioridad;
    }
   
     @Id 
    
    @Column(name="idvitory", unique=true, nullable=false, length=41)
    public String getIdvitory() {
        return this.idvitory;
    }
    
    public void setIdvitory(String idvitory) {
        this.idvitory = idvitory;
    }
    
    @Column(name="bloquear_id", unique=true, nullable=false, length=41)
    public String getBloquearId() {
        return this.bloquearId;
    }
    
    public void setBloquearId(String bloquearId) {
        this.bloquearId = bloquearId;
    }
    
    @Column(name="fecha", length=65)
    public String getFecha() {
        return this.fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    @Column(name="idpersonacaja", length=41)
    public String getIdpersonacaja() {
        return this.idpersonacaja;
    }
    
    public void setIdpersonacaja(String idpersonacaja) {
        this.idpersonacaja = idpersonacaja;
    }
    
    @Column(name="nombretabla", length=100)
    public String getNombretabla() {
        return this.nombretabla;
    }
    
    public void setNombretabla(String nombretabla) {
        this.nombretabla = nombretabla;
    }
    
    @Column(name="prioridad", length=4)
    public String getPrioridad() {
        return this.prioridad;
    }
    
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }




}


