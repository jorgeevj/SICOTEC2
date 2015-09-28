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
@Table(name = "informe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Informe.findAll", query = "SELECT i FROM Informe i"),
    @NamedQuery(name = "Informe.findByIdinforme", query = "SELECT i FROM Informe i WHERE i.idinforme = :idinforme"),
    @NamedQuery(name = "Informe.findByFecha", query = "SELECT i FROM Informe i WHERE i.fecha = :fecha"),
    @NamedQuery(name = "Informe.findByDescripcion", query = "SELECT i FROM Informe i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "Informe.findByDiagnostico", query = "SELECT i FROM Informe i WHERE i.diagnostico = :diagnostico")})
public class Informe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinforme")
    private Integer idinforme;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 255)
    @Column(name = "diagnostico")
    private String diagnostico;
    @JoinColumn(name = "idgarantia", referencedColumnName = "idgarantia")
    @ManyToOne(optional = false)
    private Garantia idgarantia;

    public Informe() {
    }

    public Informe(Integer idinforme) {
        this.idinforme = idinforme;
    }

    public Integer getIdinforme() {
        return idinforme;
    }

    public void setIdinforme(Integer idinforme) {
        this.idinforme = idinforme;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
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
        hash += (idinforme != null ? idinforme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Informe)) {
            return false;
        }
        Informe other = (Informe) object;
        if ((this.idinforme == null && other.idinforme != null) || (this.idinforme != null && !this.idinforme.equals(other.idinforme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Informe[ idinforme=" + idinforme + " ]";
    }
    
}
