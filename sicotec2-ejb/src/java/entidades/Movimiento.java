/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimiento.findAll", query = "SELECT m FROM Movimiento m"),
    @NamedQuery(name = "Movimiento.findByIdmovimiento", query = "SELECT m FROM Movimiento m WHERE m.idmovimiento = :idmovimiento"),
    @NamedQuery(name = "Movimiento.findByFecha", query = "SELECT m FROM Movimiento m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Movimiento.findByIdalmacenOrigen", query = "SELECT m FROM Movimiento m WHERE m.idalmacenOrigen = :idalmacenOrigen"),
    @NamedQuery(name = "Movimiento.findByIdalmacenDestino", query = "SELECT m FROM Movimiento m WHERE m.idalmacenDestino = :idalmacenDestino"),
    @NamedQuery(name = "Movimiento.findByNombreOrigen", query = "SELECT m FROM Movimiento m WHERE m.nombreOrigen = :nombreOrigen"),
    @NamedQuery(name = "Movimiento.findByNombreDestino", query = "SELECT m FROM Movimiento m WHERE m.nombreDestino = :nombreDestino"),
    @NamedQuery(name = "Movimiento.findByMotivo", query = "SELECT m FROM Movimiento m WHERE m.motivo = :motivo"),
    @NamedQuery(name = "Movimiento.findByComentario", query = "SELECT m FROM Movimiento m WHERE m.comentario = :comentario"),
    @NamedQuery(name = "Movimiento.findByEstado", query = "SELECT m FROM Movimiento m WHERE m.estado = :estado"),
    @NamedQuery(name = "Movimiento.findByIddocumento", query = "SELECT m FROM Movimiento m WHERE m.iddocumento = :iddocumento"),
    @NamedQuery(name = "Movimiento.findBySerie", query = "SELECT m FROM Movimiento m WHERE m.serie = :serie"),
    @NamedQuery(name = "Movimiento.findByCorrelativo", query = "SELECT m FROM Movimiento m WHERE m.correlativo = :correlativo")})
public class Movimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmovimiento")
    private Integer idmovimiento;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "idalmacenOrigen")
    private Integer idalmacenOrigen;
    @Column(name = "idalmacenDestino")
    private Integer idalmacenDestino;
    @Size(max = 45)
    @Column(name = "nombreOrigen")
    private String nombreOrigen;
    @Size(max = 45)
    @Column(name = "nombreDestino")
    private String nombreDestino;
    @Size(max = 45)
    @Column(name = "motivo")
    private String motivo;
    @Size(max = 45)
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "iddocumento")
    private Integer iddocumento;
    @Size(max = 45)
    @Column(name = "serie")
    private String serie;
    @Size(max = 45)
    @Column(name = "correlativo")
    private String correlativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movimiento")
    private List<Movimientoitem> movimientoitemList;
    @JoinColumn(name = "idtipoMovimiento", referencedColumnName = "idtipoMovimiento")
    @ManyToOne(optional = false)
    private Tipomovimiento idtipoMovimiento;

    public Movimiento() {
    }

    public Movimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Integer getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdalmacenOrigen() {
        return idalmacenOrigen;
    }

    public void setIdalmacenOrigen(Integer idalmacenOrigen) {
        this.idalmacenOrigen = idalmacenOrigen;
    }

    public Integer getIdalmacenDestino() {
        return idalmacenDestino;
    }

    public void setIdalmacenDestino(Integer idalmacenDestino) {
        this.idalmacenDestino = idalmacenDestino;
    }

    public String getNombreOrigen() {
        return nombreOrigen;
    }

    public void setNombreOrigen(String nombreOrigen) {
        this.nombreOrigen = nombreOrigen;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(Integer iddocumento) {
        this.iddocumento = iddocumento;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    @XmlTransient
    public List<Movimientoitem> getMovimientoitemList() {
        return movimientoitemList;
    }

    public void setMovimientoitemList(List<Movimientoitem> movimientoitemList) {
        this.movimientoitemList = movimientoitemList;
    }

    public Tipomovimiento getIdtipoMovimiento() {
        return idtipoMovimiento;
    }

    public void setIdtipoMovimiento(Tipomovimiento idtipoMovimiento) {
        this.idtipoMovimiento = idtipoMovimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmovimiento != null ? idmovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimiento)) {
            return false;
        }
        Movimiento other = (Movimiento) object;
        if ((this.idmovimiento == null && other.idmovimiento != null) || (this.idmovimiento != null && !this.idmovimiento.equals(other.idmovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Movimiento[ idmovimiento=" + idmovimiento + " ]";
    }
    
}
