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
import javax.persistence.JoinColumns;
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
@Table(name = "pealtipoitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pealtipoitem.findAll", query = "SELECT p FROM Pealtipoitem p"),
    @NamedQuery(name = "Pealtipoitem.findByIdpedido", query = "SELECT p FROM Pealtipoitem p WHERE p.pealtipoitemPK.idpedido = :idpedido"),
    @NamedQuery(name = "Pealtipoitem.findByIdalmacen", query = "SELECT p FROM Pealtipoitem p WHERE p.pealtipoitemPK.idalmacen = :idalmacen"),
    @NamedQuery(name = "Pealtipoitem.findByIdtipoItem", query = "SELECT p FROM Pealtipoitem p WHERE p.pealtipoitemPK.idtipoItem = :idtipoItem"),
    @NamedQuery(name = "Pealtipoitem.findByCantidad", query = "SELECT p FROM Pealtipoitem p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Pealtipoitem.findByCostoUni", query = "SELECT p FROM Pealtipoitem p WHERE p.costoUni = :costoUni"),
    @NamedQuery(name = "Pealtipoitem.findByEstado", query = "SELECT p FROM Pealtipoitem p WHERE p.estado = :estado")})
public class Pealtipoitem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PealtipoitemPK pealtipoitemPK;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costoUni")
    private Double costoUni;
    @Column(name = "estado")
    private Integer estado;
    @JoinColumns({
        @JoinColumn(name = "idtipoItem", referencedColumnName = "idtipoItem", insertable = false, updatable = false),
        @JoinColumn(name = "idalmacen", referencedColumnName = "idalmacen", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Altipoitem altipoitem;
    @JoinColumn(name = "idcompra", referencedColumnName = "idcompra")
    @ManyToOne
    private Compra idcompra;
    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;

    public Pealtipoitem() {
    }

    public Pealtipoitem(PealtipoitemPK pealtipoitemPK) {
        this.pealtipoitemPK = pealtipoitemPK;
    }

    public Pealtipoitem(int idpedido, int idalmacen, int idtipoItem) {
        this.pealtipoitemPK = new PealtipoitemPK(idpedido, idalmacen, idtipoItem);
    }

    public PealtipoitemPK getPealtipoitemPK() {
        return pealtipoitemPK;
    }

    public void setPealtipoitemPK(PealtipoitemPK pealtipoitemPK) {
        this.pealtipoitemPK = pealtipoitemPK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCostoUni() {
        return costoUni;
    }

    public void setCostoUni(Double costoUni) {
        this.costoUni = costoUni;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pealtipoitemPK != null ? pealtipoitemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pealtipoitem)) {
            return false;
        }
        Pealtipoitem other = (Pealtipoitem) object;
        if ((this.pealtipoitemPK == null && other.pealtipoitemPK != null) || (this.pealtipoitemPK != null && !this.pealtipoitemPK.equals(other.pealtipoitemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pealtipoitem[ pealtipoitemPK=" + pealtipoitemPK + " ]";
    }
    
}
