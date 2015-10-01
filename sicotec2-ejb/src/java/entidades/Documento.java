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
@Table(name = "documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d"),
    @NamedQuery(name = "Documento.findByIddocumento", query = "SELECT d FROM Documento d WHERE d.iddocumento = :iddocumento"),
    @NamedQuery(name = "Documento.findByNombre", query = "SELECT d FROM Documento d WHERE d.nombre = :nombre")})
public class Documento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "iddocumento")
    private Integer iddocumento;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documento")
    private List<Docalmacen> docalmacenList;

    public Documento() {
    }

    public Documento(Integer iddocumento) {
        this.iddocumento = iddocumento;
    }

    public Integer getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(Integer iddocumento) {
        this.iddocumento = iddocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Docalmacen> getDocalmacenList() {
        return docalmacenList;
    }

    public void setDocalmacenList(List<Docalmacen> docalmacenList) {
        this.docalmacenList = docalmacenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddocumento != null ? iddocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.iddocumento == null && other.iddocumento != null) || (this.iddocumento != null && !this.iddocumento.equals(other.iddocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Documento[ iddocumento=" + iddocumento + " ]";
    }
    
}
