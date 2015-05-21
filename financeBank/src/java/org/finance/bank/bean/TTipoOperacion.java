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
 * TTipoOperacion generated by hbm2java
 */
@Entity
@Table(name="t_tipo_operacion"
    ,schema="public"
)
public class TTipoOperacion  implements java.io.Serializable {


     private String codigoTipoOperacion;
     private String nombre;
     private String idUser;
     private String ipUser;
     private String dateUser;
     private String estado;
     private String tipo;
     private Set TOperacions = new HashSet(0);

    public TTipoOperacion() {
    }

	
    public TTipoOperacion(String codigoTipoOperacion, String nombre, String idUser, String ipUser, String dateUser) {
        this.codigoTipoOperacion = codigoTipoOperacion;
        this.nombre = nombre;
        this.idUser = idUser;
        this.ipUser = ipUser;
        this.dateUser = dateUser;
    }
    public TTipoOperacion(String codigoTipoOperacion, String nombre, String idUser, String ipUser, String dateUser, String estado, String tipo, Set TOperacions) {
       this.codigoTipoOperacion = codigoTipoOperacion;
       this.nombre = nombre;
       this.idUser = idUser;
       this.ipUser = ipUser;
       this.dateUser = dateUser;
       this.estado = estado;
       this.tipo = tipo;
       this.TOperacions = TOperacions;
    }
   
     @Id 
    
    @Column(name="codigo_tipo_operacion", unique=true, nullable=false, length=41)
    public String getCodigoTipoOperacion() {
        return this.codigoTipoOperacion;
    }
    
    public void setCodigoTipoOperacion(String codigoTipoOperacion) {
        this.codigoTipoOperacion = codigoTipoOperacion;
    }
    
    @Column(name="nombre", nullable=false, length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
    @Column(name="estado", length=40)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Column(name="tipo", length=50)
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TTipoOperacion")
    public Set getTOperacions() {
        return this.TOperacions;
    }
    
    public void setTOperacions(Set TOperacions) {
        this.TOperacions = TOperacions;
    }




}


