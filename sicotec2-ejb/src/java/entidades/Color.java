/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "color")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Color.findAll", query = "SELECT c FROM Color c"),
    @NamedQuery(name = "Color.findByIdcolor", query = "SELECT c FROM Color c WHERE c.idcolor = :idcolor"),
    @NamedQuery(name = "Color.findByNombre", query = "SELECT c FROM Color c WHERE c.nombre = :nombre")})
public class Color implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcolor")
    private Integer idcolor;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcolor")
    private List<Tipoitem> tipoitemList;

    public Color() {
    }

    public Color(Integer idcolor) {
        this.idcolor = idcolor;
    }

    public Integer getIdcolor() {
        return idcolor;
    }

    public void setIdcolor(Integer idcolor) {
        this.idcolor = idcolor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Tipoitem> getTipoitemList() {
        return tipoitemList;
    }

    public void setTipoitemList(List<Tipoitem> tipoitemList) {
        this.tipoitemList = tipoitemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcolor != null ? idcolor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Color)) {
            return false;
        }
        Color other = (Color) object;
        if ((this.idcolor == null && other.idcolor != null) || (this.idcolor != null && !this.idcolor.equals(other.idcolor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Color[ idcolor=" + idcolor + " ]";
    }
    
}
