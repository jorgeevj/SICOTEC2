/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByIditem", query = "SELECT i FROM Item i WHERE i.iditem = :iditem"),
    @NamedQuery(name = "Item.findByEstado", query = "SELECT i FROM Item i WHERE i.estado = :estado"),
    @NamedQuery(name = "Item.findByOperatividad", query = "SELECT i FROM Item i WHERE i.operatividad = :operatividad")})
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "iditem")
    private String iditem;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @Size(max = 45)
    @Column(name = "operatividad")
    private String operatividad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iditem")
    private List<Garantia> garantiaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<Movimientoitem> movimientoitemList;
    @JoinColumns({
        @JoinColumn(name = "idalmacen", referencedColumnName = "idalmacen"),
        @JoinColumn(name = "idtipoItem", referencedColumnName = "idtipoItem")})
    @ManyToOne(optional = false)
    private Altipoitem altipoitem;
    @JoinColumns({
        @JoinColumn(name = "idlote", referencedColumnName = "idlote"),
        @JoinColumn(name = "idcompra", referencedColumnName = "idcompra")})
    @ManyToOne(optional = false)
    private Lote lote;
    @JoinColumn(name = "idventa", referencedColumnName = "idventa")
    @ManyToOne
    private Venta idventa;

    public Item() {
    }

    public Item(String iditem) {
        this.iditem = iditem;
    }

    public String getIditem() {
        return iditem;
    }

    public void setIditem(String iditem) {
        this.iditem = iditem;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getOperatividad() {
        return operatividad;
    }

    public void setOperatividad(String operatividad) {
        this.operatividad = operatividad;
    }

    @XmlTransient
    public List<Garantia> getGarantiaList() {
        return garantiaList;
    }

    public void setGarantiaList(List<Garantia> garantiaList) {
        this.garantiaList = garantiaList;
    }

    @XmlTransient
    public List<Movimientoitem> getMovimientoitemList() {
        return movimientoitemList;
    }

    public void setMovimientoitemList(List<Movimientoitem> movimientoitemList) {
        this.movimientoitemList = movimientoitemList;
    }

    public Altipoitem getAltipoitem() {
        return altipoitem;
    }

    public void setAltipoitem(Altipoitem altipoitem) {
        this.altipoitem = altipoitem;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Venta getIdventa() {
        return idventa;
    }

    public void setIdventa(Venta idventa) {
        this.idventa = idventa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iditem != null ? iditem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.iditem == null && other.iditem != null) || (this.iditem != null && !this.iditem.equals(other.iditem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Item[ iditem=" + iditem + " ]";
    }
    
}
