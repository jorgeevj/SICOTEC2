/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "emppersona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emppersona.findAll", query = "SELECT e FROM Emppersona e"),
    @NamedQuery(name = "Emppersona.findByIdempresa", query = "SELECT e FROM Emppersona e WHERE e.emppersonaPK.idempresa = :idempresa"),
    @NamedQuery(name = "Emppersona.findByIdpersona", query = "SELECT e FROM Emppersona e WHERE e.emppersonaPK.idpersona = :idpersona"),
    @NamedQuery(name = "Emppersona.findByCargo", query = "SELECT e FROM Emppersona e WHERE e.cargo = :cargo")})
public class Emppersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmppersonaPK emppersonaPK;
    @Size(max = 45)
    @Column(name = "cargo")
    private String cargo;
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empresa empresa;
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;

    public Emppersona() {
    }

    public Emppersona(EmppersonaPK emppersonaPK) {
        this.emppersonaPK = emppersonaPK;
    }

    public Emppersona(int idempresa, int idpersona) {
        this.emppersonaPK = new EmppersonaPK(idempresa, idpersona);
    }

    public EmppersonaPK getEmppersonaPK() {
        return emppersonaPK;
    }

    public void setEmppersonaPK(EmppersonaPK emppersonaPK) {
        this.emppersonaPK = emppersonaPK;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emppersonaPK != null ? emppersonaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emppersona)) {
            return false;
        }
        Emppersona other = (Emppersona) object;
        if ((this.emppersonaPK == null && other.emppersonaPK != null) || (this.emppersonaPK != null && !this.emppersonaPK.equals(other.emppersonaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Emppersona[ emppersonaPK=" + emppersonaPK + " ]";
    }
    
}
