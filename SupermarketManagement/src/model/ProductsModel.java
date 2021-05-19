/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import model.Exception.InvalidProductInfoException;
import java.sql.*;

/**
 *
 * @author os
 */
public class ProductsModel extends AbstractModel implements ProductsModelInterface {
    
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet rs;
    private String sql = "";
    
    private ArrayList<Product> products;
    private ArrayList<ProductsObserver> observers = new ArrayList<>();
    
    public ProductsModel() {
        try {
            connection = this.getConnection();
            statement = connection.createStatement();
            readData();
        } catch (SQLException ex) {
        }
    }
    
    @Override
    public void readData() {
        products = new ArrayList<>();
        sql = "select * from product";
        try {
            rs = statement.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("productID");
                String name = rs.getString("productName");
                String type = rs.getString("type");
                double price = rs.getDouble("price");
                Product product = new Product(id, name, type, price);
                products.add(product);
            }
        } catch (SQLException | InvalidProductInfoException ex) {
        }
    }

    @Override
    public void add(int id, String name, String type, double price) throws InvalidProductInfoException {
        Product customer = new Product(id, name, type, price);
        products.add(customer);
        
        sql = "insert into product values (?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, type);
            preparedStatement.setDouble(4, price);
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
        }
        
        notifyObserver();
    }
    
    @Override
    public void delete(int id) {
        this.delete(statement, "product", id);
        notifyObserver();
    }

    @Override
    public void update(int id, String name, String type, double price) throws InvalidProductInfoException {
        Product customer = new Product(id, name, type, price);
        try {
            sql = "update product "
                    + "set productName = '" + name + "', type = '" + type + "', price = " + price + " "
                    + "where productID = " + id;
            statement.executeUpdate(sql);
        }
        catch(SQLException ex) {}
        readData();
        notifyObserver();
    }

    @Override
    public void registerObserver(ProductsObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ProductsObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        observers.forEach(o -> {
            o.updateProducts();
        });
    }
    
    @Override
    public int getID(int rowIndex) {
        return this.getID(preparedStatement, connection, "product", rowIndex);
    }

    @Override
    public int getNextID() {
        return this.getNextID(preparedStatement, connection, "productID", "product");
    }

    @Override
    public List getProducts() {
        return products;
    }

    @Override
    public ResultSet getResultSet() {
        return this.getResultSet(statement, "product");
    }
    
}
