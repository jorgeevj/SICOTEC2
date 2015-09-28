/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "cotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotizacion.findAll", query = "SELECT c FROM Cotizacion c"),
    @NamedQuery(name = "Cotizacion.findByIdcotizacion", query = "SELECT c FROM Cotizacion c WHERE c.idcotizacion = :idcotizacion"),
    @NamedQuery(name = "Cotizacion.findByEstado", query = "SELECT c FROM Cotizacion c WHERE c.estado = :estado"),
    @NamedQuery(name = "Cotizacion.findByFechaEnvio", query = "SELECT c FROM Cotizacion c WHERE c.fechaEnvio = :fechaEnvio"),
    @NamedQuery(name = "Cotizacion.findByDuracion", query = "SELECT c FROM Cotizacion c WHERE c.duracion = :duracion"),
    @NamedQuery(name = "Cotizacion.findByEntrega", query = "SELECT c FROM Cotizacion c WHERE c.entrega = :entrega"),
    @NamedQuery(name = "Cotizacion.findByNSerie", query = "SELECT c FROM Cotizacion c WHERE c.nSerie = :nSerie"),
    @NamedQuery(name = "Cotizacion.findByCorrelativo", query = "SELECT c FROM Cotizacion c WHERE c.correlativo = :correlativo"),
    @NamedQuery(name = "Cotizacion.findByIdalmacen", query = "SELECT c FROM Cotizacion c WHERE c.idalmacen = :idalmacen")})
public class Cotizacion implements Serializable {
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "idalmacen")
    private Integer idalmacen;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcotizacion")
    private Integer idcotizacion;
    @Column(name = "fechaEnvio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;
    @Column(name = "duracion")
    private Integer duracion;
    @Column(name = "entrega")
    private Integer entrega;
    @Size(max = 45)
    @Column(name = "nSerie")
    private String nSerie;
    @Size(max = 45)
    @Column(name = "correlativo")
    private String correlativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cotizacion")
    private List<Cotipoitem> cotipoitemList;
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    @ManyToOne(optional = false)
    private Empresa idempresa;

    public Cotizacion() {
    }

    public Cotizacion(Integer idcotizacion) {
        this.idcotizacion = idcotizacion;
    }

    public Integer getIdcotizacion() {
        return idcotizacion;
    }

    public void setIdcotizacion(Integer idcotizacion) {
        this.idcotizacion = idcotizacion;
    }


    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Integer getEntrega() {
        return entrega;
    }

    public void setEntrega(Integer entrega) {
        this.entrega = entrega;
    }

    public String getNSerie() {
        return nSerie;
    }

    public void setNSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }


    @XmlTransient
    public List<Cotipoitem> getCotipoitemList() {
        return cotipoitemList;
    }

    public void setCotipoitemList(List<Cotipoitem> cotipoitemList) {
        this.cotipoitemList = cotipoitemList;
    }

    public Empresa getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Empresa idempresa) {
        this.idempresa = idempresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcotizacion != null ? idcotizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotizacion)) {
            return false;
        }
        Cotizacion other = (Cotizacion) object;
        if ((this.idcotizacion == null && other.idcotizacion != null) || (this.idcotizacion != null && !this.idcotizacion.equals(other.idcotizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cotizacion[ idcotizacion=" + idcotizacion + " ]";
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getIdalmacen() {
        return idalmacen;
    }

    public void setIdalmacen(Integer idalmacen) {
        this.idalmacen = idalmacen;
    }
    
}
