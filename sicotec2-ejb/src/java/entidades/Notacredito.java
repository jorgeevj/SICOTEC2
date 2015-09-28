/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "notacredito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notacredito.findAll", query = "SELECT n FROM Notacredito n"),
    @NamedQuery(name = "Notacredito.findByIdnotaCredito", query = "SELECT n FROM Notacredito n WHERE n.idnotaCredito = :idnotaCredito"),
    @NamedQuery(name = "Notacredito.findByMonto", query = "SELECT n FROM Notacredito n WHERE n.monto = :monto"),
    @NamedQuery(name = "Notacredito.findByEstado", query = "SELECT n FROM Notacredito n WHERE n.estado = :estado")})
public class Notacredito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idnotaCredito")
    private Integer idnotaCredito;
    @Size(max = 45)
    @Column(name = "monto")
    private String monto;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "idgarantia", referencedColumnName = "idgarantia")
    @ManyToOne(optional = false)
    private Garantia idgarantia;

    public Notacredito() {
    }

    public Notacredito(Integer idnotaCredito) {
        this.idnotaCredito = idnotaCredito;
    }

    public Integer getIdnotaCredito() {
        return idnotaCredito;
    }

    public void setIdnotaCredito(Integer idnotaCredito) {
        this.idnotaCredito = idnotaCredito;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Garantia getIdgarantia() {
        return idgarantia;
    }

    public void setIdgarantia(Garantia idgarantia) {
        this.idgarantia = idgarantia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnotaCredito != null ? idnotaCredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notacredito)) {
            return false;
        }
        Notacredito other = (Notacredito) object;
        if ((this.idnotaCredito == null && other.idnotaCredito != null) || (this.idnotaCredito != null && !this.idnotaCredito.equals(other.idnotaCredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Notacredito[ idnotaCredito=" + idnotaCredito + " ]";
    }
    
}
