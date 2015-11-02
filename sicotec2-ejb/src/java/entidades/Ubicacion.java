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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "ubicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ubicacion.findAll", query = "SELECT u FROM Ubicacion u"),
    @NamedQuery(name = "Ubicacion.findByIdubicacion", query = "SELECT u FROM Ubicacion u WHERE u.idubicacion = :idubicacion"),
    @NamedQuery(name = "Ubicacion.findByNombre", query = "SELECT u FROM Ubicacion u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Ubicacion.findByNumero", query = "SELECT u FROM Ubicacion u WHERE u.numero = :numero"),
    @NamedQuery(name = "Ubicacion.findByCodDist", query = "SELECT u FROM Ubicacion u WHERE u.codDist = :codDist"),
    @NamedQuery(name = "Ubicacion.findByCodProv", query = "SELECT u FROM Ubicacion u WHERE u.codProv = :codProv"),
    @NamedQuery(name = "Ubicacion.findByCodDept", query = "SELECT u FROM Ubicacion u WHERE u.codDept = :codDept"),
    @NamedQuery(name = "Ubicacion.findByPrincipal", query = "SELECT u FROM Ubicacion u WHERE u.principal = :principal"),
    @NamedQuery(name = "Ubicacion.findByTipo", query = "SELECT u FROM Ubicacion u WHERE u.tipo = :tipo")})
public class Ubicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idubicacion")
    private Integer idubicacion;
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
    @Column(name = "tipo")
    private Integer tipo;
    @JoinColumns({
        @JoinColumn(name = "idempresa", referencedColumnName = "idempresa"),
        @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")})
    @ManyToOne(optional = false)
    private Emppersona emppersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idubicacion")
    private List<Telefono> telefonoList;

    public Ubicacion() {
    }

    public Ubicacion(Integer idubicacion) {
        this.idubicacion = idubicacion;
    }

    public Integer getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(Integer idubicacion) {
        this.idubicacion = idubicacion;
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

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Emppersona getEmppersona() {
        return emppersona;
    }

    public void setEmppersona(Emppersona emppersona) {
        this.emppersona = emppersona;
    }

    @XmlTransient
    public List<Telefono> getTelefonoList() {
        return telefonoList;
    }

    public void setTelefonoList(List<Telefono> telefonoList) {
        this.telefonoList = telefonoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idubicacion != null ? idubicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubicacion)) {
            return false;
        }
        Ubicacion other = (Ubicacion) object;
        if ((this.idubicacion == null && other.idubicacion != null) || (this.idubicacion != null && !this.idubicacion.equals(other.idubicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Ubicacion[ idubicacion=" + idubicacion + " ]";
    }
    
}
