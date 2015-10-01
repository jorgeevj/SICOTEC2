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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Lote.findByIdlote", query = "SELECT l FROM Lote l WHERE l.idlote = :idlote"),
    @NamedQuery(name = "Lote.findByIdtipoItem", query = "SELECT l FROM Lote l WHERE l.idtipoItem = :idtipoItem"),
    @NamedQuery(name = "Lote.findByCantidad", query = "SELECT l FROM Lote l WHERE l.cantidad = :cantidad"),
    @NamedQuery(name = "Lote.findByPrecioUni", query = "SELECT l FROM Lote l WHERE l.precioUni = :precioUni")})
public class Lote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlote")
    private Integer idlote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtipoItem")
    private int idtipoItem;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precioUni")
    private Double precioUni;
    @JoinColumn(name = "idalmacen", referencedColumnName = "idalmacen")
    @ManyToOne(optional = false)
    private Altipoitem idalmacen;
    @JoinColumn(name = "idcompra", referencedColumnName = "idcompra")
    @ManyToOne(optional = false)
    private Compra idcompra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idlote")
    private List<Item> itemList;

    public Lote() {
    }

    public Lote(Integer idlote) {
        this.idlote = idlote;
    }

    public Lote(Integer idlote, int idtipoItem) {
        this.idlote = idlote;
        this.idtipoItem = idtipoItem;
    }

    public Integer getIdlote() {
        return idlote;
    }

    public void setIdlote(Integer idlote) {
        this.idlote = idlote;
    }

    public int getIdtipoItem() {
        return idtipoItem;
    }

    public void setIdtipoItem(int idtipoItem) {
        this.idtipoItem = idtipoItem;
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

    public Altipoitem getIdalmacen() {
        return idalmacen;
    }

    public void setIdalmacen(Altipoitem idalmacen) {
        this.idalmacen = idalmacen;
    }

    public Compra getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Compra idcompra) {
        this.idcompra = idcompra;
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
        hash += (idlote != null ? idlote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lote)) {
            return false;
        }
        Lote other = (Lote) object;
        if ((this.idlote == null && other.idlote != null) || (this.idlote != null && !this.idlote.equals(other.idlote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Lote[ idlote=" + idlote + " ]";
    }
    
}
