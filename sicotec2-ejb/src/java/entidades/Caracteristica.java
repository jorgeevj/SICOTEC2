/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "caracteristica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caracteristica.findAll", query = "SELECT c FROM Caracteristica c"),
    @NamedQuery(name = "Caracteristica.findByIdcaracteristica", query = "SELECT c FROM Caracteristica c WHERE c.idcaracteristica = :idcaracteristica"),
    @NamedQuery(name = "Caracteristica.findByNombre", query = "SELECT c FROM Caracteristica c WHERE c.nombre = :nombre")})
public class Caracteristica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcaracteristica")
    private Integer idcaracteristica;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caracteristica")
    private Collection<Catipoitem> catipoitemCollection;

    public Caracteristica() {
    }

    public Caracteristica(Integer idcaracteristica) {
        this.idcaracteristica = idcaracteristica;
    }

    public Integer getIdcaracteristica() {
        return idcaracteristica;
    }

    public void setIdcaracteristica(Integer idcaracteristica) {
        this.idcaracteristica = idcaracteristica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Catipoitem> getCatipoitemCollection() {
        return catipoitemCollection;
    }

    public void setCatipoitemCollection(Collection<Catipoitem> catipoitemCollection) {
        this.catipoitemCollection = catipoitemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcaracteristica != null ? idcaracteristica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caracteristica)) {
            return false;
        }
        Caracteristica other = (Caracteristica) object;
        if ((this.idcaracteristica == null && other.idcaracteristica != null) || (this.idcaracteristica != null && !this.idcaracteristica.equals(other.idcaracteristica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Caracteristica[ idcaracteristica=" + idcaracteristica + " ]";
    }
    
}
