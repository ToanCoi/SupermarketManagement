/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Exception.InvalidAccountException;
import java.sql.*;
import java.util.ArrayList;
import model.Exception.InvalidSellerInfoException;

/**
 *
 * @author os
 */
public interface SellersModelInterface {
    
    public void readData();
    
    public boolean checkPassword(String username, String password) throws InvalidAccountException;
    
    public ArrayList<Seller> getSellers();
    
    public void add(int id, String name, String phone, String password) throws InvalidSellerInfoException;
    
    public void delete(int id);
    
    public void update(int id, String name, String phone, String password) throws InvalidSellerInfoException;
    
    public int getNextID();
    
    public int getID(int rowIndex);
    
    public void registerObserver(SellersObserver o);
    
    public void notifyObservers();
    
    public ResultSet getResultSet();
}
