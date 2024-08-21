package br.com.sistema_ponto.dao;

import br.com.sistema_ponto.dto.HorarioDTO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

public class HorarioDAO {
    public HorarioDAO(){
    }
    
    private ResultSet rs = null;
    private Statement stmt = null;
    
    public boolean inserirHorario(HorarioDTO horarioDTO){
        try{        
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "INSERT INTO horarios (numero_horario, descricao_horario, entrada_um, saida_um, entrada_dois, saida_dois) VALUES ("
                             + horarioDTO.getNumeroHorario() + ", "
                             + "'" + horarioDTO.getDescricaoHorario() + "', "
                             + "'" + Time.valueOf(horarioDTO.getEntradaUm()) + "', "
                             + "'" + Time.valueOf(horarioDTO.getSaidaUm()) + "', "
                             + "'" + Time.valueOf(horarioDTO.getEntradaDois()) + "', "
                             + "'" + Time.valueOf(horarioDTO.getSaidaDois()) + "')";
            
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
    
    public ResultSet consultarHorario(HorarioDTO horarioDTO, int opcao) {
        ResultSet rs = null;
        String comando = "";
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();

            switch (opcao) {
                case 1:
                    comando = "Select c.* " + 
                              "from horarios c " + 
                              "where descricao_horario like '" + horarioDTO.getDescricaoHorario()+ "%' " +
                              "order by c.descricao_horario";
                break;
                case 2:
                    comando = "SELECT * FROM horarios WHERE numero_horario = " + horarioDTO.getNumeroHorario();
                    break;
                case 3:
                    comando = "SELECT * FROM horarios";
                    break;
            }

            rs = stmt.executeQuery(comando.toUpperCase());
        } catch (Exception e) {
            System.out.println("Erro ao consultar horários: " + e.getMessage());
        }
        return rs;
    }
    
    public boolean alterarHorario(HorarioDTO horarioDTO){
        try{
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();

             String comando = "UPDATE horarios SET "
                             + "descricao_horario = '" + horarioDTO.getDescricaoHorario() + "', "
                             + "entrada_um = '" + Time.valueOf(horarioDTO.getEntradaUm()) + "', "
                             + "saida_um = '" + Time.valueOf(horarioDTO.getSaidaUm()) + "', "
                             + "entrada_dois = '" + Time.valueOf(horarioDTO.getEntradaDois()) + "', "
                             + "saida_dois = '" + Time.valueOf(horarioDTO.getSaidaDois()) + "' "
                             + "WHERE numero_horario = " + horarioDTO.getNumeroHorario();
            
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
    
    public boolean excluirHorario(HorarioDTO horarioDTO){
        try{
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "DELETE FROM horarios WHERE numero_horario = " + horarioDTO.getNumeroHorario();
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
