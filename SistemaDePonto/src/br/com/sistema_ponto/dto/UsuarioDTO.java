/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema_ponto.dto;

/**
 *
 * @author Breno
 */
public class UsuarioDTO {
    private String nome_user, cpf_user;
    private String login_user, senha_user;
    private int id_user;
    private boolean lgpd;

    public String getNome_user() {
        return nome_user;
    }

    public void setNome_user(String nome_user) {
        this.nome_user = nome_user;
    }

    public String getCpf_user() {
        return cpf_user;
    }

    public void setCpf_user(String cpf_user) {
        this.cpf_user = cpf_user;
    }

    public String getLogin_user() {
        return login_user;
    }

    public void setLogin_user(String login_user) {
        this.login_user = login_user;
    }

    public String getSenha_user() {
        return senha_user;
    }

    public void setSenha_user(String senha_user) {
        this.senha_user = senha_user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public boolean isLgpd() {
        return lgpd;
    }

    public void setLgpd(boolean lgpd) {
        this.lgpd = lgpd;
    }
}
