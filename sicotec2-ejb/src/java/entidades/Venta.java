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
import javax.persistence.ManyToMany;
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
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
    @NamedQuery(name = "Venta.findByIdventa", query = "SELECT v FROM Venta v WHERE v.idventa = :idventa"),
    @NamedQuery(name = "Venta.findByFecha", query = "SELECT v FROM Venta v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Venta.findByDescuento", query = "SELECT v FROM Venta v WHERE v.descuento = :descuento"),
    @NamedQuery(name = "Venta.findByTotal", query = "SELECT v FROM Venta v WHERE v.total = :total"),
    @NamedQuery(name = "Venta.findByEstado", query = "SELECT v FROM Venta v WHERE v.estado = :estado"),
    @NamedQuery(name = "Venta.findByIddocumento", query = "SELECT v FROM Venta v WHERE v.iddocumento = :iddocumento"),
    @NamedQuery(name = "Venta.findBySerie", query = "SELECT v FROM Venta v WHERE v.serie = :serie"),
    @NamedQuery(name = "Venta.findByCorrelativo", query = "SELECT v FROM Venta v WHERE v.correlativo = :correlativo"),
    @NamedQuery(name = "Venta.findByIdalmacen", query = "SELECT v FROM Venta v WHERE v.idalmacen = :idalmacen")})
public class Venta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idventa")
    private Integer idventa;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "total")
    private Double total;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @Size(max = 45)
    @Column(name = "iddocumento")
    private String iddocumento;
    @Size(max = 45)
    @Column(name = "serie")
    private String serie;
    @Size(max = 45)
    @Column(name = "correlativo")
    private String correlativo;
    @Column(name = "idalmacen")
    private Integer idalmacen;
    @ManyToMany(mappedBy = "ventaList")
    private List<Mediopago> mediopagoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venta")
    private List<Veitem> veitemList;
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    @ManyToOne(optional = false)
    private Empresa idempresa;
    @JoinColumn(name = "idimpuesto", referencedColumnName = "idimpuesto")
    @ManyToOne(optional = false)
    private Impuesto idimpuesto;

    public Venta() {
    }

    public Venta(Integer idventa) {
        this.idventa = idventa;
    }

    public Integer getIdventa() {
        return idventa;
    }

    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(String iddocumento) {
        this.iddocumento = iddocumento;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public Integer getIdalmacen() {
        return idalmacen;
    }

    public void setIdalmacen(Integer idalmacen) {
        this.idalmacen = idalmacen;
    }

    @XmlTransient
    public List<Mediopago> getMediopagoList() {
        return mediopagoList;
    }

    public void setMediopagoList(List<Mediopago> mediopagoList) {
        this.mediopagoList = mediopagoList;
    }

    @XmlTransient
    public List<Veitem> getVeitemList() {
        return veitemList;
    }

    public void setVeitemList(List<Veitem> veitemList) {
        this.veitemList = veitemList;
    }

    public Empresa getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Empresa idempresa) {
        this.idempresa = idempresa;
    }

    public Impuesto getIdimpuesto() {
        return idimpuesto;
    }

    public void setIdimpuesto(Impuesto idimpuesto) {
        this.idimpuesto = idimpuesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idventa != null ? idventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.idventa == null && other.idventa != null) || (this.idventa != null && !this.idventa.equals(other.idventa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Venta[ idventa=" + idventa + " ]";
    }
    
}
