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
@Table(name = "movimientoitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimientoitem.findAll", query = "SELECT m FROM Movimientoitem m"),
    @NamedQuery(name = "Movimientoitem.findByIdmovimiento", query = "SELECT m FROM Movimientoitem m WHERE m.movimientoitemPK.idmovimiento = :idmovimiento"),
    @NamedQuery(name = "Movimientoitem.findByEstado", query = "SELECT m FROM Movimientoitem m WHERE m.estado = :estado"),
    @NamedQuery(name = "Movimientoitem.findByIditem", query = "SELECT m FROM Movimientoitem m WHERE m.movimientoitemPK.iditem = :iditem")})
public class Movimientoitem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MovimientoitemPK movimientoitemPK;
    @Column(name = "estado")
    private Integer estado;
    @JoinColumn(name = "iditem", referencedColumnName = "iditem", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Item item;
    @JoinColumn(name = "idmovimiento", referencedColumnName = "idmovimiento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Movimiento movimiento;

    public Movimientoitem() {
    }

    public Movimientoitem(MovimientoitemPK movimientoitemPK) {
        this.movimientoitemPK = movimientoitemPK;
    }

    public Movimientoitem(int idmovimiento, String iditem) {
        this.movimientoitemPK = new MovimientoitemPK(idmovimiento, iditem);
    }

    public MovimientoitemPK getMovimientoitemPK() {
        return movimientoitemPK;
    }

    public void setMovimientoitemPK(MovimientoitemPK movimientoitemPK) {
        this.movimientoitemPK = movimientoitemPK;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movimientoitemPK != null ? movimientoitemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientoitem)) {
            return false;
        }
        Movimientoitem other = (Movimientoitem) object;
        if ((this.movimientoitemPK == null && other.movimientoitemPK != null) || (this.movimientoitemPK != null && !this.movimientoitemPK.equals(other.movimientoitemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Movimientoitem[ movimientoitemPK=" + movimientoitemPK + " ]";
    }
    
}
