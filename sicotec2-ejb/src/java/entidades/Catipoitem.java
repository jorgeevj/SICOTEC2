/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "catipoitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catipoitem.findAll", query = "SELECT c FROM Catipoitem c"),
    @NamedQuery(name = "Catipoitem.findByIdcaracteristica", query = "SELECT c FROM Catipoitem c WHERE c.catipoitemPK.idcaracteristica = :idcaracteristica"),
    @NamedQuery(name = "Catipoitem.findByIdtipoItem", query = "SELECT c FROM Catipoitem c WHERE c.catipoitemPK.idtipoItem = :idtipoItem"),
    @NamedQuery(name = "Catipoitem.findByDercripcion", query = "SELECT c FROM Catipoitem c WHERE c.dercripcion = :dercripcion")})
public class Catipoitem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CatipoitemPK catipoitemPK;
    @Size(max = 45)
    @Column(name = "dercripcion")
    private String dercripcion;
    @JoinColumn(name = "idcaracteristica", referencedColumnName = "idcaracteristica", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Caracteristica caracteristica;
    @JoinColumn(name = "idtipoItem", referencedColumnName = "idtipoItem", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tipoitem tipoitem;

    public Catipoitem() {
    }

    public Catipoitem(CatipoitemPK catipoitemPK) {
        this.catipoitemPK = catipoitemPK;
    }

    public Catipoitem(int idcaracteristica, String idtipoItem) {
        this.catipoitemPK = new CatipoitemPK(idcaracteristica, idtipoItem);
    }

    public CatipoitemPK getCatipoitemPK() {
        return catipoitemPK;
    }

    public void setCatipoitemPK(CatipoitemPK catipoitemPK) {
        this.catipoitemPK = catipoitemPK;
    }

    public String getDercripcion() {
        return dercripcion;
    }

    public void setDercripcion(String dercripcion) {
        this.dercripcion = dercripcion;
    }

    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    public Tipoitem getTipoitem() {
        return tipoitem;
    }

    public void setTipoitem(Tipoitem tipoitem) {
        this.tipoitem = tipoitem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catipoitemPK != null ? catipoitemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catipoitem)) {
            return false;
        }
        Catipoitem other = (Catipoitem) object;
        if ((this.catipoitemPK == null && other.catipoitemPK != null) || (this.catipoitemPK != null && !this.catipoitemPK.equals(other.catipoitemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Catipoitem[ catipoitemPK=" + catipoitemPK + " ]";
    }
    
}
