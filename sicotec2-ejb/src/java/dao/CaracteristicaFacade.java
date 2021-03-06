/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Caracteristica;
import entidades.Tipoitem;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jorge
 */
@Stateless
public class CaracteristicaFacade extends AbstractFacade<Caracteristica> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CaracteristicaFacade() {
        super(Caracteristica.class);
    }
    public List<Tipoitem> getAllCaracte(Tipoitem e){
        List<Tipoitem> lista= new ArrayList<Tipoitem>();
        String sql="SELECT t.nombre FROM Caracteristica t ";
        Query q=em.createQuery(sql,Tipoitem.class);
        lista=q.getResultList();       
        return lista;
    }
    
    public List<Caracteristica> getCaracteristicaXTipoItem(String idTipoItem){
        List<Caracteristica> lista = new ArrayList<Caracteristica>();
        try{
        String sql = "SELECT t "
                    + "FROM CaTipoItem t, "
                    + "     Caracteristica c "
                    + "WHERE t.tipoitem.idtipoItem = :idTipoitem "
                    + "AND   t.caracteristica.idcaracteristica = c.idcaracteristica";
        Query q = em.createQuery(sql,Caracteristica.class);
        q.setParameter("idTipoitem", idTipoItem);
        lista=q.getResultList(); 
        }catch(Exception e){
            System.out.println("error en CaTipoItem: "+e.getMessage()); 
            lista=new ArrayList<Caracteristica>();
        }
              
        return lista;
    }
}
