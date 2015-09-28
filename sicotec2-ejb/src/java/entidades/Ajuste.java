/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "ajuste")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ajuste.findAll", query = "SELECT a FROM Ajuste a"),
    @NamedQuery(name = "Ajuste.findByIdajuste", query = "SELECT a FROM Ajuste a WHERE a.idajuste = :idajuste"),
    @NamedQuery(name = "Ajuste.findByFecha", query = "SELECT a FROM Ajuste a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Ajuste.findByCantidad", query = "SELECT a FROM Ajuste a WHERE a.cantidad = :cantidad"),
    @NamedQuery(name = "Ajuste.findByObservacion", query = "SELECT a FROM Ajuste a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "Ajuste.findByTipo", query = "SELECT a FROM Ajuste a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "Ajuste.findByIdusuario", query = "SELECT a FROM Ajuste a WHERE a.idusuario = :idusuario")})
public class Ajuste implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idajuste")
    private Integer idajuste;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 45)
    @Column(name = "cantidad")
    private String cantidad;
    @Size(max = 45)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "tipo")
    private Integer tipo;
    @Column(name = "idusuario")
    private Integer idusuario;
    @JoinColumn(name = "iditem", referencedColumnName = "iditem")
    @ManyToOne(optional = false)
    private Item iditem;

    public Ajuste() {
    }

    public Ajuste(Integer idajuste) {
        this.idajuste = idajuste;
    }

    public Integer getIdajuste() {
        return idajuste;
    }

    public void setIdajuste(Integer idajuste) {
        this.idajuste = idajuste;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Item getIditem() {
        return iditem;
    }

    public void setIditem(Item iditem) {
        this.iditem = iditem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idajuste != null ? idajuste.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ajuste)) {
            return false;
        }
        Ajuste other = (Ajuste) object;
        if ((this.idajuste == null && other.idajuste != null) || (this.idajuste != null && !this.idajuste.equals(other.idajuste))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Ajuste[ idajuste=" + idajuste + " ]";
    }
    
}
