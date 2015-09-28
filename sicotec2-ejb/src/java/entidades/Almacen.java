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
@Table(name = "almacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Almacen.findAll", query = "SELECT a FROM Almacen a"),
    @NamedQuery(name = "Almacen.findByIdalmacen", query = "SELECT a FROM Almacen a WHERE a.idalmacen = :idalmacen"),
    @NamedQuery(name = "Almacen.findByNombre", query = "SELECT a FROM Almacen a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Almacen.findByTelefono", query = "SELECT a FROM Almacen a WHERE a.telefono = :telefono"),
    @NamedQuery(name = "Almacen.findByDireccion", query = "SELECT a FROM Almacen a WHERE a.direccion = :direccion"),
    @NamedQuery(name = "Almacen.findByCodDept", query = "SELECT a FROM Almacen a WHERE a.codDept = :codDept"),
    @NamedQuery(name = "Almacen.findByCodProv", query = "SELECT a FROM Almacen a WHERE a.codProv = :codProv"),
    @NamedQuery(name = "Almacen.findByCodDist", query = "SELECT a FROM Almacen a WHERE a.codDist = :codDist")})
public class Almacen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idalmacen")
    private Integer idalmacen;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 45)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 45)
    @Column(name = "cod_dept")
    private String codDept;
    @Size(max = 45)
    @Column(name = "cod_prov")
    private String codProv;
    @Size(max = 45)
    @Column(name = "cod_dist")
    private String codDist;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "almacen")
    private List<Docalmacen> docalmacenList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "almacen")
    private List<Altipoitem> altipoitemList;

    public Almacen() {
    }

    public Almacen(Integer idalmacen) {
        this.idalmacen = idalmacen;
    }

    public Integer getIdalmacen() {
        return idalmacen;
    }

    public void setIdalmacen(Integer idalmacen) {
        this.idalmacen = idalmacen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    @XmlTransient
    public List<Docalmacen> getDocalmacenList() {
        return docalmacenList;
    }

    public void setDocalmacenList(List<Docalmacen> docalmacenList) {
        this.docalmacenList = docalmacenList;
    }

    @XmlTransient
    public List<Altipoitem> getAltipoitemList() {
        return altipoitemList;
    }

    public void setAltipoitemList(List<Altipoitem> altipoitemList) {
        this.altipoitemList = altipoitemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idalmacen != null ? idalmacen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Almacen)) {
            return false;
        }
        Almacen other = (Almacen) object;
        if ((this.idalmacen == null && other.idalmacen != null) || (this.idalmacen != null && !this.idalmacen.equals(other.idalmacen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Almacen[ idalmacen=" + idalmacen + " ]";
    }
    
}
