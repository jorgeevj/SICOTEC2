/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "requerimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requerimientos.findAll", query = "SELECT r FROM Requerimientos r"),
    @NamedQuery(name = "Requerimientos.findByIdrequerimientos", query = "SELECT r FROM Requerimientos r WHERE r.idrequerimientos = :idrequerimientos"),
    @NamedQuery(name = "Requerimientos.findByCantidad", query = "SELECT r FROM Requerimientos r WHERE r.cantidad = :cantidad")})
public class Requerimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrequerimientos")
    private Integer idrequerimientos;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumns({
        @JoinColumn(name = "idlote", referencedColumnName = "idlote"),
        @JoinColumn(name = "idalmacen", referencedColumnName = "idalmacen"),
        @JoinColumn(name = "idtipoItem", referencedColumnName = "idtipoItem"),
        @JoinColumn(name = "idcompra", referencedColumnName = "idcompra")})
    @ManyToOne(optional = false)
    private Lote lote;
    @OneToMany(mappedBy = "idrequerimientos")
    private List<Pealtipoitem> pealtipoitemList;

    public Requerimientos() {
    }

    public Requerimientos(Integer idrequerimientos) {
        this.idrequerimientos = idrequerimientos;
    }

    public Integer getIdrequerimientos() {
        return idrequerimientos;
    }

    public void setIdrequerimientos(Integer idrequerimientos) {
        this.idrequerimientos = idrequerimientos;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    @XmlTransient
    public List<Pealtipoitem> getPealtipoitemList() {
        return pealtipoitemList;
    }

    public void setPealtipoitemList(List<Pealtipoitem> pealtipoitemList) {
        this.pealtipoitemList = pealtipoitemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrequerimientos != null ? idrequerimientos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Requerimientos)) {
            return false;
        }
        Requerimientos other = (Requerimientos) object;
        if ((this.idrequerimientos == null && other.idrequerimientos != null) || (this.idrequerimientos != null && !this.idrequerimientos.equals(other.idrequerimientos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Requerimientos[ idrequerimientos=" + idrequerimientos + " ]";
    }
    
}
