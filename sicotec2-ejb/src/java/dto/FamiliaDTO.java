/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Categoria;

/**
 *
 * @author Faviana
 */
public class FamiliaDTO {
    
    private Integer idfamilia;
    private String nombreFamilia;
    private Categoria idcategoria;

    public Integer getIdfamilia() {
        return idfamilia;
    }

    public void setIdfamilia(Integer idfamilia) {
        this.idfamilia = idfamilia;
    }

    public String getNombreFamilia() {
        return nombreFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public Categoria getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Categoria idcategoria) {
        this.idcategoria = idcategoria;
    }
    
    
}
