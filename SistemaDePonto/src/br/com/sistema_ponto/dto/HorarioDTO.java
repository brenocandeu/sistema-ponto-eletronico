package br.com.sistema_ponto.dto;

import java.time.LocalTime;

public class HorarioDTO {
    private int numeroHorario;
    private String descricaoHorario;
    private LocalTime entradaUm, saidaUm, entradaDois, saidaDois;

    public int getNumeroHorario() {
        return numeroHorario;
    }

    public void setNumeroHorario(int numeroHorario) {
        this.numeroHorario = numeroHorario;
    }

    public String getDescricaoHorario() {
        return descricaoHorario;
    }

    public void setDescricaoHorario(String descricaoHorario) {
        this.descricaoHorario = descricaoHorario;
    }

    public LocalTime getEntradaUm() {
        return entradaUm;
    }

    public void setEntradaUm(LocalTime entradaUm) {
        this.entradaUm = entradaUm;
    }

    public LocalTime getSaidaUm() {
        return saidaUm;
    }

    public void setSaidaUm(LocalTime saidaUm) {
        this.saidaUm = saidaUm;
    }

    public LocalTime getEntradaDois() {
        return entradaDois;
    }

    public void setEntradaDois(LocalTime entradaDois) {
        this.entradaDois = entradaDois;
    }

    public LocalTime getSaidaDois() {
        return saidaDois;
    }

    public void setSaidaDois(LocalTime saidaDois) {
        this.saidaDois = saidaDois;
    }
    
    
}
