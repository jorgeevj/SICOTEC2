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
@Table(name = "impuesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Impuesto.findAll", query = "SELECT i FROM Impuesto i"),
    @NamedQuery(name = "Impuesto.findByIdimpuesto", query = "SELECT i FROM Impuesto i WHERE i.idimpuesto = :idimpuesto"),
    @NamedQuery(name = "Impuesto.findByNombre", query = "SELECT i FROM Impuesto i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Impuesto.findByProcentaje", query = "SELECT i FROM Impuesto i WHERE i.procentaje = :procentaje")})
public class Impuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idimpuesto")
    private Integer idimpuesto;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "procentaje")
    private Double procentaje;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idimpuesto")
    private List<Venta> ventaList;

    public Impuesto() {
    }

    public Impuesto(Integer idimpuesto) {
        this.idimpuesto = idimpuesto;
    }

    public Integer getIdimpuesto() {
        return idimpuesto;
    }

    public void setIdimpuesto(Integer idimpuesto) {
        this.idimpuesto = idimpuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getProcentaje() {
        return procentaje;
    }

    public void setProcentaje(Double procentaje) {
        this.procentaje = procentaje;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idimpuesto != null ? idimpuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Impuesto)) {
            return false;
        }
        Impuesto other = (Impuesto) object;
        if ((this.idimpuesto == null && other.idimpuesto != null) || (this.idimpuesto != null && !this.idimpuesto.equals(other.idimpuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Impuesto[ idimpuesto=" + idimpuesto + " ]";
    }
    
}
