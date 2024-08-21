package br.com.sistema_ponto.view;

import br.com.sistema_ponto.ctr.FuncionarioCTR;
import br.com.sistema_ponto.dto.FuncionariosDTO;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class FuncionariosVIEW extends javax.swing.JInternalFrame {
    FuncionariosDTO funcionariosDTO = new FuncionariosDTO();
    FuncionarioCTR funcionarioCTR = new FuncionarioCTR();
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_funcionarios;

    public FuncionariosVIEW() {
        initComponents();
        modelo_jtl_consultar_funcionarios = (DefaultTableModel) jtl_consultar_funcionarios.getModel();
        preencheTabelaFuncionarios("");
    }
    
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 10, (d.height - this.getSize().height) / 2);
    }
    
    public void preencheTabelaFuncionarios(String nome_funcionario){
        try{
            modelo_jtl_consultar_funcionarios.setNumRows(0);
            funcionariosDTO.setNome_funcionario(nome_funcionario);
            rs = funcionarioCTR.consultarFuncionarios(funcionariosDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_funcionarios.addRow(new Object[]{
                    rs.getString("nome_funcionario"),
                    rs.getString("n_folha"),
                    rs.getString("n_identificador"),
                    rs.getString("n_pis"),
                    rs.getString("departamento_funcionarios"),
                    rs.getString("id_funcionario"),
                });
            }
            modelo_jtl_consultar_funcionarios.fireTableDataChanged();
        }
        catch (Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
        finally{
            funcionarioCTR.CloseDB();
        }
    }
    
    private void excluir(int idFuncionario){
        if(JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse Funcionário?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            funcionariosDTO.setId_funcionario(idFuncionario);
            JOptionPane.showMessageDialog(null, funcionarioCTR.excluirFuncionario(funcionariosDTO));
            
            int rowIndex = jtl_consultar_funcionarios.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) jtl_consultar_funcionarios.getModel();
            model.removeRow(rowIndex); // Remove a linha da tabela
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnIncluirFuncionarios = new javax.swing.JButton();
        btnAlterarFuncionarios = new javax.swing.JButton();
        btnExcluirFuncionarios = new javax.swing.JButton();
        btnFecharFuncionarios = new javax.swing.JButton();
        btnAtualizarFuncionarios = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_funcionarios = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(718, 540));
        setPreferredSize(new java.awt.Dimension(718, 540));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema_ponto/imagens/icon-funcionarios.png"))); // NOI18N
        jLabel1.setText("Funcionários |");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Dados Gerais");

        btnIncluirFuncionarios.setText("Incluir");
        btnIncluirFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirFuncionariosActionPerformed(evt);
            }
        });

        btnAlterarFuncionarios.setText("Alterar");
        btnAlterarFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarFuncionariosActionPerformed(evt);
            }
        });

        btnExcluirFuncionarios.setText("Excluir");
        btnExcluirFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirFuncionariosActionPerformed(evt);
            }
        });

        btnFecharFuncionarios.setText("Fechar");
        btnFecharFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharFuncionariosActionPerformed(evt);
            }
        });

        btnAtualizarFuncionarios.setText("Atualizar");
        btnAtualizarFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarFuncionariosActionPerformed(evt);
            }
        });

        jtl_consultar_funcionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Nº Folha", "Nº Indentificador", "Nº PIS/PASEP", "Departamento", "ID Funcionario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_funcionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_funcionariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_funcionarios);
        if (jtl_consultar_funcionarios.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_funcionarios.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_funcionarios.getColumnModel().getColumn(1).setResizable(false);
            jtl_consultar_funcionarios.getColumnModel().getColumn(2).setResizable(false);
            jtl_consultar_funcionarios.getColumnModel().getColumn(3).setResizable(false);
            jtl_consultar_funcionarios.getColumnModel().getColumn(4).setResizable(false);
            jtl_consultar_funcionarios.getColumnModel().getColumn(5).setMinWidth(0);
            jtl_consultar_funcionarios.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addContainerGap(445, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnAlterarFuncionarios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExcluirFuncionarios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFecharFuncionarios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAtualizarFuncionarios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnIncluirFuncionarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)
                        .addGap(13, 13, 13))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIncluirFuncionarios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterarFuncionarios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluirFuncionarios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFecharFuncionarios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtualizarFuncionarios)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncluirFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirFuncionariosActionPerformed
        CadastroFuncionariosVIEW cadastroFuncionariosVIEW = new CadastroFuncionariosVIEW();
        this.getDesktopPane().add(cadastroFuncionariosVIEW);
        cadastroFuncionariosVIEW.setVisible(true);
        cadastroFuncionariosVIEW.setPosicao();
    }//GEN-LAST:event_btnIncluirFuncionariosActionPerformed

    private void btnAlterarFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarFuncionariosActionPerformed
        if(jtl_consultar_funcionarios.getSelectedRow() != -1){
            int rowIndex = jtl_consultar_funcionarios.getSelectedRow();

            int idFuncionario = Integer.parseInt(String.valueOf(jtl_consultar_funcionarios.getValueAt(rowIndex,5)));
            
            CadastroFuncionariosVIEW cadastroFuncionariosVIEW = new CadastroFuncionariosVIEW();
            cadastroFuncionariosVIEW.preencherCampos(idFuncionario);

            this.getDesktopPane().add(cadastroFuncionariosVIEW);
            cadastroFuncionariosVIEW.setVisible(true);
            cadastroFuncionariosVIEW.setPosicao();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um funcionário para alterar.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btnAlterarFuncionariosActionPerformed

    private void btnExcluirFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirFuncionariosActionPerformed
        if(jtl_consultar_funcionarios.getSelectedRow() != -1){
            int rowIndex = jtl_consultar_funcionarios.getSelectedRow();
            int idFuncionarios = Integer.parseInt(String.valueOf(jtl_consultar_funcionarios.getValueAt(rowIndex,5)));
            excluir(idFuncionarios);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um Funcionarios para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
          }
    }//GEN-LAST:event_btnExcluirFuncionariosActionPerformed

    private void btnFecharFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharFuncionariosActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharFuncionariosActionPerformed

    private void btnAtualizarFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarFuncionariosActionPerformed
        preencheTabelaFuncionarios("");
    }//GEN-LAST:event_btnAtualizarFuncionariosActionPerformed

    private void jtl_consultar_funcionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_funcionariosMouseClicked
        if (evt.getClickCount() == 2) {
            int rowIndex = jtl_consultar_funcionarios.getSelectedRow();
            if (rowIndex != -1) {
                btnAlterarFuncionariosActionPerformed(null);
            }
        }
    }//GEN-LAST:event_jtl_consultar_funcionariosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarFuncionarios;
    private javax.swing.JButton btnAtualizarFuncionarios;
    private javax.swing.JButton btnExcluirFuncionarios;
    private javax.swing.JButton btnFecharFuncionarios;
    private javax.swing.JButton btnIncluirFuncionarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_funcionarios;
    // End of variables declaration//GEN-END:variables
}
