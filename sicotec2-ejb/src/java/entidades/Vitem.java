/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "vitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vitem.findAll", query = "SELECT v FROM Vitem v"),
    @NamedQuery(name = "Vitem.findByIdventa", query = "SELECT v FROM Vitem v WHERE v.vitemPK.idventa = :idventa"),
    @NamedQuery(name = "Vitem.findByIditem", query = "SELECT v FROM Vitem v WHERE v.vitemPK.iditem = :iditem"),
    @NamedQuery(name = "Vitem.findByPrecio", query = "SELECT v FROM Vitem v WHERE v.precio = :precio"),
    @NamedQuery(name = "Vitem.findByDescuento", query = "SELECT v FROM Vitem v WHERE v.descuento = :descuento")})
public class Vitem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VitemPK vitemPK;
    @Size(max = 45)
    @Column(name = "precio")
    private String precio;
    @Size(max = 45)
    @Column(name = "descuento")
    private String descuento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vitem")
    private Collection<Garantia> garantiaCollection;
    @JoinColumn(name = "iditem", referencedColumnName = "iditem", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Item item;
    @JoinColumn(name = "idventa", referencedColumnName = "idventa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Venta venta;

    public Vitem() {
    }

    public Vitem(VitemPK vitemPK) {
        this.vitemPK = vitemPK;
    }

    public Vitem(int idventa, int iditem) {
        this.vitemPK = new VitemPK(idventa, iditem);
    }

    public VitemPK getVitemPK() {
        return vitemPK;
    }

    public void setVitemPK(VitemPK vitemPK) {
        this.vitemPK = vitemPK;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    @XmlTransient
    public Collection<Garantia> getGarantiaCollection() {
        return garantiaCollection;
    }

    public void setGarantiaCollection(Collection<Garantia> garantiaCollection) {
        this.garantiaCollection = garantiaCollection;
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
        hash += (vitemPK != null ? vitemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vitem)) {
            return false;
        }
        Vitem other = (Vitem) object;
        if ((this.vitemPK == null && other.vitemPK != null) || (this.vitemPK != null && !this.vitemPK.equals(other.vitemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Vitem[ vitemPK=" + vitemPK + " ]";
    }
    
}
