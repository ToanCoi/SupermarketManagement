/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.Exception.InvalidAccountException;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.table.TableModel;
import model.Customer;
import model.CustomersModelInterface;
import model.Exception.InvalidCustomerInfoException;
import model.Exception.InvalidProductInfoException;
import model.Exception.InvalidSellerInfoException;
import model.Product;
import model.ProductsModelInterface;
import model.PurchasesModelInterface;
import model.Seller;
import model.SellersModelInterface;
import net.proteanit.sql.DbUtils;
import view.AddCustomerDialog;
import view.AddProductDialog;
import view.AddPurchaseDialog;
import view.AddSellerDialog;
import view.ChangeCustomerDialog;
import view.ChangeProductDialog;
import view.ChangeSellerDialog;
import view.ChooseCustomerDialog;
import view.ChooseInvoiceDialog;
import view.ChooseProductDialog;
import view.Login;
import view.ManagerFrame;
import view.SellerFrame;


/**
 *
 * @author os
 */
public class Controller implements ControllerInterface {
    
    private SellerFrame sellerFrame;
    private Login loginFrame;
    private ManagerFrame managerFrame;
    private ProductsModelInterface productsModel;
    private CustomersModelInterface customersModel;
    private PurchasesModelInterface purchasesModel;
    private SellersModelInterface sellersModel;
    private int customerIndex, productIndex, sellerIndex;
    private AddPurchaseDialog addPurchaseDialog;
    private int sellerActive;

    public Controller(ProductsModelInterface productsModel, CustomersModelInterface customersModel, 
            PurchasesModelInterface purchasesModel, SellersModelInterface sellersModel) {
        this.productsModel = productsModel;
        this.customersModel = customersModel;
        this.purchasesModel = purchasesModel;
        this.sellersModel = sellersModel;
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
        
        loginFrame = new Login(this);
        loginFrame.setVisible(true);
        
//            managerFrame = new ManagerFrame(this, productsModel, sellersModel);
//            managerFrame.setVisible(true);
//            managerFrame.updateProducts();
//            managerFrame.updateSellers();
//            sellerFrame = new SellerFrame(this, customersModel, purchasesModel);
//                sellerFrame.updateCustomers();
//                sellerFrame.updatePurchases();
//                sellerFrame.setVisible(true);
        
    }
    
    @Override
    public void login(String role, String username, String password) throws InvalidAccountException {
        if(role.equals("Admin")) {
            if(!username.equals("admin") || !password.equals("admin"))
                throw new InvalidAccountException("Tài khoản hoặc mật khẩu không hợp lệ");
            
            loginFrame.dispose();
            managerFrame = new ManagerFrame(this, productsModel, sellersModel);
            managerFrame.setVisible(true);
            managerFrame.updateProducts();
            managerFrame.updateSellers();
        }
        else {
            if(sellersModel.checkPassword(username, password)) {
                
                sellerActive = Integer.parseInt(username);
                loginFrame.dispose();
                sellerFrame = new SellerFrame(this, customersModel, purchasesModel);
                sellerFrame.updateCustomers();
                sellerFrame.updatePurchases();
                sellerFrame.setVisible(true);
            }
            else {
                throw new InvalidAccountException("Tài khoản hoặc mật khẩu không hợp lệ");
            }
        }
    }
    
    @Override
    public void logout(JFrame frame) {
        frame.dispose();
        loginFrame = new Login(this);
        loginFrame.setVisible(true);
        
    }
    

    @Override
    public void openAddProductDialog() {
        new AddProductDialog(sellerFrame, true, this, productsModel).setVisible(true);
    }

    @Override
    public void openAddCustomerDialog() {
        new AddCustomerDialog(sellerFrame, true, this, customersModel).setVisible(true);
    }

    @Override
    public void openAddPurchaseDialog() {
        addPurchaseDialog = new AddPurchaseDialog(sellerFrame, true, this, purchasesModel);
        addPurchaseDialog.setVisible(true);
    }
    
        @Override
    public void openAddSellerDialog() {
        new AddSellerDialog(managerFrame, true, this, sellersModel).setVisible(true);
    }

