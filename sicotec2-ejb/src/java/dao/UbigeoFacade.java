/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Ubigeo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jorge
 */
@Stateless
public class UbigeoFacade extends AbstractFacade<Ubigeo> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UbigeoFacade() {
        super(Ubigeo.class);
    }

    public List<Ubigeo> findUbigeos(String dpto,String prov,String dist) {
    String jpa="select u from Ubigeo u where 1=1 ";
    if(dpto!=null){
    jpa+="and u.codDept='"+dpto+"' ";
    }
    if(prov!=null){
    jpa+="and u.codProv='"+prov+"' ";
    }else{
    jpa+="and not (u.codProv='00') ";
    }
    if(dist!=null){
    jpa+="and u.codDist='"+dist+"' ";
    }else{
    jpa+="and not (u.codDist='00') ";
    }
        return em.createQuery(jpa, Ubigeo.class).getResultList();
    }
    
}
