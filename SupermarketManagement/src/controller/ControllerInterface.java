/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JFrame;
import javax.swing.table.TableModel;
import model.Customer;
import model.Exception.InvalidCustomerInfoException;
import model.Exception.InvalidProductInfoException;
import model.Exception.InvalidSellerInfoException;
import model.Product;
import model.Seller;

/**
 *
 * @author os
 */
public interface ControllerInterface {
    
    public void login(String role, String username, String password) throws  Exception;
    
    public void logout(JFrame frame);
    
    public void openAddProductDialog();
    
    public void openAddCustomerDialog();
    
    public void openAddPurchaseDialog();
    
    public void openAddSellerDialog();
    
    public void addProduct(int id, String name, String type, double price) throws Exception;
    
    public void addSeller(int id, String name, String phone, String password) throws Exception;
    
    public void addCustomer(int id, String name, String address, String phone) throws Exception;
    
    public void addPurchase(Customer customer, Product product, int amount);
    
    public void deleteSeller(int sellerIndex);
    
    public void deleteCustomer(int customerIndex);
    
    public void deleteProduct(int productIndex);
    
    public TableModel sortPurchasesByCustomerName();
    
    public TableModel sortPurchaseByProductName();
    
    public void openChooseCustomerDialog();
    
    public void openChooseProductDialog();
    
    public Customer getCustomerSelected();
    
    public Product getProductSelected();
    
    public Seller getSellerSelected();
    
    public void getChooseCustomerIndex(int rowIndex);
    
    public void getChooseProductIndex(int rowIndex);
    
    public void getChooseSellerIndex(int rowIndex);
    
    public void updateInvoice(int rowIndex);
    
    public void resetInvoice(int customerID);
    
    public void openChooseInvoiceDialog();
    
    public void openChangeSellerDialog();
    
    public void openChangeCustomerDialog();
    
    public void openChangeProductDialog();
    
    public void updateSellerInfo(String name, String phone, String password) throws InvalidSellerInfoException;
    
    public void updateProductInfo(String name, String type, double price) throws InvalidProductInfoException;
    
    public void updateCustomerInfo(String name, String address, String phone) throws InvalidCustomerInfoException ;
    
}
