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
@Table(name = "altipoitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Altipoitem.findAll", query = "SELECT a FROM Altipoitem a"),
    @NamedQuery(name = "Altipoitem.findByIdalmacen", query = "SELECT a FROM Altipoitem a WHERE a.altipoitemPK.idalmacen = :idalmacen"),
    @NamedQuery(name = "Altipoitem.findByCantidad", query = "SELECT a FROM Altipoitem a WHERE a.cantidad = :cantidad"),
    @NamedQuery(name = "Altipoitem.findByEstado", query = "SELECT a FROM Altipoitem a WHERE a.estado = :estado"),
    @NamedQuery(name = "Altipoitem.findByReservado", query = "SELECT a FROM Altipoitem a WHERE a.reservado = :reservado"),
    @NamedQuery(name = "Altipoitem.findByComprados", query = "SELECT a FROM Altipoitem a WHERE a.comprados = :comprados"),
    @NamedQuery(name = "Altipoitem.findByIdtipoItem", query = "SELECT a FROM Altipoitem a WHERE a.altipoitemPK.idtipoItem = :idtipoItem")})
public class Altipoitem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AltipoitemPK altipoitemPK;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "reservado")
    private Integer reservado;
    @Column(name = "comprados")
    private Integer comprados;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "altipoitem")
    private List<Requerimientos> requerimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "altipoitem")
    private List<Lote> loteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "altipoitem")
    private List<Item> itemList;
    @JoinColumn(name = "idalmacen", referencedColumnName = "idalmacen", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Almacen almacen;
    @JoinColumn(name = "idtipoItem", referencedColumnName = "idtipoItem", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tipoitem tipoitem;

    public Altipoitem() {
    }

    public Altipoitem(AltipoitemPK altipoitemPK) {
        this.altipoitemPK = altipoitemPK;
    }

    public Altipoitem(int idalmacen, String idtipoItem) {
        this.altipoitemPK = new AltipoitemPK(idalmacen, idtipoItem);
    }

    public AltipoitemPK getAltipoitemPK() {
        return altipoitemPK;
    }

    public void setAltipoitemPK(AltipoitemPK altipoitemPK) {
        this.altipoitemPK = altipoitemPK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getReservado() {
        return reservado;
    }

    public void setReservado(Integer reservado) {
        this.reservado = reservado;
    }

    public Integer getComprados() {
        return comprados;
    }

    public void setComprados(Integer comprados) {
        this.comprados = comprados;
    }

    @XmlTransient
    public List<Requerimientos> getRequerimientosList() {
        return requerimientosList;
    }

    public void setRequerimientosList(List<Requerimientos> requerimientosList) {
        this.requerimientosList = requerimientosList;
    }

    @XmlTransient
    public List<Lote> getLoteList() {
        return loteList;
    }

    public void setLoteList(List<Lote> loteList) {
        this.loteList = loteList;
    }

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
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
        hash += (altipoitemPK != null ? altipoitemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Altipoitem)) {
            return false;
        }
        Altipoitem other = (Altipoitem) object;
        if ((this.altipoitemPK == null && other.altipoitemPK != null) || (this.altipoitemPK != null && !this.altipoitemPK.equals(other.altipoitemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Altipoitem[ altipoitemPK=" + altipoitemPK + " ]";
    }
    
}
