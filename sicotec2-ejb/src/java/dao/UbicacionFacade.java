/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Ubicacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jorge
 */
@Stateless
public class UbicacionFacade extends AbstractFacade<Ubicacion> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UbicacionFacade() {
        super(Ubicacion.class);
    }

    public List<Ubicacion> findByIdEmpresa(Integer idempresa) {
       String jpa="select u from Ubicacion u where u.idempresa.idempresa="+idempresa;
    return em.createQuery(jpa, Ubicacion.class).getResultList();
    }
    
}
