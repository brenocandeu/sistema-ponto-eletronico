package br.com.sistema_ponto.view;

import java.awt.Dimension;
import br.com.sistema_ponto.dto.HorarioDTO;
import br.com.sistema_ponto.ctr.HorarioCTR;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class CadastroHorarioVIEW extends javax.swing.JInternalFrame {
    
    HorarioDTO horarioDTO = new HorarioDTO();
    HorarioCTR horarioCTR = new HorarioCTR();
    
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_horario;

    public CadastroHorarioVIEW() {
        initComponents();
        
        modelo_jtl_consultar_horario = (DefaultTableModel) jtl_consultar_horario.getModel();        
        preencheTabela("");
    }
    
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 10, (d.height - this.getSize().height) / 2);
    }
    
    public void preencheTabela(String descricao_horario){
        try{
            modelo_jtl_consultar_horario.setNumRows(0);
            horarioDTO.setDescricaoHorario(descricao_horario);
            rs = horarioCTR.consultarHorario(horarioDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_horario.addRow(new Object[]{
                    rs.getString("numero_horario"),
                    rs.getString("descricao_horario"),
                });
            }
            modelo_jtl_consultar_horario.fireTableDataChanged();
        }
        catch (Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
        finally{
            horarioCTR.CloseDB();
        }
    }
    
    private void excluir(int numero_horario){
        if(JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o Horário?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            HorarioDTO horarioDTO = new HorarioDTO();
            horarioDTO.setNumeroHorario(numero_horario);
            JOptionPane.showMessageDialog(null, horarioCTR.excluirHorario(horarioDTO));
            
            int rowIndex = jtl_consultar_horario.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) jtl_consultar_horario.getModel();
            model.removeRow(rowIndex); // Remove a linha da tabela
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIncluirHorario = new javax.swing.JButton();
        btnAlterarHorario = new javax.swing.JButton();
        btnExcluirHorario = new javax.swing.JButton();
        btnFecharHorario = new javax.swing.JButton();
        btnAtualizarHorario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_horario = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(718, 557));
        setMinimumSize(new java.awt.Dimension(718, 557));

        btnIncluirHorario.setText("Incluir");
        btnIncluirHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirHorarioActionPerformed(evt);
            }
        });

        btnAlterarHorario.setText("Alterar");
        btnAlterarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarHorarioActionPerformed(evt);
            }
        });

        btnExcluirHorario.setText("Excluir");
        btnExcluirHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirHorarioActionPerformed(evt);
            }
        });

        btnFecharHorario.setText("Fechar");
        btnFecharHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharHorarioActionPerformed(evt);
            }
        });

        btnAtualizarHorario.setText("Atualizar");
        btnAtualizarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarHorarioActionPerformed(evt);
            }
        });

        jtl_consultar_horario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Horário", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_horario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_horarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_horario);
        if (jtl_consultar_horario.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_horario.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_horario.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema_ponto/imagens/Icon-Harario.png"))); // NOI18N
        jLabel1.setText("Horários |");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Dados Gerais");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnIncluirHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAlterarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExcluirHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFecharHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAtualizarHorario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
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
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIncluirHorario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterarHorario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluirHorario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFecharHorario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtualizarHorario)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncluirHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirHorarioActionPerformed
        HorarioVIEW horarioVIEW = new HorarioVIEW();
        this.getDesktopPane().add(horarioVIEW);
        horarioVIEW.setVisible(true);
        horarioVIEW.setPosicao();
    }//GEN-LAST:event_btnIncluirHorarioActionPerformed

    private void btnFecharHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharHorarioActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharHorarioActionPerformed

    private void btnAlterarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarHorarioActionPerformed
        if(jtl_consultar_horario.getSelectedRow() != -1){
            int rowIndex = jtl_consultar_horario.getSelectedRow();

            int numero_horario = Integer.parseInt(String.valueOf(jtl_consultar_horario.getValueAt(rowIndex,0)));
            
            HorarioVIEW horarioVIEW = new HorarioVIEW();            
            horarioVIEW.preencherCampos(numero_horario);

            this.getDesktopPane().add(horarioVIEW);
            horarioVIEW.setVisible(true);
            horarioVIEW.setPosicao();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um horario para alterar.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btnAlterarHorarioActionPerformed

    private void jtl_consultar_horarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_horarioMouseClicked
        if (evt.getClickCount() == 2) {
            int rowIndex = jtl_consultar_horario.getSelectedRow();
            if (rowIndex != -1) {
                btnAlterarHorarioActionPerformed(null);
            }
        }
    }//GEN-LAST:event_jtl_consultar_horarioMouseClicked

    private void btnExcluirHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirHorarioActionPerformed
        if(jtl_consultar_horario.getSelectedRow() != -1){
            int rowIndex = jtl_consultar_horario.getSelectedRow();
            int numero_horario = Integer.parseInt(String.valueOf(jtl_consultar_horario.getValueAt(rowIndex,0)));
            excluir(numero_horario);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma empresa para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
          }
    }//GEN-LAST:event_btnExcluirHorarioActionPerformed

    private void btnAtualizarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarHorarioActionPerformed
        preencheTabela("");
    }//GEN-LAST:event_btnAtualizarHorarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarHorario;
    private javax.swing.JButton btnAtualizarHorario;
    private javax.swing.JButton btnExcluirHorario;
    private javax.swing.JButton btnFecharHorario;
    private javax.swing.JButton btnIncluirHorario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_horario;
    // End of variables declaration//GEN-END:variables
}
