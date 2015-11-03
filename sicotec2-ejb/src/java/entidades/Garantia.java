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
@Table(name = "garantia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Garantia.findAll", query = "SELECT g FROM Garantia g"),
    @NamedQuery(name = "Garantia.findByIdgarantia", query = "SELECT g FROM Garantia g WHERE g.idgarantia = :idgarantia"),
    @NamedQuery(name = "Garantia.findByFecha", query = "SELECT g FROM Garantia g WHERE g.fecha = :fecha"),
    @NamedQuery(name = "Garantia.findByEstado", query = "SELECT g FROM Garantia g WHERE g.estado = :estado")})
public class Garantia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgarantia")
    private Integer idgarantia;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "iditem", referencedColumnName = "iditem")
    @ManyToOne(optional = false)
    private Item iditem;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgarantia")
    private List<Informe> informeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgarantia")
    private List<Notacredito> notacreditoList;

    public Garantia() {
    }

    public Garantia(Integer idgarantia) {
        this.idgarantia = idgarantia;
    }

    public Integer getIdgarantia() {
        return idgarantia;
    }

    public void setIdgarantia(Integer idgarantia) {
        this.idgarantia = idgarantia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Item getIditem() {
        return iditem;
    }

    public void setIditem(Item iditem) {
        this.iditem = iditem;
    }

    @XmlTransient
    public List<Informe> getInformeList() {
        return informeList;
    }

    public void setInformeList(List<Informe> informeList) {
        this.informeList = informeList;
    }

    @XmlTransient
    public List<Notacredito> getNotacreditoList() {
        return notacreditoList;
    }

    public void setNotacreditoList(List<Notacredito> notacreditoList) {
        this.notacreditoList = notacreditoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgarantia != null ? idgarantia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Garantia)) {
            return false;
        }
        Garantia other = (Garantia) object;
        if ((this.idgarantia == null && other.idgarantia != null) || (this.idgarantia != null && !this.idgarantia.equals(other.idgarantia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Garantia[ idgarantia=" + idgarantia + " ]";
    }
    
}
