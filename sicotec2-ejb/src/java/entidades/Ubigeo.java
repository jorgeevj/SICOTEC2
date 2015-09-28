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
@Table(name = "ubigeo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ubigeo.findAll", query = "SELECT u FROM Ubigeo u"),
    @NamedQuery(name = "Ubigeo.findByIdUbigeo", query = "SELECT u FROM Ubigeo u WHERE u.idUbigeo = :idUbigeo"),
    @NamedQuery(name = "Ubigeo.findByCodDept", query = "SELECT u FROM Ubigeo u WHERE u.codDept = :codDept"),
    @NamedQuery(name = "Ubigeo.findByCodProv", query = "SELECT u FROM Ubigeo u WHERE u.codProv = :codProv"),
    @NamedQuery(name = "Ubigeo.findByCodDist", query = "SELECT u FROM Ubigeo u WHERE u.codDist = :codDist"),
    @NamedQuery(name = "Ubigeo.findByDepartamento", query = "SELECT u FROM Ubigeo u WHERE u.departamento = :departamento"),
    @NamedQuery(name = "Ubigeo.findByProvincia", query = "SELECT u FROM Ubigeo u WHERE u.provincia = :provincia"),
    @NamedQuery(name = "Ubigeo.findByDistrito", query = "SELECT u FROM Ubigeo u WHERE u.distrito = :distrito")})
public class Ubigeo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUbigeo")
    private Integer idUbigeo;
    @Size(max = 45)
    @Column(name = "cod_dept")
    private String codDept;
    @Size(max = 45)
    @Column(name = "cod_prov")
    private String codProv;
    @Size(max = 45)
    @Column(name = "cod_dist")
    private String codDist;
    @Size(max = 45)
    @Column(name = "departamento")
    private String departamento;
    @Size(max = 45)
    @Column(name = "provincia")
    private String provincia;
    @Size(max = 45)
    @Column(name = "distrito")
    private String distrito;

    public Ubigeo() {
    }

    public Ubigeo(Integer idUbigeo) {
        this.idUbigeo = idUbigeo;
    }

    public Integer getIdUbigeo() {
        return idUbigeo;
    }

    public void setIdUbigeo(Integer idUbigeo) {
        this.idUbigeo = idUbigeo;
    }

    public String getCodDept() {
        return codDept;
    }

    public void setCodDept(String codDept) {
        this.codDept = codDept;
    }

    public String getCodProv() {
        return codProv;
    }

    public void setCodProv(String codProv) {
        this.codProv = codProv;
    }

    public String getCodDist() {
        return codDist;
    }

    public void setCodDist(String codDist) {
        this.codDist = codDist;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUbigeo != null ? idUbigeo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubigeo)) {
            return false;
        }
        Ubigeo other = (Ubigeo) object;
        if ((this.idUbigeo == null && other.idUbigeo != null) || (this.idUbigeo != null && !this.idUbigeo.equals(other.idUbigeo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Ubigeo[ idUbigeo=" + idUbigeo + " ]";
    }
    
}
