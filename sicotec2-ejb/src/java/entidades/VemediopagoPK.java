/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jorge
 */
@Embeddable
public class VemediopagoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idventa")
    private int idventa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmedioPago")
    private int idmedioPago;

    public VemediopagoPK() {
    }

    public VemediopagoPK(int idventa, int idmedioPago) {
        this.idventa = idventa;
        this.idmedioPago = idmedioPago;
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getIdmedioPago() {
        return idmedioPago;
    }

    public void setIdmedioPago(int idmedioPago) {
        this.idmedioPago = idmedioPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idventa;
        hash += (int) idmedioPago;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VemediopagoPK)) {
            return false;
        }
        VemediopagoPK other = (VemediopagoPK) object;
        if (this.idventa != other.idventa) {
            return false;
        }
        if (this.idmedioPago != other.idmedioPago) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.VemediopagoPK[ idventa=" + idventa + ", idmedioPago=" + idmedioPago + " ]";
    }
    
}
