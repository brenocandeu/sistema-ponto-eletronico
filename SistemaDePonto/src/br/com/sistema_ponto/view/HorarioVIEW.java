package br.com.sistema_ponto.view;

import java.awt.Dimension;
import br.com.sistema_ponto.dto.HorarioDTO;
import br.com.sistema_ponto.ctr.HorarioCTR;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.time.LocalTime;

public class HorarioVIEW extends javax.swing.JInternalFrame {
    
    HorarioDTO horarioDTO = new HorarioDTO();
    HorarioCTR horarioCTR = new HorarioCTR();
    
    ResultSet rs;
    
    private boolean validarCampos() {
        if (numero_horario.getText().isEmpty() || descricao_horario.getText().isEmpty() || entrada_um.getText().isEmpty() ||
            saida_um.getText().isEmpty() || entrada_dois.getText().isEmpty() || saida_dois.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    
    int gravar_alterar;
    private void gravar() {
        try {
            horarioDTO.setNumeroHorario(Integer.parseInt(numero_horario.getText()));
            horarioDTO.setDescricaoHorario(descricao_horario.getText());
            horarioDTO.setEntradaUm(LocalTime.parse(entrada_um.getText()));
            horarioDTO.setSaidaUm(LocalTime.parse(saida_um.getText()));
            horarioDTO.setEntradaDois(LocalTime.parse(entrada_dois.getText()));
            horarioDTO.setSaidaDois(LocalTime.parse(saida_dois.getText()));
            
            JOptionPane.showMessageDialog(null, horarioCTR.inserirHorario(horarioDTO));
        } catch (Exception e) {
            System.out.println("Erro ao gravar horário: " + e.getMessage());
        }
    }
    
    public void preencherCampos(int numeroHorario) {
        try {
            horarioDTO.setNumeroHorario(numeroHorario);
            rs = horarioCTR.consultarHorario(horarioDTO, 2);
            if (rs.next()) {
                numero_horario.setText(String.valueOf(rs.getInt("numero_horario")));
                descricao_horario.setText(rs.getString("descricao_horario"));
                entrada_um.setText(rs.getString("entrada_um"));
                saida_um.setText(rs.getString("saida_um"));
                entrada_dois.setText(rs.getString("entrada_dois"));
                saida_dois.setText(rs.getString("saida_dois"));

                gravar_alterar = 2;
            }
        } catch (Exception e) {
            System.out.println("Erro ao preencher campos: " + e.getMessage());
        } finally {
            horarioCTR.CloseDB();
        }
    }
    
    private void alterar() {
        try {
            horarioDTO.setNumeroHorario(Integer.parseInt(numero_horario.getText()));
            horarioDTO.setDescricaoHorario(descricao_horario.getText());
            horarioDTO.setEntradaUm(LocalTime.parse(entrada_um.getText()));
            horarioDTO.setSaidaUm(LocalTime.parse(saida_um.getText()));
            horarioDTO.setEntradaDois(LocalTime.parse(entrada_dois.getText()));
            horarioDTO.setSaidaDois(LocalTime.parse(saida_dois.getText()));

            JOptionPane.showMessageDialog(null, horarioCTR.alterarHorario(horarioDTO));
        } catch (Exception e) {
            System.out.println("Erro ao alterar horário: " + e.getMessage());
        }
    }


    public HorarioVIEW() {
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

        jPanel1 = new javax.swing.JPanel();
        numero_horario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        descricao_horario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        saida_dois = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        saida_um = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        entrada_um = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        entrada_dois = new javax.swing.JFormattedTextField();
        btnConcluirHorario = new javax.swing.JButton();
        btnCancelarHorario = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(718, 557));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Número");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Descrição");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numero_horario, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(descricao_horario, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(350, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numero_horario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descricao_horario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema_ponto/imagens/Icon-Harario.png"))); // NOI18N
        jLabel1.setText("Horários |");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Incluir - Editar");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        try {
            saida_dois.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Entrada 1");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Saída 1");

        try {
            saida_um.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Entrada 2");

        try {
            entrada_um.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Saída 2");

        try {
            entrada_dois.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(entrada_um, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saida_um, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(entrada_dois, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saida_dois, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(entrada_um, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saida_um, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entrada_dois, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saida_dois, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(225, 225, 225))
        );

        btnConcluirHorario.setText("Concluir");
        btnConcluirHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConcluirHorarioActionPerformed(evt);
            }
        });

        btnCancelarHorario.setText("Cancelar");
        btnCancelarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarHorarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnConcluirHorario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelarHorario)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConcluirHorario)
                    .addComponent(btnCancelarHorario))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConcluirHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConcluirHorarioActionPerformed
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
    }//GEN-LAST:event_btnConcluirHorarioActionPerformed

    private void btnCancelarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarHorarioActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarHorarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarHorario;
    private javax.swing.JButton btnConcluirHorario;
    private javax.swing.JTextField descricao_horario;
    private javax.swing.JFormattedTextField entrada_dois;
    private javax.swing.JFormattedTextField entrada_um;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField numero_horario;
    private javax.swing.JFormattedTextField saida_dois;
    private javax.swing.JFormattedTextField saida_um;
    // End of variables declaration//GEN-END:variables
}
