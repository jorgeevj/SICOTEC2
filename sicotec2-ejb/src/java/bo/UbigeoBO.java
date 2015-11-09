/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.UbigeoFacade;
import entidades.Ubigeo;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Jorge
 */
@Stateless
@LocalBean
public class UbigeoBO {

    @EJB
    private UbigeoFacade ubigeoFacade;
    
    public List<Ubigeo> getAllDepartamentos() {
        return ubigeoFacade.findUbigeos(null, "00", "00");
    }

    public List<Ubigeo> getAllProvincias(String codDpto) {
        return ubigeoFacade.findUbigeos(codDpto, null, "00");
    }

    public List<Ubigeo> getAllDistritos(String codDpto, String codProv) {
        return ubigeoFacade.findUbigeos(codDpto, codProv, null);
    }

    public Ubigeo getUbigeo(String codDpto, String codProv, String codDist) {
        return ubigeoFacade.findUbigeos(codDpto, codProv, codDist).get(0);
    }
    
}
