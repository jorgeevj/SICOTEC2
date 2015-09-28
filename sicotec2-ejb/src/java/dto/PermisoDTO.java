/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author rikardo308
 */
public class PermisoDTO {
    private Integer idpermiso;
    private String nombre;
    private String url;

    /**
     * @return the idpermiso
     */
    public Integer getIdpermiso() {
        return idpermiso;
    }

    /**
     * @param idpermiso the idpermiso to set
     */
    public void setIdpermiso(Integer idpermiso) {
        this.idpermiso = idpermiso;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
