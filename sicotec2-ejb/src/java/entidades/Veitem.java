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
@Table(name = "veitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Veitem.findAll", query = "SELECT v FROM Veitem v"),
    @NamedQuery(name = "Veitem.findByIdventa", query = "SELECT v FROM Veitem v WHERE v.veitemPK.idventa = :idventa"),
    @NamedQuery(name = "Veitem.findByPrecio", query = "SELECT v FROM Veitem v WHERE v.precio = :precio"),
    @NamedQuery(name = "Veitem.findByDescuento", query = "SELECT v FROM Veitem v WHERE v.descuento = :descuento"),
    @NamedQuery(name = "Veitem.findByIditem", query = "SELECT v FROM Veitem v WHERE v.veitemPK.iditem = :iditem")})
public class Veitem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VeitemPK veitemPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Column(name = "descuento")
    private Double descuento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veitem")
    private List<Garantia> garantiaList;
    @JoinColumn(name = "iditem", referencedColumnName = "iditem", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Item item;
    @JoinColumn(name = "idventa", referencedColumnName = "idventa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Venta venta;

    public Veitem() {
    }

    public Veitem(VeitemPK veitemPK) {
        this.veitemPK = veitemPK;
    }

    public Veitem(int idventa, String iditem) {
        this.veitemPK = new VeitemPK(idventa, iditem);
    }

    public VeitemPK getVeitemPK() {
        return veitemPK;
    }

    public void setVeitemPK(VeitemPK veitemPK) {
        this.veitemPK = veitemPK;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    @XmlTransient
    public List<Garantia> getGarantiaList() {
        return garantiaList;
    }

    public void setGarantiaList(List<Garantia> garantiaList) {
        this.garantiaList = garantiaList;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (veitemPK != null ? veitemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veitem)) {
            return false;
        }
        Veitem other = (Veitem) object;
        if ((this.veitemPK == null && other.veitemPK != null) || (this.veitemPK != null && !this.veitemPK.equals(other.veitemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Veitem[ veitemPK=" + veitemPK + " ]";
    }
    
}
