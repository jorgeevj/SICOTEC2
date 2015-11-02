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
@Table(name = "lote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lote.findAll", query = "SELECT l FROM Lote l"),
    @NamedQuery(name = "Lote.findByIdlote", query = "SELECT l FROM Lote l WHERE l.lotePK.idlote = :idlote"),
    @NamedQuery(name = "Lote.findByCantidad", query = "SELECT l FROM Lote l WHERE l.cantidad = :cantidad"),
    @NamedQuery(name = "Lote.findByPrecioUni", query = "SELECT l FROM Lote l WHERE l.precioUni = :precioUni"),
    @NamedQuery(name = "Lote.findByIdalmacen", query = "SELECT l FROM Lote l WHERE l.lotePK.idalmacen = :idalmacen"),
    @NamedQuery(name = "Lote.findByIdtipoItem", query = "SELECT l FROM Lote l WHERE l.lotePK.idtipoItem = :idtipoItem"),
    @NamedQuery(name = "Lote.findByIdcompra", query = "SELECT l FROM Lote l WHERE l.lotePK.idcompra = :idcompra")})
public class Lote implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LotePK lotePK;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precioUni")
    private Double precioUni;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lote")
    private List<Requerimientos> requerimientosList;
    @JoinColumns({
        @JoinColumn(name = "idalmacen", referencedColumnName = "idalmacen", insertable = false, updatable = false),
        @JoinColumn(name = "idtipoItem", referencedColumnName = "idtipoItem", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Altipoitem altipoitem;
    @JoinColumn(name = "idcompra", referencedColumnName = "idcompra", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Compra compra;
    @JoinColumn(name = "idunidades", referencedColumnName = "idunidades")
    @ManyToOne(optional = false)
    private Unidades idunidades;
    @OneToMany(mappedBy = "idlote")
    private List<Item> itemList;

    public Lote() {
    }

    public Lote(LotePK lotePK) {
        this.lotePK = lotePK;
    }

    public Lote(int idlote, int idalmacen, String idtipoItem, int idcompra) {
        this.lotePK = new LotePK(idlote, idalmacen, idtipoItem, idcompra);
    }

    public LotePK getLotePK() {
        return lotePK;
    }

    public void setLotePK(LotePK lotePK) {
        this.lotePK = lotePK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUni() {
        return precioUni;
    }

    public void setPrecioUni(Double precioUni) {
        this.precioUni = precioUni;
    }

    @XmlTransient
    public List<Requerimientos> getRequerimientosList() {
        return requerimientosList;
    }

    public void setRequerimientosList(List<Requerimientos> requerimientosList) {
        this.requerimientosList = requerimientosList;
    }

    public Altipoitem getAltipoitem() {
        return altipoitem;
    }

    public void setAltipoitem(Altipoitem altipoitem) {
        this.altipoitem = altipoitem;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Unidades getIdunidades() {
        return idunidades;
    }

    public void setIdunidades(Unidades idunidades) {
        this.idunidades = idunidades;
    }

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lotePK != null ? lotePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lote)) {
            return false;
        }
        Lote other = (Lote) object;
        if ((this.lotePK == null && other.lotePK != null) || (this.lotePK != null && !this.lotePK.equals(other.lotePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Lote[ lotePK=" + lotePK + " ]";
    }
    
}
