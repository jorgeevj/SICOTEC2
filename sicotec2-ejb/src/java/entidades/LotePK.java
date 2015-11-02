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
public class LotePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idlote")
    private int idlote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcompra")
    private int idcompra;

    public LotePK() {
    }

    public LotePK(int idlote, int idcompra) {
        this.idlote = idlote;
        this.idcompra = idcompra;
    }

    public int getIdlote() {
        return idlote;
    }

    public void setIdlote(int idlote) {
        this.idlote = idlote;
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
        if (this.idcompra != other.idcompra) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.LotePK[ idlote=" + idlote + ", idcompra=" + idcompra + " ]";
    }
    
}
