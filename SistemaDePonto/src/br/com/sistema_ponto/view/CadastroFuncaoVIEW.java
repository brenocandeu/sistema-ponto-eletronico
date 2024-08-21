package br.com.sistema_ponto.view;


import br.com.sistema_ponto.dto.FuncaoDTO;
import br.com.sistema_ponto.ctr.FuncaoCTR;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class CadastroFuncaoVIEW extends javax.swing.JInternalFrame {
    
    FuncaoDTO funcaoDTO = new FuncaoDTO();
    FuncaoCTR funcaoCTR = new FuncaoCTR();
    
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_funcao;
    
    public CadastroFuncaoVIEW() {
        initComponents();
        
        modelo_jtl_consultar_funcao = (DefaultTableModel) jtl_consultar_funcao.getModel();        
        preencheTabela("");
    }
    
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 10, (d.height - this.getSize().height) / 2);
    }
    
    public void preencheTabela(String nome_funcao){
        try{
            modelo_jtl_consultar_funcao.setNumRows(0);
            funcaoDTO.setNome_funcao(nome_funcao);
            rs = funcaoCTR.consultarFuncao(funcaoDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_funcao.addRow(new Object[]{
                    rs.getString("nome_funcao"),
                    rs.getString("id_funcao"),
                });
            }
            modelo_jtl_consultar_funcao.fireTableDataChanged();
        }
        catch (Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
        finally{
            funcaoCTR.CloseDB();
        }
    }
    
    private void excluir(int id_funcao){
        if(JOptionPane.showConfirmDialog(null, "Deseja realmente excluir a Função?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            FuncaoDTO funcaoDTO = new FuncaoDTO();
            funcaoDTO.setId_funcao(id_funcao);
            JOptionPane.showMessageDialog(null, funcaoCTR.excluirFuncao(funcaoDTO));
            
            int rowIndex = jtl_consultar_funcao.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) jtl_consultar_funcao.getModel();
            model.removeRow(rowIndex); // Remove a linha da tabela
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAtualizarFuncao = new javax.swing.JButton();
        btnFecharFuncao = new javax.swing.JButton();
        btnExcluirFuncao = new javax.swing.JButton();
        btnAlterarFuncao = new javax.swing.JButton();
        btnIncluirFuncao = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_funcao = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(718, 557));
        setMinimumSize(new java.awt.Dimension(718, 557));

        btnAtualizarFuncao.setText("Atualizar");
        btnAtualizarFuncao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarFuncaoActionPerformed(evt);
            }
        });

        btnFecharFuncao.setText("Fechar");
        btnFecharFuncao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharFuncaoActionPerformed(evt);
            }
        });

        btnExcluirFuncao.setText("Excluir");
        btnExcluirFuncao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirFuncaoActionPerformed(evt);
            }
        });

        btnAlterarFuncao.setText("Alterar");
        btnAlterarFuncao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarFuncaoActionPerformed(evt);
            }
        });

        btnIncluirFuncao.setText("Incluir");
        btnIncluirFuncao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirFuncaoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema_ponto/imagens/icon-funcao.png"))); // NOI18N
        jLabel1.setText("Funções |");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Dados Gerais");

        jtl_consultar_funcao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "ID Funcao"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_funcao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_funcaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_funcao);
        if (jtl_consultar_funcao.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_funcao.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_funcao.getColumnModel().getColumn(1).setMinWidth(0);
            jtl_consultar_funcao.getColumnModel().getColumn(1).setMaxWidth(0);
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnFecharFuncao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(btnExcluirFuncao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAlterarFuncao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnIncluirFuncao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAtualizarFuncao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(btnIncluirFuncao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterarFuncao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluirFuncao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFecharFuncao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtualizarFuncao)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtualizarFuncaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarFuncaoActionPerformed
        preencheTabela("");
    }//GEN-LAST:event_btnAtualizarFuncaoActionPerformed

    private void btnFecharFuncaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharFuncaoActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharFuncaoActionPerformed

    private void btnExcluirFuncaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirFuncaoActionPerformed
        if(jtl_consultar_funcao.getSelectedRow() != -1){
            int rowIndex = jtl_consultar_funcao.getSelectedRow();
            int id_funcao = Integer.parseInt(String.valueOf(jtl_consultar_funcao.getValueAt(rowIndex,1)));
            excluir(id_funcao);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma função para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
          }
    }//GEN-LAST:event_btnExcluirFuncaoActionPerformed

    private void btnAlterarFuncaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarFuncaoActionPerformed
        if(jtl_consultar_funcao.getSelectedRow() != -1){
            int rowIndex = jtl_consultar_funcao.getSelectedRow(); // Obtém o índice da linha selecionada;;

            int id_funcao = Integer.parseInt(String.valueOf(jtl_consultar_funcao.getValueAt(rowIndex,1)));
            
            FuncaoVIEW funcaoVIEW = new FuncaoVIEW();            
            funcaoVIEW.preencherCampos(id_funcao);

            this.getDesktopPane().add(funcaoVIEW);
            funcaoVIEW.setVisible(true);
            funcaoVIEW.setPosicao();
        } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione uma função para alterar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAlterarFuncaoActionPerformed

    private void btnIncluirFuncaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirFuncaoActionPerformed
        FuncaoVIEW funcaoVIEW = new FuncaoVIEW();
        this.getDesktopPane().add(funcaoVIEW);
        funcaoVIEW.setVisible(true);
        funcaoVIEW.setPosicao();
    }//GEN-LAST:event_btnIncluirFuncaoActionPerformed

    private void jtl_consultar_funcaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_funcaoMouseClicked
        if (evt.getClickCount() == 2) {
            int rowIndex = jtl_consultar_funcao.getSelectedRow();
            if (rowIndex != -1) {
                btnAlterarFuncaoActionPerformed(null);
            }
        }
    }//GEN-LAST:event_jtl_consultar_funcaoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarFuncao;
    private javax.swing.JButton btnAtualizarFuncao;
    private javax.swing.JButton btnExcluirFuncao;
    private javax.swing.JButton btnFecharFuncao;
    private javax.swing.JButton btnIncluirFuncao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_funcao;
    // End of variables declaration//GEN-END:variables
}
