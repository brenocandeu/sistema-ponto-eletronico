package br.com.sistema_ponto.ctr;

import br.com.sistema_ponto.dao.ConexaoDAO;
import br.com.sistema_ponto.dao.HorarioDAO;
import br.com.sistema_ponto.dto.HorarioDTO;

import java.sql.ResultSet;

public class HorarioCTR {
    HorarioDAO horarioDAO = new HorarioDAO();

    public HorarioCTR() {
    }

    public String inserirHorario(HorarioDTO horarioDTO) {
        try {
            if (horarioDAO.inserirHorario(horarioDTO)) {
                return "Horário cadastrado com sucesso!!!";
            } else {
                return "Horário NÃO cadastrado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Horário NÃO cadastrado!!!";
        }
    }

    public ResultSet consultarHorario(HorarioDTO horarioDTO, int opcao) {
        ResultSet rs = null;       
        rs = horarioDAO.consultarHorario(horarioDTO, opcao);
        return rs;
    }

    public String alterarHorario(HorarioDTO horarioDTO) {
        try {
            if (horarioDAO.alterarHorario(horarioDTO)) {
                return "Horário alterado com sucesso!!!";
            } else {
                return "Horário NÃO alterado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Horário NÃO alterado!!!";
        }
    }

    public String excluirHorario(HorarioDTO horarioDTO) {
        try {
            if (horarioDAO.excluirHorario(horarioDTO)) {
                return "Horário excluído com sucesso!!!";
            } else {
                return "Horário NÃO excluído!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Horário NÃO excluído!!!";
        }
    }

    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
}
