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
public class VitemPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idventa")
    private int idventa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iditem")
    private int iditem;

    public VitemPK() {
    }

    public VitemPK(int idventa, int iditem) {
        this.idventa = idventa;
        this.iditem = iditem;
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getIditem() {
        return iditem;
    }

    public void setIditem(int iditem) {
        this.iditem = iditem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idventa;
        hash += (int) iditem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VitemPK)) {
            return false;
        }
        VitemPK other = (VitemPK) object;
        if (this.idventa != other.idventa) {
            return false;
        }
        if (this.iditem != other.iditem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.VitemPK[ idventa=" + idventa + ", iditem=" + iditem + " ]";
    }
    
}
