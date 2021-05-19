/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerInterface;
import javax.swing.JOptionPane;
import model.Exception.InvalidSellerInfoException;

/**
 *
 * @author os
 */
public class ChangeSellerDialog extends javax.swing.JDialog {
    
    ControllerInterface controller;

    /**
     * Creates new form AddsellerDialog
     * @param parent
     * @param controller
     * @param modal
     */
    public ChangeSellerDialog(java.awt.Frame parent, boolean modal, ControllerInterface controller) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        
        idLabel.setText("Mã nhân viên: " + controller.getSellerSelected().getId());
        nameField.setText(controller.getSellerSelected().getName());
        phoneField.setText(controller.getSellerSelected().getPhone());
        passwordField.setText(controller.getSellerSelected().getPassword());
        
        this.controller = controller;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        changeSellerButton = new javax.swing.JButton();
        nameField = new javax.swing.JTextField();
        phoneField = new javax.swing.JTextField();
        passwordField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        idLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        idLabel.setForeground(new java.awt.Color(255, 102, 0));
        idLabel.setText("Mã nhân viên:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setText("Họ và tên:");

        phoneLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        phoneLabel.setForeground(new java.awt.Color(255, 102, 0));
        phoneLabel.setText("SĐT");

        passwordLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(255, 102, 0));
        passwordLabel.setText("Password");

        changeSellerButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        changeSellerButton.setForeground(new java.awt.Color(255, 102, 0));
        changeSellerButton.setText("Cập nhật");
        changeSellerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSellerButtonActionPerformed(evt);
            }
        });

        nameField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        phoneField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        passwordField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(changeSellerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                    .addComponent(phoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nameField)
                                    .addComponent(phoneField)
                                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))))))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(idLabel)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneLabel)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(changeSellerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changeSellerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSellerButtonActionPerformed
        try {
            // TODO add your handling code here:
            controller.updateSellerInfo(nameField.getText(), phoneField.getText(), passwordField.getText());
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công");
            this.dispose();
        } catch (InvalidSellerInfoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_changeSellerButtonActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changeSellerButton;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel phoneLabel;
    // End of variables declaration//GEN-END:variables
}