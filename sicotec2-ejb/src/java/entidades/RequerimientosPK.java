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
public class RequerimientosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idrequerimientos")
    private int idrequerimientos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idalmacen")
    private int idalmacen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "idtipoItem")
    private String idtipoItem;

    public RequerimientosPK() {
    }

    public RequerimientosPK(int idrequerimientos, int idalmacen, String idtipoItem) {
        this.idrequerimientos = idrequerimientos;
        this.idalmacen = idalmacen;
        this.idtipoItem = idtipoItem;
    }

    public int getIdrequerimientos() {
        return idrequerimientos;
    }

    public void setIdrequerimientos(int idrequerimientos) {
        this.idrequerimientos = idrequerimientos;
    }

    public int getIdalmacen() {
        return idalmacen;
    }

    public void setIdalmacen(int idalmacen) {
        this.idalmacen = idalmacen;
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
        hash += (int) idrequerimientos;
        hash += (int) idalmacen;
        hash += (idtipoItem != null ? idtipoItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequerimientosPK)) {
            return false;
        }
        RequerimientosPK other = (RequerimientosPK) object;
        if (this.idrequerimientos != other.idrequerimientos) {
            return false;
        }
        if (this.idalmacen != other.idalmacen) {
            return false;
        }
        if ((this.idtipoItem == null && other.idtipoItem != null) || (this.idtipoItem != null && !this.idtipoItem.equals(other.idtipoItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RequerimientosPK[ idrequerimientos=" + idrequerimientos + ", idalmacen=" + idalmacen + ", idtipoItem=" + idtipoItem + " ]";
    }
    
}
