/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "Requerimientos.findByIdrequerimientos", query = "SELECT r FROM Requerimientos r WHERE r.requerimientosPK.idrequerimientos = :idrequerimientos"),
    @NamedQuery(name = "Requerimientos.findByCantidad", query = "SELECT r FROM Requerimientos r WHERE r.cantidad = :cantidad"),
    @NamedQuery(name = "Requerimientos.findByIdalmacen", query = "SELECT r FROM Requerimientos r WHERE r.requerimientosPK.idalmacen = :idalmacen"),
    @NamedQuery(name = "Requerimientos.findByIdtipoItem", query = "SELECT r FROM Requerimientos r WHERE r.requerimientosPK.idtipoItem = :idtipoItem")})
public class Requerimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RequerimientosPK requerimientosPK;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumns({
        @JoinColumn(name = "idalmacen", referencedColumnName = "idalmacen", insertable = false, updatable = false),
        @JoinColumn(name = "idtipoItem", referencedColumnName = "idtipoItem", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Altipoitem altipoitem;
    @JoinColumn(name = "idcompra", referencedColumnName = "idcompra")
    @ManyToOne(optional = false)
    private Compra idcompra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requerimientos")
    private List<Pealtipoitem> pealtipoitemList;

    public Requerimientos() {
    }

    public Requerimientos(RequerimientosPK requerimientosPK) {
        this.requerimientosPK = requerimientosPK;
    }

    public Requerimientos(int idrequerimientos, int idalmacen, String idtipoItem) {
        this.requerimientosPK = new RequerimientosPK(idrequerimientos, idalmacen, idtipoItem);
    }

    public RequerimientosPK getRequerimientosPK() {
        return requerimientosPK;
    }

    public void setRequerimientosPK(RequerimientosPK requerimientosPK) {
        this.requerimientosPK = requerimientosPK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Altipoitem getAltipoitem() {
        return altipoitem;
    }

    public void setAltipoitem(Altipoitem altipoitem) {
        this.altipoitem = altipoitem;
    }

    public Compra getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Compra idcompra) {
        this.idcompra = idcompra;
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
        hash += (requerimientosPK != null ? requerimientosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Requerimientos)) {
            return false;
        }
        Requerimientos other = (Requerimientos) object;
        if ((this.requerimientosPK == null && other.requerimientosPK != null) || (this.requerimientosPK != null && !this.requerimientosPK.equals(other.requerimientosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Requerimientos[ requerimientosPK=" + requerimientosPK + " ]";
    }
    
}
