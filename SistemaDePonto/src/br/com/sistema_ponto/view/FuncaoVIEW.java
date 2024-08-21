package br.com.sistema_ponto.view;

import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.sql.ResultSet;
import br.com.sistema_ponto.dto.FuncaoDTO;
import br.com.sistema_ponto.ctr.FuncaoCTR;

public class FuncaoVIEW extends javax.swing.JInternalFrame {
    
    FuncaoDTO funcaoDTO = new FuncaoDTO();
    FuncaoCTR funcaoCTR = new FuncaoCTR();
    
    ResultSet rs;
    
    private boolean validarCampos() {
        if (nome_funcao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    int gravar_alterar;
    private void gravar(){
        try{
            funcaoDTO.setNome_funcao(nome_funcao.getText());
            
            JOptionPane.showMessageDialog(null, funcaoCTR.inserirFuncao(funcaoDTO));
        }
        catch (Exception e){
            System.out.println("Erro ao gravar função " + e.getMessage());
        }
    }
    
    public void preencherCampos(int id_funcao){
        try{
            funcaoDTO.setId_funcao(id_funcao);
            rs = funcaoCTR.consultarFuncao(funcaoDTO, 2);
            if(rs.next()){
                nome_funcao.setText(rs.getString("nome_funcao"));
                
                gravar_alterar = 2;
            }
        }
        catch (Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
        finally{
            funcaoCTR.CloseDB();
        }
    }
    
    private void alterar(){
        try{
            funcaoDTO.setNome_funcao(nome_funcao.getText());
            
            JOptionPane.showMessageDialog(null, funcaoCTR.alterarFuncao(funcaoDTO));
        }
        catch (Exception e){
            System.out.println("Erro ao alterar função " + e.getMessage());
        }
    }

    public FuncaoVIEW() {
        initComponents();
        gravar_alterar = 1;
    }
    
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        nome_funcao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnConcluirFuncao = new javax.swing.JButton();
        btnCancelarFuncao = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(718, 540));
        setMinimumSize(new java.awt.Dimension(718, 540));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema_ponto/imagens/icon-funcao.png"))); // NOI18N
        jLabel1.setText("Funções |");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Incluir - Editar");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(682, 404));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Dados de Identificação");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Descrição");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Código");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("*");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(nome_funcao, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(248, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nome_funcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        btnConcluirFuncao.setText("Concluir");
        btnConcluirFuncao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConcluirFuncaoActionPerformed(evt);
            }
        });

        btnCancelarFuncao.setText("Cancelar");
        btnCancelarFuncao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarFuncaoActionPerformed(evt);
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
                                .addComponent(btnConcluirFuncao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelarFuncao)))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConcluirFuncao)
                    .addComponent(btnCancelarFuncao))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConcluirFuncaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConcluirFuncaoActionPerformed
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
    }//GEN-LAST:event_btnConcluirFuncaoActionPerformed

    private void btnCancelarFuncaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarFuncaoActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarFuncaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarFuncao;
    private javax.swing.JButton btnConcluirFuncao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nome_funcao;
    // End of variables declaration//GEN-END:variables
}
