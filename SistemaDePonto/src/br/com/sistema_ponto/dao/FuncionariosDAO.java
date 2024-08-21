package br.com.sistema_ponto.dao;
import java.sql.*;
import br.com.sistema_ponto.dto.FuncionariosDTO;
import br.com.sistema_ponto.dto.EmpresaDTO;
import br.com.sistema_ponto.dto.HorarioDTO;
import br.com.sistema_ponto.dto.FuncaoDTO;
import java.text.SimpleDateFormat;

public class FuncionariosDAO {
    public FuncionariosDAO(){    
    }
    
    SimpleDateFormat data_format = new SimpleDateFormat("dd/mm/yyyy");
    private ResultSet rs = null;
    private Statement stmt = null;

    public boolean inserirFuncionarios(FuncionariosDTO funcionariosDTO, EmpresaDTO empresaDTO, HorarioDTO horarioDTO, FuncaoDTO funcaoDTO){
        try{        
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();

            String comando = "INSERT INTO funcionarios (n_folha, nome_funcionario, n_pis, n_identificador, n_ctps, id_horario, id_funcao, admissao_funcionarios, demissao_funcionarios, id_empresa) VALUES ("
                    + funcionariosDTO.getN_folha() + ", "
                    + "'" + funcionariosDTO.getNome_funcionario() + "', "
                    + "'" + funcionariosDTO.getN_pis() + "', "
                    + funcionariosDTO.getN_identificador() + ", "
                    + "'" + funcionariosDTO.getN_ctps() + "', "
                    + horarioDTO.getNumeroHorario() + ", "
                    + funcaoDTO.getId_funcao() + ", "
//                    + "'" + funcionariosDTO.getDepartamento_funcionarios() + "', "
                    + "to_date('" + data_format.format(funcionariosDTO.getAdmissao_funcionarios()) + "','dd/mm/yyyy'), "
                    + (funcionariosDTO.getDemissao_funcionarios() != null ? "to_date('" + data_format.format(funcionariosDTO.getDemissao_funcionarios()) + "','dd/mm/yyyy')" : "NULL") + ", "
                    + empresaDTO.getId_empresa() + ")";
            
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
    
    public boolean alterarFuncionarios(FuncionariosDTO funcionariosDTO, EmpresaDTO empresaDTO, HorarioDTO horarioDTO, FuncaoDTO funcaoDTO){
        try{
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();

            String comando = "Update funcionarios set "
                    + "n_folha = '" + funcionariosDTO.getN_folha() + "', "
                    + "nome_funcionario = '" + funcionariosDTO.getNome_funcionario() + "', "
                    + "n_pis = '" + funcionariosDTO.getN_pis() + "', "
                    + "n_identificador  = '" + funcionariosDTO.getN_identificador() + "', "
                    + "n_ctps = '" + funcionariosDTO.getN_ctps() + "', "
                    + "admissao_funcionarios = to_date('" + data_format.format(funcionariosDTO.getAdmissao_funcionarios()) + "','dd/mm/yyyy'), "
                    + "demissao_funcionarios = " + (funcionariosDTO.getDemissao_funcionarios() != null ? "to_date('" + data_format.format(funcionariosDTO.getDemissao_funcionarios()) + "','dd/mm/yyyy')" : "NULL") + ", "
                    + "id_empresa = " + empresaDTO.getId_empresa() + ", "
                    + "id_horario = " + horarioDTO.getNumeroHorario() + ", "
                    + "id_funcao = " + funcaoDTO.getId_funcao() + " "
                    + "WHERE id_funcionario = " + funcionariosDTO.getId_funcionario();

            
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
    
    public boolean excluirFuncionario(FuncionariosDTO funcionariosDTO){
        try{
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Delete from funcionarios where id_funcionario = " + funcionariosDTO.getId_funcionario();
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
    
    public ResultSet consultarFuncionarios(FuncionariosDTO funcionariosDTO, int opcao){
        try{
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";
            
            switch(opcao){
                case 1:
                    comando = "Select c.* " + 
                              "from funcionarios c " + 
                              "where nome_funcionario like '" + funcionariosDTO.getNome_funcionario() + "%' " +
                              "order by c.nome_funcionario";
                break;
                case 2:
                    comando = "Select c.* " + 
                              "from funcionarios c " + 
                              "where c.n_pis = " + funcionariosDTO.getN_pis();
                break;
                case 3:
                    comando = "Select c.* " + 
                              "from funcionarios c " + 
                              "where c.id_funcionario = " + funcionariosDTO.getId_funcionario();
                break;
                case 4:
                    comando = "Select c.id_funcionario, c.nome_funcionario, c.n_pis " +
                              "from funcionarios c ";
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
}

