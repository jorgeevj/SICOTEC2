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
public class DocalmacenPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "iddocumento")
    private int iddocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idalmacen")
    private int idalmacen;

    public DocalmacenPK() {
    }

    public DocalmacenPK(int iddocumento, int idalmacen) {
        this.iddocumento = iddocumento;
        this.idalmacen = idalmacen;
    }

    public int getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(int iddocumento) {
        this.iddocumento = iddocumento;
    }

    public int getIdalmacen() {
        return idalmacen;
    }

    public void setIdalmacen(int idalmacen) {
        this.idalmacen = idalmacen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddocumento;
        hash += (int) idalmacen;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocalmacenPK)) {
            return false;
        }
        DocalmacenPK other = (DocalmacenPK) object;
        if (this.iddocumento != other.iddocumento) {
            return false;
        }
        if (this.idalmacen != other.idalmacen) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DocalmacenPK[ iddocumento=" + iddocumento + ", idalmacen=" + idalmacen + " ]";
    }
    
}
