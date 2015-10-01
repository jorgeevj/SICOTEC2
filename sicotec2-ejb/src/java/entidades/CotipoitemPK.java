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
public class CotipoitemPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcotizacion")
    private int idcotizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "idtipoItem")
    private String idtipoItem;

    public CotipoitemPK() {
    }

    public CotipoitemPK(int idcotizacion, String idtipoItem) {
        this.idcotizacion = idcotizacion;
        this.idtipoItem = idtipoItem;
    }

    public int getIdcotizacion() {
        return idcotizacion;
    }

    public void setIdcotizacion(int idcotizacion) {
        this.idcotizacion = idcotizacion;
    }

    public String getIdtipoItem() {
        return idtipoItem;
    }

    public void setIdtipoItem(String idtipoItem) {
        this.idtipoItem = idtipoItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcotizacion;
        hash += (idtipoItem != null ? idtipoItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CotipoitemPK)) {
            return false;
        }
        CotipoitemPK other = (CotipoitemPK) object;
        if (this.idcotizacion != other.idcotizacion) {
            return false;
        }
        if ((this.idtipoItem == null && other.idtipoItem != null) || (this.idtipoItem != null && !this.idtipoItem.equals(other.idtipoItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CotipoitemPK[ idcotizacion=" + idcotizacion + ", idtipoItem=" + idtipoItem + " ]";
    }
    
}
