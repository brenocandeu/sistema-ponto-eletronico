/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema_ponto.ctr;
import br.com.sistema_ponto.dao.ConexaoDAO;
import java.sql.ResultSet;
import br.com.sistema_ponto.dto.FuncionariosDTO;
import br.com.sistema_ponto.dao.FuncionariosDAO;
import br.com.sistema_ponto.dto.EmpresaDTO;
import br.com.sistema_ponto.dto.HorarioDTO;
import br.com.sistema_ponto.dto.FuncaoDTO;

/**
 *
 * @author Breno
 */
public class FuncionarioCTR {
    FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
    
    public FuncionarioCTR(){
        
    }
    
    public String inserirFuncionario(FuncionariosDTO funcionariosDTO, EmpresaDTO empresaDTO, HorarioDTO horarioDTO, FuncaoDTO funcaoDTO) {
        try {
            if (funcionariosDAO.inserirFuncionarios(funcionariosDTO, empresaDTO, horarioDTO, funcaoDTO)) {
                return "Funcionario cadastrado com sucesso!!!";
            } else {
                return "Funcionario NÃO cadastrado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionario NÃO cadastrado!!!";
        }
    }
    
    public String alterarFuncionarios(FuncionariosDTO funcionariosDTO, EmpresaDTO empresaDTO, HorarioDTO horarioDTO, FuncaoDTO funcaoDTO){
        try{
            if(funcionariosDAO.alterarFuncionarios(funcionariosDTO, empresaDTO, horarioDTO, funcaoDTO)){
                return "Funcionario alterado com sucesso!!!";
            } else{
                return "Funcionario NÃO alterado!!!";
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Funcionario NÃO alterado!!!";
        }
    }
    
    public String excluirFuncionario(FuncionariosDTO funcionariosDTO){
        try{
            if(funcionariosDAO.excluirFuncionario(funcionariosDTO)){
                return "Funcionario Excluído com Sucesso!!!";
            }
            else{
                return "Funcionario NÃO Excluído!!!";
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Funcionario NÃO Excluído";
        }
    }
    
    public ResultSet consultarFuncionarios(FuncionariosDTO funcionariosDTO, int opcao){
        ResultSet rs = null;
        
        rs = funcionariosDAO.consultarFuncionarios(funcionariosDTO, opcao);
        
        return rs;
    }
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    } 
}

