/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerInterface;
import javax.swing.table.DefaultTableModel;
import model.Customer;
import model.PurchasesModelInterface;
import model.PurchasesObserver;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author os
 */
public class AddPurchaseDialog extends javax.swing.JDialog implements PurchasesObserver {
    
    ControllerInterface controller;
    PurchasesModelInterface purchasesModel;

    /**
     * Creates new form AddPurchaseDialog
     * @param parent
     * @param modal
     * @param controller
     * @param purchasesModel
     */
    public AddPurchaseDialog(java.awt.Frame parent, boolean modal, ControllerInterface controller,
    PurchasesModelInterface purchasesModel) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Thêm vào DS mua hàng");
        
        this.controller = controller;
        this.purchasesModel = purchasesModel;
        purchasesModel.registerObserver(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        boughtProductTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        idLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        chooseCustomerButton = new javax.swing.JButton();
        chooseProductButton = new javax.swing.JButton();
        addPurchaseButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        boughtProductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hàng", "Tên mặt hàng", "Giá bán", "Số lượng"
            }
        ));
        jScrollPane1.setViewportView(boughtProductTable);

        jPanel1.setBackground(new java.awt.Color(255, 102, 0));

        idLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        idLabel.setForeground(new java.awt.Color(255, 255, 255));
        idLabel.setText("Mã khách hàng:");

        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel.setText("Họ và tên");

        phoneLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        phoneLabel.setForeground(new java.awt.Color(255, 255, 255));
        phoneLabel.setText("SĐT:");

        chooseCustomerButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chooseCustomerButton.setText("Chọn khách hàng");
        chooseCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseCustomerButtonActionPerformed(evt);
            }
        });

        chooseProductButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chooseProductButton.setText("Thêm mặt hàng");
        chooseProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseProductButtonActionPerformed(evt);
            }
        });

        addPurchaseButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addPurchaseButton.setText("Hủy bỏ");
        addPurchaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPurchaseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addPurchaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseCustomerButton))
                .addContainerGap(117, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(phoneLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(idLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(idLabel)
                .addGap(18, 18, 18)
                .addComponent(nameLabel)
                .addGap(26, 26, 26)
                .addComponent(phoneLabel)
                .addGap(96, 96, 96)
                .addComponent(chooseCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(chooseProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(addPurchaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chooseCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseCustomerButtonActionPerformed
        // TODO add your handling code here:
        controller.openChooseCustomerDialog();
    }//GEN-LAST:event_chooseCustomerButtonActionPerformed

    private void chooseProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseProductButtonActionPerformed
        // TODO add your handling code here:
        controller.openChooseProductDialog();
    }//GEN-LAST:event_chooseProductButtonActionPerformed

    private void addPurchaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPurchaseButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_addPurchaseButtonActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPurchaseButton;
    private javax.swing.JTable boughtProductTable;
    private javax.swing.JButton chooseCustomerButton;
    private javax.swing.JButton chooseProductButton;
    private javax.swing.JLabel idLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel phoneLabel;
    // End of variables declaration//GEN-END:variables


    @Override
    public void updatePurchases() {
        updateView(controller.getCustomerSelected());
    }
    
    private void updateView(Customer customer) {
        idLabel.setText("Mã khách hàng:" + customer.getId());
        nameLabel.setText("Họ và tên: " + customer.getName());
        phoneLabel.setText("SĐT: " + customer.getPhone());
        boughtProductTable.setModel(DbUtils.resultSetToTableModel(purchasesModel.getBoughtResultSet(customer.getId())));
    }
}
