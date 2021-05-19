/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;

/**
 *
 * @author os
 */
public interface PurchasesModelInterface {
    
    public void add(Customer customer, Product product, int sellerActiveID, int amount);
    
    public void deletePurchase(int customerID);
    
    public ResultSet sortByProductName();
    
    public ResultSet sortByCustomerName();
    
    public void registerObserver(PurchasesObserver o);
    
    public void removeObserver(PurchasesObserver o);
    
    public void notifyObserver();
    
    public ResultSet getPurchaseResultSet();
    
    public ResultSet getBoughtResultSet(int customerID);
    
    public ResultSet getCustomerBought();
    
    public int getCustomerID(int rowIndex);
    
    public String getSumCost(int customerID);
  
}
