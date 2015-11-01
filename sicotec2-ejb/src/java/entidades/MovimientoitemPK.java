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
import javax.validation.constraints.Size;

/**
 *
 * @author Jorge
 */
@Embeddable
public class MovimientoitemPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmovimiento")
    private int idmovimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "iditem")
    private String iditem;

    public MovimientoitemPK() {
    }

    public MovimientoitemPK(int idmovimiento, String iditem) {
        this.idmovimiento = idmovimiento;
        this.iditem = iditem;
    }

    public int getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(int idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public String getIditem() {
        return iditem;
    }

    public void setIditem(String iditem) {
        this.iditem = iditem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idmovimiento;
        hash += (iditem != null ? iditem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientoitemPK)) {
            return false;
        }
        MovimientoitemPK other = (MovimientoitemPK) object;
        if (this.idmovimiento != other.idmovimiento) {
            return false;
        }
        if ((this.iditem == null && other.iditem != null) || (this.iditem != null && !this.iditem.equals(other.iditem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MovimientoitemPK[ idmovimiento=" + idmovimiento + ", iditem=" + iditem + " ]";
    }
    
}
