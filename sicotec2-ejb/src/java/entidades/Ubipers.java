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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "ubipers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ubipers.findAll", query = "SELECT u FROM Ubipers u"),
    @NamedQuery(name = "Ubipers.findByIdubiPers", query = "SELECT u FROM Ubipers u WHERE u.idubiPers = :idubiPers"),
    @NamedQuery(name = "Ubipers.findByNombre", query = "SELECT u FROM Ubipers u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Ubipers.findByNumero", query = "SELECT u FROM Ubipers u WHERE u.numero = :numero"),
    @NamedQuery(name = "Ubipers.findByCodDist", query = "SELECT u FROM Ubipers u WHERE u.codDist = :codDist"),
    @NamedQuery(name = "Ubipers.findByCodProv", query = "SELECT u FROM Ubipers u WHERE u.codProv = :codProv"),
    @NamedQuery(name = "Ubipers.findByCodDept", query = "SELECT u FROM Ubipers u WHERE u.codDept = :codDept"),
    @NamedQuery(name = "Ubipers.findByPrincipal", query = "SELECT u FROM Ubipers u WHERE u.principal = :principal")})
public class Ubipers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idubiPers")
    private Integer idubiPers;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "numero")
    private String numero;
    @Size(max = 45)
    @Column(name = "cod_dist")
    private String codDist;
    @Size(max = 45)
    @Column(name = "cod_prov")
    private String codProv;
    @Size(max = 45)
    @Column(name = "cod_dept")
    private String codDept;
    @Size(max = 45)
    @Column(name = "principal")
    private String principal;
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
    @ManyToOne(optional = false)
    private Persona idpersona;

    public Ubipers() {
    }

    public Ubipers(Integer idubiPers) {
        this.idubiPers = idubiPers;
    }

    public Integer getIdubiPers() {
        return idubiPers;
    }

    public void setIdubiPers(Integer idubiPers) {
        this.idubiPers = idubiPers;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodDist() {
        return codDist;
    }

    public void setCodDist(String codDist) {
        this.codDist = codDist;
    }

    public String getCodProv() {
        return codProv;
    }

    public void setCodProv(String codProv) {
        this.codProv = codProv;
    }

    public String getCodDept() {
        return codDept;
    }

    public void setCodDept(String codDept) {
        this.codDept = codDept;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Persona getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Persona idpersona) {
        this.idpersona = idpersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idubiPers != null ? idubiPers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubipers)) {
            return false;
        }
        Ubipers other = (Ubipers) object;
        if ((this.idubiPers == null && other.idubiPers != null) || (this.idubiPers != null && !this.idubiPers.equals(other.idubiPers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Ubipers[ idubiPers=" + idubiPers + " ]";
    }
    
}
