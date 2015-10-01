/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "cotipoitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotipoitem.findAll", query = "SELECT c FROM Cotipoitem c"),
    @NamedQuery(name = "Cotipoitem.findByIdcotizacion", query = "SELECT c FROM Cotipoitem c WHERE c.cotipoitemPK.idcotizacion = :idcotizacion"),
    @NamedQuery(name = "Cotipoitem.findByIdtipoItem", query = "SELECT c FROM Cotipoitem c WHERE c.cotipoitemPK.idtipoItem = :idtipoItem"),
    @NamedQuery(name = "Cotipoitem.findByPrecio", query = "SELECT c FROM Cotipoitem c WHERE c.precio = :precio"),
    @NamedQuery(name = "Cotipoitem.findByCantidad", query = "SELECT c FROM Cotipoitem c WHERE c.cantidad = :cantidad"),
    @NamedQuery(name = "Cotipoitem.findByDescuento", query = "SELECT c FROM Cotipoitem c WHERE c.descuento = :descuento")})
public class Cotipoitem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CotipoitemPK cotipoitemPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "descuento")
    private Double descuento;
    @JoinColumn(name = "idcotizacion", referencedColumnName = "idcotizacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cotizacion cotizacion;
    @JoinColumn(name = "idtipoItem", referencedColumnName = "idtipoItem", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tipoitem tipoitem;

    public Cotipoitem() {
    }

    public Cotipoitem(CotipoitemPK cotipoitemPK) {
        this.cotipoitemPK = cotipoitemPK;
    }

    public Cotipoitem(int idcotizacion, String idtipoItem) {
        this.cotipoitemPK = new CotipoitemPK(idcotizacion, idtipoItem);
    }

    public CotipoitemPK getCotipoitemPK() {
        return cotipoitemPK;
    }

    public void setCotipoitemPK(CotipoitemPK cotipoitemPK) {
        this.cotipoitemPK = cotipoitemPK;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Tipoitem getTipoitem() {
        return tipoitem;
    }

    public void setTipoitem(Tipoitem tipoitem) {
        this.tipoitem = tipoitem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cotipoitemPK != null ? cotipoitemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotipoitem)) {
            return false;
        }
        Cotipoitem other = (Cotipoitem) object;
        if ((this.cotipoitemPK == null && other.cotipoitemPK != null) || (this.cotipoitemPK != null && !this.cotipoitemPK.equals(other.cotipoitemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cotipoitem[ cotipoitemPK=" + cotipoitemPK + " ]";
    }
    
}
