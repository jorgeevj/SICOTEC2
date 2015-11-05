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
@Table(name = "telefpersona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Telefpersona.findAll", query = "SELECT t FROM Telefpersona t"),
    @NamedQuery(name = "Telefpersona.findByIdtelefPersona", query = "SELECT t FROM Telefpersona t WHERE t.idtelefPersona = :idtelefPersona"),
    @NamedQuery(name = "Telefpersona.findByNumero", query = "SELECT t FROM Telefpersona t WHERE t.numero = :numero"),
    @NamedQuery(name = "Telefpersona.findByTipo", query = "SELECT t FROM Telefpersona t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "Telefpersona.findByOperador", query = "SELECT t FROM Telefpersona t WHERE t.operador = :operador"),
    @NamedQuery(name = "Telefpersona.findByPrincipal", query = "SELECT t FROM Telefpersona t WHERE t.principal = :principal")})
public class Telefpersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtelefPersona")
    private Integer idtelefPersona;
    @Size(max = 45)
    @Column(name = "numero")
    private String numero;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 45)
    @Column(name = "operador")
    private String operador;
    @Column(name = "principal")
    private Integer principal;
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
    @ManyToOne(optional = false)
    private Persona idpersona;

    public Telefpersona() {
    }

    public Telefpersona(Integer idtelefPersona) {
        this.idtelefPersona = idtelefPersona;
    }

    public Integer getIdtelefPersona() {
        return idtelefPersona;
    }

    public void setIdtelefPersona(Integer idtelefPersona) {
        this.idtelefPersona = idtelefPersona;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public Integer getPrincipal() {
        return principal;
    }

    public void setPrincipal(Integer principal) {
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
        hash += (idtelefPersona != null ? idtelefPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefpersona)) {
            return false;
        }
        Telefpersona other = (Telefpersona) object;
        if ((this.idtelefPersona == null && other.idtelefPersona != null) || (this.idtelefPersona != null && !this.idtelefPersona.equals(other.idtelefPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Telefpersona[ idtelefPersona=" + idtelefPersona + " ]";
    }
    
}
