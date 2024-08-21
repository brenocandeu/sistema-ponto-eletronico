/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema_ponto.view;
import br.com.sistema_ponto.dto.EmpresaDTO;
import br.com.sistema_ponto.ctr.EmpresaCTR;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aluno
 */
public class CadastroEmpresaVIEW extends javax.swing.JInternalFrame {
    
    EmpresaDTO empresaDTO = new EmpresaDTO();
    EmpresaCTR empresaCTR = new EmpresaCTR();
    
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_empresa;
    

    /**
     * Creates new form CadastroEmpresaVIEW
     */
    public CadastroEmpresaVIEW() {
        initComponents();
        
        modelo_jtl_consultar_empresa = (DefaultTableModel) jtl_consultar_empresa.getModel();        
        preencheTabela("");
    }
    
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 10, (d.height - this.getSize().height) / 2);
    }
    
    public void preencheTabela(String nome_empresa){
        try{
            modelo_jtl_consultar_empresa.setNumRows(0);
            empresaDTO.setNome_empresa(nome_empresa);
            rs = empresaCTR.consultarEmpresa(empresaDTO, 1);
            while(rs.next()){
                System.out.println("Nome da empresa: " + rs.getString("nome_empresa"));
                System.out.println("CNPJ da empresa: " + rs.getString("cnpj_empresa"));
                modelo_jtl_consultar_empresa.addRow(new Object[]{
                    rs.getString("nome_empresa"),
                    rs.getString("cnpj_empresa"),
                    rs.getString("inscricacao_empresa"),
                    rs.getString("id_empresa"),
                });
            }
            modelo_jtl_consultar_empresa.fireTableDataChanged();
        }
        catch (Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
        finally{
            empresaCTR.CloseDB();
        }
    }
    
    private void excluir(int idEmpresa){
        if(JOptionPane.showConfirmDialog(null, "Deseja realmente excluir a Empresa?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            EmpresaDTO empresaDTO = new EmpresaDTO();
            empresaDTO.setId_empresa(idEmpresa);
            JOptionPane.showMessageDialog(null, empresaCTR.excluirEmpresa(empresaDTO));
            
            int rowIndex = jtl_consultar_empresa.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) jtl_consultar_empresa.getModel();
            model.removeRow(rowIndex); // Remove a linha da tabela
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnIncluirEmpresa = new javax.swing.JButton();
        btnAlterarEmpresa = new javax.swing.JButton();
        btnExcluirEmpresa = new javax.swing.JButton();
        btnFecharEmpresa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_empresa = new javax.swing.JTable();
        btnAtualizarEmpresa = new javax.swing.JButton();

        setTitle("Empresas");
        setMinimumSize(new java.awt.Dimension(718, 557));
        setPreferredSize(new java.awt.Dimension(718, 557));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema_ponto/imagens/icon-empresa.png"))); // NOI18N
        jLabel1.setText(" Empresas |");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Dados Gerais");

        btnIncluirEmpresa.setText("Incluir");
        btnIncluirEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirEmpresaActionPerformed(evt);
            }
        });

        btnAlterarEmpresa.setText("Alterar");
        btnAlterarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarEmpresaActionPerformed(evt);
            }
        });

        btnExcluirEmpresa.setText("Excluir");
        btnExcluirEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirEmpresaActionPerformed(evt);
            }
        });

        btnFecharEmpresa.setText("Fechar");
        btnFecharEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharEmpresaActionPerformed(evt);
            }
        });

        jtl_consultar_empresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CNPJ", "Inscrições Estadual", "ID EMPRESA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_empresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_empresaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_empresa);
        if (jtl_consultar_empresa.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_empresa.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_empresa.getColumnModel().getColumn(1).setResizable(false);
            jtl_consultar_empresa.getColumnModel().getColumn(2).setResizable(false);
            jtl_consultar_empresa.getColumnModel().getColumn(3).setMinWidth(0);
            jtl_consultar_empresa.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        btnAtualizarEmpresa.setText("Atualizar");
        btnAtualizarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarEmpresaActionPerformed(evt);
            }
        });

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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnExcluirEmpresa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAlterarEmpresa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnIncluirEmpresa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAtualizarEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(btnFecharEmpresa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)))
                .addContainerGap())
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
                        .addComponent(btnIncluirEmpresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterarEmpresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluirEmpresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFecharEmpresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtualizarEmpresa)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlterarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarEmpresaActionPerformed
        if(jtl_consultar_empresa.getSelectedRow() != -1){
            int rowIndex = jtl_consultar_empresa.getSelectedRow(); // Obtém o índice da linha selecionada;;

            int idEmpresa = Integer.parseInt(String.valueOf(jtl_consultar_empresa.getValueAt(rowIndex,3)));
            
            EmpresaVIEW empresaVIEW = new EmpresaVIEW();            
            empresaVIEW.preencherCampos(idEmpresa);

            this.getDesktopPane().add(empresaVIEW);
            empresaVIEW.setVisible(true);
            empresaVIEW.setPosicao();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione uma empresa para alterar.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btnAlterarEmpresaActionPerformed

    private void btnExcluirEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirEmpresaActionPerformed
        if(jtl_consultar_empresa.getSelectedRow() != -1){
            int rowIndex = jtl_consultar_empresa.getSelectedRow();
            int idEmpresa = Integer.parseInt(String.valueOf(jtl_consultar_empresa.getValueAt(rowIndex,3)));
            excluir(idEmpresa);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma empresa para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
          }
    }//GEN-LAST:event_btnExcluirEmpresaActionPerformed

    private void btnIncluirEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirEmpresaActionPerformed
        EmpresaVIEW empresaVIEW = new EmpresaVIEW();
        this.getDesktopPane().add(empresaVIEW);
        empresaVIEW.setVisible(true);
        empresaVIEW.setPosicao();
    }//GEN-LAST:event_btnIncluirEmpresaActionPerformed

    private void jtl_consultar_empresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_empresaMouseClicked
        if (evt.getClickCount() == 2) {
            int rowIndex = jtl_consultar_empresa.getSelectedRow();
            if (rowIndex != -1) {
                btnAlterarEmpresaActionPerformed(null);
            }
        }
    }//GEN-LAST:event_jtl_consultar_empresaMouseClicked

    private void btnFecharEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharEmpresaActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharEmpresaActionPerformed

    private void btnAtualizarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarEmpresaActionPerformed
        preencheTabela("");
    }//GEN-LAST:event_btnAtualizarEmpresaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarEmpresa;
    private javax.swing.JButton btnAtualizarEmpresa;
    private javax.swing.JButton btnExcluirEmpresa;
    private javax.swing.JButton btnFecharEmpresa;
    private javax.swing.JButton btnIncluirEmpresa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_empresa;
    // End of variables declaration//GEN-END:variables
}
