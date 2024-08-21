package br.com.sistema_ponto.dto;
import java.util.Date;

public class FuncionariosDTO {
   private  String nome_funcionario, n_pis, n_ctps, empresa_funcionarios, funcao_funcionarios, departamento_funcionarios;
   private Date admissao_funcionarios, demissao_funcionarios;
   private int id_funcionario, n_folha, n_identificador, horario_funcionarios;

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getN_pis() {
        return n_pis;
    }

    public void setN_pis(String n_pis) {
        this.n_pis = n_pis;
    }

    public String getN_ctps() {
        return n_ctps;
    }

    public void setN_ctps(String n_ctps) {
        this.n_ctps = n_ctps;
    }

    public String getEmpresa_funcionarios() {
        return empresa_funcionarios;
    }

    public void setEmpresa_funcionarios(String empresa_funcionarios) {
        this.empresa_funcionarios = empresa_funcionarios;
    }

    public String getFuncao_funcionarios() {
        return funcao_funcionarios;
    }

    public void setFuncao_funcionarios(String funcao_funcionarios) {
        this.funcao_funcionarios = funcao_funcionarios;
    }

    public String getDepartamento_funcionarios() {
        return departamento_funcionarios;
    }

    public void setDepartamento_funcionarios(String departamento_funcionarios) {
        this.departamento_funcionarios = departamento_funcionarios;
    }

    public Date getAdmissao_funcionarios() {
        return admissao_funcionarios;
    }

    public void setAdmissao_funcionarios(Date admissao_funcionarios) {
        this.admissao_funcionarios = admissao_funcionarios;
    }

    public Date getDemissao_funcionarios() {
        return demissao_funcionarios;
    }

    public void setDemissao_funcionarios(Date demissao_funcionarios) {
        this.demissao_funcionarios = demissao_funcionarios;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public int getN_folha() {
        return n_folha;
    }

    public void setN_folha(int n_folha) {
        this.n_folha = n_folha;
    }

    public int getN_identificador() {
        return n_identificador;
    }

    public void setN_identificador(int n_identificador) {
        this.n_identificador = n_identificador;
    }

    public int getHorario_funcionarios() {
        return horario_funcionarios;
    }

    public void setHorario_funcionarios(int horario_funcionarios) {
        this.horario_funcionarios = horario_funcionarios;
    }
}
