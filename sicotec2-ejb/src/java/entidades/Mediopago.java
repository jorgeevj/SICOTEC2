/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "mediopago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mediopago.findAll", query = "SELECT m FROM Mediopago m"),
    @NamedQuery(name = "Mediopago.findByIdmedioPago", query = "SELECT m FROM Mediopago m WHERE m.idmedioPago = :idmedioPago"),
    @NamedQuery(name = "Mediopago.findByNombre", query = "SELECT m FROM Mediopago m WHERE m.nombre = :nombre")})
public class Mediopago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmedioPago")
    private Integer idmedioPago;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @JoinTable(name = "vmediopago", joinColumns = {
        @JoinColumn(name = "idmedioPago", referencedColumnName = "idmedioPago")}, inverseJoinColumns = {
        @JoinColumn(name = "idventa", referencedColumnName = "idventa")})
    @ManyToMany
    private Collection<Venta> ventaCollection;

    public Mediopago() {
    }

    public Mediopago(Integer idmedioPago) {
        this.idmedioPago = idmedioPago;
    }

    public Integer getIdmedioPago() {
        return idmedioPago;
    }

    public void setIdmedioPago(Integer idmedioPago) {
        this.idmedioPago = idmedioPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Venta> getVentaCollection() {
        return ventaCollection;
    }

    public void setVentaCollection(Collection<Venta> ventaCollection) {
        this.ventaCollection = ventaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedioPago != null ? idmedioPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mediopago)) {
            return false;
        }
        Mediopago other = (Mediopago) object;
        if ((this.idmedioPago == null && other.idmedioPago != null) || (this.idmedioPago != null && !this.idmedioPago.equals(other.idmedioPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Mediopago[ idmedioPago=" + idmedioPago + " ]";
    }
    
}
