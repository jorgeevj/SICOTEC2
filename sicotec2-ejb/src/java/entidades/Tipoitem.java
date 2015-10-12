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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "tipoitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoitem.findAll", query = "SELECT t FROM Tipoitem t"),
    @NamedQuery(name = "Tipoitem.findByIdtipoItem", query = "SELECT t FROM Tipoitem t WHERE t.idtipoItem = :idtipoItem"),
    @NamedQuery(name = "Tipoitem.findByNumParte", query = "SELECT t FROM Tipoitem t WHERE t.numParte = :numParte"),
    @NamedQuery(name = "Tipoitem.findByNombre", query = "SELECT t FROM Tipoitem t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tipoitem.findByDescripcion", query = "SELECT t FROM Tipoitem t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tipoitem.findByTipo", query = "SELECT t FROM Tipoitem t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "Tipoitem.findByPrecioLista", query = "SELECT t FROM Tipoitem t WHERE t.precioLista = :precioLista"),
    @NamedQuery(name = "Tipoitem.findByDesCliente", query = "SELECT t FROM Tipoitem t WHERE t.desCliente = :desCliente"),
    @NamedQuery(name = "Tipoitem.findByDesDistribuidor", query = "SELECT t FROM Tipoitem t WHERE t.desDistribuidor = :desDistribuidor")})
public class Tipoitem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "idtipoItem")
    private String idtipoItem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numParte")
    private String numParte;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 300)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precioLista")
    private Double precioLista;
    @Size(max = 45)
    @Column(name = "desCliente")
    private String desCliente;
    @Size(max = 45)
    @Column(name = "desDistribuidor")
    private String desDistribuidor;
    @ManyToMany(mappedBy = "tipoitemList")
    private List<Caracteristica> caracteristicaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoitem")
    private List<Cotipoitem> cotipoitemList;
    @JoinColumn(name = "idfamilia", referencedColumnName = "idfamilia")
    @ManyToOne(optional = false)
    private Familia idfamilia;
    @JoinColumn(name = "idmarca", referencedColumnName = "idmarca")
    @ManyToOne(optional = false)
    private Marca idmarca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoitem")
    private List<Altipoitem> altipoitemList;

    public Tipoitem() {
    }

    public Tipoitem(String idtipoItem) {
        this.idtipoItem = idtipoItem;
    }

    public Tipoitem(String idtipoItem, String numParte) {
        this.idtipoItem = idtipoItem;
        this.numParte = numParte;
    }

    public String getIdtipoItem() {
        return idtipoItem;
    }

    public void setIdtipoItem(String idtipoItem) {
        this.idtipoItem = idtipoItem;
    }

    public String getNumParte() {
        return numParte;
    }

    public void setNumParte(String numParte) {
        this.numParte = numParte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecioLista() {
        return precioLista;
    }

    public void setPrecioLista(Double precioLista) {
        this.precioLista = precioLista;
    }

    public String getDesCliente() {
        return desCliente;
    }

    public void setDesCliente(String desCliente) {
        this.desCliente = desCliente;
    }

    public String getDesDistribuidor() {
        return desDistribuidor;
    }

    public void setDesDistribuidor(String desDistribuidor) {
        this.desDistribuidor = desDistribuidor;
    }

    @XmlTransient
    public List<Caracteristica> getCaracteristicaList() {
        return caracteristicaList;
    }

    public void setCaracteristicaList(List<Caracteristica> caracteristicaList) {
        this.caracteristicaList = caracteristicaList;
    }

    @XmlTransient
    public List<Cotipoitem> getCotipoitemList() {
        return cotipoitemList;
    }

    public void setCotipoitemList(List<Cotipoitem> cotipoitemList) {
        this.cotipoitemList = cotipoitemList;
    }

    public Familia getIdfamilia() {
        return idfamilia;
    }

    public void setIdfamilia(Familia idfamilia) {
        this.idfamilia = idfamilia;
    }

    public Marca getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(Marca idmarca) {
        this.idmarca = idmarca;
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
        hash += (idtipoItem != null ? idtipoItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoitem)) {
            return false;
        }
        Tipoitem other = (Tipoitem) object;
        if ((this.idtipoItem == null && other.idtipoItem != null) || (this.idtipoItem != null && !this.idtipoItem.equals(other.idtipoItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tipoitem[ idtipoItem=" + idtipoItem + " ]";
    }
    
}
