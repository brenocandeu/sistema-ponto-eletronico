/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema_ponto.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.sistema_ponto.dto.UsuarioDTO;
import br.com.sistema_ponto.ctr.UsuarioCTR;

/**
 *
 * @author Breno
 */
public class CadastroUsuarioVIEW extends javax.swing.JInternalFrame {
    
    UsuarioDTO usuarioDTO = new UsuarioDTO();
    UsuarioCTR usuarioCTR = new UsuarioCTR();
    
    int gravar_alterar;
    
    ResultSet rs;
    
    private boolean validarCampos() {
        if (nome_user.getText().isEmpty() || cpf_user.getText().isEmpty() || login_user.getText().isEmpty() ||
            senha_user.getPassword() == null) {

            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);

            return false;
        }

        return true;
    }
    
    private void gravar(){
        try{
            usuarioDTO.setNome_user(nome_user.getText());
            usuarioDTO.setCpf_user(cpf_user.getText());
            usuarioDTO.setLgpd(checkAlteracaoPonto.isSelected());
            usuarioDTO.setLogin_user(login_user.getText());
            
            if((checkAlterarSenha.isSelected()) && (senha_user.getPassword().length != 0)){
                usuarioDTO.setSenha_user(String.valueOf(senha_user.getPassword()));
            } else{
                usuarioDTO.setSenha_user(null);
            }
            
            JOptionPane.showMessageDialog(null, usuarioCTR.inserirUsuario(usuarioDTO));
        }
        catch (Exception e){
            System.out.println("Erro ao gravar " + e.getMessage());
        }
    }
    
    public void preencherCampos(int idUsuario){
        try{
            usuarioDTO.setId_user(idUsuario);
            rs = usuarioCTR.consultarUsuario(usuarioDTO, 2);
            if(rs.next()){        
                nome_user.setText(rs.getString("nome_user"));
                cpf_user.setText(rs.getString("cpf_user"));
                login_user.setText(rs.getString("login_user"));
                boolean lgpd = rs.getBoolean("lgpd");
                checkAlteracaoPonto.setSelected(lgpd);
             
                gravar_alterar = 2;
                
                senha_user.setEnabled(false);
                checkAlterarSenha.setSelected(false);
            }
        }
        catch (Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
        finally{
            usuarioCTR.CloseDB();
        }
    }
    
    private void alterar(){
        try {
            usuarioDTO.setNome_user(nome_user.getText());
            usuarioDTO.setCpf_user(cpf_user.getText());
            usuarioDTO.setLgpd(checkAlteracaoPonto.isSelected());
            usuarioDTO.setLogin_user(login_user.getText());
            
            if((checkAlterarSenha.isSelected()) && (senha_user.getPassword().length != 0)){
                usuarioDTO.setSenha_user(String.valueOf(senha_user.getPassword()));
            } else{
                usuarioDTO.setSenha_user(null);
            }
            
            JOptionPane.showMessageDialog(null, usuarioCTR.alterarUsuario(usuarioDTO));
        } catch (Exception e) {
            System.out.println("Erro ao Alterar " + e.getMessage());
        }
    }
   
    public CadastroUsuarioVIEW() {
        initComponents();
        gravar_alterar = 1;
        senha_user.setEnabled(false);
    }
    
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nome_user = new javax.swing.JTextField();
        login_user = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        checkAdm = new javax.swing.JCheckBox();
        checkBloqueado = new javax.swing.JCheckBox();
        checkAlteracaoPonto = new javax.swing.JCheckBox();
        checkDesativado = new javax.swing.JCheckBox();
        cpf_user = new javax.swing.JFormattedTextField();
        checkAlterarSenha = new javax.swing.JCheckBox();
        senha_user = new javax.swing.JPasswordField();
        btnConcluirUser = new javax.swing.JButton();
        btnCancelarUser = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(718, 557));
        setMinimumSize(new java.awt.Dimension(718, 557));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Nome");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("CPF");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Login");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Senha");

        nome_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nome_userActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Dados de Identificação");

        checkAdm.setBackground(new java.awt.Color(255, 255, 255));
        checkAdm.setText("Administrador");

        checkBloqueado.setBackground(new java.awt.Color(255, 255, 255));
        checkBloqueado.setText("Bloqueado");
        checkBloqueado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBloqueadoActionPerformed(evt);
            }
        });

        checkAlteracaoPonto.setBackground(new java.awt.Color(255, 255, 255));
        checkAlteracaoPonto.setText("Permitir Alteração de Ponto");

        checkDesativado.setBackground(new java.awt.Color(255, 255, 255));
        checkDesativado.setText("Desativado");

        try {
            cpf_user.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        checkAlterarSenha.setBackground(new java.awt.Color(255, 255, 255));
        checkAlterarSenha.setText("Alterar Senha");
        checkAlterarSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkAlterarSenhaMouseClicked(evt);
            }
        });

        senha_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                senha_userActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(login_user, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nome_user, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cpf_user, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkBloqueado)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(checkAdm)
                                .addGap(18, 18, 18)
                                .addComponent(checkAlteracaoPonto))
                            .addComponent(checkDesativado)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(senha_user, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(checkAlterarSenha)))))
                .addContainerGap(231, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nome_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cpf_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(login_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(senha_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkAlterarSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkAdm)
                    .addComponent(checkAlteracaoPonto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkBloqueado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkDesativado)
                .addGap(17, 17, 17))
        );

        btnConcluirUser.setText("Concluir");
        btnConcluirUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConcluirUserActionPerformed(evt);
            }
        });

        btnCancelarUser.setText("Cancelar");
        btnCancelarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarUserActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema_ponto/imagens/icon-key.png"))); // NOI18N
        jLabel7.setText("Usuários |");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Incluir - Editar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnConcluirUser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConcluirUser)
                    .addComponent(btnCancelarUser))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarUserActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarUserActionPerformed

    private void checkBloqueadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBloqueadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBloqueadoActionPerformed

    private void nome_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nome_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nome_userActionPerformed

    private void senha_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_senha_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_senha_userActionPerformed

    private void btnConcluirUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConcluirUserActionPerformed
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
    }//GEN-LAST:event_btnConcluirUserActionPerformed

    private void checkAlterarSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkAlterarSenhaMouseClicked
        if(checkAlterarSenha.isSelected()){
            senha_user.setEnabled(true);
        } else{
            senha_user.setEnabled(false);
            senha_user.setText(null);
        }
    }//GEN-LAST:event_checkAlterarSenhaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarUser;
    private javax.swing.JButton btnConcluirUser;
    private javax.swing.JCheckBox checkAdm;
    private javax.swing.JCheckBox checkAlteracaoPonto;
    private javax.swing.JCheckBox checkAlterarSenha;
    private javax.swing.JCheckBox checkBloqueado;
    private javax.swing.JCheckBox checkDesativado;
    private javax.swing.JFormattedTextField cpf_user;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField login_user;
    private javax.swing.JTextField nome_user;
    private javax.swing.JPasswordField senha_user;
    // End of variables declaration//GEN-END:variables
}
