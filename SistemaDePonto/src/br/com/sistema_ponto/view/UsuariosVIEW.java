package br.com.sistema_ponto.view;

import br.com.sistema_ponto.ctr.UsuarioCTR;
import br.com.sistema_ponto.dto.UsuarioDTO;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class UsuariosVIEW extends javax.swing.JInternalFrame {
    
    UsuarioDTO usuarioDTO = new UsuarioDTO();
    UsuarioCTR usuarioCTR = new UsuarioCTR();
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_usuarios;

    public UsuariosVIEW() {
        initComponents();
        modelo_jtl_consultar_usuarios = (DefaultTableModel) jtl_consultar_usuarios.getModel();
        preencheTabelaUsuarios("");
    }
    
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 10, (d.height - this.getSize().height) / 2);
    }
    
    public void preencheTabelaUsuarios(String nome_user){
        try{
            modelo_jtl_consultar_usuarios.setNumRows(0);
            usuarioDTO.setNome_user(nome_user);
            rs = usuarioCTR.consultarUsuario(usuarioDTO, 1);
            while(rs.next()){
                java.sql.Timestamp ultimoLogin = rs.getTimestamp("ultimo_login");
                String ultimoLoginFormatado = (ultimoLogin != null) 
                    ? new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(ultimoLogin) 
                    : "Nunca fez login";
                
                boolean lgpd = rs.getBoolean("lgpd");
                
                modelo_jtl_consultar_usuarios.addRow(new Object[]{
                    rs.getString("nome_user"),
                    ultimoLoginFormatado,
                    lgpd,
                    rs.getString("id_user"),
                });
            }
            modelo_jtl_consultar_usuarios.fireTableDataChanged();
        }
        catch (Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
        finally{
            usuarioCTR.CloseDB();
        }
    }
    
    private void excluir(int idUsuario){
        if(JOptionPane.showConfirmDialog(null, "Deseja realmente excluir a Usuário?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            usuarioDTO.setId_user(idUsuario);
            JOptionPane.showMessageDialog(null, usuarioCTR.excluirUsuario(usuarioDTO));
            
            int rowIndex = jtl_consultar_usuarios.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) jtl_consultar_usuarios.getModel();
            model.removeRow(rowIndex); // Remove a linha da tabela
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_usuarios = new javax.swing.JTable();
        btnAtualizarUsuario = new javax.swing.JButton();
        btnFecharUsuario = new javax.swing.JButton();
        btnExcluirUsuario = new javax.swing.JButton();
        btnAlterarUsuario = new javax.swing.JButton();
        btnIncluirUsuario = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(718, 540));
        setMinimumSize(new java.awt.Dimension(718, 540));

        jtl_consultar_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Último Login", "LGPD", "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_usuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_usuarios);
        if (jtl_consultar_usuarios.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_usuarios.getColumnModel().getColumn(1).setMinWidth(170);
            jtl_consultar_usuarios.getColumnModel().getColumn(1).setMaxWidth(170);
            jtl_consultar_usuarios.getColumnModel().getColumn(2).setMinWidth(45);
            jtl_consultar_usuarios.getColumnModel().getColumn(2).setMaxWidth(45);
            jtl_consultar_usuarios.getColumnModel().getColumn(3).setMinWidth(0);
            jtl_consultar_usuarios.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        btnAtualizarUsuario.setText("Atualizar");
        btnAtualizarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarUsuarioActionPerformed(evt);
            }
        });

        btnFecharUsuario.setText("Fechar");
        btnFecharUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharUsuarioActionPerformed(evt);
            }
        });

        btnExcluirUsuario.setText("Excluir");
        btnExcluirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirUsuarioActionPerformed(evt);
            }
        });

        btnAlterarUsuario.setText("Alterar");
        btnAlterarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarUsuarioActionPerformed(evt);
            }
        });

        btnIncluirUsuario.setText("Incluir");
        btnIncluirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirUsuarioActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema_ponto/imagens/icon-empresa.png"))); // NOI18N
        jLabel1.setText("Usuários |");

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
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnFecharUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(btnExcluirUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAlterarUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnIncluirUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAtualizarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(btnIncluirUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluirUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFecharUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtualizarUsuario)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtl_consultar_usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_usuariosMouseClicked
        if (evt.getClickCount() == 2) {
            int rowIndex = jtl_consultar_usuarios.getSelectedRow();
            if (rowIndex != -1) {
                btnAlterarUsuarioActionPerformed(null);
            }
        }
    }//GEN-LAST:event_jtl_consultar_usuariosMouseClicked

    private void btnAtualizarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarUsuarioActionPerformed
        preencheTabelaUsuarios("");
    }//GEN-LAST:event_btnAtualizarUsuarioActionPerformed

    private void btnFecharUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharUsuarioActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharUsuarioActionPerformed

    private void btnExcluirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirUsuarioActionPerformed
        if(jtl_consultar_usuarios.getSelectedRow() != -1){
            int rowIndex = jtl_consultar_usuarios.getSelectedRow();
            int idUsuario = Integer.parseInt(String.valueOf(jtl_consultar_usuarios.getValueAt(rowIndex,3)));
            excluir(idUsuario);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um usuario para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
          }
    }//GEN-LAST:event_btnExcluirUsuarioActionPerformed

    private void btnAlterarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarUsuarioActionPerformed
        if(jtl_consultar_usuarios.getSelectedRow() != -1){
            int rowIndex = jtl_consultar_usuarios.getSelectedRow(); // Obtém o índice da linha selecionada;;

            int idUsuario = Integer.parseInt(String.valueOf(jtl_consultar_usuarios.getValueAt(rowIndex,3)));
            
            CadastroUsuarioVIEW cadastroUsuarioVIEW = new CadastroUsuarioVIEW();
            cadastroUsuarioVIEW.preencherCampos(idUsuario);

            this.getDesktopPane().add(cadastroUsuarioVIEW);
            cadastroUsuarioVIEW.setVisible(true);
            cadastroUsuarioVIEW.setPosicao();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um usuário para alterar.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btnAlterarUsuarioActionPerformed

    private void btnIncluirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirUsuarioActionPerformed
        CadastroUsuarioVIEW cadastroUsuarioVIEW = new CadastroUsuarioVIEW();
        this.getDesktopPane().add(cadastroUsuarioVIEW);
        cadastroUsuarioVIEW.setVisible(true);
        cadastroUsuarioVIEW.setPosicao();
    }//GEN-LAST:event_btnIncluirUsuarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarUsuario;
    private javax.swing.JButton btnAtualizarUsuario;
    private javax.swing.JButton btnExcluirUsuario;
    private javax.swing.JButton btnFecharUsuario;
    private javax.swing.JButton btnIncluirUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_usuarios;
    // End of variables declaration//GEN-END:variables
}
