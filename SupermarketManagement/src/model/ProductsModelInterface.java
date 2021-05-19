/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.List;
import model.Exception.InvalidProductInfoException;
/**
 *
 * @author os
 */
public interface ProductsModelInterface {
    
    public void readData();
    
    public int getNextID();
    
    public int getID(int rowIndex);
    
    public void add(int id, String name, String type, double price) throws InvalidProductInfoException;
    
    public void delete(int id);
    
    public void update(int id, String name, String type, double price) throws InvalidProductInfoException;
    
    public List<Product> getProducts();
    
    public void registerObserver(ProductsObserver o);
    
    public void removeObserver(ProductsObserver o);
    
    public void notifyObserver();
    
    public ResultSet getResultSet();
}
