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
public class SellersModel extends AbstractModel implements SellersModelInterface {
    
    private ArrayList<Seller> sellers;
    private ArrayList<SellersObserver> observers = new ArrayList<>();
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private String sql = "";
    
    public SellersModel() {
        try {
            connection = this.getConnection();
            statement = connection.createStatement();
            readData();
        } catch (SQLException ex) {
        }
    }

    @Override
    public void readData() {
        sellers = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("select * from seller");
            while(rs.next()) {
                int id = rs.getInt("sellerID");
                String name = rs.getString("sellerName");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                Seller seller = new Seller(id, name, phone, password);
                sellers.add(seller);
            }
        } 
        catch (SQLException | InvalidSellerInfoException ex) {
        } 
    }

    @Override
    public boolean checkPassword(String username, String password) throws InvalidAccountException {
        try {
            preparedStatement = connection.prepareStatement("select password from seller where sellerID = ?");
            preparedStatement.setInt(1, Integer.parseInt(username));
            ResultSet rs = preparedStatement.executeQuery();
            String pass = "";
            while(rs.next()) {
                pass = rs.getString("password");
            }
            if(pass.equals(password)) {
                return true;  
            }
        } 
        catch(NumberFormatException ex) {
            throw new InvalidAccountException("Tài khoản hoặc mật khẩu không hợp lệ");
        }
        catch (SQLException ex) {
        }
        return false;
    }
    
    @Override
    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    @Override
    public void registerObserver(SellersObserver o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> {
            observer.updateSellers();
        });
    }

    @Override
    public void add(int id, String name, String phone, String password) throws InvalidSellerInfoException {
        Seller seller = new Seller(id, name, phone, password);
        sellers.add(seller);
        
        try {
            sql = "insert into seller values(?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, password);
            
            preparedStatement.executeUpdate();
        }
        catch(SQLException ex) {
        }
        notifyObservers();
    }
    
    @Override
    public void delete(int id) {
        this.delete(statement, "seller", id);
        readData();
        notifyObservers();
    }
    
    @Override
    public void update(int id, String name, String phone, String password) throws InvalidSellerInfoException {
        Seller seller = new Seller(id, name, phone, password);
        try {
            sql = "update seller "
                    + "set sellerName = '" + name + "', phone = '" + phone + "', password = '" + password + "' "
                    + "where sellerID = " + id;
            statement.executeUpdate(sql);
        }
        catch(SQLException ex) {}
        readData();
        notifyObservers();
    }

    @Override
    public int getNextID() {
        return this.getNextID(preparedStatement, connection, "sellerID", "seller");
    }
    
    @Override
    public int getID(int rowIndex) {
        return this.getID(preparedStatement, connection, "seller", rowIndex);
    }

    @Override
    public ResultSet getResultSet() {
        return this.getResultSet(statement, "seller");
    }
    
}
