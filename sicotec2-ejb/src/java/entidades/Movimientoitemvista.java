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
@Table(name = "movimientoitemvista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimientoitemvista.findAll", query = "SELECT m FROM Movimientoitemvista m"),
    @NamedQuery(name = "Movimientoitemvista.findByIditem", query = "SELECT m FROM Movimientoitemvista m WHERE m.iditem = :iditem"),
    @NamedQuery(name = "Movimientoitemvista.findByIdtipoitem", query = "SELECT m FROM Movimientoitemvista m WHERE m.idtipoitem = :idtipoitem"),
    @NamedQuery(name = "Movimientoitemvista.findByIdmovimiento", query = "SELECT m FROM Movimientoitemvista m WHERE m.idmovimiento = :idmovimiento"),
    @NamedQuery(name = "Movimientoitemvista.findByNombretitem", query = "SELECT m FROM Movimientoitemvista m WHERE m.nombretitem = :nombretitem"),
    @NamedQuery(name = "Movimientoitemvista.findByDescripciontitem", query = "SELECT m FROM Movimientoitemvista m WHERE m.descripciontitem = :descripciontitem"),
    @NamedQuery(name = "Movimientoitemvista.findByNombremarca", query = "SELECT m FROM Movimientoitemvista m WHERE m.nombremarca = :nombremarca"),
    @NamedQuery(name = "Movimientoitemvista.findByNombrefamilia", query = "SELECT m FROM Movimientoitemvista m WHERE m.nombrefamilia = :nombrefamilia")})
public class Movimientoitemvista implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "iditem")
    private String iditem;
    @Size(max = 45)
    @Column(name = "idtipoitem")
    private String idtipoitem;
    @Column(name = "idmovimiento")
    private Integer idmovimiento;
    @Size(max = 45)
    @Column(name = "nombretitem")
    private String nombretitem;
    @Size(max = 45)
    @Column(name = "descripciontitem")
    private String descripciontitem;
    @Size(max = 45)
    @Column(name = "nombremarca")
    private String nombremarca;
    @Size(max = 45)
    @Column(name = "nombrefamilia")
    private String nombrefamilia;

    public Movimientoitemvista() {
    }

    public Movimientoitemvista(String iditem) {
        this.iditem = iditem;
    }

    public String getIditem() {
        return iditem;
    }

    public void setIditem(String iditem) {
        this.iditem = iditem;
    }

    public String getIdtipoitem() {
        return idtipoitem;
    }

    public void setIdtipoitem(String idtipoitem) {
        this.idtipoitem = idtipoitem;
    }

    public Integer getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public String getNombretitem() {
        return nombretitem;
    }

    public void setNombretitem(String nombretitem) {
        this.nombretitem = nombretitem;
    }

    public String getDescripciontitem() {
        return descripciontitem;
    }

    public void setDescripciontitem(String descripciontitem) {
        this.descripciontitem = descripciontitem;
    }

    public String getNombremarca() {
        return nombremarca;
    }

    public void setNombremarca(String nombremarca) {
        this.nombremarca = nombremarca;
    }

    public String getNombrefamilia() {
        return nombrefamilia;
    }

    public void setNombrefamilia(String nombrefamilia) {
        this.nombrefamilia = nombrefamilia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iditem != null ? iditem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientoitemvista)) {
            return false;
        }
        Movimientoitemvista other = (Movimientoitemvista) object;
        if ((this.iditem == null && other.iditem != null) || (this.iditem != null && !this.iditem.equals(other.iditem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Movimientoitemvista[ iditem=" + iditem + " ]";
    }
    
}
