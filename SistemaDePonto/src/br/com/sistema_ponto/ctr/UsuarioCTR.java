/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema_ponto.ctr;
import java.sql.ResultSet;
import br.com.sistema_ponto.dto.UsuarioDTO;
import br.com.sistema_ponto.dao.UsuarioDAO;
import br.com.sistema_ponto.dao.ConexaoDAO;

/**
 *
 * @author Breno
 */
public class UsuarioCTR {
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public UsuarioCTR(){
    }
    
    public String inserirUsuario(UsuarioDTO usuarioDTO){
        try {
            if (usuarioDAO.inserirUsuario(usuarioDTO)){
                return "Usuário Cadastrado com Sucesso!!!";
            } else{
                return "Usuário NÃO Cadastrado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Usuário NÃO Cadastrado";
        }
    }
    
    public String alterarUsuario(UsuarioDTO usuarioDTO){
        try {
            if(usuarioDAO.alterarUsuario(usuarioDTO)){
                return "Usuário Alterado com Sucesso!!!";
            } else{
                return "Usuário NÃO Alterdo!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Usuário NÃO Alterado!!!";
        }
    }
    
    public String excluirUsuario(UsuarioDTO usuarioDTO){
        try {
            if(usuarioDAO.excluirUsuario(usuarioDTO)){
                return "Usuário Excluído com Sucesso!!!";
            } else{
                return "Usuário NÃO Excluído!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Usuário NÃO Excluído!!!";
        }
    }
    
    public ResultSet consultarUsuario(UsuarioDTO usuarioDTO, int opc){
        ResultSet rs = null;       
        rs = usuarioDAO.consultarUsuario(usuarioDTO, opc);
        return rs;
    }
    
    public boolean logarUsuario(UsuarioDTO usuarioDTO) {
        return usuarioDAO.logarUsuario(usuarioDTO);
    }
    
    public int obterIdUsuario(String loginUser, String senhaUser) {
        return usuarioDAO.obterIdUsuario(loginUser, senhaUser);
    }

    public void atualizarUltimoLogin(int userId) {
        usuarioDAO.atualizarUltimoLogin(userId);
    }
    
    public boolean existemUsuarios() {
        return usuarioDAO.existemUsuarios();
    }
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
    
}
