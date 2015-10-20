/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.PealtipoitemFacade;
import javax.ejb.*;

/**
 *
 * @author Cesar
 */
@Stateless
@LocalBean
public class PedidoaltipoitemBO {
    @EJB
    private PealtipoitemFacade pedidoFacade = new PealtipoitemFacade();
    
    
     
}
