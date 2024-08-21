/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema_ponto.view;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.sql.ResultSet;
import br.com.sistema_ponto.dto.EmpresaDTO;
import br.com.sistema_ponto.ctr.EmpresaCTR;

/**
 *
 * @author Breno
 */
public class EmpresaVIEW extends javax.swing.JInternalFrame {
    
    EmpresaDTO empresaDTO = new EmpresaDTO();
    EmpresaCTR empresaCTR = new EmpresaCTR();
    
    ResultSet rs;
    
    private boolean validarCampos() {
        if (nome_empresa.getText().isEmpty() || cnpj_empresa.getText().isEmpty() || inscricacao_empresa.getText().isEmpty() ||
            nome_responsavel.getText().isEmpty() || cargo_responsavel.getText().isEmpty() || logradouro_empresa.getText().isEmpty() ||
            bairro_empresa.getText().isEmpty() || cidade_empresa.getText().isEmpty() || cep_empresa.getText().isEmpty() ||
            estado_empresa.getSelectedItem().toString().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!validarCNPJ(cnpj_empresa.getText())) {
            JOptionPane.showMessageDialog(this, "CNPJ inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    
    private boolean validarCNPJ(String cnpj) {
        // Remove caracteres não numéricos
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verifica se o CNPJ possui 14 dígitos
        if (cnpj.length() != 14) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        char primeiroDigito = cnpj.charAt(0);
        boolean todosIguais = true;
        for (char c : cnpj.toCharArray()) {
            if (c != primeiroDigito) {
                todosIguais = false;
                break;
            }
        }
        if (todosIguais) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 12; i++) {
            try {
                soma += Character.getNumericValue(cnpj.charAt(i)) * pesos1[i];
            } catch (NumberFormatException e) {
                return false; // Caso o caractere não seja um dígito numérico
            }
        }
        int digito1 = 11 - (soma % 11);
        digito1 = (digito1 >= 10) ? 0 : digito1;

        // Calcula o segundo dígito verificador
        soma = 0;
        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 13; i++) {
            try {
                soma += Character.getNumericValue(cnpj.charAt(i)) * pesos2[i];
            } catch (NumberFormatException e) {
                return false; // Caso o caractere não seja um dígito numérico
            }
        }
        int digito2 = 11 - (soma % 11);
        digito2 = (digito2 >= 10) ? 0 : digito2;

        // Verifica se os dígitos verificadores estão corretos
        return cnpj.endsWith(String.valueOf(digito1) + digito2);
    }
    
    int gravar_alterar;
    private void gravar(){
        try{
            empresaDTO.setNome_empresa(nome_empresa.getText());
            empresaDTO.setCnpj_empresa(cnpj_empresa.getText());
            empresaDTO.setInscricacao_empresa(inscricacao_empresa.getText());
            empresaDTO.setNome_responsavel(nome_responsavel.getText());
            empresaDTO.setCargo_responsavel(cargo_responsavel.getText());
            empresaDTO.setLogradouro_empresa(logradouro_empresa.getText());
            empresaDTO.setBairro_empresa(bairro_empresa.getText());
            empresaDTO.setCidade_empresa(cidade_empresa.getText());
            empresaDTO.setCep_empresa(cep_empresa.getText());
            empresaDTO.setEstado_empresa(estado_empresa.getSelectedItem().toString());
            
            JOptionPane.showMessageDialog(null, empresaCTR.inserirEmpresa(empresaDTO));
        }
        catch (Exception e){
            System.out.println("Erro ao gravar " + e.getMessage());
        }
    }
    
    public void preencherCampos(int idEmpresa){
        try{
            empresaDTO.setId_empresa(idEmpresa);
            rs = empresaCTR.consultarEmpresa(empresaDTO, 3);
            if(rs.next()){        
                nome_empresa.setText(rs.getString("nome_empresa"));
                cnpj_empresa.setText(rs.getString("cnpj_empresa"));
                inscricacao_empresa.setText(rs.getString("inscricacao_empresa"));
                nome_responsavel.setText(rs.getString("nome_responsavel"));
                cargo_responsavel.setText(rs.getString("cargo_responsavel"));
                logradouro_empresa.setText(rs.getString("logradouro_empresa"));
                bairro_empresa.setText(rs.getString("bairro_empresa"));
                cidade_empresa.setText(rs.getString("cidade_empresa"));
                cep_empresa.setText(rs.getString("cep_empresa"));
                estado_empresa.setSelectedItem(rs.getString("estado_empresa"));
                
                gravar_alterar = 2;
            }
        }
        catch (Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
        finally{
            empresaCTR.CloseDB();
        }
    }
    
    private void alterar(){
        try{
            empresaDTO.setNome_empresa(nome_empresa.getText());
            empresaDTO.setCnpj_empresa(cnpj_empresa.getText());
            empresaDTO.setInscricacao_empresa(inscricacao_empresa.getText());
            empresaDTO.setNome_responsavel(nome_responsavel.getText());
            empresaDTO.setCargo_responsavel(cargo_responsavel.getText());
            empresaDTO.setLogradouro_empresa(logradouro_empresa.getText());
            empresaDTO.setBairro_empresa(bairro_empresa.getText());
            empresaDTO.setCidade_empresa(cidade_empresa.getText());
            empresaDTO.setCep_empresa(cep_empresa.getText());
            empresaDTO.setEstado_empresa(estado_empresa.getSelectedItem().toString());
            
            JOptionPane.showMessageDialog(null, empresaCTR.alterarEmpresa(empresaDTO));
        }
        catch (Exception e){
            System.out.println("Erro ao alterar " + e.getMessage());
        }
    }
    
    public EmpresaVIEW() {
        initComponents();
        gravar_alterar = 1;
    }
    
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nome_empresa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        inscricacao_empresa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nome_responsavel = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cargo_responsavel = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        logradouro_empresa = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        bairro_empresa = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cidade_empresa = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cep_empresa = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        estado_empresa = new javax.swing.JComboBox<>();
        cnpj_empresa = new javax.swing.JFormattedTextField();
        btnConcluirEmpresa = new javax.swing.JButton();
        btnCancelarEmpresa = new javax.swing.JButton();

        setBackground(new java.awt.Color(248, 248, 248));
        setTitle("Empresas");
        setMinimumSize(new java.awt.Dimension(718, 540));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema_ponto/imagens/icon-empresa.png"))); // NOI18N
        jLabel1.setText(" Empresas |");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Incluir - Editar");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Dados de Identificação");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setText("Empresas:");

        nome_empresa.setForeground(new java.awt.Color(0, 102, 153));
        nome_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nome_empresaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Inscrição:");

        inscricacao_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inscricacao_empresaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Dados Genéricos");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Nome:");

