package br.com.sistema_ponto.view;

import javax.swing.JOptionPane;
import br.com.sistema_ponto.dto.UsuarioDTO;
import br.com.sistema_ponto.ctr.UsuarioCTR;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

public class LoginVIEW extends javax.swing.JFrame {
    UsuarioDTO usuarioDTO = new UsuarioDTO();
    UsuarioCTR usuarioCTR = new UsuarioCTR();

    public LoginVIEW() {
        initComponents();
        carregaImg();
        this.setLocationRelativeTo(null);      
    }
    
    private void carregaImg(){
        try {
            ImageIcon imageicon = new ImageIcon(getClass().getResource("../imagens/Fundo_Login.png"));
            Image image = imageicon.getImage();
            JDesktopPane desktopPane = new JDesktopPane() {
                @Override
                protected void paintComponent(Graphics graphics){
                    super.paintComponent(graphics);
                    graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                }
            };
            setContentPane(desktopPane);
            desktopPane.setLayout(null);
            addLoginComponents(desktopPane);
            setSize(475, 290);
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar a imagem: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addLoginComponents(JDesktopPane desktopPane) {  
        desktopPane.add(jLabel1);
        desktopPane.add(jLabel2);
        desktopPane.add(login_user);
        desktopPane.add(jLabel3);
        desktopPane.add(senha_user);
        desktopPane.add(btnLogar);
        desktopPane.add(btnCancelar);
    }
    
    private void logar(){
        usuarioDTO.setLogin_user(login_user.getText());
        usuarioDTO.setSenha_user(String.valueOf(senha_user.getPassword()));
        
        boolean loginSucesso = usuarioCTR.logarUsuario(usuarioDTO);
        
        if (loginSucesso) {
            int userId = usuarioCTR.obterIdUsuario(usuarioDTO.getLogin_user(), usuarioDTO.getSenha_user());

            // Atualiza o último login do usuário
            if (userId > 0) {
                usuarioCTR.atualizarUltimoLogin(userId);
            }

            this.dispose();
            PrincipalVIEW principalVIEW = new PrincipalVIEW();
            principalVIEW.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(null, "Login ou senha incorretos!!!");
        }
    }
    
    private boolean verificaPreenchimento(){
        if(login_user.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "O campo Login deve ser preenchido");
            login_user.requestFocus();
            return false;
        } else{
            if((senha_user.getPassword().length == 0)){
                JOptionPane.showMessageDialog(null, "O campo senha deve ser preenchido");
                senha_user.requestFocus();
                return false;
            }
            else{
                return true;
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        login_user = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        senha_user = new javax.swing.JPasswordField();
        btnLogar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Acesso ao Sistema");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Secullus Ponto Online");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Usuário");

        login_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_userActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Senha");

        btnLogar.setText("OK");
        btnLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(login_user)
                            .addComponent(senha_user, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(38, 38, 38))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(login_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senha_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnLogar))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogarActionPerformed
        if(verificaPreenchimento()){
            logar();
        }
    }//GEN-LAST:event_btnLogarActionPerformed

    private void login_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_login_userActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        UsuarioCTR usuarioCTR = new UsuarioCTR();
        if (!usuarioCTR.existemUsuarios()) {
            PrincipalVIEW principalVIEW = new PrincipalVIEW();
            principalVIEW.setVisible(true);
        } else {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new LoginVIEW().setVisible(true);
                }
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnLogar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField login_user;
    private javax.swing.JPasswordField senha_user;
    // End of variables declaration//GEN-END:variables

}
