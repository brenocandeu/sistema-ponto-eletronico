package br.com.sistema_ponto.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import br.com.sistema_ponto.dto.FuncionariosDTO;
import br.com.sistema_ponto.ctr.FuncionarioCTR;
import br.com.sistema_ponto.dto.EmpresaDTO;
import br.com.sistema_ponto.ctr.EmpresaCTR;
import br.com.sistema_ponto.dto.HorarioDTO;
import br.com.sistema_ponto.ctr.HorarioCTR;
import br.com.sistema_ponto.dto.FuncaoDTO;
import br.com.sistema_ponto.ctr.FuncaoCTR;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class CadastroFuncionariosVIEW extends javax.swing.JInternalFrame {
    SimpleDateFormat date_format = new SimpleDateFormat("dd/mm/yyyy");
    
    FuncionariosDTO funcionariosDTO = new FuncionariosDTO();
    FuncionarioCTR funcionarioCTR = new FuncionarioCTR();
    EmpresaDTO empresaDTO = new EmpresaDTO();
    EmpresaCTR empresaCTR = new EmpresaCTR();
    HorarioDTO horarioDTO = new HorarioDTO();
    HorarioCTR horarioCTR = new HorarioCTR();
    FuncaoDTO funcaoDTO = new FuncaoDTO();
    FuncaoCTR funcaoCTR = new FuncaoCTR();
    
    int gravar_alterar;
    
    ResultSet rs;

    public CadastroFuncionariosVIEW() {
        initComponents();
        gravar_alterar = 1;
    }
    
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    private boolean validarCampos() {
        // Verifica campos de texto
        if (n_folha_funcionarios.getText().isEmpty() || 
            nome_funcionarios.getText().isEmpty() || 
            n_pis_funcionarios.getText().isEmpty() ||
            n_identificador_funcionarios.getText().isEmpty() || 
            n_ctps_funcionarios.getText().isEmpty() || 
            empresa_funcionarios.getSelectedItem().toString().isEmpty() ||
            horario_funcionarios.getSelectedItem().toString().isEmpty() ||
            funcao_funcionarios.getSelectedItem().toString().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validação do PIS
        if (!validarPIS(n_pis_funcionarios.getText())) {
            JOptionPane.showMessageDialog(null, "O número do PIS informado é inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String dataString = admissao_funcionarios.getText().trim();
        if (dataString.isEmpty() || dataString.matches("^\\s*/\\s*/\\s*$")) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione uma data de admissão.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    
    private boolean validarPIS(String pis) {
        // Remove caracteres não numéricos
        pis = pis.replaceAll("[^0-9]", "");

        // Verifica se o PIS tem 11 dígitos numéricos
        if (pis.length() != 11) {
            return false;
        }

        int[] multiplicadores = {3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;

        // Calcula o somatório dos produtos dos dígitos pelos multiplicadores
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(pis.charAt(i)) * multiplicadores[i];
        }

        int resto = soma % 11;
        int digitoVerificador = 11 - resto;

        // Se o dígito verificador for 10 ou 11, ele deve ser 0
        if (digitoVerificador == 10 || digitoVerificador == 11) {
            digitoVerificador = 0;
        }

        // Verifica se o dígito verificador bate com o último dígito do PIS
        return digitoVerificador == Character.getNumericValue(pis.charAt(10));
    }
    
    private List<EmpresaDTO> empresas = new ArrayList<>();
    
    private void preencheEmpresaBox(String nome_empresa, int idFuncionario) {
        ResultSet rsEmpresas = null;
        ResultSet rsFuncionario = null;

        try {
            empresa_funcionarios.removeAllItems();
            empresas.clear(); // Limpa a lista de empresas

            EmpresaDTO empresaDTO = new EmpresaDTO();
            empresaDTO.setNome_empresa(nome_empresa);

            // Consulta para obter a lista de empresas
            rsEmpresas = empresaCTR.consultarEmpresa(empresaDTO, 1);
            while (rsEmpresas.next()) {
                EmpresaDTO empresa = new EmpresaDTO();
                empresa.setId_empresa(rsEmpresas.getInt("id_empresa"));
                empresa.setNome_empresa(rsEmpresas.getString("nome_empresa"));

                // Adiciona o objeto EmpresaDTO à lista
                empresas.add(empresa);

                // Adiciona apenas o nome da empresa ao JComboBox
                empresa_funcionarios.addItem(empresa.getNome_empresa());
            }

            // Consulta para obter os dados do funcionário, somente se idFuncionario for diferente de 0
            if (idFuncionario > 0) {
                funcionariosDTO.setId_funcionario(idFuncionario);
                rsFuncionario = funcionarioCTR.consultarFuncionarios(funcionariosDTO, 3);

                if (rsFuncionario.next()) {
                    int idEmpresaFuncionario = rsFuncionario.getInt("id_empresa");

                    // Percorre a lista de empresas para encontrar a empresa correta
                    for (EmpresaDTO empresa : empresas) {
                        if (empresa.getId_empresa() == idEmpresaFuncionario) {
                            // Seleciona o item no JComboBox com base no nome da empresa
                            empresa_funcionarios.setSelectedItem(empresa.getNome_empresa());
                            break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Erro preencheEmpresaBox: " + e.getMessage());
        } finally {
            // Fecha os ResultSets e a conexão com o banco de dados
            try {
                if (rsEmpresas != null) rsEmpresas.close();
                if (rsFuncionario != null) rsFuncionario.close();
                if (funcionarioCTR != null) funcionarioCTR.CloseDB();
            } catch (Exception e) {
                System.out.println("Erro ao fechar ResultSet ou conexão: " + e.getMessage());
            }
        }
    }
    
    private List<HorarioDTO> horarios = new ArrayList<>();
    
    private void preencheHorarioBox(String descricao_horario, int idFuncionario) {
        ResultSet rsHorarios = null;
        ResultSet rsFuncionario = null;

        try {
            horario_funcionarios.removeAllItems();
            horarios.clear(); // Limpa a lista de horários

            HorarioDTO horarioDTO = new HorarioDTO();
            horarioDTO.setDescricaoHorario(descricao_horario);

            // Consulta para obter a lista de horários
            rsHorarios = horarioCTR.consultarHorario(horarioDTO, 1);
            while (rsHorarios.next()) {
                HorarioDTO horario = new HorarioDTO();
                horario.setNumeroHorario(rsHorarios.getInt("numero_horario"));
                horario.setDescricaoHorario(rsHorarios.getString("descricao_horario"));

                // Adiciona o objeto HorarioDTO à lista
                horarios.add(horario);

                // Adiciona apenas a descrição do horário ao JComboBox
                horario_funcionarios.addItem(horario.getDescricaoHorario());
            }

            // Consulta para obter os dados do funcionário, somente se idFuncionario for diferente de 0
            if (idFuncionario > 0) {
                funcionariosDTO.setId_funcionario(idFuncionario);
                rsFuncionario = funcionarioCTR.consultarFuncionarios(funcionariosDTO, 3);

                if (rsFuncionario.next()) {
                    int idHorarioFuncionario = rsFuncionario.getInt("id_horario");

                    // Percorre a lista de horários para encontrar o horário correto
                    for (HorarioDTO horario : horarios) {
                        if (horario.getNumeroHorario() == idHorarioFuncionario) {
                            // Seleciona o item no JComboBox com base na descrição do horário
                            horario_funcionarios.setSelectedItem(horario.getDescricaoHorario());
                            break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Erro preencheHorarioBox: " + e.getMessage());
        } finally {
            // Fecha os ResultSets e a conexão com o banco de dados
            try {
                if (rsHorarios != null) rsHorarios.close();
                if (rsFuncionario != null) rsFuncionario.close();
                if (horarioCTR != null) horarioCTR.CloseDB();
            } catch (Exception e) {
                System.out.println("Erro ao fechar ResultSet ou conexão: " + e.getMessage());
            }
        }
    }
    
    private List<FuncaoDTO> funcoes = new ArrayList<>();
    
    private void preencheFuncaoBox(String nome_funcao, int idFuncionario) {
        ResultSet rsFuncao = null;
        ResultSet rsFuncionario = null;

        try {
            funcao_funcionarios.removeAllItems();
            funcoes.clear(); // Limpa a lista de horários

            FuncaoDTO funcaoDTO = new FuncaoDTO();
            funcaoDTO.setNome_funcao(nome_funcao);

            // Consulta para obter a lista de horários
            rsFuncao = funcaoCTR.consultarFuncao(funcaoDTO, 1);
            while (rsFuncao.next()) {
                FuncaoDTO funcao = new FuncaoDTO();
                funcao.setId_funcao(rsFuncao.getInt("id_funcao"));
                funcao.setNome_funcao(rsFuncao.getString("nome_funcao"));

                // Adiciona o objeto HorarioDTO à lista
                funcoes.add(funcao);

                // Adiciona apenas a descrição do horário ao JComboBox
                funcao_funcionarios.addItem(funcao.getNome_funcao());
            }

            // Consulta para obter os dados do funcionário, somente se idFuncionario for diferente de 0
            if (idFuncionario > 0) {
                funcionariosDTO.setId_funcionario(idFuncionario);
                rsFuncionario = funcionarioCTR.consultarFuncionarios(funcionariosDTO, 3);

                if (rsFuncionario.next()) {
                    int idFuncaoFuncionario = rsFuncionario.getInt("id_funcao");

                    // Percorre a lista de horários para encontrar o horário correto
                    for (FuncaoDTO funcao : funcoes) {
                        if (funcao.getId_funcao() == idFuncaoFuncionario) {
                            // Seleciona o item no JComboBox com base na descrição do horário
                            funcao_funcionarios.setSelectedItem(funcao.getNome_funcao());
                            break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Erro preencheFuncaoBox: " + e.getMessage());
        } finally {
            // Fecha os ResultSets e a conexão com o banco de dados
            try {
                if (rsFuncao != null) rsFuncao.close();
                if (rsFuncionario != null) rsFuncionario.close();
                if (funcaoCTR != null) funcaoCTR.CloseDB();
            } catch (Exception e) {
                System.out.println("Erro ao fechar ResultSet ou conexão: " + e.getMessage());
            }
        }
    }
    
    private void gravar(){
        try{
            funcionariosDTO.setN_folha(Integer.parseInt(n_folha_funcionarios.getText()));
            funcionariosDTO.setNome_funcionario(nome_funcionarios.getText());
            funcionariosDTO.setN_pis(n_pis_funcionarios.getText());
            funcionariosDTO.setN_identificador(Integer.parseInt(n_identificador_funcionarios.getText()));
            funcionariosDTO.setN_ctps(n_ctps_funcionarios.getText());
            
            // Obtenha o objeto EmpresaDTO selecionado na caixa de seleção
            int selectedIndex = empresa_funcionarios.getSelectedIndex();
            if (selectedIndex >= 0) {
                EmpresaDTO selectedEmpresa = empresas.get(selectedIndex);
                empresaDTO.setId_empresa(selectedEmpresa.getId_empresa());
                //System.out.println(selectedEmpresa.getId_empresa());
            }
            
            int selectedIndexHorario = horario_funcionarios.getSelectedIndex();
            if (selectedIndexHorario >= 0) {
                HorarioDTO selectedHorario = horarios.get(selectedIndexHorario);
                horarioDTO.setNumeroHorario(selectedHorario.getNumeroHorario());
            }
            
            int selectedIndexFuncao = funcao_funcionarios.getSelectedIndex();
            if (selectedIndexFuncao >= 0) {
                FuncaoDTO selectedFuncao = funcoes.get(selectedIndexFuncao);
                funcaoDTO.setId_funcao(selectedFuncao.getId_funcao());
            }

            //funcionariosDTO.setDepartamento_funcionarios(departamento_funcionarios.getSelectedItem().toString());
            
            Date admissaoDate = date_format.parse(admissao_funcionarios.getText().trim());
            funcionariosDTO.setAdmissao_funcionarios(admissaoDate);
            
            String dataString = demissao_funcionarios.getText().trim();
            if (dataString.isEmpty() || dataString.matches("^\\s*/\\s*/\\s*$")) {
                funcionariosDTO.setDemissao_funcionarios(null);
            } else {
                funcionariosDTO.setDemissao_funcionarios(date_format.parse(demissao_funcionarios.getText()));
            }
            
           
            JOptionPane.showMessageDialog(null, funcionarioCTR.inserirFuncionario(funcionariosDTO, empresaDTO, horarioDTO, funcaoDTO));
        }
        catch (Exception e){
            System.out.println("Erro ao gravar " + e.getMessage());
        }
    }
    
    public void preencherCampos(int idFuncionario){
        try{
            gravar_alterar = 2;
            funcionariosDTO.setId_funcionario(idFuncionario);
            rs = funcionarioCTR.consultarFuncionarios(funcionariosDTO, 3);
            if(rs.next()){
                n_folha_funcionarios.setText(rs.getString("n_folha"));
                nome_funcionarios.setText(rs.getString("nome_funcionario"));
                n_pis_funcionarios.setText(rs.getString("n_pis"));
                n_identificador_funcionarios.setText(rs.getString("n_identificador"));
                n_ctps_funcionarios.setText(rs.getString("n_ctps"));
                preencheEmpresaBox("", idFuncionario);
                preencheHorarioBox("", idFuncionario);
                preencheFuncaoBox("", idFuncionario);
                //departamento_funcionarios.setSelectedItem(rs.getString("departamento_funcionarios"));
                
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd");
                Date admissaoDate = inputFormat.parse(rs.getString("admissao_funcionarios"));
                admissao_funcionarios.setText(date_format.format(admissaoDate));

                String demissao = rs.getString("demissao_funcionarios");
                if (demissao != null) {
                    Date demissaoDate = inputFormat.parse(demissao);
                    demissao_funcionarios.setText(date_format.format(demissaoDate));
                } else {
                    demissao_funcionarios.setText("");
                }
                
                gravar_alterar = 2;
            }
        }
        catch (Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
        finally{
            funcionarioCTR.CloseDB();
        }
    }
    
    private void alterarFuncionario(){
        try{
            funcionariosDTO.setN_folha(Integer.parseInt(n_folha_funcionarios.getText()));
            funcionariosDTO.setNome_funcionario(nome_funcionarios.getText());
            funcionariosDTO.setN_pis(n_pis_funcionarios.getText());
            funcionariosDTO.setN_identificador(Integer.parseInt(n_identificador_funcionarios.getText()));
            funcionariosDTO.setN_ctps(n_identificador_funcionarios.getText());
            int selectedIndex = empresa_funcionarios.getSelectedIndex();
            if (selectedIndex >= 0) {
                EmpresaDTO selectedEmpresa = empresas.get(selectedIndex);
                empresaDTO.setId_empresa(selectedEmpresa.getId_empresa()); // Define o ID da empresa no DTO
            }
            int selectedIndexHorario = horario_funcionarios.getSelectedIndex();
            if (selectedIndexHorario >= 0) {
                HorarioDTO selectedHorario = horarios.get(selectedIndexHorario);
                horarioDTO.setNumeroHorario(selectedHorario.getNumeroHorario());
            }
            int selectedIndexFuncao = funcao_funcionarios.getSelectedIndex();
            if (selectedIndexFuncao >= 0) {
                FuncaoDTO selectedFuncao = funcoes.get(selectedIndexFuncao);
                funcaoDTO.setId_funcao(selectedFuncao.getId_funcao());
            }
            //funcionariosDTO.setDepartamento_funcionarios(departamento_funcionarios.getSelectedItem().toString());
            Date admissaoDate = date_format.parse(admissao_funcionarios.getText().trim());
            funcionariosDTO.setAdmissao_funcionarios(admissaoDate);
            
            String dataString = demissao_funcionarios.getText().trim();
            if (dataString.isEmpty() || dataString.matches("^\\s*/\\s*/\\s*$")) {
                funcionariosDTO.setDemissao_funcionarios(null);
            } else {
                funcionariosDTO.setDemissao_funcionarios(date_format.parse(demissao_funcionarios.getText()));
            }
            
            JOptionPane.showMessageDialog(null, funcionarioCTR.alterarFuncionarios(funcionariosDTO, empresaDTO, horarioDTO, funcaoDTO));
        }
        catch (Exception e){
            System.out.println("Erro ao alterar " + e.getMessage());
        }
    } 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        n_folha_funcionarios = new javax.swing.JTextField();
        nome_funcionarios = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        n_identificador_funcionarios = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        n_ctps_funcionarios = new javax.swing.JTextField();
        empresa_funcionarios = new javax.swing.JComboBox();
        horario_funcionarios = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        departamento_funcionarios = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        admissao_funcionarios = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        demissao_funcionarios = new javax.swing.JFormattedTextField();
        funcao_funcionarios = new javax.swing.JComboBox<>();
        n_pis_funcionarios = new javax.swing.JFormattedTextField();
        btnCancelarFuncionarios = new javax.swing.JButton();
        btnConcluirFuncionarios = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(718, 540));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Dados de Identificação");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Nº Folha");

        n_folha_funcionarios.setForeground(new java.awt.Color(0, 102, 153));
        n_folha_funcionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n_folha_funcionariosActionPerformed(evt);
            }
        });

        nome_funcionarios.setForeground(new java.awt.Color(0, 102, 153));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setText("Nº PIS/PASEP");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Nº Identificador");

        n_identificador_funcionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n_identificador_funcionariosActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Dados Genéricos");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 153));
        jLabel10.setText("Empresa");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setText("Horário");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 153));
        jLabel15.setText("Nome");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("CTPS");

        n_ctps_funcionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n_ctps_funcionariosActionPerformed(evt);
            }
        });

        empresa_funcionarios.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                empresa_funcionariosAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        empresa_funcionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empresa_funcionariosActionPerformed(evt);
            }
        });

        horario_funcionarios.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                horario_funcionariosAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 153));
        jLabel17.setText("Função");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 153));
        jLabel18.setText("Departamento");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 153));
        jLabel19.setText("Admissão");

        try {
            admissao_funcionarios.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        admissao_funcionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admissao_funcionariosActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Demissão");

        try {
            demissao_funcionarios.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        demissao_funcionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demissao_funcionariosActionPerformed(evt);
            }
        });

        funcao_funcionarios.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                funcao_funcionariosAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        try {
            n_pis_funcionarios.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.#####.##-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11))
                                    .addGap(113, 113, 113)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(horario_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(empresa_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(funcao_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel20)
                                            .addGap(109, 109, 109)
                                            .addComponent(demissao_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(155, 155, 155))
                                        .addComponent(jLabel18)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel19)
                                            .addGap(109, 109, 109)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(departamento_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(admissao_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(95, 95, 95)))
                            .addGap(146, 146, 146))
                        .addComponent(jLabel9)
                        .addComponent(jLabel3)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addComponent(jLabel15))
                            .addGap(71, 71, 71)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(n_folha_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(n_identificador_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nome_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(n_ctps_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(n_pis_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(n_folha_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(n_pis_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(n_identificador_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(n_ctps_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(empresa_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(horario_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(funcao_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(departamento_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(admissao_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(demissao_funcionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        btnCancelarFuncionarios.setText("Cancelar");
        btnCancelarFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarFuncionariosActionPerformed(evt);
            }
        });

        btnConcluirFuncionarios.setText("Concluir");
        btnConcluirFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConcluirFuncionariosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema_ponto/imagens/icon-funcionarios.png"))); // NOI18N
        jLabel1.setText("Funcionários |");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Incluir - Editar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnConcluirFuncionarios)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelarFuncionarios)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarFuncionarios)
                    .addComponent(btnConcluirFuncionarios))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void n_folha_funcionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n_folha_funcionariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_n_folha_funcionariosActionPerformed

    private void n_identificador_funcionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n_identificador_funcionariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_n_identificador_funcionariosActionPerformed

    private void btnCancelarFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarFuncionariosActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarFuncionariosActionPerformed

    private void btnConcluirFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConcluirFuncionariosActionPerformed
        if(gravar_alterar == 1){
            if (validarCampos()) {
                gravar();
                gravar_alterar = 0;
                this.dispose();
            }
        }
        else{
            if(gravar_alterar == 2){
                if (validarCampos()) {
                    alterarFuncionario();
                    gravar_alterar = 0;
                    this.dispose();
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Erro no sistema!!!");
            }
        }
    }//GEN-LAST:event_btnConcluirFuncionariosActionPerformed

    private void n_ctps_funcionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n_ctps_funcionariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_n_ctps_funcionariosActionPerformed

    private void admissao_funcionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admissao_funcionariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_admissao_funcionariosActionPerformed

    private void demissao_funcionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demissao_funcionariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_demissao_funcionariosActionPerformed

    private void empresa_funcionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empresa_funcionariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empresa_funcionariosActionPerformed

    private void empresa_funcionariosAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_empresa_funcionariosAncestorAdded
        if(gravar_alterar == 1){
            preencheEmpresaBox("", 0);
        }
    }//GEN-LAST:event_empresa_funcionariosAncestorAdded

    private void horario_funcionariosAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_horario_funcionariosAncestorAdded
        if(gravar_alterar == 1){
            preencheHorarioBox("", 0);
        }
    }//GEN-LAST:event_horario_funcionariosAncestorAdded

    private void funcao_funcionariosAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_funcao_funcionariosAncestorAdded
        if(gravar_alterar == 1){
            preencheFuncaoBox("", 0);
        }
    }//GEN-LAST:event_funcao_funcionariosAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField admissao_funcionarios;
    private javax.swing.JButton btnCancelarFuncionarios;
    private javax.swing.JButton btnConcluirFuncionarios;
    private javax.swing.JFormattedTextField demissao_funcionarios;
    private javax.swing.JComboBox<String> departamento_funcionarios;
    private javax.swing.JComboBox empresa_funcionarios;
    private javax.swing.JComboBox<String> funcao_funcionarios;
    private javax.swing.JComboBox<String> horario_funcionarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField n_ctps_funcionarios;
    private javax.swing.JTextField n_folha_funcionarios;
    private javax.swing.JTextField n_identificador_funcionarios;
    private javax.swing.JFormattedTextField n_pis_funcionarios;
    private javax.swing.JTextField nome_funcionarios;
    // End of variables declaration//GEN-END:variables
}