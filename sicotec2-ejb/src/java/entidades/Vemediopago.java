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
@Table(name = "vemediopago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vemediopago.findAll", query = "SELECT v FROM Vemediopago v"),
    @NamedQuery(name = "Vemediopago.findByIdventa", query = "SELECT v FROM Vemediopago v WHERE v.vemediopagoPK.idventa = :idventa"),
    @NamedQuery(name = "Vemediopago.findByIdmedioPago", query = "SELECT v FROM Vemediopago v WHERE v.vemediopagoPK.idmedioPago = :idmedioPago"),
    @NamedQuery(name = "Vemediopago.findByMonto", query = "SELECT v FROM Vemediopago v WHERE v.monto = :monto")})
public class Vemediopago implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VemediopagoPK vemediopagoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Double monto;
    @JoinColumn(name = "idmedioPago", referencedColumnName = "idmedioPago", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Mediopago mediopago;
    @JoinColumn(name = "idventa", referencedColumnName = "idventa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Venta venta;

    public Vemediopago() {
    }

    public Vemediopago(VemediopagoPK vemediopagoPK) {
        this.vemediopagoPK = vemediopagoPK;
    }

    public Vemediopago(int idventa, int idmedioPago) {
        this.vemediopagoPK = new VemediopagoPK(idventa, idmedioPago);
    }

    public VemediopagoPK getVemediopagoPK() {
        return vemediopagoPK;
    }

    public void setVemediopagoPK(VemediopagoPK vemediopagoPK) {
        this.vemediopagoPK = vemediopagoPK;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Mediopago getMediopago() {
        return mediopago;
    }

    public void setMediopago(Mediopago mediopago) {
        this.mediopago = mediopago;
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
        hash += (vemediopagoPK != null ? vemediopagoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vemediopago)) {
            return false;
        }
        Vemediopago other = (Vemediopago) object;
        if ((this.vemediopagoPK == null && other.vemediopagoPK != null) || (this.vemediopagoPK != null && !this.vemediopagoPK.equals(other.vemediopagoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Vemediopago[ vemediopagoPK=" + vemediopagoPK + " ]";
    }
    
}
