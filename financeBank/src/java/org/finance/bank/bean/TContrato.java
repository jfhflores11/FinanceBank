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
 * TContrato generated by hbm2java
 */
@Entity
@Table(name="t_contrato"
    ,schema="public"
)
public class TContrato  implements java.io.Serializable {


     private String idcontrato;
     private TRegistroPrestamo TRegistroPrestamo;
     private String fecha;
     private String numContrato;
     private String dniRuc;
     private String nombre;
     private String nombreRep;
     private String descripcion;
     private String estado;

    public TContrato() {
    }

	
    public TContrato(String idcontrato, TRegistroPrestamo TRegistroPrestamo, String fecha, String dniRuc, String nombre, String nombreRep, String descripcion, String estado) {
        this.idcontrato = idcontrato;
        this.TRegistroPrestamo = TRegistroPrestamo;
        this.fecha = fecha;
        this.dniRuc = dniRuc;
        this.nombre = nombre;
        this.nombreRep = nombreRep;
        this.descripcion = descripcion;
        this.estado = estado;
    }
    public TContrato(String idcontrato, TRegistroPrestamo TRegistroPrestamo, String fecha, String numContrato, String dniRuc, String nombre, String nombreRep, String descripcion, String estado) {
       this.idcontrato = idcontrato;
       this.TRegistroPrestamo = TRegistroPrestamo;
       this.fecha = fecha;
       this.numContrato = numContrato;
       this.dniRuc = dniRuc;
       this.nombre = nombre;
       this.nombreRep = nombreRep;
       this.descripcion = descripcion;
       this.estado = estado;
    }
   
     @Id 
    
    @Column(name="idcontrato", unique=true, nullable=false, length=41)
    public String getIdcontrato() {
        return this.idcontrato;
    }
    
    public void setIdcontrato(String idcontrato) {
        this.idcontrato = idcontrato;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idprestamo", nullable=false)
    public TRegistroPrestamo getTRegistroPrestamo() {
        return this.TRegistroPrestamo;
    }
    
    public void setTRegistroPrestamo(TRegistroPrestamo TRegistroPrestamo) {
        this.TRegistroPrestamo = TRegistroPrestamo;
    }
    
    @Column(name="fecha", nullable=false, length=50)
    public String getFecha() {
        return this.fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    @Column(name="num_contrato", length=40)
    public String getNumContrato() {
        return this.numContrato;
    }
    
    public void setNumContrato(String numContrato) {
        this.numContrato = numContrato;
    }
    
    @Column(name="dni_ruc", nullable=false, length=20)
    public String getDniRuc() {
        return this.dniRuc;
    }
    
    public void setDniRuc(String dniRuc) {
        this.dniRuc = dniRuc;
    }
    
    @Column(name="nombre", nullable=false, length=120)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name="nombre_rep", nullable=false, length=100)
    public String getNombreRep() {
        return this.nombreRep;
    }
    
    public void setNombreRep(String nombreRep) {
        this.nombreRep = nombreRep;
    }
    
    @Column(name="descripcion", nullable=false, length=4000)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Column(name="estado", nullable=false, length=20)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }




}


