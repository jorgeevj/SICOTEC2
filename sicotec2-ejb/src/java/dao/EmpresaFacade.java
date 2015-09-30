/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.EmpresaDTO;
import entidades.Empresa;
import java.util.*;
import javax.ejb.*;
import javax.persistence.*;

/**
 *
 * @author Jorge
 */
@Stateless
public class EmpresaFacade extends AbstractFacade<Empresa> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaFacade() {
        super(Empresa.class);
    }
    
    public List<Empresa>getEmpresaBusqueda(EmpresaDTO empresaDTO){
        List<Empresa> empresas = new ArrayList<Empresa>();
        String ejbQuery = "SELECT u FROM Empresa u " +
                          "WHERE u.tipo = 1 ";
        if(empresaDTO.getNombre() != null && empresaDTO.getNombre() != ""){
            ejbQuery +="AND u.nombre = " + empresaDTO.getNombre();
        }
        if(empresaDTO.getRuc()    != null && empresaDTO.getRuc()    != ""){
            ejbQuery +="AND u.ruc = " + empresaDTO.getRuc();
        }

        try{
            Query query = em.createQuery(ejbQuery,Empresa.class);
            empresas = query.getResultList();
        }catch(Exception e)
        {
            empresas = null;
        }
        
        return empresas;
    }
}
