/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema_ponto.ctr;

import br.com.sistema_ponto.dao.ConexaoDAO;
import java.sql.ResultSet;
import br.com.sistema_ponto.dto.EmpresaDTO;
import br.com.sistema_ponto.dao.EmpresaDAO;

/**
 *
 * @author Breno
 */
public class EmpresaCTR {
    EmpresaDAO empresaDAO = new EmpresaDAO();
    
    public EmpresaCTR(){
        
    }

    public String inserirEmpresa(EmpresaDTO empresaDTO){
        try{
            if(empresaDAO.inserirEmpresa(empresaDTO)){
                return "Empresa cadastrada com sucesso!!!";
            }else{
                return "Empresa NÃO cadastrada!!!";
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Empresa NÃO cadastrada!!!";
        }
    }
    
    public ResultSet consultarTodasEmpresas(){
        ResultSet rs = null;
        
        rs = empresaDAO.consultarTodasEmpresas();
        
        return rs;
    }
    
    public ResultSet consultarEmpresa(EmpresaDTO empresaDTO, int opcao){
        ResultSet rs = null;
        
        rs = empresaDAO.consultarEmpresa(empresaDTO, opcao);
        
        return rs;
    }
    
    public String alterarEmpresa(EmpresaDTO empresaDTO){
        try{
            if(empresaDAO.alterarEmpresa(empresaDTO)){
                return "Empresa alterada com sucesso!!!";
            } else{
                return "Empresa NÃO alterada!!!";
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Empresa NÃO alterada!!!";
        }
    }
    
    public String excluirEmpresa(EmpresaDTO empresaDTO){
        try{
            if(empresaDAO.excluirEmpresa(empresaDTO)){
                return "Empresa Excluída com Sucesso!!!";
            }
            else{
                return "Empresa NÃO Excluída!!!";
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Empresa NÃO Excluída";
        }
    }
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
}
