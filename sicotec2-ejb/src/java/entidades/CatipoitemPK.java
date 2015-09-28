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
public class CatipoitemPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcaracteristica")
    private int idcaracteristica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtipoItem")
    private int idtipoItem;

    public CatipoitemPK() {
    }

    public CatipoitemPK(int idcaracteristica, int idtipoItem) {
        this.idcaracteristica = idcaracteristica;
        this.idtipoItem = idtipoItem;
    }

    public int getIdcaracteristica() {
        return idcaracteristica;
    }

    public void setIdcaracteristica(int idcaracteristica) {
        this.idcaracteristica = idcaracteristica;
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
        hash += (int) idcaracteristica;
        hash += (int) idtipoItem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatipoitemPK)) {
            return false;
        }
        CatipoitemPK other = (CatipoitemPK) object;
        if (this.idcaracteristica != other.idcaracteristica) {
            return false;
        }
        if (this.idtipoItem != other.idtipoItem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CatipoitemPK[ idcaracteristica=" + idcaracteristica + ", idtipoItem=" + idtipoItem + " ]";
    }
    
}
