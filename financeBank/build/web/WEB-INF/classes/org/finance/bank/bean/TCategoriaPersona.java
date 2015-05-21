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
 * TCategoriaPersona generated by hbm2java
 */
@Entity
@Table(name="t_categoria_persona"
    ,schema="public"
)
public class TCategoriaPersona  implements java.io.Serializable {


     private String idcategoriapersona;
     private String descripcion;
     private Set TPersonas = new HashSet(0);

    public TCategoriaPersona() {
    }

	
    public TCategoriaPersona(String idcategoriapersona, String descripcion) {
        this.idcategoriapersona = idcategoriapersona;
        this.descripcion = descripcion;
    }
    public TCategoriaPersona(String idcategoriapersona, String descripcion, Set TPersonas) {
       this.idcategoriapersona = idcategoriapersona;
       this.descripcion = descripcion;
       this.TPersonas = TPersonas;
    }
   
     @Id 
    
    @Column(name="idcategoriapersona", unique=true, nullable=false, length=41)
    public String getIdcategoriapersona() {
        return this.idcategoriapersona;
    }
    
    public void setIdcategoriapersona(String idcategoriapersona) {
        this.idcategoriapersona = idcategoriapersona;
    }
    
    @Column(name="descripcion", nullable=false, length=100)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TCategoriaPersona")
    public Set getTPersonas() {
        return this.TPersonas;
    }
    
    public void setTPersonas(Set TPersonas) {
        this.TPersonas = TPersonas;
    }




}

