package br.com.sistema_ponto.dao;

import br.com.sistema_ponto.dto.FuncaoDTO;
import java.sql.*;

public class FuncaoDAO {
    public FuncaoDAO(){
    }
    
    private ResultSet rs = null;
    private Statement stmt = null;
    
    public boolean inserirFuncao(FuncaoDTO funcaoDTO) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "INSERT INTO funcao (nome_funcao) VALUES ('" + funcaoDTO.getNome_funcao() + "')";

            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public ResultSet consultarTodasFuncoes() {
        ResultSet rs = null;
        Statement stmt = null;

        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "SELECT id_funcao, nome_funcao FROM funcao ORDER BY nome_funcao";
            rs = stmt.executeQuery(comando);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rs;
    }

    public ResultSet consultarFuncao(FuncaoDTO funcaoDTO, int opcao){
        ResultSet rs = null;
        Statement stmt = null;

        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";

            switch(opcao) {
                case 1:
                    comando = "SELECT f.* " + 
                              "FROM funcao f " +
                              "WHERE f.nome_funcao LIKE '" + funcaoDTO.getNome_funcao() + "%' " +
                              "ORDER BY f.nome_funcao";
                    break;
                case 2:
                    comando = "SELECT * FROM funcao WHERE id_funcao = " + funcaoDTO.getId_funcao();
                    break;
                case 3:
                    comando = "SELECT * FROM funcao";
                    break;
            }

            rs = stmt.executeQuery(comando.toUpperCase());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rs;
    }

    public boolean alterarFuncao(FuncaoDTO funcaoDTO) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();

            String comando = "UPDATE funcao SET nome_funcao = '" + funcaoDTO.getNome_funcao() + "' WHERE id_funcao = " + funcaoDTO.getId_funcao();

            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }

    public boolean excluirFuncao(FuncaoDTO funcaoDTO) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "DELETE FROM funcao WHERE id_funcao = " + funcaoDTO.getId_funcao();
            stmt.execute(comando);

            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
    
}