    @Override
    public void addProduct(int id, String name, String type, double price) throws InvalidProductInfoException {
        productsModel.add(id, name, type, price);
    }

    @Override
    public void addCustomer(int id, String name, String address, String phone) throws InvalidCustomerInfoException {
        customersModel.add(id, name, address, phone);
    }
    
    @Override
    public void addSeller(int id, String name, String phone, String password) throws InvalidSellerInfoException {
        sellersModel.add(id, name, phone, password);
    }

    @Override
    public void addPurchase(Customer customer, Product product, int amount) {
        purchasesModel.add(customer, product, sellerActive, amount);
    }
    
    
    @Override
    public void updateSellerInfo(String name, String phone, String password) throws InvalidSellerInfoException {
        sellersModel.update(this.getSellerSelected().getId(), name, phone, password);
    }

    @Override
    public void updateProductInfo(String name, String type, double price)  throws InvalidProductInfoException{
        productsModel.update(this.getProductSelected().getId(), name, type, price);
    }

    @Override
    public void updateCustomerInfo(String name, String address, String phone) throws InvalidCustomerInfoException {
        customersModel.update(this.getCustomerSelected().getId(), name, address, phone);
    }
    
    @Override
    public void deleteSeller(int rowIndex) {
        sellersModel.delete(sellersModel.getID(rowIndex));
    }

    @Override
    public void deleteCustomer(int rowIndex) {
        customersModel.delete(customersModel.getID(rowIndex));
    }

    @Override
    public void deleteProduct(int rowIndex) {
        productsModel.delete(productsModel.getID(rowIndex));
    }

    @Override
    public void openChooseCustomerDialog() {
        new ChooseCustomerDialog(sellerFrame, true, this, customersModel).setVisible(true);
    }

    @Override
    public void openChooseProductDialog() {
        new ChooseProductDialog(sellerFrame, true, this, productsModel).setVisible(true);
    }

    @Override
    public TableModel sortPurchasesByCustomerName() {
        return DbUtils.resultSetToTableModel(purchasesModel.sortByCustomerName());
    }

    @Override
    public TableModel sortPurchaseByProductName() {
        return DbUtils.resultSetToTableModel(purchasesModel.sortByProductName());
    }

    @Override
    public void getChooseCustomerIndex(int customerIndex) {
        this.customerIndex = customerIndex;
        purchasesModel.notifyObserver();
    }

    @Override
    public void getChooseProductIndex(int productIndex) {
        this.productIndex = productIndex;
    }
    
    @Override
    public void getChooseSellerIndex(int sellerIndex) {
        this.sellerIndex = sellerIndex;
    }
    
    @Override
    public Customer getCustomerSelected() {
        return customersModel.getCustomers().get(customerIndex);
    }

    @Override
    public Product getProductSelected() {
        return  productsModel.getProducts().get(productIndex);
    }
    
    @Override
    public Seller getSellerSelected() {
        return sellersModel.getSellers().get(sellerIndex);
    }

    @Override
    public void updateInvoice(int rowIndex) {
        int customerID = purchasesModel.getCustomerID(rowIndex);
        sellerFrame.updateInvoice(customerID, customersModel.getCustomerName(customerID), customersModel.getCustomerPhone(customerID));
    }
    
    @Override
    public void resetInvoice(int customerID) {
        purchasesModel.deletePurchase(customerID);
        sellerFrame.resetInvoice();
    }

    @Override
    public void openChooseInvoiceDialog() {
        new ChooseInvoiceDialog(sellerFrame, true, this, purchasesModel).setVisible(true);
    }

    @Override
    public void openChangeSellerDialog() {
        new ChangeSellerDialog(managerFrame, true, this).setVisible(true);
    }
    
        @Override
    public void openChangeCustomerDialog() {
        new ChangeCustomerDialog(sellerFrame, true, this).setVisible(true);
    }

    @Override
    public void openChangeProductDialog() {
        new ChangeProductDialog(managerFrame, true, this).setVisible(true);
    }


}
