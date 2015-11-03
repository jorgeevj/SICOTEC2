/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Empresa;

import bo.EmpresaBO;
import dto.EmpresaDTO;
import entidades.Empresa;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jorge
 */
@ManagedBean
@SessionScoped
public class EmpresaMB {
    @EJB
    private EmpresaBO empresaBO;

    EmpresaDTO consultaEmpresaDTO;
    List<EmpresaDTO> listaEmpresa;
    /**
     * Creates a new instance of EmpresaMB
     */
    public EmpresaMB() {
    }
    @PostConstruct
    public void init() {
      consultaEmpresaDTO=new EmpresaDTO();
      listaEmpresa=empresaBO.getAllEmpresas();
    }
}
