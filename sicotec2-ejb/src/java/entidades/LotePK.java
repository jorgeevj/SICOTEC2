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
public class LotePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idlote")
    private int idlote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idalmacen")
    private int idalmacen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "idtipoItem")
    private String idtipoItem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcompra")
    private int idcompra;

    public LotePK() {
    }

    public LotePK(int idlote, int idalmacen, String idtipoItem, int idcompra) {
        this.idlote = idlote;
        this.idalmacen = idalmacen;
        this.idtipoItem = idtipoItem;
        this.idcompra = idcompra;
    }

    public int getIdlote() {
        return idlote;
    }

    public void setIdlote(int idlote) {
        this.idlote = idlote;
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

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idlote;
        hash += (int) idalmacen;
        hash += (idtipoItem != null ? idtipoItem.hashCode() : 0);
        hash += (int) idcompra;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LotePK)) {
            return false;
        }
        LotePK other = (LotePK) object;
        if (this.idlote != other.idlote) {
            return false;
        }
        if (this.idalmacen != other.idalmacen) {
            return false;
        }
        if ((this.idtipoItem == null && other.idtipoItem != null) || (this.idtipoItem != null && !this.idtipoItem.equals(other.idtipoItem))) {
            return false;
        }
        if (this.idcompra != other.idcompra) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.LotePK[ idlote=" + idlote + ", idalmacen=" + idalmacen + ", idtipoItem=" + idtipoItem + ", idcompra=" + idcompra + " ]";
    }
    
}
