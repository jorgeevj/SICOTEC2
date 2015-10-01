/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;

/**
 *
 * @author rikardo308
 */
public class MovimientoDTO {
    private Integer idmovimiento;
    private Date fecha;
    private Integer idalmacenOrigen;
    private Integer idalmacenDestino;
    private String nombreOrigen;
    private String nombreDestino;
    private String motivo;
    private String comentario;
    private Integer estado;
    private Integer iddocumento;
    private String serie;
    private String correlativo;
    
    //AGREGADOS
    private String colorEstado;
    private String colorLetra;
    private int idTipoDocumento;
    private String TipoDocumento;
    private Integer idTipoMovimiento;
    private String TipoMovimiento;

    /**
     * @return the idmovimiento
     */
    public Integer getIdmovimiento() {
        return idmovimiento;
    }

    /**
     * @param idmovimiento the idmovimiento to set
     */
    public void setIdmovimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the idalmacenOrigen
     */
    public Integer getIdalmacenOrigen() {
        return idalmacenOrigen;
    }

    /**
     * @param idalmacenOrigen the idalmacenOrigen to set
     */
    public void setIdalmacenOrigen(Integer idalmacenOrigen) {
        this.idalmacenOrigen = idalmacenOrigen;
    }

    /**
     * @return the idalmacenDestino
     */
    public Integer getIdalmacenDestino() {
        return idalmacenDestino;
    }

    /**
     * @param idalmacenDestino the idalmacenDestino to set
     */
    public void setIdalmacenDestino(Integer idalmacenDestino) {
        this.idalmacenDestino = idalmacenDestino;
    }

    /**
     * @return the nombreOrigen
     */
    public String getNombreOrigen() {
        return nombreOrigen;
    }

    /**
     * @param nombreOrigen the nombreOrigen to set
     */
    public void setNombreOrigen(String nombreOrigen) {
        this.nombreOrigen = nombreOrigen;
    }

    /**
     * @return the nombreDestino
     */
    public String getNombreDestino() {
        return nombreDestino;
    }

    /**
     * @param nombreDestino the nombreDestino to set
     */
    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the estado
     */
    public Integer getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    /**
     * @return the iddocumento
     */
    public Integer getIddocumento() {
        return iddocumento;
    }

    /**
     * @param iddocumento the iddocumento to set
     */
    public void setIddocumento(Integer iddocumento) {
        this.iddocumento = iddocumento;
    }

    

    /**
     * @return the correlativo
     */
    public String getCorrelativo() {
        return correlativo;
    }

    /**
     * @param correlativo the correlativo to set
     */
    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    /**
     * @return the colorEstado
     */
    public String getColorEstado() {
        return colorEstado;
    }

    /**
     * @param colorEstado the colorEstado to set
     */
    public void setColorEstado(String colorEstado) {
        this.colorEstado = colorEstado;
    }

    /**
     * @return the colorLetra
     */
    public String getColorLetra() {
        return colorLetra;
    }

    /**
     * @param colorLetra the colorLetra to set
     */
    public void setColorLetra(String colorLetra) {
        this.colorLetra = colorLetra;
    }

    /**
     * @return the idTipoMovimiento
     */
    public Integer getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    /**
     * @param idTipoMovimiento the idTipoMovimiento to set
     */
    public void setIdTipoMovimiento(Integer idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    /**
     * @return the TipoMovimiento
     */
    public String getTipoMovimiento() {
        return TipoMovimiento;
    }

    /**
     * @param TipoMovimiento the TipoMovimiento to set
     */
    public void setTipoMovimiento(String TipoMovimiento) {
        this.TipoMovimiento = TipoMovimiento;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the idTipoDocumento
     */
    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    /**
     * @param idTipoDocumento the idTipoDocumento to set
     */
    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    /**
     * @return the TipoDocumento
     */
    public String getTipoDocumento() {
        return TipoDocumento;
    }

    /**
     * @param TipoDocumento the TipoDocumento to set
     */
    public void setTipoDocumento(String TipoDocumento) {
        this.TipoDocumento = TipoDocumento;
    }
    
    
}
