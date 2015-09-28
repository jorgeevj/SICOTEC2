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
public class EmppersonaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idempresa")
    private int idempresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpersona")
    private int idpersona;

    public EmppersonaPK() {
    }

    public EmppersonaPK(int idempresa, int idpersona) {
        this.idempresa = idempresa;
        this.idpersona = idpersona;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idempresa;
        hash += (int) idpersona;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmppersonaPK)) {
            return false;
        }
        EmppersonaPK other = (EmppersonaPK) object;
        if (this.idempresa != other.idempresa) {
            return false;
        }
        if (this.idpersona != other.idpersona) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EmppersonaPK[ idempresa=" + idempresa + ", idpersona=" + idpersona + " ]";
    }
    
}
