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
@Table(name = "tipomovimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipomovimiento.findAll", query = "SELECT t FROM Tipomovimiento t"),
    @NamedQuery(name = "Tipomovimiento.findByIdtipoMovimiento", query = "SELECT t FROM Tipomovimiento t WHERE t.idtipoMovimiento = :idtipoMovimiento"),
    @NamedQuery(name = "Tipomovimiento.findByIddocumento", query = "SELECT t FROM Tipomovimiento t WHERE t.iddocumento = :iddocumento"),
    @NamedQuery(name = "Tipomovimiento.findByNombre", query = "SELECT t FROM Tipomovimiento t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tipomovimiento.findByNSerie", query = "SELECT t FROM Tipomovimiento t WHERE t.nSerie = :nSerie"),
    @NamedQuery(name = "Tipomovimiento.findByCorrelativo", query = "SELECT t FROM Tipomovimiento t WHERE t.correlativo = :correlativo")})
public class Tipomovimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtipoMovimiento")
    private Integer idtipoMovimiento;
    @Size(max = 45)
    @Column(name = "iddocumento")
    private String iddocumento;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "nSerie")
    private String nSerie;
    @Size(max = 45)
    @Column(name = "correlativo")
    private String correlativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipoMovimiento")
    private List<Movimiento> movimientoList;

    public Tipomovimiento() {
    }

    public Tipomovimiento(Integer idtipoMovimiento) {
        this.idtipoMovimiento = idtipoMovimiento;
    }

    public Integer getIdtipoMovimiento() {
        return idtipoMovimiento;
    }

    public void setIdtipoMovimiento(Integer idtipoMovimiento) {
        this.idtipoMovimiento = idtipoMovimiento;
    }

    public String getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(String iddocumento) {
        this.iddocumento = iddocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNSerie() {
        return nSerie;
    }

    public void setNSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    @XmlTransient
    public List<Movimiento> getMovimientoList() {
        return movimientoList;
    }

    public void setMovimientoList(List<Movimiento> movimientoList) {
        this.movimientoList = movimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoMovimiento != null ? idtipoMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipomovimiento)) {
            return false;
        }
        Tipomovimiento other = (Tipomovimiento) object;
        if ((this.idtipoMovimiento == null && other.idtipoMovimiento != null) || (this.idtipoMovimiento != null && !this.idtipoMovimiento.equals(other.idtipoMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tipomovimiento[ idtipoMovimiento=" + idtipoMovimiento + " ]";
    }
    
}
