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
public class EmpresaDTO {
   private  String nome_empresa, cnpj_empresa, inscricacao_empresa, nome_responsavel, cargo_responsavel, logradouro_empresa, bairro_empresa, cidade_empresa, cep_empresa, estado_empresa;
   private int id_empresa;

    public String getNome_empresa() {
        return nome_empresa;
    }

    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
    }

    public String getCnpj_empresa() {
        return cnpj_empresa;
    }

    public void setCnpj_empresa(String cnpj_empresa) {
        this.cnpj_empresa = cnpj_empresa;
    }

    public String getInscricacao_empresa() {
        return inscricacao_empresa;
    }

    public void setInscricacao_empresa(String inscricacao_empresa) {
        this.inscricacao_empresa = inscricacao_empresa;
    }

    public String getNome_responsavel() {
        return nome_responsavel;
    }

    public void setNome_responsavel(String nome_responsavel) {
        this.nome_responsavel = nome_responsavel;
    }

    public String getCargo_responsavel() {
        return cargo_responsavel;
    }

    public void setCargo_responsavel(String cargo_responsavel) {
        this.cargo_responsavel = cargo_responsavel;
    }

    public String getLogradouro_empresa() {
        return logradouro_empresa;
    }

    public void setLogradouro_empresa(String logradouro_empresa) {
        this.logradouro_empresa = logradouro_empresa;
    }

    public String getBairro_empresa() {
        return bairro_empresa;
    }

    public void setBairro_empresa(String bairro_empresa) {
        this.bairro_empresa = bairro_empresa;
    }

    public String getCidade_empresa() {
        return cidade_empresa;
    }

    public void setCidade_empresa(String cidade_empresa) {
        this.cidade_empresa = cidade_empresa;
    }

    public String getCep_empresa() {
        return cep_empresa;
    }

    public void setCep_empresa(String cep_empresa) {
        this.cep_empresa = cep_empresa;
    }

    public String getEstado_empresa() {
        return estado_empresa;
    }

    public void setEstado_empresa(String estado_empresa) {
        this.estado_empresa = estado_empresa;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }
}
