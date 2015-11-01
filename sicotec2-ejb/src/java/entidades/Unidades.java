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
@Table(name = "unidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unidades.findAll", query = "SELECT u FROM Unidades u"),
    @NamedQuery(name = "Unidades.findByIdunidades", query = "SELECT u FROM Unidades u WHERE u.idunidades = :idunidades"),
    @NamedQuery(name = "Unidades.findByNombre", query = "SELECT u FROM Unidades u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Unidades.findByUnidades", query = "SELECT u FROM Unidades u WHERE u.unidades = :unidades")})
public class Unidades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idunidades")
    private Integer idunidades;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "unidades")
    private Integer unidades;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idunidades")
    private List<Lote> loteList;

    public Unidades() {
    }

    public Unidades(Integer idunidades) {
        this.idunidades = idunidades;
    }

    public Integer getIdunidades() {
        return idunidades;
    }

    public void setIdunidades(Integer idunidades) {
        this.idunidades = idunidades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    @XmlTransient
    public List<Lote> getLoteList() {
        return loteList;
    }

    public void setLoteList(List<Lote> loteList) {
        this.loteList = loteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idunidades != null ? idunidades.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidades)) {
            return false;
        }
        Unidades other = (Unidades) object;
        if ((this.idunidades == null && other.idunidades != null) || (this.idunidades != null && !this.idunidades.equals(other.idunidades))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Unidades[ idunidades=" + idunidades + " ]";
    }
    
}
