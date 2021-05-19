/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.sql.*;
import model.Exception.InvalidCustomerInfoException;

/**
 *
 * @author os
 */
public interface CustomersModelInterface {
    
    public void readData();
    
    public int getNextID();
    
    public int getID(int rowIndex);
    
    public List<Customer> getCustomers();
    
    public void add(int id, String name, String address, String phone) throws InvalidCustomerInfoException;
    
    public void delete(int id);
    
    public void update(int id, String name, String address, String phone) throws InvalidCustomerInfoException;
 
    public void registerObserver(CustomersObserver o);
    
    public void removeObserver(CustomersObserver o);
    
    public void notifyObserver();
    
    public ResultSet getResultSet();
    
    public String getCustomerName(int customerID);
    
    public String getCustomerPhone(int customerID);
}
