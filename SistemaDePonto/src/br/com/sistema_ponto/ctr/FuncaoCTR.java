package br.com.sistema_ponto.ctr;

import br.com.sistema_ponto.dao.ConexaoDAO;
import java.sql.ResultSet;
import br.com.sistema_ponto.dao.FuncaoDAO;
import br.com.sistema_ponto.dto.FuncaoDTO;

public class FuncaoCTR {
    FuncaoDAO funcaoDAO = new FuncaoDAO();
    
    public FuncaoCTR(){
        
    }

    public String inserirFuncao(FuncaoDTO funcaoDTO){
        try{
            if(funcaoDAO.inserirFuncao(funcaoDTO)){
                return "Função cadastrada com sucesso!!!";
            }else{
                return "Função NÃO cadastrada!!!";
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Função NÃO cadastrada!!!";
        }
    }
    
    public ResultSet consultarTodasFuncoes(){
        ResultSet rs = null;
        
        rs = funcaoDAO.consultarTodasFuncoes();
        
        return rs;
    }
    
    public ResultSet consultarFuncao(FuncaoDTO funcaoDTO, int opcao){
        ResultSet rs = null;
        
        rs = funcaoDAO.consultarFuncao(funcaoDTO, opcao);
        
        return rs;
    }
    
    public String alterarFuncao(FuncaoDTO funcaoDTO){
        try{
            if(funcaoDAO.alterarFuncao(funcaoDTO)){
                return "Função alterada com sucesso!!!";
            } else{
                return "Função NÃO alterada!!!";
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Função NÃO alterada!!!";
        }
    }
    
    public String excluirFuncao(FuncaoDTO funcaoDTO){
        try{
            if(funcaoDAO.excluirFuncao(funcaoDTO)){
                return "Função Excluída com Sucesso!!!";
            }
            else{
                return "Função NÃO Excluída!!!";
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Função NÃO Excluída";
        }
    }
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
}
