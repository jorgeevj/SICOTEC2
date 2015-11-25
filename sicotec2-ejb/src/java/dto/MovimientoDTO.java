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
    private String descripcionEstado;
    private String colorLetra;
    private Integer idTipoDocumento;
    private String TipoDocumento;
    private Integer idTipoMovimiento;
    private String TipoMovimiento;
    private Date fechaInicio;
    private Date fechaFin;
    
    private Integer idCompra;
    private Integer idVenta;
    
    private Integer idUsuario;
    private String nombreUsuario;

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
    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    /**
     * @param idTipoDocumento the idTipoDocumento to set
     */
    public void setIdTipoDocumento(Integer idTipoDocumento) {
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

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the idCompra
     */
    public Integer getIdCompra() {
        return idCompra;
    }

    /**
     * @param idCompra the idCompra to set
     */
    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    /**
     * @return the idVenta
     */
    public Integer getIdVenta() {
        return idVenta;
    }

    /**
     * @param idVenta the idVenta to set
     */
    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    /**
     * @return the descripcionEstado
     */
    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    /**
     * @param descripcionEstado the descripcionEstado to set
     */
    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    /**
     * @return the idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
