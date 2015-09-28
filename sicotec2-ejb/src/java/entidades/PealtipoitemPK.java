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
public class PealtipoitemPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpedido")
    private int idpedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idalmacen")
    private int idalmacen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtipoItem")
    private int idtipoItem;

    public PealtipoitemPK() {
    }

    public PealtipoitemPK(int idpedido, int idalmacen, int idtipoItem) {
        this.idpedido = idpedido;
        this.idalmacen = idalmacen;
        this.idtipoItem = idtipoItem;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getIdalmacen() {
        return idalmacen;
    }

    public void setIdalmacen(int idalmacen) {
        this.idalmacen = idalmacen;
    }

    public int getIdtipoItem() {
        return idtipoItem;
    }

    public void setIdtipoItem(int idtipoItem) {
        this.idtipoItem = idtipoItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idpedido;
        hash += (int) idalmacen;
        hash += (int) idtipoItem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PealtipoitemPK)) {
            return false;
        }
        PealtipoitemPK other = (PealtipoitemPK) object;
        if (this.idpedido != other.idpedido) {
            return false;
        }
        if (this.idalmacen != other.idalmacen) {
            return false;
        }
        if (this.idtipoItem != other.idtipoItem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.PealtipoitemPK[ idpedido=" + idpedido + ", idalmacen=" + idalmacen + ", idtipoItem=" + idtipoItem + " ]";
    }
    
}
