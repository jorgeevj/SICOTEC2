/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Caracteristica.findByNombre", query = "SELECT c FROM Caracteristica c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Caracteristica.findByDescripcion", query = "SELECT c FROM Caracteristica c WHERE c.descripcion = :descripcion")})
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
    @Size(max = 300)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinTable(name = "catipoitem", joinColumns = {
        @JoinColumn(name = "idcaracteristica", referencedColumnName = "idcaracteristica")}, inverseJoinColumns = {
        @JoinColumn(name = "idtipoItem", referencedColumnName = "idtipoItem")})
    @ManyToMany
    private List<Tipoitem> tipoitemList;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
