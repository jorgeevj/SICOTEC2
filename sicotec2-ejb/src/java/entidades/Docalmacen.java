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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "docalmacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docalmacen.findAll", query = "SELECT d FROM Docalmacen d"),
    @NamedQuery(name = "Docalmacen.findByIddocumento", query = "SELECT d FROM Docalmacen d WHERE d.docalmacenPK.iddocumento = :iddocumento"),
    @NamedQuery(name = "Docalmacen.findByIdalmacen", query = "SELECT d FROM Docalmacen d WHERE d.docalmacenPK.idalmacen = :idalmacen"),
    @NamedQuery(name = "Docalmacen.findByNunSerie", query = "SELECT d FROM Docalmacen d WHERE d.nunSerie = :nunSerie"),
    @NamedQuery(name = "Docalmacen.findByCorrelativo", query = "SELECT d FROM Docalmacen d WHERE d.correlativo = :correlativo")})
public class Docalmacen implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DocalmacenPK docalmacenPK;
    @Column(name = "nunSerie")
    private Integer nunSerie;
    @Column(name = "correlativo")
    private Integer correlativo;
    @JoinColumn(name = "idalmacen", referencedColumnName = "idalmacen", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Almacen almacen;
    @JoinColumn(name = "iddocumento", referencedColumnName = "iddocumento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Documento documento;

    public Docalmacen() {
    }

    public Docalmacen(DocalmacenPK docalmacenPK) {
        this.docalmacenPK = docalmacenPK;
    }

    public Docalmacen(int iddocumento, int idalmacen) {
        this.docalmacenPK = new DocalmacenPK(iddocumento, idalmacen);
    }

    public DocalmacenPK getDocalmacenPK() {
        return docalmacenPK;
    }

    public void setDocalmacenPK(DocalmacenPK docalmacenPK) {
        this.docalmacenPK = docalmacenPK;
    }

    public Integer getNunSerie() {
        return nunSerie;
    }

    public void setNunSerie(Integer nunSerie) {
        this.nunSerie = nunSerie;
    }

    public Integer getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(Integer correlativo) {
        this.correlativo = correlativo;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docalmacenPK != null ? docalmacenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docalmacen)) {
            return false;
        }
        Docalmacen other = (Docalmacen) object;
        if ((this.docalmacenPK == null && other.docalmacenPK != null) || (this.docalmacenPK != null && !this.docalmacenPK.equals(other.docalmacenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Docalmacen[ docalmacenPK=" + docalmacenPK + " ]";
    }
    
}
