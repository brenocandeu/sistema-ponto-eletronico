/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema_ponto.dao;

/**
 *
 * @author Breno
 */

import java.sql.*;
import br.com.sistema_ponto.dto.EmpresaDTO;

public class EmpresaDAO {
    public EmpresaDAO(){
        
    }
    
    private ResultSet rs = null;
    private Statement stmt = null;

    public boolean inserirEmpresa(EmpresaDTO empresaDTO){
        try{        
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Insert into empresa (nome_empresa, cnpj_empresa, inscricacao_empresa, "
                            + "nome_responsavel, cargo_responsavel, logradouro_empresa, bairro_empresa, cidade_empresa, estado_empresa, cep_empresa) values ("
                            + "'" + empresaDTO.getNome_empresa() + "', "
                            + "'" + empresaDTO.getCnpj_empresa() + "', "
                            + "'" + empresaDTO.getInscricacao_empresa() + "', "
                            + "'" + empresaDTO.getNome_responsavel() + "', "
                            + "'" + empresaDTO.getCargo_responsavel() + "', "
                            + "'" + empresaDTO.getLogradouro_empresa() + "', "
                            + "'" + empresaDTO.getBairro_empresa() + "', "
                            + "'" + empresaDTO.getCidade_empresa() + "', "
                            + "'" + empresaDTO.getEstado_empresa() + "', "
                            + "'" + empresaDTO.getCep_empresa() + "')";
            
            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }
    
    public ResultSet consultarTodasEmpresas() {
        ResultSet rs = null;
        Statement stmt = null;

        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "SELECT id_empresa, nome_empresa FROM empresa ORDER BY nome_empresa";
            rs = stmt.executeQuery(comando);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rs;
    }
    
    public ResultSet consultarEmpresa(EmpresaDTO empresaDTO, int opcao){
        try{
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";
            
            switch(opcao){
                case 1:
                    comando = "Select c.* " + 
                              "from empresa c " + 
                              "where nome_empresa like '" + empresaDTO.getNome_empresa() + "%' " +
                              "order by c.nome_empresa";
                break;
                case 2:
                    comando = "Select c.* " + 
                              "from empresa c " + 
                              "where c.cnpj_empresa = " + empresaDTO.getCnpj_empresa();
                break;
                case 3:
                    comando = "Select c.* " + 
                              "from empresa c " + 
                              "where c.id_empresa = " + empresaDTO.getId_empresa();
                break;
                case 4:
                    comando = "Select c.id_empresa, c.nome_empresa, c.cnpj_empresa " +
                              "from empresa c ";
                break;
            }
            
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        }
        
        catch (Exception e){
            System.out.println(e.getMessage());
            return rs;
        }
    }
    
    public boolean alterarEmpresa(EmpresaDTO empresaDTO){
        try{
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();

            String comando = "Update empresa set "
                            + "nome_empresa = '" + empresaDTO.getNome_empresa() + "', "
                            + "cnpj_empresa = '" + empresaDTO.getCnpj_empresa() + "', "
                            + "inscricacao_empresa = '" + empresaDTO.getInscricacao_empresa() + "', "
                            + "nome_responsavel = '" + empresaDTO.getNome_responsavel() + "', "
                            + "cargo_responsavel = '" + empresaDTO.getCargo_responsavel() + "', "
                            + "logradouro_empresa = '" + empresaDTO.getLogradouro_empresa() + "', "
                            + "bairro_empresa = '" + empresaDTO.getBairro_empresa() + "', "
                            + "cidade_empresa = '" + empresaDTO.getCidade_empresa() + "', "
                            + "cep_empresa = '" + empresaDTO.getCep_empresa() + "', "
                            + "estado_empresa = '" + empresaDTO.getEstado_empresa() + "' "
                            + "where id_empresa = " + empresaDTO.getId_empresa();
            
            stmt .execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean excluirEmpresa(EmpresaDTO empresaDTO){
        try{
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Delete from empresa where id_empresa = " + empresaDTO.getId_empresa();
            stmt.execute(comando);
            
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }
}
