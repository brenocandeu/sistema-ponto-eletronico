package br.com.sistema_ponto.view;

import javax.swing.JOptionPane;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import br.com.sistema_ponto.dto.UsuarioDTO;

public class PrincipalVIEW extends javax.swing.JFrame {
    public PrincipalVIEW() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("../imagens/imagem_de_fundo.png"));
        Image image = imageIcon.getImage();
        desktopPane = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics graphics){
                graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        menuBar = new javax.swing.JMenuBar();
        itemMenuEmmpresa = new javax.swing.JMenu();
        openEmpresa = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        openFuncionarios = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        openUsuario = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktopPane.setMinimumSize(new java.awt.Dimension(1280, 720));

        itemMenuEmmpresa.setMnemonic('f');
        itemMenuEmmpresa.setText("Cadastro");

        openEmpresa.setMnemonic('o');
        openEmpresa.setText("Empresa");
        openEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openEmpresaActionPerformed(evt);
            }
        });
        itemMenuEmmpresa.add(openEmpresa);

        jMenuItem1.setText("Horários");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        itemMenuEmmpresa.add(jMenuItem1);

        openFuncionarios.setText("Funcionários");
        openFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFuncionariosActionPerformed(evt);
            }
        });
        itemMenuEmmpresa.add(openFuncionarios);
        itemMenuEmmpresa.add(jSeparator1);

        jMenuItem2.setText("Funções");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        itemMenuEmmpresa.add(jMenuItem2);

        menuBar.add(itemMenuEmmpresa);

        jMenu1.setText("Manutenção");

        openUsuario.setText("Usuários");
        openUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openUsuarioActionPerformed(evt);
            }
        });
        jMenu1.add(openUsuario);

        menuBar.add(jMenu1);

        menuSair.setText("Sair");
        menuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSairMouseClicked(evt);
            }
        });
        menuBar.add(menuSair);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sair(){
        Object[] options = { "Sair", "Cancelar" };
        if(JOptionPane.showOptionDialog(null, "Deseja sair do sistema?", "Informação", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]) == 0){
            System.exit(0);
        }
    }
    
    private void abreEmpresaVIEW(){       
        CadastroEmpresaVIEW cadastroEmpresaVIEW = new CadastroEmpresaVIEW();
        this.desktopPane.add(cadastroEmpresaVIEW);
        cadastroEmpresaVIEW.setVisible(true);
        cadastroEmpresaVIEW.setPosicao();
    }
    
    private void abreFuncionariosVIEW(){
        FuncionariosVIEW funcionariosVIEW = new FuncionariosVIEW();
        this.desktopPane.add(funcionariosVIEW);
        funcionariosVIEW.setVisible(true);
        funcionariosVIEW.setPosicao();
    }
    
    private void abreUsuarioVIEW(){
        UsuariosVIEW usuariosVIEW = new UsuariosVIEW();
        this.desktopPane.add(usuariosVIEW);
        usuariosVIEW.setVisible(true);
        usuariosVIEW.setPosicao();
    }
    
    private void abreCadastroHorarioVIEW(){
        CadastroHorarioVIEW cadastroHorarioVIEW = new CadastroHorarioVIEW();
        this.desktopPane.add(cadastroHorarioVIEW);
        cadastroHorarioVIEW.setVisible(true);
        cadastroHorarioVIEW.setPosicao();
    }
    
    private void abreCadastroFuncaoVIEW(){
        CadastroFuncaoVIEW cadastroFuncaoVIEW = new CadastroFuncaoVIEW();
        this.desktopPane.add(cadastroFuncaoVIEW);
        cadastroFuncaoVIEW.setVisible(true);
        cadastroFuncaoVIEW.setPosicao();
    }
    
    private void openEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openEmpresaActionPerformed
        abreEmpresaVIEW();
    }//GEN-LAST:event_openEmpresaActionPerformed

    private void menuSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSairMouseClicked
        sair();
    }//GEN-LAST:event_menuSairMouseClicked

    private void openFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFuncionariosActionPerformed
        abreFuncionariosVIEW();
    }//GEN-LAST:event_openFuncionariosActionPerformed

    private void openUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openUsuarioActionPerformed
        abreUsuarioVIEW();
    }//GEN-LAST:event_openUsuarioActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        abreCadastroHorarioVIEW();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        abreCadastroFuncaoVIEW();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu itemMenuEmmpresa;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuSair;
    private javax.swing.JMenuItem openEmpresa;
    private javax.swing.JMenuItem openFuncionarios;
    private javax.swing.JMenuItem openUsuario;
    // End of variables declaration//GEN-END:variables

}