        nome_responsavel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nome_responsavelActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Cargo:");

        cargo_responsavel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargo_responsavelActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Responsável");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 153));
        jLabel10.setText("Endereço:");

        logradouro_empresa.setForeground(new java.awt.Color(0, 102, 153));
        logradouro_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logradouro_empresaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setText("Bairro:");

        bairro_empresa.setForeground(new java.awt.Color(0, 102, 153));
        bairro_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bairro_empresaActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 153));
        jLabel12.setText("Cidade:");

        cidade_empresa.setForeground(new java.awt.Color(0, 102, 153));
        cidade_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cidade_empresaActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("CEP:");

        cep_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cep_empresaActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 153));
        jLabel14.setText("Estado:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 153));
        jLabel15.setText("CNPJ:");

        estado_empresa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        estado_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estado_empresaActionPerformed(evt);
            }
        });

        try {
            cnpj_empresa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
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
                    .addComponent(jLabel9)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addComponent(jLabel15))
                            .addGap(64, 64, 64)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cargo_responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nome_responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nome_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cnpj_empresa, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inscricacao_empresa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(logradouro_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bairro_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13)
                                .addComponent(jLabel14))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cep_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cidade_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(estado_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(170, 170, 170))))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nome_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cnpj_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inscricacao_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(nome_responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cargo_responsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(logradouro_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(bairro_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cidade_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cep_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(estado_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        btnConcluirEmpresa.setText("Concluir");
        btnConcluirEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConcluirEmpresaActionPerformed(evt);
            }
        });

        btnCancelarEmpresa.setText("Cancelar");
        btnCancelarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarEmpresaActionPerformed(evt);
            }
        });

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
                                .addComponent(btnConcluirEmpresa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelarEmpresa)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelarEmpresa)
                    .addComponent(btnConcluirEmpresa))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nome_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nome_empresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nome_empresaActionPerformed

    private void inscricacao_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inscricacao_empresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inscricacao_empresaActionPerformed

    private void nome_responsavelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nome_responsavelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nome_responsavelActionPerformed

    private void cargo_responsavelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargo_responsavelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cargo_responsavelActionPerformed

    private void logradouro_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logradouro_empresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logradouro_empresaActionPerformed

    private void bairro_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bairro_empresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bairro_empresaActionPerformed

    private void cidade_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cidade_empresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cidade_empresaActionPerformed

    private void cep_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cep_empresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cep_empresaActionPerformed

    private void btnConcluirEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConcluirEmpresaActionPerformed
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
                    alterar();
                    gravar_alterar = 0;
                    this.dispose();
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Erro no sistema!!!");
            }
        }
    }//GEN-LAST:event_btnConcluirEmpresaActionPerformed

    private void btnCancelarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEmpresaActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarEmpresaActionPerformed

    private void estado_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estado_empresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estado_empresaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bairro_empresa;
    private javax.swing.JButton btnCancelarEmpresa;
    private javax.swing.JButton btnConcluirEmpresa;
    private javax.swing.JTextField cargo_responsavel;
    private javax.swing.JTextField cep_empresa;
    private javax.swing.JTextField cidade_empresa;
    private javax.swing.JFormattedTextField cnpj_empresa;
    private javax.swing.JComboBox<String> estado_empresa;
    private javax.swing.JTextField inscricacao_empresa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField logradouro_empresa;
    private javax.swing.JTextField nome_empresa;
    private javax.swing.JTextField nome_responsavel;
    // End of variables declaration//GEN-END:variables
}
