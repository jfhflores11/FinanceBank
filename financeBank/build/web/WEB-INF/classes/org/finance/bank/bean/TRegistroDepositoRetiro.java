package org.finance.bank.bean;
// Generated 07/01/2014 06:22:58 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TRegistroDepositoRetiro generated by hbm2java
 */
@Entity
@Table(name="t_registro_deposito_retiro"
    ,schema="public"
)
public class TRegistroDepositoRetiro  implements java.io.Serializable {


     private String iddepositoretiro;
     private TCuentaPersona TCuentaPersona;
     private TOperacion TOperacion;
     private String fecha;
     private String numCta;
     private BigDecimal importe;
     private String nombreRepresentante;
     private String apellidosRepresentante;
     private String estado;
     private String idUser;
     private String ipUser;
     private String dateUser;
     private String numeroCheque;
     private String numDias;
     private String dniRepresentante;
     private String fechaNacRepresentante;
     private Set TCertificados = new HashSet(0);

    public TRegistroDepositoRetiro() {
    }

	
    public TRegistroDepositoRetiro(String iddepositoretiro, TCuentaPersona TCuentaPersona, TOperacion TOperacion, String fecha, String numCta, BigDecimal importe, String estado, String idUser, String ipUser, String dateUser) {
        this.iddepositoretiro = iddepositoretiro;
        this.TCuentaPersona = TCuentaPersona;
        this.TOperacion = TOperacion;
        this.fecha = fecha;
        this.numCta = numCta;
        this.importe = importe;
        this.estado = estado;
        this.idUser = idUser;
        this.ipUser = ipUser;
        this.dateUser = dateUser;
    }
    public TRegistroDepositoRetiro(String iddepositoretiro, TCuentaPersona TCuentaPersona, TOperacion TOperacion, String fecha, String numCta, BigDecimal importe, String nombreRepresentante, String apellidosRepresentante, String estado, String idUser, String ipUser, String dateUser, String numeroCheque, String numDias, String dniRepresentante, String fechaNacRepresentante, Set TCertificados) {
       this.iddepositoretiro = iddepositoretiro;
       this.TCuentaPersona = TCuentaPersona;
       this.TOperacion = TOperacion;
       this.fecha = fecha;
       this.numCta = numCta;
       this.importe = importe;
       this.nombreRepresentante = nombreRepresentante;
       this.apellidosRepresentante = apellidosRepresentante;
       this.estado = estado;
       this.idUser = idUser;
       this.ipUser = ipUser;
       this.dateUser = dateUser;
       this.numeroCheque = numeroCheque;
       this.numDias = numDias;
       this.dniRepresentante = dniRepresentante;
       this.fechaNacRepresentante = fechaNacRepresentante;
       this.TCertificados = TCertificados;
    }
   
     @Id 
    
    @Column(name="iddepositoretiro", unique=true, nullable=false, length=41)
    public String getIddepositoretiro() {
        return this.iddepositoretiro;
    }
    
    public void setIddepositoretiro(String iddepositoretiro) {
        this.iddepositoretiro = iddepositoretiro;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idcuentapersona", nullable=false)
    public TCuentaPersona getTCuentaPersona() {
        return this.TCuentaPersona;
    }
    
    public void setTCuentaPersona(TCuentaPersona TCuentaPersona) {
        this.TCuentaPersona = TCuentaPersona;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_operacion", nullable=false)
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
    
    @Column(name="num_cta", nullable=false, length=30)
    public String getNumCta() {
        return this.numCta;
    }
    
    public void setNumCta(String numCta) {
        this.numCta = numCta;
    }
    
    @Column(name="importe", nullable=false, precision=20)
    public BigDecimal getImporte() {
        return this.importe;
    }
    
    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }
    
    @Column(name="nombre_representante", length=200)
    public String getNombreRepresentante() {
        return this.nombreRepresentante;
    }
    
    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }
    
    @Column(name="apellidos_representante", length=200)
    public String getApellidosRepresentante() {
        return this.apellidosRepresentante;
    }
    
    public void setApellidosRepresentante(String apellidosRepresentante) {
        this.apellidosRepresentante = apellidosRepresentante;
    }
    
    @Column(name="estado", nullable=false, length=10)
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
    
    @Column(name="numero_cheque", length=20)
    public String getNumeroCheque() {
        return this.numeroCheque;
    }
    
    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }
    
    @Column(name="num_dias", length=10)
    public String getNumDias() {
        return this.numDias;
    }
    
    public void setNumDias(String numDias) {
        this.numDias = numDias;
    }
    
    @Column(name="dni_representante", length=12)
    public String getDniRepresentante() {
        return this.dniRepresentante;
    }
    
    public void setDniRepresentante(String dniRepresentante) {
        this.dniRepresentante = dniRepresentante;
    }
    
    @Column(name="fecha_nac_representante", length=50)
    public String getFechaNacRepresentante() {
        return this.fechaNacRepresentante;
    }
    
    public void setFechaNacRepresentante(String fechaNacRepresentante) {
        this.fechaNacRepresentante = fechaNacRepresentante;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TRegistroDepositoRetiro")
    public Set getTCertificados() {
        return this.TCertificados;
    }
    
    public void setTCertificados(Set TCertificados) {
        this.TCertificados = TCertificados;
    }




}


