/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema_ponto.dao;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import br.com.sistema_ponto.dto.UsuarioDTO;

/**
 *
 * @author Breno
 */
public class UsuarioDAO {
    public UsuarioDAO(){
    }
    
    private ResultSet rs = null;
    private Statement stmt = null;
    
    public boolean inserirUsuario(UsuarioDTO usuarioDTO){
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into usuario (nome_user, cpf_user, "
                    + "login_user, lgpd, senha_user) values ( "
                    + "'" + usuarioDTO.getNome_user().toUpperCase() + "', "
                    + "'" + usuarioDTO.getCpf_user() + "', "
                    + "'" + usuarioDTO.getLogin_user() + "', "
                    + (usuarioDTO.isLgpd()? "TRUE" : "FALSE") + ", "
                    + "'" + criptografar(usuarioDTO.getSenha_user()) + "') ";
            
            stmt.execute(comando);
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean alterarUsuario(UsuarioDTO usuarioDTO){
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
           String comando = "";

            comando = "UPDATE usuario SET "
                    + "nome_user = '" + usuarioDTO.getNome_user().toUpperCase() + "', "
                    + "cpf_user = '" + usuarioDTO.getCpf_user() + "', "
                    + "login_user = '" + usuarioDTO.getLogin_user() + "', "
                    + "lgpd = " + (usuarioDTO.isLgpd() ? "TRUE" : "FALSE") + " ";

            if (usuarioDTO.getSenha_user() != null) {
                comando += ", senha_user = '" + criptografar(usuarioDTO.getSenha_user()) + "'";
            }

            comando += " WHERE id_user = " + usuarioDTO.getId_user();
            
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
    
    public boolean excluirUsuario(UsuarioDTO usuarioDTO){
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Delete from usuario where id_user = "
                    + usuarioDTO.getId_user();
            
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
    
    public ResultSet consultarUsuario(UsuarioDTO usuarioDTO, int opc){
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";
            
            switch (opc){
                case 1:
                    comando = "Select f.* " +
                              "from usuario f " +
                              "where nome_user ilike '" + usuarioDTO.getNome_user() + "%' " +
                              "order by f.nome_user";
                break;
                case 2:
                    comando = "Select f.* " +
                              "from usuario f " +
                              "where f.id_user = " + usuarioDTO.getId_user();
                break;
            }
            
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }
    
    public boolean logarUsuario(UsuarioDTO usuarioDTO) {
        try {
            ConexaoDAO.ConectDB();

            String comando = "SELECT 1 " +
                             "FROM usuario " +
                             "WHERE login_user = ? " +
                             "AND senha_user = ?";

            PreparedStatement stmt = ConexaoDAO.con.prepareStatement(comando);
            stmt.setString(1, usuarioDTO.getLogin_user());
            stmt.setString(2, criptografar(usuarioDTO.getSenha_user()));

            ResultSet rs = stmt.executeQuery();

            // Se o ResultSet tiver algum registro, significa que o login é válido
            return rs.next();

        } catch (Exception e) {
            System.out.println("Erro ao executar consulta SQL: " + e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public void atualizarUltimoLogin(int userId) {
        try {
            ConexaoDAO.ConectDB(); // Certifique-se de que a conexão está aberta
            String comando = "UPDATE usuario SET ultimo_login = ? WHERE id_user = ?";
            try (PreparedStatement stmt = ConexaoDAO.con.prepareStatement(comando)) {
                Timestamp agora = Timestamp.valueOf(java.time.LocalDateTime.now());
                stmt.setTimestamp(1, agora);
                stmt.setInt(2, userId);

                int linhasAfetadas = stmt.executeUpdate();
                ConexaoDAO.con.commit();

                // Mensagens de depuração
                System.out.println("Atualizando último login para o usuário com ID: " + userId);
                System.out.println("Data e hora do último login: " + agora);
                System.out.println("Linhas afetadas: " + linhasAfetadas);
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o último login: " + e.getMessage());
        } finally {
            ConexaoDAO.CloseDB(); // Fecha a conexão no final
        }
    }

    public int obterIdUsuario(String loginUser, String senhaUser) {
        int userId = -1;
        try {
            ConexaoDAO.ConectDB(); // Certifique-se de que a conexão está aberta
            String comando = "SELECT id_user FROM usuario WHERE login_user = ? AND senha_user = ?";
            try (PreparedStatement stmt = ConexaoDAO.con.prepareStatement(comando)) {
                stmt.setString(1, loginUser);
                stmt.setString(2, criptografar(senhaUser));
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        userId = rs.getInt("id_user");
                    }
                }
                // Mensagens de depuração
                System.out.println("Login: " + loginUser);
                System.out.println("Senha (criptografada): " + criptografar(senhaUser));
                System.out.println("ID do usuário obtido: " + userId);
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter ID do usuário: " + e.getMessage());
        } finally {
            ConexaoDAO.CloseDB(); // Fecha a conexão no final
        }
        return userId;
    }
    
    public boolean existemUsuarios() {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "SELECT COUNT(*) AS total FROM usuario";
            
            rs = stmt.executeQuery(comando);
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Erro ao executar consulta SQL: " + e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
           
    }
    
    private static MessageDigest md = null;
    
    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }
    
    private static char[] hexCodes(byte[] text){
        char[] hexOutput = new char[text.length * 3];
        String hexString;
        
        for (int i = 0; i < text.length; i++){
            hexString = "00" + Integer.toHexString(text[i]);
            hexString.toUpperCase().getChars(hexString.length() - 3,
                            hexString.length(), hexOutput, i * 3);
        }
        return hexOutput;
    }
    
    public static String criptografar(String pwd) {
        if(md != null){
            return new String(hexCodes(md.digest(pwd.getBytes())));
        }
        return null;
    }
}

